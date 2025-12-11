# JavaApplication Lab

This project has been converted to a standard Maven layout.

Build and run (PowerShell):

```powershell
mvn clean package
mvn -Dexec.mainClass=main.java.main.StudentManagementApp exec:java
```

Or run the produced jar:

```powershell
java -cp target\javaapplication-lab-0.1.0-SNAPSHOT.jar main.java.main.StudentManagementApp
```

Notes:
- Sources are under `src/main/java/main` with package `main.java.main`.
- Placeholder files previously at `src/main/` were left as notices and can be removed.

