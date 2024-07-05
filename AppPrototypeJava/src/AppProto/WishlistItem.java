package AppProto;

public class WishlistItem {
    private String imagePath;
    private String title;
    private String description;

    public WishlistItem(String imagePath, String title, String description) {
        this.imagePath = imagePath;
        this.title = title;
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}
