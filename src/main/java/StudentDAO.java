import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDAO {

    public void addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(student_id, name, age, course, semester, email, marks) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, student.getStudentID());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getSemester());
            statement.setString(6, student.getEmail());
            statement.setDouble(7, student.getMarks());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to add student.");
            e.printStackTrace();
        }
    }
    public void viewStudents(){
        String sql = "SELECT * FROM students";
        try(Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); java.sql.ResultSet result = statement.executeQuery()){
            System.out.println("\n======== ALL STUDENTS ========");
            while (result.next()) {
                System.out.println(                    "ID: "+ result.getInt("student_id") + " | Name: " + result.getString("name") + " | Age: " + result.getInt("age") + " | Course: " + result.getString("course") + " | Semeste: " + result.getInt("semester") + " | Email: " + result.getString("email") + " | Marks: " + result.getDouble("marks")
                );  
            }

        } 
         catch (SQLException e) {
            System.out.println("Failed to load stidents.");
            e.printStackTrace();
        }
    }
    public void searchStudent(int studentId) {

        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                System.out.println("\n========== STUDENT FOUND ==========");

                System.out.println("ID       : " + result.getInt("student_id"));
                System.out.println("Name     : " + result.getString("name"));
                System.out.println("Age      : " + result.getInt("age"));
                System.out.println("Course   : " + result.getString("course"));
                System.out.println("Semester : " + result.getInt("semester"));
                System.out.println("Email    : " + result.getString("email"));
                System.out.println("Marks    : " + result.getDouble("marks"));

            } else {

                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Failed to search student.");
            e.printStackTrace();
        }
    }
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET name = ?, age = ?, course = ?, " +
                    "semester = ?, email = ?, marks = ? WHERE student_id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getSemester());
            statement.setString(5, student.getEmail());
            statement.setDouble(6, student.getMarks());
            statement.setInt(7, student.getStudentID());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Failed to update student.");
            e.printStackTrace();
        }
    }
    public void deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Failed to delete student.");
            e.printStackTrace();
        }
    }
    public boolean studentExists(int studentId) {

        String sql = "SELECT student_id FROM students WHERE student_id = ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            return result.next();

        } catch (SQLException e) {

            System.out.println("Failed to check student ID.");
            e.printStackTrace();

            return false;
        }
    }
    public void showStatistics() {

        String sql = "SELECT COUNT(*) AS total, " +
                    "AVG(marks) AS average, " +
                    "MAX(marks) AS highest, " +
                    "MIN(marks) AS lowest " +
                    "FROM students";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ) {

            if (result.next()) {

                System.out.println("\n========== STUDENT STATISTICS ==========");

                System.out.println("Total Students : " + result.getInt("total"));
                System.out.println("Average Marks  : " + result.getDouble("average"));
                System.out.println("Highest Marks  : " + result.getDouble("highest"));
                System.out.println("Lowest Marks   : " + result.getDouble("lowest"));

            }

        } catch (SQLException e) {

            System.out.println("Failed to load statistics.");
            e.printStackTrace();
        }
    }
    public void searchStudentByName(String name) {

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, "%" + name + "%");

            ResultSet result = statement.executeQuery();

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println("\n-----------------------------");
                System.out.println("ID       : " + result.getInt("student_id"));
                System.out.println("Name     : " + result.getString("name"));
                System.out.println("Age      : " + result.getInt("age"));
                System.out.println("Course   : " + result.getString("course"));
                System.out.println("Semester : " + result.getInt("semester"));
                System.out.println("Email    : " + result.getString("email"));
                System.out.println("Marks    : " + result.getDouble("marks"));
            }

            if (!found) {
                System.out.println("No student found with that name.");
            }

        } catch (SQLException e) {

            System.out.println("Failed to search student by name.");
            e.printStackTrace();
        }
    }
}