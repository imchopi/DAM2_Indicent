package incident.Incident.adapter;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import incident.Incident.core.Exceptions.Users.UserDoesNotExistsException;
import incident.Incident.domain.Incident;
import incident.Incident.domain.User;
import incident.Incident.domain.UserLogin;
import incident.Incident.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class UserController {

    public PasswordEncoder encoder;
    private UserService service;

    public UserController(UserService service, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.service = service;
    }
        

    @GetMapping("/users")
    public Iterable<User> getAllUser() {
        return service.getAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User entity) {
        User user;
        try {
            user = service.create(entity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The email already exist");
        }

        return user;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserLogin userLogin) {
        User user = new User();
        try {
            user = service.getUserByEmail(userLogin.getEmail());
            return encoder.matches(userLogin.getPassword(), user.getPassword());
        } catch (UserDoesNotExistsException e) {
            e.printStackTrace();
            return false;
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User entity) {
        try {
            service.update(id, entity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
