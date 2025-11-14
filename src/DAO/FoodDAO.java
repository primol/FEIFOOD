/*
 * FoodDAO - Simple PostgreSQL integration
 */
package DAO;

import Model.Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author unifvipereira
 */
public class FoodDAO {
    private Connection conexao;
    
    public FoodDAO() {
        Conexao con = new Conexao();
        try {
            this.conexao = con.getConnection();
            if (isTableEmpty()) {
                populateInitialData();
                insertFoodFromList();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            // Fallback to in-memory if DB fails
        }
    }

    private boolean isTableEmpty() throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbfood";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) == 0;
    }

    private void insertFoodFromList() throws SQLException {
        String sql = "INSERT INTO tbfood (id, nome, descricao, preco, avaliacao, num_avaliacoes, categoria, imagem, is_alcoholic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        for (Food food : foodList) {
            stmt.setInt(1, food.getId());
            stmt.setString(2, food.getName());
            stmt.setString(3, food.getDescription());
            stmt.setDouble(4, food.getPrice());
            stmt.setDouble(5, food.getRating());
            stmt.setInt(6, food.getRatingCount());
            stmt.setString(7, food.getCategory());
            stmt.setString(8, food.getImagePath());
            stmt.setBoolean(9, food.isAlcoholic());
            stmt.addBatch();
        }
        stmt.executeBatch();
    }
    
    // In-memory fallback (if database not available)
    private static List<Food> foodList = new ArrayList<>();
    private static int nextId = 1;
    
    private void populateInitialData() {
        // Sample foods
        foodList.add(new Food(nextId++, "Pizza Margherita", "Pizza clássica com molho de tomate, mussarela e manjericão", 35.90, 4.5, 20, "Pizza", "images/pizza.jpg"));
        foodList.add(new Food(nextId++, "Hambúrguer Artesanal", "Hambúrguer com carne artesanal, queijo, alface e tomate", 28.50, 4.8, 15, "Hambúrguer", "images/hamburguer.jpg"));
        foodList.add(new Food(nextId++, "Sushi Combo", "Combo com 12 peças de sushi variado", 45.00, 4.7, 30, "Japonês", "images/sushi.jpg"));
        foodList.add(new Food(nextId++, "Salada Caesar", "Salada fresca com frango grelhado e molho caesar", 22.90, 4.3, 12, "Salada", "images/salada.jpg"));
        foodList.add(new Food(nextId++, "Lasanha à Bolonhesa", "Lasanha tradicional com molho bolonhesa e queijo", 32.00, 4.6, 25, "Italiano", "images/lasanha.jpg"));
        foodList.add(new Food(nextId++, "Taco Mexicano", "Tacos com carne, queijo, alface e molho especial", 18.90, 4.4, 18, "Mexicano", "images/taco.jpg"));
        foodList.add(new Food(nextId++, "Frango Grelhado", "Peito de frango grelhado com arroz e batata", 26.50, 4.5, 22, "Brasileiro", "images/frango.jpg"));
        foodList.add(new Food(nextId++, "Ramen Tradicional", "Ramen com caldo de porco, ovos e noodles", 38.00, 4.9, 28, "Japonês", "images/ramen.jpg"));
        Food heineken = new Food(nextId++, "Heineken", "Cerveja Heineken Long Neck 330ml", 8.00, 4.9, 100, "Bebida", "images/heineken.jpg");
        heineken.setAlcoholic(true);
        foodList.add(heineken);
    }
    
    public List<Food> getAllFoods() {
        if (conexao != null) {
            try {
                return getAllFoodsFromDB();
            } catch (SQLException e) {
                System.err.println("Erro ao buscar alimentos: " + e.getMessage());
            }
        }
        // Fallback to in-memory list
        return new ArrayList<>(foodList);
    }
    
    private List<Food> getAllFoodsFromDB() throws SQLException {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM tbfood ORDER BY nome";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String imagePath = null;
            try {
                imagePath = rs.getString("imagem");
            } catch (SQLException e) {
                // Column might not exist yet
            }
            Food food = new Food(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                rs.getDouble("avaliacao"),
                rs.getInt("num_avaliacoes"),
                rs.getString("categoria"),
                imagePath
            );
            try {
                food.setAlcoholic(rs.getBoolean("is_alcoholic"));
            } catch (SQLException e) {
                // Column might not exist yet
            }
            foods.add(food);
        }
        return foods;
    }
    
    public List<Food> searchFoods(String searchTerm) {
        if (conexao != null) {
            try {
                return searchFoodsFromDB(searchTerm);
            } catch (SQLException e) {
                System.err.println("Erro ao buscar alimentos: " + e.getMessage());
            }
        }
        
        // Fallback to in-memory search
        List<Food> results = new ArrayList<>();
        String term = searchTerm.toLowerCase();
        for (Food food : foodList) {
            if (food.getName().toLowerCase().contains(term) ||
                food.getDescription().toLowerCase().contains(term) ||
                food.getCategory().toLowerCase().contains(term)) {
                results.add(food);
            }
        }
        return results;
    }
    
    private List<Food> searchFoodsFromDB(String searchTerm) throws SQLException {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM tbfood WHERE LOWER(nome) LIKE ? OR LOWER(descricao) LIKE ? OR LOWER(categoria) LIKE ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        String term = "%" + searchTerm.toLowerCase() + "%";
        stmt.setString(1, term);
        stmt.setString(2, term);
        stmt.setString(3, term);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String imagePath = null;
            try {
                imagePath = rs.getString("imagem");
            } catch (SQLException e) {
                // Column might not exist yet
            }
            Food food = new Food(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                rs.getDouble("avaliacao"),
                rs.getInt("num_avaliacoes"),
                rs.getString("categoria"),
                imagePath
            );
            try {
                food.setAlcoholic(rs.getBoolean("is_alcoholic"));
            } catch (SQLException e) {
                // Column might not exist yet
            }
            foods.add(food);
        }
        return foods;
    }
    
    public Food getFoodById(int id) {
        if (conexao != null) {
            try {
                return getFoodByIdFromDB(id);
            } catch (SQLException e) {
                System.err.println("Erro ao buscar alimento: " + e.getMessage());
            }
        }
        
        for (Food food : foodList) {
            if (food.getId() == id) {
                return food;
            }
        }
        return null;
    }
    
    private Food getFoodByIdFromDB(int id) throws SQLException {
        String sql = "SELECT * FROM tbfood WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            String imagePath = null;
            try {
                imagePath = rs.getString("imagem");
            } catch (SQLException e) {
                // Column might not exist yet
            }
            Food food = new Food(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                rs.getDouble("avaliacao"),
                rs.getInt("num_avaliacoes"),
                rs.getString("categoria"),
                imagePath
            );
            try {
                food.setAlcoholic(rs.getBoolean("is_alcoholic"));
            } catch (SQLException e) {
                // Column might not exist yet
            }
            return food;
        }
        return null;
    }
    
    public void rateFood(int foodId, int stars) {
        if (conexao != null) {
            try {
                rateFoodInDB(foodId, stars);
                return;
            } catch (SQLException e) {
                System.err.println("Erro ao avaliar alimento: " + e.getMessage());
            }
        }
        
        // Fallback to in-memory
        Food food = getFoodById(foodId);
        if (food != null) {
            food.addRating(stars);
        }
    }
    
    private void rateFoodInDB(int foodId, int stars) throws SQLException {
        // Get current rating
        Food food = getFoodByIdFromDB(foodId);
        if (food == null) return;
        
        double currentRating = food.getRating();
        int currentCount = food.getRatingCount();
        
        // Calculate new rating
        double totalRating = (currentRating * currentCount) + stars;
        int newCount = currentCount + 1;
        double newRating = totalRating / newCount;
        
        // Update database
        String sql = "UPDATE tbfood SET avaliacao = ?, num_avaliacoes = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setDouble(1, newRating);
        stmt.setInt(2, newCount);
        stmt.setInt(3, foodId);
        stmt.executeUpdate();
    }
}
