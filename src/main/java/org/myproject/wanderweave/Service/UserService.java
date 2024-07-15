package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Domain.User;
import org.myproject.wanderweave.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserService_ITF {

    @Autowired
    private UserRepository userRepository;

    //로그인
    @Override
    @Transactional
    public Optional<User> login(String email, String password) {
        Optional<User> loginUser = Optional.ofNullable(userRepository.findByUserEmail(email));
        if (loginUser.isPresent()) {
            User user = loginUser.get();
            if (password.equals(user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    //회원가입
    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    //유저정보수정
    @Override
    @Transactional
    public void updateUser(User user, Long userId) {
        //기존정보
        User existingUser = userRepository.findById(userId).orElse(null);

        //업데이트정보
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setUserEmail(user.getUserEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setBirthday(user.getBirthday());
            existingUser.setUserPhone(user.getUserPhone());
            //업데이트 정보 저장
            userRepository.save(existingUser);
        }
    }
}
