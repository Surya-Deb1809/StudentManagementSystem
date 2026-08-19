public class Student {
    private int studentID;
    private String name;
    private int age;
    private String course;
    private int semester;
    private String email;
    private double marks;

    public Student(int studentID, String name, int age, String course, int semester, String email, double marks){
        this.studentID =  studentID;
        this.name = name;
        this.age = age;
        this.course = course;
        this.semester = semester;
        this.email = email;
        this.marks = marks;

    }
    public int getStudentID(){
        return studentID;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getCourse(){
        return course;
    }
    public int getSemester(){
        return semester;
    }
    public String getEmail(){
        return email;
    }
    public double getMarks(){
        return marks;
    }
}
