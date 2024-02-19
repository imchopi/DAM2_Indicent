package incident.Incident.adapter;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import incident.Incident.core.Exceptions.Users.UserDoesNotExistsException;
import incident.Incident.domain.Incident;
import incident.Incident.domain.IncidentRepository;
import incident.Incident.domain.User;
import incident.Incident.domain.UserIdDto;
import incident.Incident.service.IncidentService;
import incident.Incident.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

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

    @PostMapping(path = "/incidents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserIdDto createIncident(@RequestPart("incident") UserIdDto incident, @RequestParam("file") MultipartFile file) throws UserDoesNotExistsException {
        try (InputStream imageIS = file.getInputStream()) {
            byte[] imageB = imageIS.readAllBytes();
            String contentType = file.getContentType();
            incident.setFile(imageB);
            incident.setFileType(contentType);
            this.incidentSvc.create(incident);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incident;
    }

    @DeleteMapping("/incidents/{id}")
    public void deleteIncident(@PathVariable int id) {
        incidentSvc.delete(id);
    }

    @PutMapping("/incidents/{id}")
    public Incident updateIncident(@PathVariable int id, @RequestBody UserIdDto entity) {
        try {
            return incidentSvc.update(id, entity);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
