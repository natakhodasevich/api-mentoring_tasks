package api.usersApp.entityReads.service;

import api.usersApp.entityReads.UserEntityReadsEndpoints;
import api.usersApp.entityReads.models.GetUserResponse;
import java.util.List;
import static core.utils.PayloadEntityReadsUtil.PayloadEntityReader.givenEntityArrayType;
import static core.utils.RequestSpecificationUtils.buildReqResApiRequestSpec;

public class UserEntityReadsService {

    public List<GetUserResponse> getAllUsers() {
        return givenEntityArrayType(GetUserResponse[].class)
                .withRequestSpecification(buildReqResApiRequestSpec())
                .getEntitiesFromUrl(UserEntityReadsEndpoints.GET_ALL_USERS, true, GetUserResponse.class, "data");
    }
}
