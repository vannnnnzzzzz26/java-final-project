import java.awt.*;
import javax.swing.*;

public class contact extends JPanel {

    static String[] contacts = new String[100];
    static int count = 0;

    private JTextArea display;
    private JTextField nameField;

    public contact() {
        setLayout(new BorderLayout());

        // Input + Buttons
        JPanel topPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Manage Contacts"));

        nameField = new JTextField();
        JButton addBtn = new JButton("Add Contact");
        JButton searchBtn = new JButton("Search");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        topPanel.add(new JLabel("Name:"));
        topPanel.add(nameField);
        topPanel.add(addBtn);
        topPanel.add(searchBtn);
        topPanel.add(updateBtn);
        topPanel.add(deleteBtn);

        add(topPanel, BorderLayout.NORTH);

        //  Display Contacts
        display = new JTextArea(15, 30);
        display.setEditable(false);
        add(new JScrollPane(display), BorderLayout.CENTER);

        //  ACTIONS 

        // ADD CONTACT
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                contacts[count] = name;
                count++;
                display.setText(showContacts());
                nameField.setText("");
            }
        });

        // SEARCH CONTACT
        searchBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String result = "Not found.";
            for (int i = 0; i < count; i++) {
                if (contacts[i].equalsIgnoreCase(name)) {
                    result = "Found at index: " + i;
                    break;
                }
            }
            JOptionPane.showMessageDialog(this, result);
        });

        // UPDATE CONTACT
        updateBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter index to update:"));
                if (index >= 0 && index < count) {
                    String newName = JOptionPane.showInputDialog(this, "Enter new name:");
                    contacts[index] = newName;
                    display.setText(showContacts());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        // DELETE CONTACT
        deleteBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter index to delete:"));
                if (index >= 0 && index < count) {
                    for (int i = index; i < count - 1; i++) {
                        contacts[i] = contacts[i + 1];
                    }
                    count--;
                    display.setText(showContacts());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });
    }

    // DISPLAY CONTACTS
    static String showContacts() {
        if (count == 0) return "No contacts available.\n";
        StringBuilder sb = new StringBuilder("Contacts:\n");
        for (int i = 0; i < count; i++) {
            sb.append(i).append(". ").append(contacts[i]).append("\n");
        }
        return sb.toString();
    }
}
