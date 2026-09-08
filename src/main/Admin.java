public class Admin extends User{
    //Creates an admin using the user constructor
    public Admin(int userId, String name, String email, String password){
        super(userId, name, email, password);
    }

    //identifies this user as an admin
    @Override 

    public String getRole(){
        return "Admin";
    }
}