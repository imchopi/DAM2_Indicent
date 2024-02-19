package incident.Incident.domain;


public class UserIdDto {
    private int id;
    private String title;
    private String description;
    private String status;
    private int user;
    private byte[] file;
    String fileType;

    public UserIdDto(String title, String description, String status, int user, byte[] file, String fileType) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
        this.file = file;
        this.fileType = fileType;
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

    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
