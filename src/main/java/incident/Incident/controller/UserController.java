package incident.Incident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import incident.Incident.domain.User;
import incident.Incident.domain.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(
        @RequestParam String nickname,
        @RequestParam String name,
        @RequestParam String surname1,
        @RequestParam String surname2,
        @RequestParam String email,
        @RequestParam String password,
        @RequestParam String rol
    ) {
        User u = new User();
        u.setNickname(nickname);
        u.setName(name);
        u.setSurname1(surname1);
        u.setSurname2(surname2);
        u.setEmail(email);
        u.setPassword(password);
        u.setRol(rol);
        userRepository.save(u);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

}
