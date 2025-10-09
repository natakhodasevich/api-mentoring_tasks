package api;

public class UserRegisterResponse {

    private Integer id;
    private String token;

    public UserRegisterResponse(){

    }

    public UserRegisterResponse(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
