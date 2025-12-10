package com.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StudentManagementApp Tests")
public class StudentManagementAppTest {

    @Test
    @DisplayName("Test StudentManagementApp instantiation")
    public void testApplicationInstantiation() {
        assertDoesNotThrow(() -> {
            StudentManagementApp app = new StudentManagementApp();
            assertNotNull(app);
        });
    }

    @Test
    @DisplayName("Test StudentService integration with StudentManagementApp")
    public void testServiceIntegration() {
        StudentService service = new StudentService();

        // Simulate add student operation
        Student s1 = service.addStudent("John", 25);
        Student s2 = service.addStudent("Jane", 23);

        assertEquals(2, service.getAllStudents().size());
        assertEquals("John", s1.getName());
        assertEquals("Jane", s2.getName());
    }

    @Test
    @DisplayName("Test add student workflow")
    public void testAddStudentWorkflow() {
        StudentService service = new StudentService();

        // Simulate: Enter name "Alice", age 22
        Student student = service.addStudent("Alice", 22);

        assertNotNull(student);
        assertEquals("Alice", student.getName());
        assertEquals(22, student.getAge());
        assertEquals(1, student.getId());
    }

    @Test
    @DisplayName("Test update student workflow")
    public void testUpdateStudentWorkflow() {
        StudentService service = new StudentService();

        // Add a student
        service.addStudent("Bob", 25);

        // Simulate: Enter ID 1, new name "Bob Updated", new age 26
        boolean updated = service.updateStudent(1, "Bob Updated", 26);

        assertTrue(updated);
        Student student = service.findById(1).get();
        assertEquals("Bob Updated", student.getName());
        assertEquals(26, student.getAge());
    }

    @Test
    @DisplayName("Test delete student workflow")
    public void testDeleteStudentWorkflow() {
        StudentService service = new StudentService();

        // Add students
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 25);

        // Simulate: Enter ID 1
        boolean deleted = service.deleteStudent(1);

        assertTrue(deleted);
        assertEquals(1, service.getAllStudents().size());
        assertFalse(service.findById(1).isPresent());
        assertTrue(service.findById(2).isPresent());
    }

    @Test
    @DisplayName("Test show all students workflow")
    public void testShowAllStudentsWorkflow() {
        StudentService service = new StudentService();

        // Add multiple students
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 25);
        service.addStudent("Charlie", 23);

        // Get all students
        var students = service.getAllStudents();

        assertEquals(3, students.size());
        assertTrue(students.stream().anyMatch(s -> s.getName().equals("Alice")));
        assertTrue(students.stream().anyMatch(s -> s.getName().equals("Bob")));
        assertTrue(students.stream().anyMatch(s -> s.getName().equals("Charlie")));
    }

    @Test
    @DisplayName("Test menu option 1: Add Student")
    public void testMenuOption1AddStudent() {
        StudentService service = new StudentService();
        String name = "Test Student";
        int age = 20;

        Student student = service.addStudent(name, age);

        assertEquals(name, student.getName());
        assertEquals(age, student.getAge());
        assertEquals(1, student.getId());
    }

    @Test
    @DisplayName("Test menu option 2: Update Student")
    public void testMenuOption2UpdateStudent() {
        StudentService service = new StudentService();
        service.addStudent("Original", 25);

        boolean success = service.updateStudent(1, "Updated", 30);

        assertTrue(success);
        Student student = service.findById(1).get();
        assertEquals("Updated", student.getName());
        assertEquals(30, student.getAge());
    }

    @Test
    @DisplayName("Test menu option 3: Delete Student")
    public void testMenuOption3DeleteStudent() {
        StudentService service = new StudentService();
        service.addStudent("To Delete", 25);

        boolean success = service.deleteStudent(1);

        assertTrue(success);
        assertTrue(service.getAllStudents().isEmpty());
    }

    @Test
    @DisplayName("Test menu option 4: Show All Students")
    public void testMenuOption4ShowAllStudents() {
        StudentService service = new StudentService();
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 24);

        var allStudents = service.getAllStudents();

        assertFalse(allStudents.isEmpty());
        assertEquals(2, allStudents.size());
    }

    @Test
    @DisplayName("Test invalid student ID handling")
    public void testInvalidStudentIdHandling() {
        StudentService service = new StudentService();
        service.addStudent("Alice", 22);

        // Try to update non-existent student
        boolean updated = service.updateStudent(999, "Name", 25);
        assertFalse(updated);

        // Try to delete non-existent student
        boolean deleted = service.deleteStudent(999);
        assertFalse(deleted);

        // Try to find non-existent student
        var found = service.findById(999);
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Test handling empty database on show all")
    public void testShowAllOnEmptyDatabase() {
        StudentService service = new StudentService();

        var students = service.getAllStudents();

        assertTrue(students.isEmpty());
        assertEquals(0, students.size());
    }

    @Test
    @DisplayName("Test handling empty name")
    public void testEmptyNameHandling() {
        StudentService service = new StudentService();

        Student student = service.addStudent("", 25);

        assertEquals("", student.getName());
        assertEquals(25, student.getAge());
    }

    @Test
    @DisplayName("Test handling zero age")
    public void testZeroAgeHandling() {
        StudentService service = new StudentService();

        Student student = service.addStudent("Name", 0);

        assertEquals(0, student.getAge());
    }

    @Test
    @DisplayName("Test handling special characters in name")
    public void testSpecialCharactersInName() {
        StudentService service = new StudentService();

        Student student = service.addStudent("O'Brien-Smith Jr.", 25);

        assertEquals("O'Brien-Smith Jr.", student.getName());
    }

    @Test
    @DisplayName("Test multiple operations in sequence")
    public void testMultipleOperationsSequence() {
        StudentService service = new StudentService();

        // Add 3 students
        service.addStudent("Alice", 22);
        service.addStudent("Bob", 23);
        service.addStudent("Charlie", 24);
        assertEquals(3, service.getAllStudents().size());

        // Update one
        service.updateStudent(2, "Bob Updated", 25);
        Student bob = service.findById(2).get();
        assertEquals("Bob Updated", bob.getName());

        // Delete one
        service.deleteStudent(3);
        assertEquals(2, service.getAllStudents().size());

        // Verify remaining
        assertTrue(service.findById(1).isPresent());
        assertTrue(service.findById(2).isPresent());
        assertFalse(service.findById(3).isPresent());
    }

    @Test
    @DisplayName("Test student persistence through operations")
    public void testStudentPersistenceThroughOperations() {
        StudentService service = new StudentService();

        Student original = service.addStudent("Test", 25);
        int originalId = original.getId();

        // Update student
        service.updateStudent(originalId, "Test Updated", 26);

        // Retrieve and verify
        Student updated = service.findById(originalId).get();
        assertEquals("Test Updated", updated.getName());
        assertEquals(26, updated.getAge());
        assertEquals(originalId, updated.getId());
    }
}

