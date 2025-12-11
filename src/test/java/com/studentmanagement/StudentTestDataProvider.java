package com.studentmanagement;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * Data provider class for parameterized tests.
 * This class contains static methods that supply test data to parameterized test methods.
 * Using @MethodSource with this class separates test data from test logic.
 */
public class StudentTestDataProvider {

    /**
     * Provides test data for adding students with various names and ages.
     * @return Stream of Arguments (name, age)
     */
    public static Stream<Arguments> provideStudentDataForAddition() {
        return Stream.of(
            Arguments.of("Alice", 22),
            Arguments.of("Bob", 25),
            Arguments.of("Charlie", 30),
            Arguments.of("Diana", 19),
            Arguments.of("Edward", 45),
            Arguments.of("Fiona", 21),
            Arguments.of("George", 35)
        );
    }

    /**
     * Provides test data for ID increment testing.
     * @return Stream of Arguments (name, age)
     */
    public static Stream<Arguments> provideStudentDataForIdIncrement() {
        return Stream.of(
            Arguments.of("John Smith", 25),
            Arguments.of("Jane Doe", 30),
            Arguments.of("Alice Johnson", 28),
            Arguments.of("Bob Williams", 35)
        );
    }

    /**
     * Provides test data for special character handling in names.
     * @return Stream of Arguments (name, age)
     */
    public static Stream<Arguments> provideStudentDataWithSpecialCharacters() {
        return Stream.of(
            Arguments.of("O'Brien", 25),
            Arguments.of("Jean-Pierre", 30),
            Arguments.of("María José", 28),
            Arguments.of("José María", 22),
            Arguments.of("Anna-Marie O'Connor", 45)
        );
    }

    /**
     * Provides test data for boundary age values.
     * @return Stream of Arguments (name, age)
     */
    public static Stream<Arguments> provideStudentDataWithBoundaryAges() {
        return Stream.of(
            Arguments.of("Alice", 0),
            Arguments.of("Bob", 1),
            Arguments.of("Charlie", 100),
            Arguments.of("Diana", 999)
        );
    }

    /**
     * Provides test data for edge case names.
     * @return Stream of Arguments (name, age)
     */
    public static Stream<Arguments> provideStudentDataWithEdgeCaseNames() {
        return Stream.of(
            Arguments.of("", 25),          // Empty name
            Arguments.of("A", 20),         // Single character
            Arguments.of("VeryLongName1234567890VeryLongName", 30)  // Very long name
        );
    }

    /**
     * Provides test data for multiple student operations.
     * @return Stream of Arguments (name, age)
     */
    public static Stream<Arguments> provideStudentDataForMultipleOperations() {
        return Stream.of(
            Arguments.of("Student1", 20),
            Arguments.of("Student2", 21),
            Arguments.of("Student3", 22),
            Arguments.of("Student4", 23),
            Arguments.of("Student5", 24)
        );
    }

    /**
     * Provides comprehensive test data for various student scenarios.
     * This can be used for more complex parameterized tests.
     * @return Stream of Arguments (name, age, expectedId, expectedSize)
     */
    public static Stream<Arguments> provideComprehensiveStudentData() {
        return Stream.of(
            Arguments.of("Alice", 22, 1, 1),
            Arguments.of("Bob", 25, 2, 2),
            Arguments.of("Charlie", 30, 3, 3),
            Arguments.of("Diana", 19, 4, 4),
            Arguments.of("Edward", 45, 5, 5)
        );
    }

    /**
     * Provides test data for invalid student operations.
     * Used for testing error handling and edge cases.
     * @return Stream of Arguments (invalidId)
     */
    public static Stream<Arguments> provideInvalidStudentIds() {
        return Stream.of(
            Arguments.of(999),
            Arguments.of(0),
            Arguments.of(-1),
            Arguments.of(12345)
        );
    }

    /**
     * Provides test data for student update operations.
     * @return Stream of Arguments (originalName, originalAge, newName, newAge)
     */
    public static Stream<Arguments> provideStudentDataForUpdate() {
        return Stream.of(
            Arguments.of("Alice", 22, "Alice Updated", 23),
            Arguments.of("Bob", 25, "Robert", 26),
            Arguments.of("Charlie", 30, "Charles", 31),
            Arguments.of("Diana", 19, "Diana Prince", 20)
        );
    }
}

