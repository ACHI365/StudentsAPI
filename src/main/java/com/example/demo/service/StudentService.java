package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.studentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final studentRepository studentRepository;

    public StudentService(com.example.demo.repository.studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent(Student student){
        Optional<Student> sameEmail = studentRepository.
                findStudentByEmail(student.getEmail());

        if(sameEmail.isPresent()){
            throw new IllegalStateException("Email already taken");
        }

        return studentRepository.save(student);
    }

    public void deleteStudent(long id){
        if(!studentRepository.existsById(id))
            throw new IllegalStateException("Student with this id does not exists");

        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(long id, String name, String email){
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with this id does not exist"
        ));

        if(name != null && name.length() > 0
        && !student.getName().equals(name))
            student.setName(name);

        if(email != null && email.length() > 0
                && !student.getEmail().equals(email)){
            Optional<Student> sameEmail = studentRepository.
                    findStudentByEmail(student.getEmail());

            if(sameEmail.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
    }
}
