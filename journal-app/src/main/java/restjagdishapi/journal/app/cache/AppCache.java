package restjagdishapi.journal.app.cache;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import restjagdishapi.journal.app.entity.ConfigJournalAppEntity;
import restjagdishapi.journal.app.repository.ConfigJournalAppRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache = new HashMap<>();

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
       for(ConfigJournalAppEntity configJournalAppEntity : all){
           appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
       }
    }
}
