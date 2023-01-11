package com.dashboard.projectboard.controller;

import com.dashboard.projectboard.controller.request.UserJoinRequest;
import com.dashboard.projectboard.controller.request.UserLoginRequest;
import com.dashboard.projectboard.controller.response.Response;
import com.dashboard.projectboard.controller.response.UserJoinResponse;
import com.dashboard.projectboard.controller.response.UserLoginResponse;
import com.dashboard.projectboard.model.User;
import com.dashboard.projectboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //Todo: implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request){
        User user = userService.join(request.getUserName(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(user));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request){
        String token = userService.login(request.getUserName(), request.getPassword());
        return Response.success(new UserLoginResponse(token));

    }

}
