package incident.Incident.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import incident.Incident.core.Exceptions.Incidents.IncidentAlreadyExistsException;
import incident.Incident.core.Exceptions.Users.UserAlreadyExistsException;
import incident.Incident.core.Exceptions.Users.UserDoesNotExistsException;
import incident.Incident.domain.Incident;
import incident.Incident.domain.IncidentRepository;
import incident.Incident.domain.User;
import incident.Incident.domain.UserIdDto;
import incident.Incident.domain.UserRepository;

@Service
public class IncidentServiceImplementation implements IncidentService {

    private final IncidentRepository repository;
    private final UserRepository userRepo;

    public IncidentServiceImplementation(
            IncidentRepository repository,
            UserRepository userRepo) {
        this.userRepo = userRepo;
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
    public Incident create(@RequestBody UserIdDto entity) throws UserDoesNotExistsException {

        int userId = entity.getUser();
        User user = this.userRepo.findById(userId).orElseThrow(() -> new UserDoesNotExistsException());

        Incident incident = new Incident();

        BeanUtils.copyProperties(entity, incident, "userId");
        incident.setUser(user);

        return repository.save(incident);

    }


    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Incident update(int id, UserIdDto entity) {
        Incident incident = repository.findById(id).orElseThrow();

        incident.setId(id);
        incident.setTitle(entity.getTitle());
        incident.setDescription(entity.getDescription());
        incident.setStatus(entity.getStatus());

        return repository.save(incident);
    }

    @Override
    public boolean userExists(int id) {
        return this.userRepo.existsById(id);
    }


}
