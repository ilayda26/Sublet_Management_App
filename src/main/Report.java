import java.time.LocalDateTime;

public class Report{
    private int reportId;
    private String reason;
    private String status;
    private LocalDateTime date;

    public Report(int reportId, String reason, String status, LocalDateTime date){
        this.reportId = reportId;
        this.reason = reason;
        this.status = status;
        this.date = date;
    }

    public int getReportId(){
        return reportId;
    }

    public void setReportId(int reportId){
        this.reportId = reportId;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason = reason;
    }
    
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;  
    }

    public LocalDateTime getDate(){
        return date;
    }
    
    public void setDate(LocalDateTime date){
        this.date = date;
    }
}


