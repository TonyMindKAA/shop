package com.epam.student.krynytskyi.service;

import com.epam.student.krynytskyi.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    boolean authenticate(String email, String password, HttpServletRequest req) throws Exception;

    boolean isExistUser(String email) throws Exception;

    boolean insertUser(User newUser) throws Exception;

    User getUserByEmail(String email) throws Exception;

    void removeUser(String email) throws Exception;
}
