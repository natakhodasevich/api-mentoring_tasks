package api.usersApp.extendedProperties.models;

public class UserUpdateRequest {

    private String name;
    private String job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
