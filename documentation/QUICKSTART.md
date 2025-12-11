# JavaApplication_Lab - Quick Start Guide

## ✅ Project Successfully Converted to Maven Structure

Your project has been successfully restructured to follow Maven standards!

## Project Structure

```
JavaApplication_Lab/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── studentmanagement/
│   │   │           ├── Student.java
│   │   │           ├── StudentService.java
│   │   │           └── StudentManagementApp.java
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── resources/
├── pom.xml                    # Maven configuration
├── .gitignore                 # Git ignore rules
├── MAVEN_CONVERSION.md        # Detailed conversion info
└── README.md                  # Original README
```

## Common Maven Commands

### Compile the project
```bash
mvn clean compile
```

### Build JAR file
```bash
mvn clean package
```

### Run the application
```bash
mvn exec:java -Dexec.mainClass="com.studentmanagement.StudentManagementApp"
```

### Run tests (when added)
```bash
mvn test
```

### Clean build artifacts
```bash
mvn clean
```

### View project info
```bash
mvn help:active-profiles
mvn dependency:tree
```

## IDE Setup

### IntelliJ IDEA
1. Open project: `File → Open → Select JavaApplication_Lab`
2. Maven should auto-detect the project
3. Right-click pom.xml → Maven → Reload projects

### Visual Studio Code
1. Install "Extension Pack for Java"
2. Open the project folder
3. Maven extension will auto-detect

### Eclipse
1. File → Import → Maven → Existing Maven Projects
2. Select the project root directory

## Application Usage

The application is a Student Management System with the following features:

### Menu Options:
1. **Add Student** - Create a new student record
2. **Update Student** - Modify student information
3. **Delete Student** - Remove a student record
4. **Show All Students** - Display all students
5. **Exit** - Close the application

### Running the Application

**Method 1: Using Maven**
```bash
cd D:\JavaApplication_Lab
mvn exec:java -Dexec.mainClass="com.studentmanagement.StudentManagementApp"
```

**Method 2: Using JAR file**
```bash
cd D:\JavaApplication_Lab
mvn clean package
java -jar target/javaapplication-lab-0.1.0-SNAPSHOT.jar
```

**Method 3: From IDE**
- Right-click `StudentManagementApp.java`
- Select "Run" or "Run with Debug"

## Project Dependencies

Currently, the project has **no external dependencies**. It uses only Java standard library.

To add dependencies in the future, edit `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Key Configuration

**File**: `pom.xml`

- **Project ID**: `com.example:javaapplication-lab:0.1.0-SNAPSHOT`
- **Java Version**: 17
- **Encoding**: UTF-8
- **Main Class**: `com.studentmanagement.StudentManagementApp`

## Adding Tests

Create test files in `src/test/java/com/studentmanagement/`:

```java
package com.studentmanagement;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentServiceTest {
    @Test
    public void testAddStudent() {
        StudentService service = new StudentService();
        Student s = service.addStudent("John Doe", 25);
        assertEquals("John Doe", s.getName());
        assertEquals(25, s.getAge());
    }
}
```

Then add JUnit dependency to pom.xml and run:
```bash
mvn test
```

## Troubleshooting

### Issue: "Maven not found"
**Solution**: Install Maven from https://maven.apache.org/download.cgi

### Issue: "Java version not supported"
**Solution**: Edit pom.xml and change `<java.version>` to your installed Java version

### Issue: "Class not found"
**Solution**: Ensure package name matches directory structure: `com.studentmanagement` → `src/main/java/com/studentmanagement/`

## Next Steps

1. ✅ **Project Structure**: Already done!
2. **Add Unit Tests**: Create test classes in `src/test/java/`
3. **Add Dependencies**: Modify `pom.xml` as needed
4. **Version Control**: Push to Git with `.gitignore` included
5. **CI/CD**: Set up GitHub Actions, Jenkins, or other tools

## Documentation Files

- `MAVEN_CONVERSION.md` - Detailed conversion information
- `README.md` - Original project README
- This file - Quick start guide

---

**Status**: ✅ Successfully converted to Maven structure
**Ready to use**: Yes
**Build system**: Maven 3.6+
**Java version**: 17+

