package restjagdishapi.journal.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import restjagdishapi.journal.app.scheduler.UserScheduler;

@SpringBootTest
public class UserSchedulersTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testFetchUsersAndSendSAMail() {
        userScheduler.fetchUsersAndSendSAMail();
    }
}
