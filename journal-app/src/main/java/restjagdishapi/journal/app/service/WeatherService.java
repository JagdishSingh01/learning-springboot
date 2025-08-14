package restjagdishapi.journal.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import restjagdishapi.journal.app.api.response.WeatherResponse;

@Component
public class WeatherService {
    private static final String apiKey = "6b6c446cd7c686b1dc2fb9904a0b71e2";

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);

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


        ResponseEntity<WeatherResponse> respose = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = respose.getBody();
        return body;
    }
}
