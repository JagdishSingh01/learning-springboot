package restjagdishapi.journal.app.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import restjagdishapi.journal.app.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import restjagdishapi.journal.app.repository.UserRepository;
import java.util.List;
import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        User mockUser = new User("ram", "ram#fsd");
        mockUser.setRoles(List.of("USER"));

        when(userRepository.findByUserName(anyString()))
                .thenReturn(mockUser);
        UserDetails user = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
}
