package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Domain.User;

import java.util.Optional;

public interface UserService_ITF {

    Optional<User> login(String Email,String password);

    User save(User user);

    void updateUser(User user, Long userId);
}
