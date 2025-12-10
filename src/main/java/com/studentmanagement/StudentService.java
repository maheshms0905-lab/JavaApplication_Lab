package com.studentmanagement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private int nextId = 1;
    public Student addStudent(String name, int age) {
        Student s = new Student(nextId++, name, age);
        students.add(s);
        return s;
    }
    public boolean updateStudent(int id, String newName, int newAge) {
        Optional<Student> opt = students.stream().filter(s -> s.getId() == id).findFirst();
        if (opt.isEmpty()) return false;
        Student s = opt.get();
        s.setName(newName);
        s.setAge(newAge);
        return true;
    }
    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getId() == id);
    }
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    public Optional<Student> findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst();
    }
}