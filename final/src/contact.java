import java.awt.*;
import javax.swing.*;

public class contact {

    static String[] contacts = new String[100];
    static int count = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Contact List");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // ui components
        JTextField nameField = new JTextField(20);
        JButton addBtn = new JButton("Add Contact");
        JButton searchBtn = new JButton("Search");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        
        JTextArea display = new JTextArea(15, 30);
        display.setEditable(false);

        // add contact function 
        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                contacts[count] = name;
                count++;
                display.setText(showContacts());
                nameField.setText("");
            }
        });

        // search function
        searchBtn.addActionListener(e -> {
            String name = nameField.getText();
            String result = "Not found.";
            for (int i = 0; i < count; i++) {
                if (contacts[i].equalsIgnoreCase(name)) {
                    result = "Found at index: " + i;
                    break;
                }
            }
            JOptionPane.showMessageDialog(frame, result);
        });

        // UPDATE CONTACT
        updateBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(JOptionPane.showInputDialog("Enter index to update:"));
                if (index >= 0 && index < count) {
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    contacts[index] = newName;
                    display.setText(showContacts());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        // DELETE CONTACT
        deleteBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(JOptionPane.showInputDialog("Enter index to delete:"));
                if (index >= 0 && index < count) {
                    for (int i = index; i < count - 1; i++) {
                        contacts[i] = contacts[i + 1];
                    }
                    count--;
                    display.setText(showContacts());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        // ADD COMPONENTS TO JFrame
        frame.add(new JLabel("Enter name:"));
        frame.add(nameField);
        frame.add(addBtn);
        frame.add(searchBtn);
        frame.add(updateBtn);
        frame.add(deleteBtn);
        frame.add(new JScrollPane(display));

        frame.setVisible(true);
    }

    // para sa display ng contact
    static String showContacts() {
        if (count == 0) return "No contacts available.\n";
        StringBuilder sb = new StringBuilder("Contacts:\n");
        for (int i = 0; i < count; i++) {
            sb.append(i).append(". ").append(contacts[i]).append("\n");
        }
        return sb.toString();
    }
}
