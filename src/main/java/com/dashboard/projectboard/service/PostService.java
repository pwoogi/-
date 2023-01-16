package com.dashboard.projectboard.service;

import com.dashboard.projectboard.exception.BoardException;
import com.dashboard.projectboard.exception.ErrorCode;
import com.dashboard.projectboard.model.entity.PostEntity;
import com.dashboard.projectboard.model.entity.UserEntity;
import com.dashboard.projectboard.repository.PostEntityRepository;
import com.dashboard.projectboard.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;

    @Transactional
    public void create(String title, String body, String userName){

        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(()->
                new BoardException(ErrorCode.USER_NOT_FOUND, String.format("%s is not found", userName)));
        postEntityRepository.save(PostEntity.of(title, body, userEntity));

    }

    @Transactional
    public void modify(String title, String body, String userName, Integer postId){
        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(()->
                new BoardException(ErrorCode.USER_NOT_FOUND, String.format("%s is not found", userName)));

        //post exist

        //post permission

    }

}
