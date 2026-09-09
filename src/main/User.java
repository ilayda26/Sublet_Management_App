import java.time.LocalDateTime;

public abstract class User {

    //common details for every user

    private int userId;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;


    public User(int userId, String name, String email, String password){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt =LocalDateTime.now();


    }

    public int getUserId(){
        return userId;
    }

    public String getName(){
        return name ;
    }

    public String getEmail(){
        return email ;
    }

    public String getPassword(){
        return password ;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt ;
    }
    //allows profile details to be updated

    public void setEmail(String email) {
        this.email = email;
    }

     public void setPassword(String password) {
        this.password = password;
    }

    public abstract String getRole();






}