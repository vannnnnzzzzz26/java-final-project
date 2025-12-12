import java.awt.*;
import java.util.Stack;
import javax.swing.*;

public class StackingBoxesUI extends JPanel {

    private Stack<String> boxes = new Stack<>();
    private JTextArea displayArea;
    private JTextField inputField;

    public StackingBoxesUI() {

        setLayout(new BorderLayout());

        //  L Input Box 
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Box Name: "));
        inputField = new JTextField(20);
        topPanel.add(inputField);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        //  BUTTON PANEL 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5, 5, 5));

        JButton pushBtn = new JButton("Push");
        JButton popBtn = new JButton("Pop");
        JButton peekBtn = new JButton("Peek");
        JButton showBtn = new JButton("Show All");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(pushBtn);
        buttonPanel.add(popBtn);
        buttonPanel.add(peekBtn);
        buttonPanel.add(showBtn);
        buttonPanel.add(exitBtn);

        // ADD PANELS 
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        //  BUTTON ACTIONS 
        pushBtn.addActionListener(e -> {
            String box = inputField.getText().trim();
            if (box.isEmpty()) {
                showMessage("Please enter a box name!");
                return;
            }
            boxes.push(box);
            showMessage("Box '" + box + "' added on top!");
            inputField.setText("");
        });

        popBtn.addActionListener(e -> {
            if (boxes.isEmpty()) {
                showMessage("No boxes to remove!");
            } else {
                showMessage("Removed top box: " + boxes.pop());
            }
        });

        peekBtn.addActionListener(e -> {
            if (boxes.isEmpty()) {
                showMessage("No boxes in the stack!");
            } else {
                showMessage("Top box: " + boxes.peek());
            }
        });

        showBtn.addActionListener(e -> {
            if (boxes.isEmpty()) {
                showMessage("No boxes stored!");
            } else {
                showMessage("All boxes: " + boxes);
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void showMessage(String msg) {
        displayArea.append(msg + "\n");
    }
}
