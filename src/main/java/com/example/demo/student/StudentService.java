package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    protected List<Student> getStudents(){

        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws Exception {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new Exception("Email Taken");
        }else {
            studentRepository.save(student);
        }

    }


    public void deleteStudent(long studentID) throws Exception {

        if (!(studentRepository.existsById(studentID))) {
            throw new Exception("Student does not exist with ID "+studentID);
        }else{
            studentRepository.deleteById(studentID);
        }


    }

    @Transactional
    public void updateStudent(long studentID, String name, String email) throws Exception {
        if (!(studentRepository.existsById(studentID))) {
            throw new Exception("Student does not exist with ID "+studentID);
        }else{
            Optional<Student> student = studentRepository.findById(studentID);
            if ((name != null) && (name.length()>0) && (!(Objects.equals(student.get().getName(),name)))){
                    student.get().setName(name);
            }
            Optional<Student> studentEmail = studentRepository.findStudentByEmail(email);
            if (studentEmail.isPresent()){
                throw new Exception("Email already taken");
            }
            else {
                if ((email != null) && (email.length() > 0) && (!(Objects.equals(student.get().getEmail(), email)))) {
                    student.get().setName(name);
                }
            }
        }
    }
}
