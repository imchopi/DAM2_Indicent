package incident.Incident.service;

import java.util.Optional;

import incident.Incident.domain.Incident;
import incident.Incident.domain.User;

public interface UserService {
    
    public Iterable<User> getAll();

    public Optional<User> getById(int id);

    public User create(User entity);

    public void delete(int id);
    
    public void update(int id, User entity);

}
