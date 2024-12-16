package com.lms.LMS.controller;

import com.lms.LMS.exception.NotFound;
import com.lms.LMS.model.Course;
import com.lms.LMS.service.CourseSer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseCont
{
    private final CourseSer courseSer;

    public CourseCont(CourseSer courseSer)
    {
        this.courseSer = courseSer;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Course> createCourse(@PathVariable Long userId, @RequestBody Course course) {
        Course saved = courseSer.createCourse(userId, course);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN', 'INSTRUCTOR')")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseSer.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseSer.getCourseById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseSer.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }

    @PutMapping("/update/{courseId}/{userId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @PathVariable Long userId, @RequestBody Course updatedCourse)
    {
        try
        {
            Course course = courseSer.updateCourse(courseId, userId, updatedCourse);
            return ResponseEntity.ok(course);
        }
        catch (AccessDeniedException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        catch (NotFound e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
