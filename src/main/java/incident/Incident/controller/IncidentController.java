package incident.Incident.controller;

import org.springframework.web.bind.annotation.RestController;

import incident.Incident.domain.IncidentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(path = "/api")
public class IncidentController {
    @Autowired
    private IncidentRepository incidentRepository;

    
    
    
}
