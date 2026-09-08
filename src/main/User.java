import java.time.LocalDateTime;

public abstract class User {

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


}