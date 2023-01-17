package com.dashboard.projectboard.fixture;

import com.dashboard.projectboard.model.entity.PostEntity;
import com.dashboard.projectboard.model.entity.UserEntity;

public class PostEntityFixture {

    public static PostEntity get(String userName, Integer postId, Integer userId){
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUserName(userName);

        PostEntity result = new PostEntity();
        result.setUser(user);
        result.setId(postId);

        return result;

    }
}
