package com.dashboard.projectboard.service;

import com.dashboard.projectboard.exception.BoardException;
import com.dashboard.projectboard.exception.ErrorCode;
import com.dashboard.projectboard.model.User;
import com.dashboard.projectboard.model.entity.UserEntity;
import com.dashboard.projectboard.repository.UserEntityRepository;
import com.dashboard.projectboard.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    public User loadUserByUserName(String userName){
        return userEntityRepository.findByUserName(userName).map(User::fromEntity).orElseThrow(() ->
                new BoardException(ErrorCode.USER_NOT_FOUND, String.format("%s is not found", userName)));

    }

    @Transactional
    public User join(String userName, String password) {
        //회원가입하려는 userName으로 회원가입된 user 확인
        userEntityRepository.findByUserName(userName)
                .ifPresent(it -> {
                    throw new BoardException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", userName));
                });

        //회원가입 진행 = user 등록
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, encoder.encode(password)));
        return User.fromEntity(userEntity);

    }

    //Todo: implement
    public String login(String userName, String password) {
        //회원가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(userName).orElseThrow(
                () -> new BoardException(ErrorCode.USER_NOT_FOUND, String.format("%s is not found", userName)));

        // 비밀번호 체크
        if (!encoder.matches(password, userEntity.getPassword())) {
            throw new BoardException(ErrorCode.INVALID_PASSWORD);
        }
        // token 생성
        return JwtTokenUtils.generateToken(userName, secretKey, expiredTimeMs);

    }
}
