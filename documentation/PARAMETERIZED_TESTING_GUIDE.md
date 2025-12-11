# Parameterized Testing Quick Reference

## What is Parameterized Testing?

Parameterized testing allows you to run the same test logic multiple times with different input values. Instead of writing separate test methods for each case, you write one test method that executes multiple times with different parameters.

## Before Parameterization (Old Way)
```java
@Test
public void testAddStudentAlice() {
    StudentService service = new StudentService();
    Student s = service.addStudent("Alice", 22);
    assertEquals("Alice", s.getName());
    assertEquals(22, s.getAge());
}

@Test
public void testAddStudentBob() {
    StudentService service = new StudentService();
    Student s = service.addStudent("Bob", 25);
    assertEquals("Bob", s.getName());
    assertEquals(25, s.getAge());
}

// Need separate method for each test case...
```

## After Parameterization (New Way)
```java
@ParameterizedTest
@CsvSource({
    "Alice, 22",
    "Bob, 25",
    "Charlie, 30"
})
public void testAddStudentParameterized(String name, int age) {
    StudentService service = new StudentService();
    Student s = service.addStudent(name, age);
    assertEquals(name, s.getName());
    assertEquals(age, s.getAge());
}
```

**Result**: One method, three test cases (no code duplication!)

## Annotations Used in This Project

### @ParameterizedTest
Marks a test method as parameterized. Instead of `@Test`, use this annotation.

```java
@ParameterizedTest
public void myTest(String param1, int param2) {
    // Test logic
}
```

### @CsvSource
Provides test parameters as comma-separated values (CSV).

```java
@ParameterizedTest
@CsvSource({
    "Alice, 22",           // First test case
    "Bob, 25",             // Second test case
    "Charlie, 30"          // Third test case
})
public void testAddStudent(String name, int age) {
    // Each iteration uses one row from the CSV
}
```

### @ValueSource
Provides a single parameter with multiple values.

```java
@ParameterizedTest
@ValueSource(ints = { 1, 2, 3, 4, 5 })
public void testWithIntegers(int number) {
    // Test runs 5 times with values 1, 2, 3, 4, 5
}
```

### @DisplayName
Provides a readable name for test cases.

```java
@DisplayName("Test addStudent with various names and ages")
public void testAddStudentParameterized(String name, int age) {
    // ...
}
```

## Parameterized Tests Added to This Project

### 1. testAddStudentParameterized
**What it tests**: Adding students with different names and ages

```java
@ParameterizedTest
@CsvSource({
    "Alice, 22",
    "Bob, 25",
    "Charlie, 30",
    "Diana, 19",
    "Edward, 45",
    "Fiona, 21",
    "George, 35"
})
@DisplayName("Test addStudent with various names and ages")
public void testAddStudentParameterized(String name, int age) {
    // 7 test cases run here
}
```
**Test Executions**: 7

### 2. testAddStudentIdIncrement
**What it tests**: ID increments correctly across multiple students

```java
@ParameterizedTest
@CsvSource({
    "John Smith, 25",
    "Jane Doe, 30",
    "Alice Johnson, 28",
    "Bob Williams, 35"
})
@DisplayName("Test addStudent ID increments correctly for multiple students")
public void testAddStudentIdIncrement(String name, int age) {
    // 4 test cases run here
}
```
**Test Executions**: 4

### 3. testAddStudentSpecialCharactersParameterized
**What it tests**: Special characters in names are preserved

```java
@ParameterizedTest
@CsvSource({
    "O'Brien, 25",
    "Jean-Pierre, 30",
    "María José, 28",
    "José María, 22",
    "Anna-Marie O'Connor, 45"
})
@DisplayName("Test addStudent with special characters in names")
public void testAddStudentSpecialCharactersParameterized(String name, int age) {
    // 5 test cases run here
}
```
**Test Executions**: 5

### 4. testAddStudentBoundaryAgesParameterized
**What it tests**: Boundary values for ages (0, 1, 100, 999)

```java
@ParameterizedTest
@CsvSource({
    "Alice, 0",
    "Bob, 1",
    "Charlie, 100",
    "Diana, 999"
})
@DisplayName("Test addStudent with boundary ages")
public void testAddStudentBoundaryAgesParameterized(String name, int age) {
    // 4 test cases run here
}
```
**Test Executions**: 4

### 5. testAddStudentEdgeCaseNamesParameterized
**What it tests**: Edge case names (empty, single char, very long)

```java
@ParameterizedTest
@CsvSource({
    ", 25",          // Empty name
    "A, 20",         // Single character
    "VeryLongName1234567890VeryLongName, 30"  // Long name
})
@DisplayName("Test addStudent with edge case names")
public void testAddStudentEdgeCaseNamesParameterized(String name, int age) {
    // 3 test cases run here
}
```
**Test Executions**: 3

### 6. testAddStudentMultipleCallsParameterized
**What it tests**: Multiple additions maintain list integrity

```java
@ParameterizedTest
@CsvSource({
    "Student1, 20",
    "Student2, 21",
    "Student3, 22",
    "Student4, 23",
    "Student5, 24"
})
@DisplayName("Test addStudent multiple calls maintain list integrity")
public void testAddStudentMultipleCallsParameterized(String name, int age) {
    // 5 test cases run here
}
```
**Test Executions**: 5

## Total Test Statistics

| Category | Count |
|----------|-------|
| Original Tests | 28 |
| New Parameterized Tests | 28 |
| StudentServiceTest | 19 |
| StudentTest | 14 |
| **TOTAL** | **78** |

## How to Add More Test Cases

### Step 1: Add a CSV row to @CsvSource
```java
@ParameterizedTest
@CsvSource({
    "Alice, 22",
    "Bob, 25",
    "NewName, 40"  // ← Add new case here
})
```

### Step 2: Run tests
```powershell
mvn test
```

That's it! The new test case will execute automatically.

## Running Tests

### Run all tests:
```powershell
mvn test
```

### Run a specific parameterized test:
```powershell
mvn test -Dtest=StudentManagementAppTest#testAddStudentParameterized
```

### Run tests with detailed output:
```powershell
mvn test -X
```

### Generate and view the HTML report:
```powershell
mvn verify
Start-Process "D:\JavaApplication_Lab\target\report\surefire-report.html"
```

## Benefits of Parameterized Testing

✅ **Less Code**: Write once, test many times  
✅ **Easier Maintenance**: Add test cases by adding CSV rows  
✅ **Better Coverage**: Test more scenarios with less code  
✅ **Clear Test Reports**: Each parameter set appears as separate test  
✅ **Readable Data**: CSV format shows test inputs clearly  
✅ **Reduced Duplication**: No copy-paste test methods  

## Key Takeaway

Instead of writing:
```java
testAddAlice()
testAddBob()
testAddCharlie()
// ... repeat 100 times
```

You write:
```java
testAddStudent(name, age) // with @ParameterizedTest and @CsvSource
```

This is the power of parameterized testing! 🚀


