package com.lms.LMS.repository;
import com.lms.LMS.model.Course;
import com.lms.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long>
{
}
