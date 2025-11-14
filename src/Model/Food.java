/*
 * Food Model for Food Delivery App
 */
package Model;

/**
 *
 * @author unifvipereira
 */
public class Food {
    private int id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private int ratingCount;
    private String category;
    private String imagePath;
    private boolean isAlcoholic;
    
    public Food() {
    }
    
    public Food(int id, String name, String description, double price, double rating, int ratingCount, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.category = category;
        this.imagePath = null;
        this.isAlcoholic = false;
    }
    
    public Food(int id, String name, String description, double price, double rating, int ratingCount, String category, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.category = category;
        this.imagePath = imagePath;
        this.isAlcoholic = false;
    }
    
    public Food(String name, String description, double price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = 0.0;
        this.ratingCount = 0;
        this.category = category;
        this.isAlcoholic = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean isAlcoholic) {
        this.isAlcoholic = isAlcoholic;
    }
    
    public void addRating(int stars) {
        if (stars >= 0 && stars <= 5) {
            double totalRating = (rating * ratingCount) + stars;
            ratingCount++;
            rating = totalRating / ratingCount;
        }
    }
    
    @Override
    public String toString() {
        return name + " - R$ " + String.format("%.2f", price);
    }
}

