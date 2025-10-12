package api.users.commands.models;

public class UnSuccessUserRegisterResponse {

    String error;

    public UnSuccessUserRegisterResponse() {
    }

    public UnSuccessUserRegisterResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
