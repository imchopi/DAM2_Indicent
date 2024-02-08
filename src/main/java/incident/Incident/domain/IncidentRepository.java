package incident.Incident.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IncidentRepository extends CrudRepository<Incident, Integer> {

    public Optional<Incident> findById(int id);
    public boolean existsById(int id);

}
