public class Lister extends User{
    //Creates a lister using the user constructor
    public Lister(int userId, String name, String email, String password){
        super(userId, name, email, password);
    }

    //identifies this user as a lister
    @Override 

    public String getRole(){
        return "Lister";
    }
}