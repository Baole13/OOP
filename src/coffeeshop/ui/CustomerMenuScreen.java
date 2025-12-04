package coffeeshop.ui;
import coffeeshop.database.FileMenuRepository;
import coffeeshop.database.FileOrderRepository;
import coffeeshop.database.MenuRepository;
import coffeeshop.database.OrderRepository;
import coffeeshop.model.MenuItem;
import coffeeshop.model.Order;
import coffeeshop.model.OrderItem;
import coffeeshop.util.FormatUtil;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CustomerMenuScreen extends JFrame {
    private String username;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private JTable menuTable;
    private JTable cartTable;
    private DefaultTableModel menuModel;
    private DefaultTableModel cartModel;
    private List<OrderItem> cart;
    private JLabel totalLabel;
    
    public CustomerMenuScreen(String username) {
        this(username, new FileMenuRepository(), new FileOrderRepository());
    }

    public CustomerMenuScreen(String username, MenuRepository menuRepository, OrderRepository orderRepository) {
        this.username = username;
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.cart = new ArrayList<>();
        
        setTitle("Menu - Khách hàng: " + username);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = Theme.gradientPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel menuPanel = new JPanel(new BorderLayout());
        Theme.styleCard(menuPanel);
        menuPanel.setBorder(BorderFactory.createTitledBorder("Danh sách món ăn"));
        
        String[] menuColumns = {"ID", "Tên món", "Giá"};
        menuModel = new DefaultTableModel(menuColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        menuTable = new JTable(menuModel);
        Theme.styleTable(menuTable);
        menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuTable.getColumnModel().getColumn(2)
                 .setCellRenderer(new CurrencyCellRenderer());
        loadMenu();
        
        JScrollPane menuScroll = new JScrollPane(menuTable);
        menuScroll.setPreferredSize(new Dimension(400, 300));
        menuPanel.add(menuScroll, BorderLayout.CENTER);
        
        JButton addToCartButton = new JButton("Thêm vào giỏ hàng");
        Theme.stylePrimaryButton(addToCartButton);
        addToCartButton.addActionListener(e -> {
            int selectedRow = menuTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn món ăn!", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String name = (String) menuModel.getValueAt(selectedRow, 1);
            double price = (Double) menuModel.getValueAt(selectedRow, 2);
            
            boolean found = false;
            for (OrderItem item : cart) {
                if (item.getItemName().equals(name)) {
                    item.setQuantity(item.getQuantity() + 1);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                cart.add(new OrderItem(name, 1, price));
            }
            
            updateCartTable();
        });
        menuPanel.add(addToCartButton, BorderLayout.SOUTH);
        
        JPanel cartPanel = new JPanel(new BorderLayout());
        Theme.styleCard(cartPanel);
        cartPanel.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));
        
        String[] cartColumns = {"Tên món", "Số lượng", "Giá", "Thành tiền"};
        cartModel = new DefaultTableModel(cartColumns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable = new JTable(cartModel);
        Theme.styleTable(cartTable);
        cartTable.getColumnModel().getColumn(2)
                 .setCellRenderer(new CurrencyCellRenderer());
        cartTable.getColumnModel().getColumn(3)
                 .setCellRenderer(new CurrencyCellRenderer());
        JScrollPane cartScroll = new JScrollPane(cartTable);
        cartScroll.setPreferredSize(new Dimension(400, 300));
        cartPanel.add(cartScroll, BorderLayout.CENTER);
        
        JPanel cartButtonPanel = new JPanel(new FlowLayout());
        cartButtonPanel.setOpaque(false);
        JButton removeButton = new JButton("Xóa món");
        JButton placeOrderButton = new JButton("Đặt hàng");
        Theme.styleSecondaryButton(removeButton);
        Theme.stylePrimaryButton(placeOrderButton);
        
        removeButton.addActionListener(e -> {
            int selectedRow = cartTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn món để xóa!", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            cart.remove(selectedRow);
            updateCartTable();
        });
        
        placeOrderButton.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Giỏ hàng trống!", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Xác nhận đặt hàng?", 
                "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                Order order = new Order(orderRepository.getNextOrderId(), username);
                cart.forEach(order::addItem);
                
                orderRepository.addOrder(order);
                cart.clear();
                updateCartTable();
                
                JOptionPane.showMessageDialog(this, 
                    "Đặt hàng thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        cartButtonPanel.add(removeButton);
        cartButtonPanel.add(placeOrderButton);
        
        totalLabel = new JLabel("Tổng tiền: " + FormatUtil.formatCurrency(0));
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JPanel cartBottomPanel = new JPanel(new BorderLayout());
        cartBottomPanel.add(cartButtonPanel, BorderLayout.CENTER);
        cartBottomPanel.add(totalLabel, BorderLayout.SOUTH);
        cartPanel.add(cartBottomPanel, BorderLayout.SOUTH);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);
        JButton viewHistoryButton = new JButton("Xem lịch sử đơn hàng");
        JButton logoutButton = new JButton("Đăng xuất");
        Theme.styleSecondaryButton(viewHistoryButton);
        Theme.styleSecondaryButton(logoutButton);
        
        viewHistoryButton.addActionListener(e -> showOrderHistory());
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true);
        });
        
        bottomPanel.add(viewHistoryButton);
        bottomPanel.add(logoutButton);
        
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(menuPanel, BorderLayout.WEST);
        centerPanel.add(cartPanel, BorderLayout.EAST);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        updateCartTable();
    }
    
    private void loadMenu() {
        menuModel.setRowCount(0);
        List<MenuItem> menu = menuRepository.loadMenu();
        for (MenuItem item : menu) {
            menuModel.addRow(new Object[]{
                item.getId(),
                item.getName(),
                item.getPrice()
            });
        }
    }
    
    private void updateCartTable() {
        cartModel.setRowCount(0);
        double total = 0.0;
        
        for (OrderItem item : cart) {
            double subtotal = item.getSubtotal();
            total += subtotal;
            cartModel.addRow(new Object[]{
                item.getItemName(),
                item.getQuantity(),
                FormatUtil.formatCurrency(item.getPrice()),
                FormatUtil.formatCurrency(subtotal)
            });
        }
        
        totalLabel.setText("Tổng tiền: " + FormatUtil.formatCurrency(total));
    }
    
    private void showOrderHistory() {
        java.util.List<Order> orders = orderRepository.getOrdersByUsername(username);
        
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Bạn chưa có đơn hàng nào!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        JDialog historyDialog = new JDialog(this, "Lịch sử đơn hàng", true);
        historyDialog.setSize(600, 400);
        historyDialog.setLocationRelativeTo(this);
        
        String[] columns = {"Mã đơn", "Thời gian", "Tổng tiền", "Món hàng"};
        DefaultTableModel historyModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Order order : orders) {
            historyModel.addRow(new Object[]{
                order.getOrderId(),
                order.getOrderTime().toString(),
                FormatUtil.formatCurrency(order.getTotalAmount()),
                summarizeItems(order)
            });
        }
        
        JTable historyTable = new JTable(historyModel);
        historyTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        historyTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = historyTable.getSelectedRow();
                if (selectedRow != -1) {
                    Order order = orders.get(selectedRow);
                    showOrderDetails(order);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(historyTable);
        historyDialog.add(scrollPane);
        historyDialog.setVisible(true);
    }
    
    private void showOrderDetails(Order  order) {
        StringBuilder details = new StringBuilder();
        details.append("Mã đơn: ").append(order.getOrderId()).append("\n");
        details.append("Thời gian: ").append(order.getOrderTime()).append("\n\n");
        details.append("Chi tiết đơn hàng:\n");
        
        for (OrderItem item : order.getItems()) {
            details.append("- ").append(item.getItemName())
                   .append(" x ").append(item.getQuantity())
                   .append(" = ").append(FormatUtil.formatCurrency(item.getSubtotal()))
                   .append("\n");
        }
        
        details.append("\nTổng tiền: ").append(FormatUtil.formatCurrency(order.getTotalAmount()));
        
        JOptionPane.showMessageDialog(this, details.toString(), 
            "Chi tiết đơn hàng", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private String summarizeItems(Order order) {
        StringBuilder sb = new StringBuilder();
        java.util.List<OrderItem> its = order.getItems();
        for (int i = 0; i < its.size(); i++) {
            OrderItem it = its.get(i);
            sb.append(it.getItemName()).append(" x ").append(it.getQuantity());
            if (i < its.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    private static class CurrencyCellRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof Number) {
                double amount = ((Number) value).doubleValue();
                setText(FormatUtil.formatCurrency(amount));
            } else {
                super.setValue(value);
            }
        }
    }
}

