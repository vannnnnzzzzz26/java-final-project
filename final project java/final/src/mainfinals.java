import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class mainfinals extends JFrame {

    private ArrayList<JPanel> pages = new ArrayList<>();
    private int currentIndex = 0;

    private JPanel contentPanel;

    public mainfinals() {
        setTitle("FINAL MENU");
        setSize(600, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     pages.add(new PlaylistDemo());
pages.add(new CircularTable());
pages.add(new FitnessTracker());
pages.add(new contact());
pages.add(new OrderQueue());
pages.add(new StackingBoxesUI());

// Navigation Buttons
        JButton btnPrev = new JButton("Previous");
        JButton btnNext = new JButton("Next");

        btnPrev.addActionListener(e -> showPage(currentIndex - 1));
        btnNext.addActionListener(e -> showPage(currentIndex + 1));

        JPanel nav = new JPanel();
        nav.add(btnPrev);
        nav.add(btnNext);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        add(nav, BorderLayout.SOUTH);

        showPage(0);
        setVisible(true);
    }

    private void showPage(int index) {
        if (index < 0 || index >= pages.size()) return;

        // Switch page
        currentIndex = index;
        contentPanel.removeAll();
        contentPanel.add(pages.get(index), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        new mainfinals();
    }
}
