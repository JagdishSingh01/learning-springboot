package restjagdishapi.journal.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restjagdishapi.journal.app.entity.User;
import restjagdishapi.journal.app.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/newuser")
    public void createUsers(@RequestBody User user){
        userService.saveEntry(user);
    }
}
