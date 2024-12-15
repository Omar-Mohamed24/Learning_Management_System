package com.lms.LMS.repository;

import com.lms.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User, Long>
{
}
