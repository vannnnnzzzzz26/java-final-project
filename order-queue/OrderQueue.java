import java.awt.*;
import javax.swing.*;

public class OrderQueue {

    static String[] orders = new String[100];
    static int front = 0; // index of first order
    static int rear = 0;  // index of next empty slot

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fast-Food Order Queue");
        frame.setSize(400, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // UI components
        JTextField orderField = new JTextField(20);
        JButton addBtn = new JButton("Place Order");
        JButton finishBtn = new JButton("Finish Order");
        JButton nextBtn = new JButton("Next Order");
        JTextArea display = new JTextArea(15, 30);
        display.setEditable(false);

        // PLACE ORDER (enqueue)
        addBtn.addActionListener(e -> {
            String order = orderField.getText();
            if (!order.isEmpty()) {
                if (rear < orders.length) {
                    orders[rear] = order;
                    rear++;
                    display.setText(showOrders());
                    orderField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Queue is full!");
                }
            }
        });

        // FINISH ORDER (dequeue)
        finishBtn.addActionListener(e -> {
            if (front < rear) {
                JOptionPane.showMessageDialog(frame, "Finished Order: " + orders[front]);
                front++;
                display.setText(showOrders());
            } else {
                JOptionPane.showMessageDialog(frame, "No orders to finish.");
            }
        });

        // NEXT ORDER (peek/front)
        nextBtn.addActionListener(e -> {
            if (front < rear) {
                JOptionPane.showMessageDialog(frame, "Next Order: " + orders[front]);
            } else {
                JOptionPane.showMessageDialog(frame, "No orders in queue.");
            }
        });

        // ADD COMPONENTS TO FRAME
        frame.add(new JLabel("Enter order:"));
        frame.add(orderField);
        frame.add(addBtn);
        frame.add(finishBtn);
        frame.add(nextBtn);
        frame.add(new JScrollPane(display));

        frame.setLocationRelativeTo(null); // center the window
        frame.setVisible(true);
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
