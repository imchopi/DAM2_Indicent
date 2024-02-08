package incident.Incident.adapter;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import incident.Incident.domain.Incident;
import incident.Incident.domain.IncidentRepository;
import incident.Incident.domain.IncidentRequest;
import incident.Incident.domain.User;
import incident.Incident.service.IncidentService;
import incident.Incident.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class IncidentController {

    private UserService userSvc;
    private IncidentService incidentSvc;

    public IncidentController(IncidentService incidentSvc, UserService userSvc) {
        this.userSvc = userSvc;
        this.incidentSvc = incidentSvc;
    }

    @GetMapping("/incidents")
    public Iterable<Incident> getAllIncident() {
        return incidentSvc.getAll();
    }

    @GetMapping("/incidents/{id}")
    public Optional<Incident> getIncidentById(@PathVariable int id) {
        return incidentSvc.getById(id);
    }

    @PostMapping("/incidents")
    public Incident createIncident(@RequestBody Incident incident) {
        try {
            Incident savedIncident = incidentSvc.create(incident);
            return savedIncident;
        } catch (Exception e) {
            // Manejar el caso en el que el usuario no exista
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }

    @PostMapping("/incidents_users")
    public User asignamiento(@RequestBody IncidentRequest request) {
        try {
            int userId = request.getUser().getId();
            User user = userSvc.getById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            int incidentId = request.getIncidents().getId();
            Incident incident = incidentSvc.getById(incidentId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incident not found"));
    
            if (incident == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incident not found");
            }
            incidentSvc.update(incidentId, incident);
            userSvc.update(userId, user);
            return user;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error assigning task to user", e);
        }
    }

    @DeleteMapping("/incidents/{id}")
    public void deleteIncident(@PathVariable int id) {
        incidentSvc.delete(id);
    }

    @PutMapping("/incidents/{id}")
    public Incident updateIncident(@PathVariable int id, @RequestBody Incident entity) {
        try {
            return incidentSvc.update(id, entity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
