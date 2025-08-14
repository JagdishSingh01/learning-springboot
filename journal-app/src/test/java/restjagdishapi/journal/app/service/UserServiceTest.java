package restjagdishapi.journal.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import restjagdishapi.journal.app.entity.User;
import restjagdishapi.journal.app.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings ={
            "ram",
            "jp",
            "viv",

    })
    public void testFindByUserName(String name){

        assertNotNull(userRepository.findByUserName(name),"failed for: " + name);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,6"
    })
    public void test(int a  , int b, int expected){
        assertEquals(expected, a+b);

    }


    @BeforeEach
    void setup() {
        // Clean up all existing users
        userRepository.deleteAll();

        // Insert the test users
        userRepository.save(new User("ram", "password123"));
        userRepository.save(new User("jp", "password456"));
    }

    @Test
    void testFindByUserName_ram() {
        User user = userRepository.findByUserName("ram");
        assertThat(user).isNotNull();
        assertThat(user.getUserName()).isEqualTo("ram");
    }

    @Test
    void testFindByUserName_jp() {
        User user = userRepository.findByUserName("jp");
        assertThat(user).isNotNull();
        assertThat(user.getUserName()).isEqualTo("jp");
    }
}
