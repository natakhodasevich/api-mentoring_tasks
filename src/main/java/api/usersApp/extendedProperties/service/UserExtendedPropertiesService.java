package api.usersApp.extendedProperties.service;

import api.usersApp.extendedProperties.UserExtendedPropertiesEndpoints;
import api.usersApp.extendedProperties.models.UserTime;
import io.restassured.response.Response;

import static core.utils.ExtendedPropertiesUtils.sendDeleteRequest;
import static core.utils.ExtendedPropertiesUtils.sendPutRequest;
import static core.utils.RequestSpecificationUtils.buildReqResApiRequestSpec;

public class UserExtendedPropertiesService {

    public Response updateUser(UserTime command) {
        return sendPutRequest(command, buildReqResApiRequestSpec(), UserExtendedPropertiesEndpoints.UPDATE_USER);
    }

    public Response deleteUser() {
        return sendDeleteRequest(buildReqResApiRequestSpec(), UserExtendedPropertiesEndpoints.DELETE_USER);
    }
}
