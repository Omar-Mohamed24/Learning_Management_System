package com.lms.LMS.service.IMPL;

import com.lms.LMS.dto.UserDto;
import com.lms.LMS.exception.NotFound;
import com.lms.LMS.mapper.UserMap;
import com.lms.LMS.model.User;
import com.lms.LMS.repository.UserRepo;
import com.lms.LMS.service.UserSer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSer_impl implements UserSer
{
    private UserRepo userrepo;

    public UserSer_impl(UserRepo userrepo)
    {
        this.userrepo = userrepo;
    }

    @Override
    public UserDto createUser(UserDto userdto)
    {
        User user = UserMap.mapToUser(userdto);
        User Saved = userrepo.save(user);

        return UserMap.mapToUserDto(Saved);
    }

    @Override
    public UserDto GetUser(long userId)
    {
        User Search = userrepo.findById(userId).orElseThrow(() -> new NotFound("user not found: " + userId));
        return UserMap.mapToUserDto(Search);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        List<User> users = userrepo.findAll();
        return users.stream().map((user) -> UserMap.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(long userId, UserDto updated)
    {
        User user = userrepo.findById(userId).orElseThrow(() -> new NotFound("user not found: " + userId));

        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        user.setEmail(updated.getEmail());
        user.setPassword(updated.getPassword());

        User saved = userrepo.save(user);
        return UserMap.mapToUserDto(saved);
    }

    @Override
    public void deleteUser(Long userId)
    {
        User user = userrepo.findById(userId).orElseThrow(() -> new NotFound("user not found: " + userId));
        userrepo.deleteById(userId);
    }
}
