package api.usersApp.entityReads.service;

import api.usersApp.entityReads.UserEntityReadsEndpoints;
import api.usersApp.entityReads.models.GetUserResponse;
import java.util.List;
import static core.utils.PayloadEntityReadsUtil.PayloadEntityReader.givenEntityArrayType;
import static core.utils.RequestSpecificationUtil.buildReqResApiRequestSpec;

public class UserEntityReadsService {

    /**
     * Retrieves all users from the API.
     * <p>
     * Sends a GET request and
     * maps the JSON array under the "data" field to a list of {@link GetUserResponse}.
     * </p>
     *
     * @return a list of {@link GetUserResponse} representing all users
     */
    public List<GetUserResponse> getAllUsers() {
        return givenEntityArrayType(GetUserResponse[].class)
                .withRequestSpecification(buildReqResApiRequestSpec())
                .getEntitiesFromUrl(UserEntityReadsEndpoints.GET_ALL_USERS, true, GetUserResponse.class, "data");
    }
}
