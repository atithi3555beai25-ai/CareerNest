import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages student records by coordinating validation, sorting, JDBC persistence, and file I/O.
 */
public class StudentManager {
    private static final String CSV_HEADER = "id,name,branch,cgpa,backlogs";

    private final StudentDAO dao = new StudentDAO();
    private final InputValidator validator = new InputValidator();
    private final ErrorHandler errorHandler = new ErrorHandler();

    public void add(Student s) throws Exception {
        validator.validateStudent(s);
        dao.save(s);
    }

    public Student find(int id) throws Exception {
        if (!validator.validId(id)) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        return dao.find(id);
    }

    public boolean exists(int id) throws Exception {
        if (!validator.validId(id)) {
            return false;
        }
        return dao.exists(id);
    }

    public ArrayList<Student> getAll() throws Exception {
        return dao.all();
    }

    /**
     * Returns all students from the database sorted by CGPA in descending order.
     *
     * @return new ArrayList sorted from highest CGPA to lowest CGPA
     * @throws Exception if database access fails
     */
    public ArrayList<Student> getStudentsSortedByCgpa() throws Exception {
        return sortByCgpaDesc(dao.all());
    }

    /**
     * Returns a new list sorted by CGPA descending (highest first) without modifying the input list.
     *
     * @param students input list of students
     * @return new sorted ArrayList of non-null students
     */
    public ArrayList<Student> sortByCgpaDesc(ArrayList<Student> students) {
        if (students == null || students.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Student> sorted = new ArrayList<>();
        for (Student student : students) {
            if (student != null) {
                sorted.add(student);
            }
        }

        Collections.sort(sorted);
        return sorted;
    }

    public ArrayList<Student> findByBranch(String branch) throws Exception {
        if (!validator.validBranch(branch)) {
            throw new IllegalArgumentException("Branch cannot be empty or invalid.");
        }
        return dao.findByBranch(branch.trim());
    }

    public boolean update(Student s) throws Exception {
        validator.validateStudent(s);
        return dao.update(s);
    }

    public boolean delete(int id) throws Exception {
        if (!validator.validId(id)) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        return dao.delete(id);
    }

    public void list() throws Exception {
        System.out.println("Checking students...");
        printStudents(dao.all());
    }

    /**
     * Displays all students sorted by CGPA in descending order.
     *
     * @throws Exception if database access fails
     */
    public void listSortedByCgpa() throws Exception {
        System.out.println("Checking students (sorted by CGPA descending)...");
        printStudents(getStudentsSortedByCgpa());
    }

    /**
     * Exports all student records from the database to the specified CSV file.
     *
     * @param filePath destination file path
     * @return true if saved successfully, false if an error occurred
     */
    public boolean saveAllToFile(String filePath) {
        try {
            return saveToFile(dao.all(), filePath);
        } catch (Exception e) {
            errorHandler.show("Export Students", e);
            return false;
        }
    }

    /**
     * Saves the given list of students to a UTF-8 CSV file using BufferedWriter and Files.
     * Skips null, invalid, or duplicate-ID entries while preserving valid records.
     *
     * @param students list of students to save
     * @param filePath destination file path
     * @return true if the file was written successfully, false otherwise
     */
    public boolean saveToFile(ArrayList<Student> students, String filePath) {
        if (students == null) {
            errorHandler.show("Save Students", new IllegalArgumentException("Student list cannot be null."));
            return false;
        }
        Path path = resolvePath(filePath, "Save Students");
        if (path == null) {
            return false;
        }

        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            int savedCount = 0;
            Set<Integer> seenIds = new HashSet<>();

            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                writer.write(CSV_HEADER);
                writer.newLine();

                for (Student s : students) {
                    if (s == null) {
                        continue;
                    }
                    try {
                        validator.validateStudent(s);
                    } catch (IllegalArgumentException e) {
                        errorHandler.show("Save Students (ID " + s.getId() + ")", e);
                        continue;
                    }
                    if (!seenIds.add(s.getId())) {
                        System.out.println("Skipping duplicate student ID during save: " + s.getId());
                        continue;
                    }
                    writer.write(formatCsvLine(s));
                    writer.newLine();
                    savedCount++;
                }
            }

            System.out.println("Saved " + savedCount + " student record(s) to " + path.toAbsolutePath());
            return true;
        } catch (IOException | SecurityException e) {
            errorHandler.show("Save Students", e);
            return false;
        }
    }

    /**
     * Loads valid student records from a CSV file, handling missing files and bad lines gracefully.
     *
     * @param filePath source file path
     * @return ArrayList of valid, non-duplicate Student objects
     */
    public ArrayList<Student> loadFromFile(String filePath) {
        return loadFromFile(filePath, new ArrayList<>());
    }

    /**
     * Loads valid student records from a CSV file and merges them with existing students
     * without overwriting existing entries or introducing duplicate student IDs.
     *
     * @param filePath         source file path
     * @param existingStudents existing student records to preserve
     * @return new ArrayList containing preserved existing records plus newly loaded valid records
     */
    public ArrayList<Student> loadFromFile(String filePath, ArrayList<Student> existingStudents) {
        ArrayList<Student> result = new ArrayList<>();
        Set<Integer> seenIds = new HashSet<>();

        if (existingStudents != null) {
            for (Student existing : existingStudents) {
                if (existing != null && seenIds.add(existing.getId())) {
                    result.add(existing);
                }
            }
        }

        Path path = resolvePath(filePath, "Load Students");
        if (path == null) {
            return result;
        }

        if (!Files.exists(path)) {
            errorHandler.show("Load Students", new NoSuchFileException(path.toString()));
            return result;
        }
        if (Files.isDirectory(path)) {
            errorHandler.show("Load Students",
                    new IOException("Path points to a directory rather than a file: " + path));
            return result;
        }

        int loadedCount = 0;
        int skippedCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                if (lineNumber == 1 && trimmed.equalsIgnoreCase(CSV_HEADER)) {
                    continue;
                }

                try {
                    Student student = parseCsvLine(trimmed, lineNumber);
                    if (!seenIds.add(student.getId())) {
                        skippedCount++;
                        System.out.println("Skipping duplicate student ID " + student.getId()
                                + " on line " + lineNumber + ".");
                        continue;
                    }
                    result.add(student);
                    loadedCount++;
                } catch (IllegalArgumentException e) {
                    skippedCount++;
                    errorHandler.show("Load Students (line " + lineNumber + ")", e);
                }
            }

            System.out.println("Loaded " + loadedCount + " student record(s) from " + path.toAbsolutePath()
                    + (skippedCount > 0 ? " (" + skippedCount + " skipped)." : "."));
        } catch (IOException | SecurityException e) {
            errorHandler.show("Load Students", e);
        }

        return result;
    }

    private Path resolvePath(String filePath, String context) {
        if (!validator.nonEmpty(filePath)) {
            errorHandler.show(context, new IllegalArgumentException("File path cannot be empty."));
            return null;
        }
        try {
            return Path.of(filePath.trim());
        } catch (InvalidPathException e) {
            errorHandler.show(context, e);
            return null;
        }
    }

    private String formatCsvLine(Student s) {
        return s.getId() + ","
                + s.getName() + ","
                + s.getBranch() + ","
                + s.getCgpa() + ","
                + s.getBacklogs();
    }

    private Student parseCsvLine(String line, int lineNumber) {
        String[] parts = line.split(",", -1);
        if (parts.length != 5) {
            throw new IllegalArgumentException(
                    "Invalid record format on line " + lineNumber + " (expected 5 comma-separated fields).");
        }

        int id;
        double cgpa;
        int backlogs;
        try {
            id = Integer.parseInt(parts[0].trim());
            cgpa = Double.parseDouble(parts[3].trim());
            backlogs = Integer.parseInt(parts[4].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid numeric field on line " + lineNumber + ": " + e.getMessage());
        }

        String name = parts[1].trim();
        String branch = parts[2].trim();

        validator.validateStudentFields(id, name, branch, cgpa, backlogs);
        return new Student(id, name, branch, cgpa, backlogs);
    }

    private void printStudents(ArrayList<Student> students) {
        System.out.println("Total students: " + students.size());

        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        for (Student s : students) {
            System.out.println("ID: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Branch: " + s.getBranch());
            System.out.println("CGPA: " + s.getCgpa());
            System.out.println("Backlogs: " + s.getBacklogs());
            System.out.println("--------------------");
        }
    }
}
