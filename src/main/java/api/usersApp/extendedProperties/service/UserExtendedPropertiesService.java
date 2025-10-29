package api.usersApp.extendedProperties.service;

import api.usersApp.extendedProperties.UserExtendedPropertiesEndpoints;
import api.usersApp.extendedProperties.models.UserTime;
import io.restassured.response.Response;

import static core.utils.CommandSenderUtil.sendPutCommand;
import static core.utils.RequestSpecificationUtils.buildReqResApiRequestSpec;

public class UserExtendedPropertiesService {

    public Response updateUser(UserTime command) {
        return sendPutCommand(command, buildReqResApiRequestSpec(), UserExtendedPropertiesEndpoints.UPDATE_USER);
    }
}
