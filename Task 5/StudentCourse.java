import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class StudentCourse{
    public static void main(String[] args) {
        CourseDatabase courseDB = new CourseDatabase();
        StudentDatabase studentDB = new StudentDatabase();

        // Adding some courses
        courseDB.addCourse(new Course("CS3CO31", "Computer Science", "Basics of Computer Science", 30, "MWF 9-10 AM"));
        courseDB.addCourse(new Course("BIO101", "Biology  ", "Basics of cell biology, genetics, and evolution.", 25, "TTh 11-12:30 PM"));

        // Adding some students
        studentDB.addStudent(new Student("S001", "Palak"));
        studentDB.addStudent(new Student("S002", "Tanisha"));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Display Courses");
            System.out.println("2. Display Students");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    courseDB.displayCourses();
                    break;
                case 2:
                    studentDB.displayStudents();
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = studentDB.getStudentById(studentId);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    Course course = courseDB.getCourseByCode(courseCode);
                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }
                    if (student.registerCourse(course)) {
                        System.out.println("Registered successfully.");
                    } else {
                        System.out.println("Failed to register. Course might be full.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    student = studentDB.getStudentById(studentId);
                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.print("Enter course code: ");
                    courseCode = scanner.nextLine();
                    course = courseDB.getCourseByCode(courseCode);
                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }
                    if (student.dropCourse(course)) {
                        System.out.println("Dropped successfully.");
                    } else {
                        System.out.println("Failed to drop the course.");
                    }
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank You for Registration!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}

public class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolled;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public boolean enroll() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        } else {
            return false;
        }
    }

    public boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description + "\nCapacity: " + capacity + "\nEnrolled: " + enrolled + "\nSchedule: " + schedule;
    }
}
public class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (course.enroll()) {
            registeredCourses.add(course);
            return true;
        } else {
            return false;
        }
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.drop();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\nName: " + name + "\nRegistered Courses: " + registeredCourses.size();
    }
}
public class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public void displayCourses() {
        for (Course course : courses) {
            System.out.println(course);
            System.out.println();
        }
    }
}
public class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
            System.out.println();
        }
    }
}
