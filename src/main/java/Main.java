import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Student Statistics");
            System.out.println("8. Exit");
            System.out.println("=================================");

            int choice = readPositiveInt(scanner, "Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent(scanner, studentDAO);
                    break;

                case 2:
                    studentDAO.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();

                    studentDAO.searchStudent(searchId);
                    break;
                case 4:

                    String searchName = readNonEmptyString(
                            scanner,
                            "Enter Student Name to search: "
                    );

                    studentDAO.searchStudentByName(searchName);

                    break;    

                case 5:

                    System.out.println("\n========== UPDATE STUDENT ==========");

                    int updateId = readPositiveInt(scanner, "Enter Student ID: ");

                    if (!studentDAO.studentExists(updateId)) {

                        System.out.println("Student not found!");

                        break;
                    }

                    String updateName = readNonEmptyString(scanner, "Enter New Name: ");

                    int updateAge = readAge(scanner);

                    String updateCourse = readNonEmptyString(scanner, "Enter New Course: ");

                    int updateSemester = readSemester(scanner);

                    String updateEmail = readEmail(scanner);

                    double updateMarks = readMarks(scanner);

                    Student updatedStudent = new Student(
                            updateId,
                            updateName,
                            updateAge,
                            updateCourse,
                            updateSemester,
                            updateEmail,
                            updateMarks
                    );

                    studentDAO.updateStudent(updatedStudent);

                    break;

                case 6:

                    System.out.println("\n========== DELETE STUDENT ==========");

                    int deleteId = readPositiveInt(scanner, "Enter Student ID: ");

                    System.out.print("Are you sure you want to delete this student? (Y/N): ");
                    String confirmation = scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("Y")) {

                        studentDAO.deleteStudent(deleteId);

                    } else {

                        System.out.println("Delete operation cancelled.");
                    }

                    break;
                case 7:

                    studentDAO.showStatistics();

                    break;
                case 8:
                    System.out.println("Thank you for using Student Management System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentDAO studentDAO) {

        System.out.println("\n========== ADD STUDENT ==========");

        int id;

        while (true) {

            id = readPositiveInt(scanner, "Enter Student ID: ");

            if (!studentDAO.studentExists(id)) {
                break;
            }

            System.out.println("Student ID already exists! Please enter another ID.");
        }

        String name = readNonEmptyString(scanner, "Enter Name: ");

        int age = readAge(scanner);

        String course = readNonEmptyString(scanner, "Enter Course: ");

        int semester = readSemester(scanner);

        String email = readEmail(scanner);

        double marks = readMarks(scanner);

        Student student = new Student(
                id,
                name,
                age,
                course,
                semester,
                email,
                marks
        );

        studentDAO.addStudent(student);
    }
    private static int readPositiveInt(Scanner scanner, String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {

                int value = scanner.nextInt();
                scanner.nextLine();

                if (value > 0) {
                    return value;
                }

                System.out.println("Value must be greater than 0.");

            } else {

                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
    private static int readAge(Scanner scanner) {

        while (true) {

            System.out.print("Enter Age: ");

            if (scanner.hasNextInt()) {

                int age = scanner.nextInt();
                scanner.nextLine();

                if (age >= 5 && age <= 100) {
                    return age;
                }

                System.out.println("Age must be between 5 and 100.");

            } else {

                System.out.println("Please enter a valid age.");
                scanner.nextLine();
            }
        }
    }
    private static String readNonEmptyString(Scanner scanner, String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }
    private static int readSemester(Scanner scanner) {

        while (true) {

            System.out.print("Enter Semester: ");

            if (scanner.hasNextInt()) {

                int semester = scanner.nextInt();
                scanner.nextLine();

                if (semester >= 1 && semester <= 8) {
                    return semester;
                }

                System.out.println("Semester must be between 1 and 8.");

            } else {

                System.out.println("Please enter a valid semester.");
                scanner.nextLine();
            }
        }
    }
    private static String readEmail(Scanner scanner) {

        while (true) {

            System.out.print("Enter Email: ");

            String email = scanner.nextLine().trim();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }

            System.out.println("Please enter a valid email address.");
        }
    }
    private static double readMarks(Scanner scanner) {

        while (true) {

            System.out.print("Enter Marks: ");

            if (scanner.hasNextDouble()) {

                double marks = scanner.nextDouble();
                scanner.nextLine();

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println("Marks must be between 0 and 100.");

            } else {

                System.out.println("Please enter a valid marks value.");
                scanner.nextLine();
            }
        }
    }
}