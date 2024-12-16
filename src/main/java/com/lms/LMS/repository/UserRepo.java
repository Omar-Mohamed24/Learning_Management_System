package com.lms.LMS.repository;
import com.lms.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <User, Long>
{
    Optional<User> findByusername(String username);
}
