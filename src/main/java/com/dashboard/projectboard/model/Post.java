package com.dashboard.projectboard.model;

import com.dashboard.projectboard.model.entity.PostEntity;
import com.dashboard.projectboard.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Post {

    private Integer id;

    private String title;

    private String body;

    private User user;

    private Timestamp registeredAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static Post fromEntity(PostEntity postEntity){
        return new Post(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getBody(),
                User.fromEntity(postEntity.getUser()),
                postEntity.getRegisteredAt(),
                postEntity.getUpdatedAt(),
                postEntity.getDeletedAt()

        );

    }

}
