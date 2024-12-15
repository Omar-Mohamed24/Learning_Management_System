package com.lms.LMS.mapper;

import com.lms.LMS.dto.UserDto;
import com.lms.LMS.model.User;

public class UserMap
{
    public static UserDto mapToUserDto(User user)
    {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public static User mapToUser(UserDto user)
    {
        return new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}