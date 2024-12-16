package com.lms.LMS.service;
import com.lms.LMS.exception.NotFound;
import com.lms.LMS.model.Course;
import com.lms.LMS.model.User;
import com.lms.LMS.repository.CourseRepo;
import com.lms.LMS.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSer
{
    @Autowired
    private CourseRepo courseRepository;
    @Autowired
    private UserRepo userRepository;

    public Course createCourse(Long userId, Course course) {
        // Step 1: Fetch the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("User not found with ID: " + userId));

        // Step 3: Assign the user as the instructor for the course
        course.setInstructor(user);

        // Step 4: Save the course to the database
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id)
    {
        return courseRepository.findById(id).orElseThrow(() -> new NotFound("User not found"));
    }

    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }

    public void deleteCourse(Long courseId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        courseRepository.delete(course);
    }

    public Course updateCourse(Long courseId, Long userId, Course updatedCourse) {
        // Step 1: Fetch the course by ID
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFound("Course not found with ID: " + courseId));

        // Step 2: Fetch the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("User not found with ID: " + userId));

        // Step 3: Check if the user is the instructor or has admin privileges
        if (!existingCourse.getInstructor().equals(user) && !user.getRole().equals("ADMIN")) {
            throw new AccessDeniedException("User does not have the required role to update this course.");
        }

        // Step 4: Update course details
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());

        // Save and return the updated course
        return courseRepository.save(existingCourse);
    }
}
