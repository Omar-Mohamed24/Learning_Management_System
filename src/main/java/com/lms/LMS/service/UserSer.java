package com.lms.LMS.service;
import java.util.List;
import com.lms.LMS.dto.UserDto;

public interface UserSer
{
    UserDto createUser(UserDto userdto);
    UserDto GetUser(long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(long userId, UserDto updated);
    void deleteUser(Long userId);
}
