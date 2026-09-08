public class Student extends User{
    //creates a student using the User constructor
    public Student(int userId, String name, String email, String password){
        super(userId, name, email, password);
    }

    //identifies this user as a student
    @Override 
    public String getRole(){
        return "Student";
    }
}