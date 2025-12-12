import java.awt.*;
import javax.swing.*;

public class OrderQueue extends JPanel {

    static String[] orders = new String[100];
    static int front = 0; // index of first order
    static int rear = 0;  // index of next empty slot

    private JTextField orderField;
    private JTextArea display;

    public OrderQueue() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Fast-Food Orders"));

        orderField = new JTextField(20);
        JButton addBtn = new JButton("Place Order");
        JButton finishBtn = new JButton("Finish Order");
        JButton nextBtn = new JButton("Next Order");

        topPanel.add(new JLabel("Enter order:"));
        topPanel.add(orderField);
        topPanel.add(addBtn);
        topPanel.add(finishBtn);
        topPanel.add(nextBtn);

        add(topPanel, BorderLayout.NORTH);

        // CENTER PANEL Display Orders
        display = new JTextArea(15, 30);
        display.setEditable(false);
        add(new JScrollPane(display), BorderLayout.CENTER);


        // PLACE ORDER enqueue
        addBtn.addActionListener(e -> {
            String order = orderField.getText().trim();
            if (!order.isEmpty()) {
                if (rear < orders.length) {
                    orders[rear] = order;
                    rear++;
                    display.setText(showOrders());
                    orderField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Queue is full!");
                }
            }
        });

        // FINISH ORDER dequeue
        finishBtn.addActionListener(e -> {
            if (front < rear) {
                JOptionPane.showMessageDialog(this, "Finished Order: " + orders[front]);
                front++;
                display.setText(showOrders());
            } else {
                JOptionPane.showMessageDialog(this, "No orders to finish.");
            }
        });

        // NEXT ORDER peekand front
        nextBtn.addActionListener(e -> {
            if (front < rear) {
                JOptionPane.showMessageDialog(this, "Next Order: " + orders[front]);
            } else {
                JOptionPane.showMessageDialog(this, "No orders in queue.");
            }
        });

        // INITIAL DISPLAY
        display.setText(showOrders());
    }

    // DISPLAY CURRENT ORDERS
    static String showOrders() {
        if (front == rear) return "No orders in queue.\n";
        StringBuilder sb = new StringBuilder("Orders in Queue:\n");
        for (int i = front; i < rear; i++) {
            sb.append(i - front + 1).append(". ").append(orders[i]).append("\n");
        }
        return sb.toString();
    }
}
