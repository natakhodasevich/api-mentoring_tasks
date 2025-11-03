package api.usersApp.extendedProperties.service;

import api.usersApp.extendedProperties.UserExtendedPropertiesEndpoints;
import api.usersApp.extendedProperties.models.UserUpdateRequest;
import io.restassured.response.Response;

import static core.utils.ExtendedPropertiesUtil.sendDeleteRequest;
import static core.utils.ExtendedPropertiesUtil.sendPutRequest;
import static core.utils.RequestSpecificationUtil.buildReqResApiRequestSpec;

public class UserExtendedPropertiesService {

    public Response updateUser(UserUpdateRequest command) {
        return sendPutRequest(command, buildReqResApiRequestSpec(), UserExtendedPropertiesEndpoints.UPDATE_USER);
    }

    public Response deleteUser() {
        return sendDeleteRequest(buildReqResApiRequestSpec(), UserExtendedPropertiesEndpoints.DELETE_USER);
    }
}
