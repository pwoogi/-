package com.dashboard.projectboard.controller;

import com.dashboard.projectboard.controller.request.UserJoinRequest;
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
    public void join(@RequestBody UserJoinRequest request){

        userService.join(request.getUserName(), request.getPassword());

    }

}
