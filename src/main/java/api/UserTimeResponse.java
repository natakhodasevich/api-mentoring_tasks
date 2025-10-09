package api;

public class UserTimeResponse extends UserTime{


    private String updatedAt;



    public UserTimeResponse() {
    }

    public UserTimeResponse(String updatedAt) {
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
