package com.example.demo.controller;

import com.example.demo.model.Faculty;
import com.example.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    // A simple in-memory list acting as our "database" for learning purposes
    private List<Faculty> facultyList = new ArrayList<>();

    public FacultyController() {
        // Create sample students for Computer Science
        List<Student> csStudents = Arrays.asList(
            new Student(1L, "Alice Smith", "alice@example.com"),
            new Student(2L, "Bob Jones", "bob@example.com")
        );

        // Create sample students for Engineering
        List<Student> engStudents = Arrays.asList(
            new Student(3L, "Charlie Brown", "charlie@example.com"),
            new Student(4L, "Diana Prince", "diana@example.com")
        );

        // Populate our collection
        facultyList.add(new Faculty(1L, "Computer Science", csStudents));
        facultyList.add(new Faculty(2L, "Engineering", engStudents));
    }

    // 1. Show all faculties
    @GetMapping
    public String listFaculties(Model model) {
        model.addAttribute("faculties", facultyList);
        return "faculty-list";
    }

    // 2. Show students inside a specific faculty using PathVariable ID
    @GetMapping("/{id}/students")
    public String viewFacultyStudents(@PathVariable Long id, Model model) {
        Faculty selectedFaculty = null;

        // Loop through our list to find the matching faculty
        for (Faculty faculty : facultyList) {
            if (faculty.getId().equals(id)) {
                selectedFaculty = faculty;
                break;
            }
        }

        if (selectedFaculty != null) {
            model.addAttribute("faculty", selectedFaculty);
            model.addAttribute("students", selectedFaculty.getStudents());
        }
        
        return "faculty-students";
    }
}
