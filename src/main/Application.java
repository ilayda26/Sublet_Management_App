
import java.time.LocalDateTime;

public class Application{

    //stores a student's application for a listing

    private int applicationId;
    private int studentId;
    private int listingId;
    private String status;
    private LocalDateTime appliedAt;
   

    public Application(
        int applicationId, int studentId, int listingId

    ){
        this.listingId = listingId;
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.listingId = listingId;
        this.status = "Pending";
        this.appliedAt= LocalDateTime.now();
      


    }

     public int getApplicationId(){
        return applicationId;
    }

    public int getStudentId(){
        return studentId;
    }

    public int  getListingId(){
        return listingId ;
    }

    public String getStatus(){
        return status;
    }

     

    public LocalDateTime getAppliedAt(){
        return appliedAt;
    }

    

    //used when a lister to accept or delete an application

    public void setStatus(String status) {
        this.status = status;
    }

    



   







}