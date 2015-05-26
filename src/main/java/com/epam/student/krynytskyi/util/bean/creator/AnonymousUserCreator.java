package com.epam.student.krynytskyi.util.bean.creator;


import com.epam.student.krynytskyi.entity.Role;
import com.epam.student.krynytskyi.entity.User;

public class AnonymousUserCreator {
    public User create(){
        User user = new User();
        Role role = new Role();
        role.setRole("ANONYMOUS");
        user.setRole(role);
        return user;
    }
}
