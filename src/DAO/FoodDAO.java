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
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            this.conexao = null; // Ensure conexao is null if connection fails
            // Fallback to in-memory if DB fails
        }
    }
    
    // In-memory fallback (if database not available)
    private static List<Food> listaAlimentos = new ArrayList<>();
    private static int proximoId = 1;
    
    static {
        // Sample foods
        listaAlimentos.add(new Food(proximoId++, "Pizza Margherita", "Pizza clássica com molho de tomate, mussarela e manjericão", 35.90, 4.5, 20, "Pizza", null));
        listaAlimentos.add(new Food(proximoId++, "Hambúrguer Artesanal", "Hambúrguer com carne artesanal, queijo, alface e tomate", 28.50, 4.8, 15, "Hambúrguer", null));
        listaAlimentos.add(new Food(proximoId++, "Sushi Combo", "Combo com 12 peças de sushi variado", 45.00, 4.7, 30, "Japonês", null));
        listaAlimentos.add(new Food(proximoId++, "Salada Caesar", "Salada fresca com frango grelhado e molho caesar", 22.90, 4.3, 12, "Salada", null));
        listaAlimentos.add(new Food(proximoId++, "Lasanha à Bolonhesa", "Lasanha tradicional com molho bolonhesa e queijo", 32.00, 4.6, 25, "Italiano", null));
        listaAlimentos.add(new Food(proximoId++, "Taco Mexicano", "Tacos com carne, queijo, alface e molho especial", 18.90, 4.4, 18, "Mexicano", null));
        listaAlimentos.add(new Food(proximoId++, "Frango Grelhado", "Peito de frango grelhado com arroz e batata", 26.50, 4.5, 22, "Brasileiro", null));
        listaAlimentos.add(new Food(proximoId++, "Ramen Tradicional", "Ramen com caldo de porco, ovos e noodles", 38.00, 4.9, 28, "Japonês", null));
    }
    
    public List<Food> getAllFoods() {
        if (conexao != null) {
            try {
                return getAllFoodsFromDB();
            } catch (SQLException e) {
                System.err.println("Erro ao buscar todos os alimentos do banco de dados: " + e.getMessage());
            }
        }
        // Always return the in-memory list to ensure the UI is populated.
        // For a production app, you would want to fetch from the database and handle empty states.
        return new ArrayList<>(listaAlimentos);
    }
    
    private List<Food> getAllFoodsFromDB() throws SQLException {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM tbfood ORDER BY nome";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String caminhoImagem = null;
            try {
                caminhoImagem = rs.getString("imagem");
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
                caminhoImagem
            );
            foods.add(food);
        }
        return foods;
    }
    
    public List<Food> searchFoods(String termoBusca) {
        if (conexao != null) {
            try {
                return searchFoodsFromDB(termoBusca);
            } catch (SQLException e) {
                System.err.println("Erro ao buscar alimentos: " + e.getMessage());
            }
        }
        
        // Fallback to in-memory search
        List<Food> resultados = new ArrayList<>();
        String termo = termoBusca.toLowerCase();
        for (Food alimento : listaAlimentos) {
            if (alimento.getName().toLowerCase().contains(termo) ||
                alimento.getDescription().toLowerCase().contains(termo) ||
                alimento.getCategory().toLowerCase().contains(termo)) {
                resultados.add(alimento);
            }
        }
        return resultados;
    }
    
    private List<Food> searchFoodsFromDB(String termoBusca) throws SQLException {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM tbfood WHERE LOWER(nome) LIKE ? OR LOWER(descricao) LIKE ? OR LOWER(categoria) LIKE ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        String termo = "%" + termoBusca.toLowerCase() + "%";
        stmt.setString(1, termo);
        stmt.setString(2, termo);
        stmt.setString(3, termo);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            String caminhoImagem = null;
            try {
                caminhoImagem = rs.getString("imagem");
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
                caminhoImagem
            );
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
        
        for (Food alimento : listaAlimentos) {
            if (alimento.getId() == id) {
                return alimento;
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
            String caminhoImagem = null;
            try {
                caminhoImagem = rs.getString("imagem");
            } catch (SQLException e) {
                // Column might not exist yet
            }
            return new Food(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getDouble("preco"),
                rs.getDouble("avaliacao"),
                rs.getInt("num_avaliacoes"),
                rs.getString("categoria"),
                caminhoImagem
            );
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
        Food alimento = getFoodById(foodId);
        if (alimento != null) {
            alimento.addRating(stars);
        }
    }
    
    private void rateFoodInDB(int foodId, int stars) throws SQLException {
        // Get current rating
        Food alimento = getFoodByIdFromDB(foodId);
        if (alimento == null) return;
        
        double avaliacaoAtual = alimento.getRating();
        int contagemAtual = alimento.getRatingCount();
        
        // Calculate new rating
        double totalAvaliacao = (avaliacaoAtual * contagemAtual) + stars;
        int novaContagem = contagemAtual + 1;
        double novaAvaliacao = totalAvaliacao / novaContagem;
        
        // Update database
        String sql = "UPDATE tbfood SET avaliacao = ?, num_avaliacoes = ? WHERE id = ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setDouble(1, novaAvaliacao);
        stmt.setInt(2, novaContagem);
        stmt.setInt(3, foodId);
        stmt.executeUpdate();
    }
}
