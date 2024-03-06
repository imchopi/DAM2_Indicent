package incident.Incident.domain;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity(name = "incidents")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    @Column(nullable = true)
    private String status;
    @Column(name = "user", nullable = false)
    private int user;
    @Lob
    @Column(columnDefinition = "LONGBLOB", nullable = true)
    private byte[] file;
    @Column(nullable = true)
    String fileType;

    public Incident(String title, String description, String status, int user, byte[] file, String fileType) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
        this.file = file;
        this.fileType = fileType;
    }

    public Incident() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    
    public byte[] getImage() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getImageType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
