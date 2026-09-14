import java.time.LocalDateTime;

public class Report {

    private int reportId;
    private int studentId;
    private int listingId;

    private String reason;
    private String status;

    private LocalDateTime date;
    private LocalDateTime reportedAt;


    public Report(
            int reportId,
            String reason,
            String status,
            LocalDateTime date) {

        this.reportId = reportId;
        this.reason = reason;
        this.status = status;
        this.date = date;
        this.reportedAt = date;
    }


    public Report(
            int reportId,
            int studentId,
            int listingId,
            String reason) {

        this.reportId = reportId;
        this.studentId = studentId;
        this.listingId = listingId;
        this.reason = reason;

        this.status = "Pending";
        this.reportedAt = LocalDateTime.now();
        this.date = this.reportedAt;
    }


    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
        this.reportedAt = date;
    }


    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
        this.date = reportedAt;
    }


    public void submitReport() {
        status = "Submitted";
    }

    public void reviewReport() {
        status = "Under Review";
    }

    public void updateStatus(String newStatus) {
        status = newStatus;
    }
}