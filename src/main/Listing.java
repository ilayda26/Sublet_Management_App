import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Listing {

    private int listingId;
    private int listerId;
    private String title;
    private String location;
    private String borough;
    private String neighbourhood;
    private BigDecimal price;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String roomType;
    private String status;
    private boolean available;
    private LocalDateTime createdAt;

    public Listing(
            int listingId,
            String title,
            String location,
            BigDecimal price,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            String status,
            LocalDateTime createdAt) {

        this.listingId = listingId;
        this.title = title;
        this.location = location;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
        this.available = "Available".equalsIgnoreCase(status);
    }

    public Listing(
            int listingId,
            int listerId,
            String title,
            String borough,
            String neighbourhood,
            double price,
            LocalDate startDate,
            LocalDate endDate,
            String roomType,
            String description) {

        this.listingId = listingId;
        this.listerId = listerId;
        this.title = title;
        this.borough = borough;
        this.neighbourhood = neighbourhood;
        this.location = borough + ", " + neighbourhood;
        this.price = BigDecimal.valueOf(price);
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomType = roomType;
        this.description = description;
        this.available = true;
        this.status = "Available";
        this.createdAt = LocalDateTime.now();
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getListerId() {
        return listerId;
    }

    public void setListerId(int listerId) {
        this.listerId = listerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.available = "Available".equalsIgnoreCase(status);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;

        if (available) {
            this.status = "Available";
        } else {
            this.status = "Unavailable";
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}