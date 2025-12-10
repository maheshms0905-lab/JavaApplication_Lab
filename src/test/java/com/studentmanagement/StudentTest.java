package com.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student(1, "John Doe", 25);
    }

    @Test
    public void testStudentCreation() {
        assertNotNull(student);
        assertEquals(1, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals(25, student.getAge());
    }

    @Test
    public void testGetId() {
        assertEquals(1, student.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", student.getName());
    }

    @Test
    public void testGetAge() {
        assertEquals(25, student.getAge());
    }

    @Test
    public void testSetName() {
        student.setName("Jane Doe");
        assertEquals("Jane Doe", student.getName());
    }

    @Test
    public void testSetAge() {
        student.setAge(30);
        assertEquals(30, student.getAge());
    }

    @Test
    public void testSetNameEmptyString() {
        student.setName("");
        assertEquals("", student.getName());
    }

    @Test
    public void testSetAgeZero() {
        student.setAge(0);
        assertEquals(0, student.getAge());
    }

    @Test
    public void testSetAgeNegative() {
        student.setAge(-5);
        assertEquals(-5, student.getAge());
    }

    @Test
    public void testToString() {
        String expected = "Student{id=1, name='John Doe', age=25}";
        assertEquals(expected, student.toString());
    }

    @Test
    public void testToStringAfterModification() {
        student.setName("Jane Smith");
        student.setAge(28);
        String expected = "Student{id=1, name='Jane Smith', age=28}";
        assertEquals(expected, student.toString());
    }

    @Test
    public void testMultipleStudents() {
        Student student2 = new Student(2, "Alice Johnson", 22);
        Student student3 = new Student(3, "Bob Smith", 27);

        assertEquals(1, student.getId());
        assertEquals(2, student2.getId());
        assertEquals(3, student3.getId());

        assertNotEquals(student.getName(), student2.getName());
        assertNotEquals(student.getAge(), student3.getAge());
    }

    @Test
    public void testStudentWithSpecialCharacters() {
        Student specialStudent = new Student(5, "O'Brien-Smith", 24);
        assertEquals("O'Brien-Smith", specialStudent.getName());
    }

    @Test
    public void testStudentIdImmutable() {
        Student s1 = new Student(10, "Test", 20);
        Student s2 = new Student(10, "Test", 20);
        assertEquals(s1.getId(), s2.getId());
        assertEquals(10, s1.getId());
    }
}

