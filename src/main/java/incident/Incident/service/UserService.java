package incident.Incident.service;

import java.util.Optional;

import incident.Incident.domain.Incident;
import incident.Incident.domain.User;

public interface UserService {
    
    public Iterable<User> getAll();

    public Optional<User> getById(int user);

    public User create(User entity);

    public void delete(int id);
    
    public User update(int id, User entity);

}
