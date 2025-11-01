package api.usersApp.extendedProperties.models;

public class UserUpdateResponse extends UserUpdateRequest {


    private String updatedAt;



    public UserUpdateResponse() {
    }

    public UserUpdateResponse(String updatedAt) {
    super();
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
