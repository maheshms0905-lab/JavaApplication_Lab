# JUnit Tests - Quick Reference

## 📋 Test Files Created

Three comprehensive test classes have been created in `src/test/java/com/studentmanagement/`:

1. **StudentTest.java** (14 tests)
   - Tests for Student class getters, setters, and edge cases

2. **StudentServiceTest.java** (23 tests)
   - Tests for CRUD operations (Create, Read, Update, Delete)

3. **StudentManagementAppTest.java** (19 tests)
   - Tests for application workflows and menu operations

## 🚀 Running Tests

### Run ALL tests:
```bash
mvn clean test
```

### Run ALL tests with detailed output:
```bash
mvn clean test -X
```

### Run specific test class:
```bash
mvn test -Dtest=StudentTest
mvn test -Dtest=StudentServiceTest
mvn test -Dtest=StudentManagementAppTest
```

### Run specific test method:
```bash
mvn test -Dtest=StudentTest#testStudentCreation
mvn test -Dtest=StudentServiceTest#testAddStudent
```

### Run tests and skip compilation:
```bash
mvn test -DskipTests=false
```

## 📊 What's Tested

### ✅ Student Class Tests
- Object creation and initialization
- Getter methods (getId, getName, getAge)
- Setter methods (setName, setAge)
- Edge cases (empty strings, zero/negative ages)
- toString() method
- Multiple instances
- Special characters in names

### ✅ StudentService Class Tests
- Adding students (single and multiple)
- Retrieving all students
- Finding student by ID
- Updating student information
- Deleting students
- ID auto-increment verification
- Error handling (non-existent IDs)
- Empty database handling
- Complete lifecycle workflows

### ✅ StudentManagementApp Class Tests
- Application instantiation
- Menu option 1: Add Student
- Menu option 2: Update Student
- Menu option 3: Delete Student
- Menu option 4: Show All Students
- Invalid input handling
- Empty database handling
- Sequential operations
- Data persistence

## 📈 Test Statistics

Total Tests: **56**
- StudentTest: 14 tests
- StudentServiceTest: 23 tests
- StudentManagementAppTest: 19 tests

## 🔍 Test Output Example

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.studentmanagement.StudentTest
Tests run: 14, Failures: 0, Errors: 0, Skipped: 0

Running com.studentmanagement.StudentServiceTest
Tests run: 23, Failures: 0, Errors: 0, Skipped: 0

Running com.studentmanagement.StudentManagementAppTest
Tests run: 19, Failures: 0, Errors: 0, Skipped: 0

-------------------------------------------------------
Results :

Tests run: 56, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS
-------------------------------------------------------
```

## 📁 Test File Locations

```
JavaApplication_Lab/
├── src/
│   ├── main/java/com/studentmanagement/
│   │   ├── Student.java
│   │   ├── StudentService.java
│   │   └── StudentManagementApp.java
│   │
│   └── test/java/com/studentmanagement/    ← TEST FILES HERE
│       ├── StudentTest.java
│       ├── StudentServiceTest.java
│       └── StudentManagementAppTest.java
│
└── pom.xml (updated with JUnit dependencies)
```

## 🛠️ JUnit Dependencies Added

```xml
<!-- JUnit 5 Jupiter API -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>

<!-- JUnit 5 Engine -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.9.2</version>
    <scope>test</scope>
</dependency>

<!-- Maven Surefire Plugin for running tests -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
</plugin>
```

## 🎯 Common Test Assertions

```java
// Equality
assertEquals(expected, actual);
assertNotEquals(unexpected, actual);

// Boolean
assertTrue(condition);
assertFalse(condition);

// Null checks
assertNull(object);
assertNotNull(object);

// Exceptions
assertThrows(Exception.class, () -> method());
assertDoesNotThrow(() -> method());
```

## 💡 Tips

1. **IDE Integration**: Most IDEs (IntelliJ, Eclipse, VS Code) recognize JUnit tests automatically
   - Right-click test class → Run Tests
   - Right-click test method → Run Test

2. **Test Driven Development**: Write tests first, then implementation

3. **Continuous Integration**: Set up Maven to run tests on every commit:
   ```bash
   # In GitHub Actions, Jenkins, etc.
   mvn clean test
   ```

4. **Code Coverage**: Add coverage with JaCoCo
   ```bash
   mvn clean test jacoco:report
   ```

## 📝 Test Documentation

See `TEST_DOCUMENTATION.md` for:
- Detailed description of each test
- Test patterns used
- Best practices implemented
- Troubleshooting guide

## ✨ Features Tested

| Feature | Status |
|---------|--------|
| Create Student | ✅ 3 tests |
| Read Student | ✅ 4 tests |
| Update Student | ✅ 5 tests |
| Delete Student | ✅ 3 tests |
| Get All Students | ✅ 4 tests |
| ID Auto-increment | ✅ 2 tests |
| Error Handling | ✅ 5 tests |
| Edge Cases | ✅ 8 tests |
| Workflows | ✅ 4 tests |
| Data Persistence | ✅ 2 tests |
| **Total Coverage** | **✅ 40 scenarios** |

## 🚨 Troubleshooting

**Q: Tests not found?**
A: Ensure test files are in `src/test/java/` directory with names ending in "Test"

**Q: Import errors for JUnit?**
A: Run `mvn dependency:resolve` to download dependencies

**Q: Tests failing?**
A: Check pom.xml has JUnit dependencies and Maven surefire plugin

**Q: Want to skip tests during build?**
A: Use `-DskipTests` flag: `mvn clean install -DskipTests`

## 🎓 Learning Resources

- [JUnit 5 Official Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/)
- [Best Practices for Unit Testing](https://www.oracle.com/technical-resources/articles/java/unit-testing-best-practices.html)

---

**Ready to test!** Run `mvn clean test` to execute all 56 tests.

