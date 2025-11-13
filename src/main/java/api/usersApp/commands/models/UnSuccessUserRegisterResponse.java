package api.usersApp.commands.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnSuccessUserRegisterResponse {

    @JsonProperty("error")
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
