package com.dashboard.projectboard.controller.response;

import com.dashboard.projectboard.model.User;
import com.dashboard.projectboard.model.UserRole;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserJoinResponse {

    private Integer id;
    private String userName;
    private UserRole role;

    public static UserJoinResponse fromUser(User user){
        return new UserJoinResponse(
                user.getId(),
                user.getUserName(),
                user.getUserRole()

        );

    }
}
