package incident.Incident.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import incident.Incident.core.Exceptions.Incidents.IncidentAlreadyExistsException;
import incident.Incident.domain.Incident;
import incident.Incident.domain.IncidentRepository;

@Service
public class IncidentServiceImplementation implements IncidentService {
    
    private IncidentRepository repository;

    public IncidentServiceImplementation(
        IncidentRepository repository
    ){
        this.repository = repository;
    }

    @Override
    public Iterable<Incident> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Incident> getById(int id) {
        return this.repository.findById(id);
    }

    @Override
    public Incident create(@RequestBody Incident entity) {

        if (repository.existsById(entity.getId())) {
            throw new IncidentAlreadyExistsException();
        }

        return repository.save(entity);

    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Incident update(int id, Incident entity) {
        Incident incident = repository.findById(id).orElseThrow();

        incident.setId(entity.getId());
        incident.setTitle(entity.getTitle());
        incident.setDescription(entity.getDescription());
        incident.setUser(entity.getUser());
        incident.setStatus(entity.getStatus());

        return repository.save(incident);
    }

}
