package incident.Incident.domain;

public class IncidentRequest {
    private User user;
    private Incident incidents;
    public Incident getIncidents() {
        return incidents;
    }
    public void setIncidents(Incident incidents) {
        this.incidents = incidents;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    
}
