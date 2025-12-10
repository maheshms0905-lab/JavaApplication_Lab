package com.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService service;

    @BeforeEach
    public void setUp() {
        service = new StudentService();
    }

    @Test
    public void testAddStudent() {
        Student student = service.addStudent("John Doe", 25);

        assertNotNull(student);
        assertEquals("John Doe", student.getName());
        assertEquals(25, student.getAge());
        assertEquals(1, student.getId());
    }

    @Test
    public void testAddMultipleStudents() {
        Student s1 = service.addStudent("Alice", 22);
        Student s2 = service.addStudent("Bob", 23);
        Student s3 = service.addStudent("Charlie", 24);

        assertEquals(1, s1.getId());
        assertEquals(2, s2.getId());
        assertEquals(3, s3.getId());

        assertEquals("Alice", s1.getName());
        assertEquals("Bob", s2.getName());
        assertEquals("Charlie", s3.getName());
    }

    @Test
    public void testAddStudentWithDifferentAges() {
        Student young = service.addStudent("Young Person", 18);
        Student old = service.addStudent("Old Person", 65);

        assertEquals(18, young.getAge());
        assertEquals(65, old.getAge());
    }

    @Test
    public void testGetAllStudentsEmpty() {
        List<Student> students = service.getAllStudents();

        assertNotNull(students);
        assertTrue(students.isEmpty());
        assertEquals(0, students.size());
    }

    @Test
    public void testGetAllStudentsWithData() {
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 23);
        service.addStudent("Charlie", 24);

        List<Student> students = service.getAllStudents();

        assertNotNull(students);
        assertEquals(3, students.size());
    }

    @Test
    public void testGetAllStudentsReturnsIndependentList() {
        service.addStudent("Alice", 22);
        List<Student> students1 = service.getAllStudents();
        service.addStudent("Bob", 23);
        List<Student> students2 = service.getAllStudents();

        assertEquals(1, students1.size());
        assertEquals(2, students2.size());
    }

    @Test
    public void testUpdateStudent() {
        service.addStudent("Original Name", 25);

        boolean updated = service.updateStudent(1, "New Name", 30);

        assertTrue(updated);
        Student s = service.findById(1).get();
        assertEquals("New Name", s.getName());
        assertEquals(30, s.getAge());
    }

    @Test
    public void testUpdateStudentNotFound() {
        service.addStudent("Alice", 22);

        boolean updated = service.updateStudent(999, "New Name", 30);

        assertFalse(updated);
    }

    @Test
    public void testUpdateStudentMultipleTimes() {
        service.addStudent("Alice", 22);

        service.updateStudent(1, "Alice Updated", 23);
        Student s = service.findById(1).get();
        assertEquals("Alice Updated", s.getName());
        assertEquals(23, s.getAge());

        service.updateStudent(1, "Alice Final", 25);
        s = service.findById(1).get();
        assertEquals("Alice Final", s.getName());
        assertEquals(25, s.getAge());
    }

    @Test
    public void testDeleteStudent() {
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 23);

        boolean deleted = service.deleteStudent(1);

        assertTrue(deleted);
        assertEquals(1, service.getAllStudents().size());
        assertFalse(service.findById(1).isPresent());
    }

    @Test
    public void testDeleteStudentNotFound() {
        service.addStudent("Alice", 22);

        boolean deleted = service.deleteStudent(999);

        assertFalse(deleted);
        assertEquals(1, service.getAllStudents().size());
    }

    @Test
    public void testDeleteAllStudents() {
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 23);
        service.addStudent("Charlie", 24);

        service.deleteStudent(1);
        service.deleteStudent(2);
        service.deleteStudent(3);

        List<Student> students = service.getAllStudents();
        assertTrue(students.isEmpty());
    }

    @Test
    public void testFindById() {
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 23);

        Optional<Student> student = service.findById(1);

        assertTrue(student.isPresent());
        assertEquals("Alice", student.get().getName());
        assertEquals(22, student.get().getAge());
    }

    @Test
    public void testFindByIdNotFound() {
        service.addStudent("Alice", 22);

        Optional<Student> student = service.findById(999);

        assertFalse(student.isPresent());
    }

    @Test
    public void testFindByIdEmpty() {
        Optional<Student> student = service.findById(1);

        assertFalse(student.isPresent());
    }

    @Test
    public void testIdIncrementsProperly() {
        Student s1 = service.addStudent("First", 20);
        Student s2 = service.addStudent("Second", 21);
        Student s3 = service.addStudent("Third", 22);
        Student s4 = service.addStudent("Fourth", 23);

        assertEquals(1, s1.getId());
        assertEquals(2, s2.getId());
        assertEquals(3, s3.getId());
        assertEquals(4, s4.getId());
    }

    @Test
    public void testUpdateOnlyName() {
        service.addStudent("Original", 25);

        service.updateStudent(1, "Updated", 25);
        Student s = service.findById(1).get();

        assertEquals("Updated", s.getName());
        assertEquals(25, s.getAge());
    }

    @Test
    public void testUpdateOnlyAge() {
        service.addStudent("Name", 25);

        service.updateStudent(1, "Name", 35);
        Student s = service.findById(1).get();

        assertEquals("Name", s.getName());
        assertEquals(35, s.getAge());
    }

    @Test
    public void testCompleteLifecycle() {
        // Add students
        Student s1 = service.addStudent("Alice", 22);
        Student s2 = service.addStudent("Bob", 23);
        assertEquals(2, service.getAllStudents().size());

        // Update a student
        service.updateStudent(1, "Alice Updated", 25);
        Student updated = service.findById(1).get();
        assertEquals("Alice Updated", updated.getName());
        assertEquals(25, updated.getAge());

        // Delete a student
        service.deleteStudent(2);
        assertEquals(1, service.getAllStudents().size());
        assertFalse(service.findById(2).isPresent());

        // Verify remaining student
        Student remaining = service.findById(1).get();
        assertEquals("Alice Updated", remaining.getName());
    }
}

