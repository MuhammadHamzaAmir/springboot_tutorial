package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")

public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }




    @GetMapping
    protected List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    protected void registerNewStudent(@RequestBody Student student) throws Exception {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentID}")
    protected void deleteStudent(@PathVariable("studentID") long studentID) throws Exception {
        studentService.deleteStudent(studentID);
    }

    @PutMapping(path = "{studentID}")
    protected void updateStudent(@PathVariable("studentID") long studentID,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String email) throws Exception {
        studentService.updateStudent(studentID,name,email);

    }

}
