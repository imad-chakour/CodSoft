import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<String> registeredStudents = new ArrayList<>();

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public boolean hasAvailableSlot() {
        return registeredStudents.size() < capacity;
    }

    public void registerStudent(String studentId) {
        if (hasAvailableSlot()) {
            registeredStudents.add(studentId);
        } else {
            System.out.println("No available slots for course " + title);
        }
    }

    public void removeStudent(String studentId) {
        registeredStudents.remove(studentId);
    }

    public int availableSlots() {
        return capacity - registeredStudents.size();
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description + "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nAvailable Slots: " + availableSlots();
    }
}

class Student {
    String id;
    String name;
    List<String> registeredCourses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerForCourse(Course course) {
        if (course.hasAvailableSlot()) {
            course.registerStudent(this.id);
            registeredCourses.add(course.code);
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }

    public void dropCourse(Course course) {
        course.removeStudent(this.id);
        registeredCourses.remove(course.code);
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\nName: " + name + "\nRegistered Courses: " + registeredCourses;
    }
}

public class StudentCourseRegistrationSystem {
    static Map<String, Course> courses = new HashMap<>();
    static Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Sample Data
        courses.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basics of CS", 30, "MWF 10-11 AM"));
        courses.put("MATH101", new Course("MATH101", "Calculus I", "Differential Calculus", 25, "TTh 9-10:30 AM"));

        while (true) {
            System.out.println("\n--- Course Management System ---");
            System.out.println("1. View Courses");
            System.out.println("2. Register Student");
            System.out.println("3. Register for Course");
            System.out.println("4. Drop Course");
            System.out.println("5. View Student Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses.values()) {
                        System.out.println(course);
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    students.put(studentId, new Student(studentId, studentName));
                    System.out.println("Student registered successfully.");
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();
                    if (!students.containsKey(studentId)) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    if (!courses.containsKey(courseCode)) {
                        System.out.println("Course not found.");
                        break;
                    }
                    students.get(studentId).registerForCourse(courses.get(courseCode));
                    System.out.println("Registration complete.");
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();
                    if (!students.containsKey(studentId)) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    if (!courses.containsKey(courseCode)) {
                        System.out.println("Course not found.");
                        break;
                    }
                    students.get(studentId).dropCourse(courses.get(courseCode));
                    System.out.println("Course dropped successfully.");
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();
                    if (!students.containsKey(studentId)) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.println(students.get(studentId));
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
