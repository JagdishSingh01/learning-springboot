package restjagdishapi.journal.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restjagdishapi.journal.app.api.response.WeatherResponse;
import restjagdishapi.journal.app.cache.AppCache;
import restjagdishapi.journal.app.constants.Placeholders;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather:" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> respose = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
            WeatherResponse body = respose.getBody();
            if(body != null){
                redisService.set("weather:" + city, body, 600l); // Cache for 10 minutes
            }
            return body;
        }

/*---------for post calls-------------------------*/
//        String requestBody = "{\n" +
//                "\"userName\": \"jacky\",\n" +
//                "\"password\":\"jacky\",\n"+
//                "}";
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key", "value");
//        User user = User.builder().username("viv").password("viv").build();
//        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
/*------------------------------------------------*/


    }
}
