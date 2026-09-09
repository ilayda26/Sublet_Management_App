import java.time.LocalDate;
import java.time.LocalDateTime;

public class Listing{

    //Main details of a sublet

    private int listingId;
    private int listerId;
    private String title;
    private String borough;
    private String neighbourhood;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String roomType;
    private String description;
    private boolean available;

    public Listing(
        int listingId, int listerId, String title, String borough, String neighbourhood, double price, LocalDate startDate, LocalDate endDate, String roomType, String description

    ){
        this.listingId = listingId;
        this.listerId = listerId;
        this.title = title;
        this.borough = borough;
        this.neighbourhood = neighbourhood;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomType = roomType;
        this.description = description;
        this.available = true;


    }

     public int getlistingId(){
        return listingId;
    }

    public int getListerId(){
        return listerId ;
    }

    public String getTitle(){
        return title ;
    }

    public String getBorough(){
        return borough ;
    }

     public String getNeighbourhood(){
        return neighbourhood;
    }

     public double getPrice(){
        return price;
    }

    public LocalDate getStartDate(){
        return startDate ;
    }

    public LocalDate getEndDate(){
        return endDate ;
    }

     public String getRoomType(){
        return roomType;
    }

     public String getDescription(){
        return description ;
    }

     public boolean isAvailable(){
        return available ;
    }

    //used when a lister edits a listing

    public void setPrice(double price) {
        this.price = price;
    }

     public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }



   







}