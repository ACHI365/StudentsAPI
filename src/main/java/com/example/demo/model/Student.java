package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "students")
public class Student {
    //generate table with id as auto_increment and primary
    private @Id @GeneratedValue long id;
    private String name;
    private LocalDate DoB;
    private String email;

    //make age virtualy generated column
    @Transient
    private int age;

    public Student(){

    }

    public Student(long id, String name, LocalDate doB, String email) {
        this.id = id;
        this.name = name;
        this.DoB = doB;
        this.email = email;
    }

    public Student(String name, LocalDate doB, String email) {
        this.name = name;
        this.DoB = doB;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", DoB=" + DoB +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate doB) {
        DoB = doB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //generate age
    public int getAge() {
        return Period.between(this.DoB, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}
