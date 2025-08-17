package restjagdishapi.journal.app.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import restjagdishapi.journal.app.cache.AppCache;
import restjagdishapi.journal.app.entity.JournalEntry;
import restjagdishapi.journal.app.entity.User;
import restjagdishapi.journal.app.enums.Sentiment;
import restjagdishapi.journal.app.repository.UserRepositoryImpl;
import restjagdishapi.journal.app.service.EmailService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 12 * * SUN")
    public void fetchUsersAndSendSAMail(){
        List<User> users = userRepository.getUserForSA();
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                List<JournalEntry> journalEntries = user.getJournalEntries();
                List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
                Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
                for (Sentiment sentiment : sentiments) {
                    if (sentiment != null) {
                        sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                    }
                }
                Sentiment mostFrequentSentiment = null;
                int maxCount = 0;
                for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                    if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        mostFrequentSentiment = entry.getKey();
                    }
                }
                if (mostFrequentSentiment != null) {
                    emailService.sendEmail(user.getEmail(),
                            "Sentiment Analysis Report",
                            "Hello, your most frequent sentiment for the past week is: " + mostFrequentSentiment.toString());
                } else {
                    emailService.sendEmail(user.getEmail(),
                            "Sentiment Analysis Report",
                            "Hello, we could not determine your sentiment for the past week.");
                }
            }
        } else {
            System.out.println("No users found for sentiment analysis.");
        }
    }

    @Scheduled(cron = "0 5 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}
