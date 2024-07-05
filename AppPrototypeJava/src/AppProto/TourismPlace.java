package AppProto;

public class TourismPlace {

    private String name;
    private String location;
    private String object;
    private String description;
    private String openTime;
    private String rating;
    private String imageUrl;

    public TourismPlace(String name, String location, String description, String openTime, String rating, String object, String imageUrl) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.openTime = openTime;
        this.rating = rating;
        this.object = object;      
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenTime() {
        return openTime;
    }
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }


}
