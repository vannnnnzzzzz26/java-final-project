import java.awt.*;
import javax.swing.*;

class Node {
    String song;
    Node prev;
    Node next;

    public Node(String song) {
        this.song = song;
    }
}

public class PlaylistDemo extends JPanel {

    Node head = null;
    Node tail = null;
    Node current = null;

    private JTextArea playlistArea;
    private JTextField songField, indexField;
    private JLabel nowPlaying;

    public PlaylistDemo() {
        setLayout(new BorderLayout());

        // --- TOP PANEL (Inputs) ---
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createTitledBorder("Add Song"));

        topPanel.add(new JLabel("Song Title:"));
        songField = new JTextField();
        topPanel.add(songField);

        topPanel.add(new JLabel("Insert After Index:"));
        indexField = new JTextField();
        topPanel.add(indexField);

        add(topPanel, BorderLayout.NORTH);

        playlistArea = new JTextArea();
        playlistArea.setEditable(false);
        playlistArea.setFont(new Font("Arial", Font.PLAIN, 16));
        add(new JScrollPane(playlistArea), BorderLayout.CENTER);

        //  BOTTOM PANEL Buttons
        JPanel bottomPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        JButton btnFront = new JButton("Add Front");
        JButton btnEnd = new JButton("Add End");
        JButton btnMiddle = new JButton("Add Middle");
        JButton btnNext = new JButton("Next Song");
        JButton btnPrev = new JButton("Previous Song");

        nowPlaying = new JLabel("Now Playing: None", SwingConstants.CENTER);

        bottomPanel.add(btnFront);
        bottomPanel.add(btnEnd);
        bottomPanel.add(btnMiddle);
        bottomPanel.add(btnNext);
        bottomPanel.add(btnPrev);
        bottomPanel.add(nowPlaying);

        add(bottomPanel, BorderLayout.SOUTH);

        btnFront.addActionListener(e -> addFront());
        btnEnd.addActionListener(e -> addEnd());
        btnMiddle.addActionListener(e -> addMiddle());
        btnNext.addActionListener(e -> playNext());
        btnPrev.addActionListener(e -> playPrevious());
    }

    //  ADD SONG AT FRONT 
    void addFront() {
        String title = songField.getText().trim();
        if (title.isEmpty()) return;

        Node newNode = new Node(title);

        if (head == null) {
            head = tail = current = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        songField.setText("");
        updatePlaylist();
    }

    // ADD SONG AT END 
    void addEnd() {
        String title = songField.getText().trim();
        if (title.isEmpty()) return;

        Node newNode = new Node(title);

        if (tail == null) {
            head = tail = current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        songField.setText("");
        updatePlaylist();
    }

    //  ADD MIDDLE 
    void addMiddle() {
        String title = songField.getText().trim();
        String idxText = indexField.getText().trim();
        if (title.isEmpty() || idxText.isEmpty()) return;

        int index = Integer.parseInt(idxText);

        Node temp = head;
        int i = 0;

        while (temp != null && i < index) {
            temp = temp.next;
            i++;
        }

        if (temp == null) return;

        Node newNode = new Node(title);
        Node nextNode = temp.next;

        temp.next = newNode;
        newNode.prev = temp;

        if (nextNode != null) {
            newNode.next = nextNode;
            nextNode.prev = newNode;
        } else {
            tail = newNode;
        }

        songField.setText("");
        indexField.setText("");
        updatePlaylist();
    }

    //  PLAY NEXT 
    void playNext() {
        if (current == null) return;

        if (current.next != null) {
            current = current.next;
            nowPlaying.setText("Now Playing: " + current.song);
        } else {
            nowPlaying.setText("Last Song Reached");
        }
    }

    // PLAY PREVIOUS 
    void playPrevious() {
        if (current == null) return;

        if (current.prev != null) {
            current = current.prev;
            nowPlaying.setText("Now Playing: " + current.song);
        } else {
            nowPlaying.setText("First Song Reached");
        }
    }

    //  UPDATE PLAYLIST TEXT ARE
    void updatePlaylist() {
        playlistArea.setText("");

        Node temp = head;
        int index = 0;

        while (temp != null) {
            playlistArea.append(index + ". " + temp.song + "\n");
            temp = temp.next;
            index++;
        }

        if (current != null)
            nowPlaying.setText("Now Playing: " + current.song);
        else
            nowPlaying.setText("Now Playing: None");
    }
}
