package api.users.commands.service;

import api.users.commands.UserCommandsEndpoints;
import api.users.commands.models.UserRegisterRequest;
import io.restassured.response.Response;

import static core.utils.CommandSenderUtil.sendCommand;
import static core.utils.RequestSpecificationUtils.buildReqResApiRequestSpec;

public class UserCommandsService {

    public Response registerNewUser(UserRegisterRequest command) {
        return sendCommand(command, buildReqResApiRequestSpec(), UserCommandsEndpoints.REGISTER_USER);
    }
}
