package incident.Incident.service;

import java.util.Optional;

import incident.Incident.domain.Incident;

public interface IncidentService {
    
    public Iterable<Incident> getAll();

    public Optional<Incident> getById(int id);

    public Incident create(Incident entity);

    public void delete(int id);
    
    public Incident update(int id, Incident entity);
    
}
