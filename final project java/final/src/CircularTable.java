import java.awt.*;
import javax.swing.*;

class PersonNode {
    String name;
    PersonNode next;

    PersonNode(String name) {
        this.name = name;
        this.next = null;
    }
}

class CircularSeating {
    private PersonNode head;

    public void insertAtStart(String name) {
        PersonNode newNode = new PersonNode(name);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            PersonNode temp = head;
            while (temp.next != head) temp = temp.next;
            newNode.next = head;
            temp.next = newNode;
            head = newNode;
        }
    }

    public boolean insertBetween(String firstName, String secondName, String newName) {
        if (head == null) return false;
        PersonNode temp = head;
        do {
            if (temp.name.equals(firstName) && temp.next.name.equals(secondName)) {
                PersonNode newNode = new PersonNode(newName);
                newNode.next = temp.next;
                temp.next = newNode;
                return true;
            }
            temp = temp.next;
        } while (temp != head);
        return false;
    }

    public String traverseClockwise() {
        if (head == null) return "No people seated.";
        StringBuilder sb = new StringBuilder();
        PersonNode temp = head;
        do {
            sb.append(temp.name).append(" -> ");
            temp = temp.next;
        } while (temp != head);
        sb.append("(back to ").append(head.name).append(")");
        return sb.toString();
    }
}

public class CircularTable extends JPanel {

    private CircularSeating seating = new CircularSeating();
    private JTextArea displayArea;

    public CircularTable() {

        setLayout(new FlowLayout());

        JTextField nameField = new JTextField(20);

        JButton addStartBtn = new JButton("Add at Start");
        JButton addBetweenBtn = new JButton("Add Between");
        JButton traverseBtn = new JButton("Traverse");

        displayArea = new JTextArea(15, 30);
        displayArea.setEditable(false);

        addStartBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                seating.insertAtStart(name);
                displayArea.setText(seating.traverseClockwise());
                nameField.setText("");
            }
        });

        // Add Between
        addBetweenBtn.addActionListener(e -> {
            JTextField firstField = new JTextField(10);
            JTextField secondField = new JTextField(10);
            JTextField newNameField = new JTextField(10);
            JPanel panel = new JPanel(new GridLayout(3,2));
            panel.add(new JLabel("First Name:")); panel.add(firstField);
            panel.add(new JLabel("Second Name:")); panel.add(secondField);
            panel.add(new JLabel("New Name:")); panel.add(newNameField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Insert Between", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                boolean success = seating.insertBetween(
                    firstField.getText().trim(),
                    secondField.getText().trim(),
                    newNameField.getText().trim()
                );
                if (!success) JOptionPane.showMessageDialog(null, "Pair not found!");
                displayArea.setText(seating.traverseClockwise());
            }
        });

        // Traverse
        traverseBtn.addActionListener(e -> displayArea.setText(seating.traverseClockwise()));

        // Add components to panel
        add(new JLabel("Enter Name:"));
        add(nameField);
        add(addStartBtn);
        add(addBetweenBtn);
        add(traverseBtn);
        add(new JScrollPane(displayArea));
    }
}
