package com.dashboard.projectboard.service;

import com.dashboard.projectboard.exception.BoardException;
import com.dashboard.projectboard.exception.ErrorCode;
import com.dashboard.projectboard.model.User;
import com.dashboard.projectboard.model.entity.UserEntity;
import com.dashboard.projectboard.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    //Todo: implement
    public User join(String userName, String password) {
        //회원가입하려는 userName으로 회원가입된 user 확인
        userEntityRepository.findByUserName(userName)
                .ifPresent(it ->{
                    throw new BoardException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName));
                });

        //회원가입 진행 = user 등록
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, password));

        return User.fromEntity(userEntity);

    }

    //Todo: implement
    public String login(String userName, String password) {
        //회원가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(() -> new BoardException(ErrorCode.DUPLICATED_USER_NAME, ""));

        // 비밀번호 체크
        if(!userEntity.getPassword().equals(password)) {
            throw new BoardException(ErrorCode.DUPLICATED_USER_NAME, "");
        }
        // token 생성

        return "";

    }
}
