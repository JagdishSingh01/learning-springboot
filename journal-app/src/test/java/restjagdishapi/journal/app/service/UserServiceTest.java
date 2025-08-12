package restjagdishapi.journal.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import restjagdishapi.journal.app.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings ={
            "ram",
            "jp",
            "viv",
            "om"
    })
    public void testFindByUserName(String name){

        assertNotNull(userRepository.findByUserName(name),"failed for: " + name);
    }

//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,10,12",
//            "3,3,9"
//    })
//    public void test(int a  , int b, int expected){
//        assertEquals(expected, a+b);
//
//    }
}
