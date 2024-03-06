package incident.Incident.service;

import java.util.Optional;

import incident.Incident.core.Exceptions.Users.UserDoesNotExistsException;
import incident.Incident.domain.Incident;
import incident.Incident.domain.UserIdDto;

public interface IncidentService {
    
    public Iterable<Incident> getAll();

    public Optional<Incident> getById(int id);

    public Incident create(UserIdDto entity) throws UserDoesNotExistsException;

    public Incident createIncident(Incident entity) throws UserDoesNotExistsException;

    public void delete(int id);
    
    public Incident update(int id, UserIdDto entity);

    public boolean userExists(int id);
    
}
