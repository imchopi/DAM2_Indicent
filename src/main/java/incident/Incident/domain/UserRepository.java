package incident.Incident.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    public Optional<User> findByEmail(String email);
    public boolean existsByEmail(String email);
    
}
