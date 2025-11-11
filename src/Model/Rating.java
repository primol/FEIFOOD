package Model;

public class Rating {
    private int id;
    private int foodId;
    private int userId;
    private int rating;

    public Rating(int id, int foodId, int userId, int rating) {
        this.id = id;
        this.foodId = foodId;
        this.userId = userId;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
