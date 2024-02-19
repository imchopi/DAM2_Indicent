package incident.Incident.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import incident.Incident.core.Exceptions.Users.UserAlreadyExistsException;
import incident.Incident.domain.Incident;
import incident.Incident.domain.User;
import incident.Incident.domain.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    private PasswordEncoder encoder;
    private UserRepository repository;

    public UserServiceImplementation(
            UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public Iterable<User> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<User> getById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public User create(@RequestBody User entity) {

        if (repository.existsByEmail(entity.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        String encodedPassword = this.encoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
        return repository.save(entity);

    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public void update(int id, User entity) {
        User user = repository.findById(id).orElseThrow();

        user.setId(entity.getId());
        user.setNickname(entity.getNickname());
        user.setName(entity.getName());
        user.setSurname1(entity.getSurname1());
        user.setSurname2(entity.getSurname2());
        user.setRol(entity.getRol());
        repository.save(user);
    }

}
