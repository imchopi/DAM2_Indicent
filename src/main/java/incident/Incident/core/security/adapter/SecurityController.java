package incident.Incident.core.security.adapter;

import org.springframework.web.bind.annotation.RestController;

import incident.Incident.domain.User;
import incident.Incident.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class SecurityController {

    private final UserService service;
    
    private PasswordEncoder encoder;

    public SecurityController(UserService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }
    
    @PostMapping("/register")
    public User postMethodName(@RequestBody User entity) {
        String encodedPassword = this.encoder.encode(entity.getPassword());
        System.out.println("La contraseña codificada es " + encodedPassword);
        entity.setPassword(encodedPassword);
        return service.create(entity);
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken token) {
        return token;
    }

}
