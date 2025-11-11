/*
 * Main Food Menu Window - Modern Mobile Design
 */
package View;

import Controller.FoodMenuController;
import Model.Food;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author unifvipereira
 */
public class FoodMenu extends javax.swing.JFrame {

    private FoodMenuController controller;
    private JPanel foodCardsPanel;
    private JScrollPane scrollPane;
    private Food selectedFood;
    private Color redTheme = new Color(220, 20, 60); // Crimson red
    private Color lightRed = new Color(255, 240, 240);
    private Color darkRed = new Color(180, 0, 40);
    
    /**
     * Creates new form FoodMenu
     */
    public FoodMenu() {
        initComponents();
        controller = new FoodMenuController(this);
        controller.loadFoods();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        // Mobile proportions: 375x812 (iPhone-like)
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iFood - Menu");
        setResizable(false);
        setSize(400, 800);
        getContentPane().setBackground(lightRed);
        getContentPane().setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(redTheme);
        headerPanel.setPreferredSize(new Dimension(400, 80));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel titleLabel = new JLabel("🍔 iFood");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JLabel cartLabel = new JLabel("🛒 0");
        cartLabel.setName("cartCount");
        cartLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cartLabel.setForeground(Color.WHITE);
        cartLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        headerPanel.add(cartLabel, BorderLayout.EAST);
        
        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(lightRed);
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JTextField searchField = new JTextField(20);
        searchField.setName("txtSearch");
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchField.setPreferredSize(new Dimension(280, 40));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(redTheme, 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        searchField.setBackground(Color.WHITE);
        
        JButton searchBtn = new JButton("🔍");
        searchBtn.setName("btnSearch");
        searchBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        searchBtn.setPreferredSize(new Dimension(50, 40));
        searchBtn.setBackground(redTheme);
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setBorderPainted(false);
        searchBtn.setFocusPainted(false);
        searchBtn.addActionListener(e -> controller.searchFoods());
        
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        
        // Food Cards Panel (Scrollable)
        foodCardsPanel = new JPanel();
        foodCardsPanel.setLayout(new BoxLayout(foodCardsPanel, BoxLayout.Y_AXIS));
        foodCardsPanel.setBackground(lightRed);
        foodCardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        scrollPane = new JScrollPane(foodCardsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBackground(lightRed);
        
        // Action Buttons Panel
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(lightRed);
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
        JButton addBtn = new JButton("➕ Adicionar");
        addBtn.setName("btnAddToCart");
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addBtn.setPreferredSize(new Dimension(170, 45));
        addBtn.setBackground(redTheme);
        addBtn.setForeground(Color.WHITE);
        addBtn.setBorderPainted(false);
        addBtn.setFocusPainted(false);
        addBtn.addActionListener(e -> controller.addToCart());
        
        JButton detailsBtn = new JButton("ℹ️ Detalhes");
        detailsBtn.setName("btnViewDetails");
        detailsBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        detailsBtn.setPreferredSize(new Dimension(170, 45));
        detailsBtn.setBackground(darkRed);
        detailsBtn.setForeground(Color.WHITE);
        detailsBtn.setBorderPainted(false);
        detailsBtn.setFocusPainted(false);
        detailsBtn.addActionListener(e -> controller.viewDetails());
        
        JButton cartBtn = new JButton("🛒 Carrinho");
        cartBtn.setName("btnViewCart");
        cartBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cartBtn.setPreferredSize(new Dimension(340, 45));
        cartBtn.setBackground(redTheme);
        cartBtn.setForeground(Color.WHITE);
        cartBtn.setBorderPainted(false);
        cartBtn.setFocusPainted(false);
        cartBtn.addActionListener(e -> controller.viewCart());
        
        actionPanel.add(addBtn);
        actionPanel.add(detailsBtn);
        actionPanel.add(cartBtn);
        
        // Main content panel (search + scroll)
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(lightRed);
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add components to main frame
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(actionPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }
    
    public void displayFoods(List<Food> foods) {
        foodCardsPanel.removeAll();
        selectedFood = null;
        
        for (Food food : foods) {
            JPanel card = createFoodCard(food);
            foodCardsPanel.add(card);
            foodCardsPanel.add(Box.createVerticalStrut(15));
        }
        
        foodCardsPanel.revalidate();
        foodCardsPanel.repaint();
    }
    
    private JPanel createFoodCard(Food food) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(redTheme, 2),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        card.setPreferredSize(new Dimension(350, 140));
        card.setMaximumSize(new Dimension(350, 140));
        
        // Add click listener
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectCard(card);
                selectedFood = food;
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(255, 250, 250));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (selectedFood != food) {
                    card.setBackground(Color.WHITE);
                }
            }
        });
        
        // Image Panel
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                
                ImageIcon icon = null;
                if (food.getImagePath() != null && !food.getImagePath().isEmpty()) {
                    File imgFile = new File(food.getImagePath());
                    if (imgFile.exists()) {
                        icon = new ImageIcon(food.getImagePath());
                    }
                }
                
                if (icon == null) {
                    // Default placeholder - red gradient
                    g2d.setColor(redTheme);
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Segoe UI", Font.BOLD, 24));
                    String emoji = getFoodEmoji(food.getCategory());
                    int emojiWidth = g2d.getFontMetrics().stringWidth(emoji);
                    g2d.drawString(emoji, (getWidth() - emojiWidth) / 2, getHeight() / 2 + 8);
                } else {
                    Image img = icon.getImage();
                    Image scaled = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g2d.drawImage(scaled, 0, 0, null);
                }
            }
        };
        imagePanel.setPreferredSize(new Dimension(110, 110));
        imagePanel.setBackground(redTheme);
        
        // Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        JLabel nameLabel = new JLabel(food.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(redTheme);
        
        JLabel descLabel = new JLabel("<html><body style='width: 200px'>" + 
            (food.getDescription().length() > 60 ? 
                food.getDescription().substring(0, 60) + "..." : 
                food.getDescription()) + "</body></html>");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(new Color(80, 80, 80));
        
        JLabel priceLabel = new JLabel("R$ " + String.format("%.2f", food.getPrice()));
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        priceLabel.setForeground(redTheme);
        
        JLabel ratingLabel = new JLabel("⭐ " + String.format("%.1f", food.getRating()) + 
            " (" + food.getRatingCount() + ")");
        ratingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        ratingLabel.setForeground(new Color(100, 100, 100));
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(ratingLabel);
        
        card.add(imagePanel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private String getFoodEmoji(String category) {
        if (category == null) return "🍽️";
        switch (category.toLowerCase()) {
            case "pizza": return "🍕";
            case "hambúrguer": return "🍔";
            case "japonês": return "🍣";
            case "salada": return "🥗";
            case "italiano": return "🍝";
            case "mexicano": return "🌮";
            case "brasileiro": return "🍖";
            default: return "🍽️";
        }
    }
    
    private void selectCard(JPanel card) {
        // Reset all cards
        for (java.awt.Component comp : foodCardsPanel.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(Color.WHITE);
            }
        }
        // Highlight selected
        card.setBackground(new Color(255, 230, 230));
    }
    
    public Food getSelectedFood() {
        return selectedFood;
    }
    
    public JTextField getTxtSearch() {
        return (JTextField) findComponentByName("txtSearch");
    }
    
    public JButton getBtnAddToCart() {
        return (JButton) findComponentByName("btnAddToCart");
    }
    
    public JButton getBtnViewDetails() {
        return (JButton) findComponentByName("btnViewDetails");
    }
    
    public JButton getBtnViewCart() {
        return (JButton) findComponentByName("btnViewCart");
    }
    
    public JButton getBtnSearch() {
        return (JButton) findComponentByName("btnSearch");
    }
    
    private java.awt.Component findComponentByName(String name) {
        return findComponentByName(getContentPane(), name);
    }
    
    private java.awt.Component findComponentByName(java.awt.Container container, String name) {
        for (java.awt.Component comp : container.getComponents()) {
            if (name.equals(comp.getName())) {
                return comp;
            }
            if (comp instanceof java.awt.Container) {
                java.awt.Component found = findComponentByName((java.awt.Container) comp, name);
                if (found != null) return found;
            }
        }
        return null;
    }
    
    public void updateCartCount(int count) {
        JLabel cartLabel = (JLabel) findComponentByName("cartCount");
        if (cartLabel != null) {
            cartLabel.setText("🛒 " + count);
        }
    }
    
    // Legacy methods for compatibility
    public javax.swing.JList<Food> getFoodList() {
        return null; // Not used anymore
    }
    
    public javax.swing.DefaultListModel<Food> getFoodListModel() {
        return null; // Not used anymore
    }
}
