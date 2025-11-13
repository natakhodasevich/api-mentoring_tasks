package api.usersApp.entityReads;

import api.usersApp.entityReads.models.GetUserResponse;
import api.usersApp.entityReads.service.UserEntityReadsService;

import java.util.List;

public class UserEntityReads {
    UserEntityReadsService userEntityReadsService = new UserEntityReadsService();

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the {@link GetUserResponse} matching the ID, or null if not found
     */
    public GetUserResponse getUserById(Integer id) {
        List<GetUserResponse> allUsers = userEntityReadsService.getAllUsers();
        return allUsers.stream().filter(user -> id.equals(user.getId())).findFirst().orElse(null);
    }

    /**
     * Retrieves a user by their first name.
     *
     * @param name the first name of the user
     * @return the {@link GetUserResponse} matching the first name, or null if not found
     */
    public GetUserResponse getUserByName(String name) {
        List<GetUserResponse> allUsers = userEntityReadsService.getAllUsers();
        return allUsers.stream().filter(user -> name.equals(user.getFirst_name())).findFirst().orElse(null);
    }
}
