package api.usersApp.entityReads;

import api.usersApp.entityReads.models.GetUserResponse;
import api.usersApp.entityReads.service.UserEntityReadsService;

import java.util.List;

public class UserEntityReads {
    UserEntityReadsService userEntityReadsService = new UserEntityReadsService();

    public GetUserResponse getUserById(Integer id) {
        List<GetUserResponse> allUsers = userEntityReadsService.getAllUsers();
        return allUsers.stream().filter(user -> id.equals(user.getId())).findFirst().orElse(null);
    }

    public GetUserResponse getUserByName(String name) {
        List<GetUserResponse> allUsers = userEntityReadsService.getAllUsers();
        return allUsers.stream().filter(user -> name.equals(user.getFirst_name())).findFirst().orElse(null);
    }
}
