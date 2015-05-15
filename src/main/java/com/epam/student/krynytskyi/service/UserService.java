package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.entity.User;

public interface UserService {
    boolean authenticate(String email, String password) throws Exception;

    boolean isExistUser(String email) throws Exception;

    boolean insertUser(User newUser) throws Exception;

    User getUserByEmail(String email) throws Exception;

    void removeUser(String email) throws Exception;
}
