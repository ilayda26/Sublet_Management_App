import java.time.LocalDateTime;

public class Report{

    //Stores a report made about a listing

    private int reportId;
    private int studentId;
    private int listingId;
    private String reason;
    private String status;
    private LocalDateTime reportedAt;

    public Report(int reportId, int studentId, int listingId, String reason){
        this.reportId = reportId;
        this.studentId = studentId;
        this.listingId = listingId;
        this.reason = reason ;
        this.status = "Pending";
        this.reportedAt = LocalDateTime.now();


    }

    public int getReportId(){
        return reportId;
    }

     public int getStudentId(){
        return studentId;
    }

     public int getListingId(){
        return listingId;
    }

     public String getReason(){
        return reason;
    }

     public String getStatus(){
        return status;
    }

    public LocalDateTime getReportedAt(){
        return reportedAt;
    }


    //Allows the admin to resolve a report

    public void setStatus(String status){
        this.status = status;
    }
}