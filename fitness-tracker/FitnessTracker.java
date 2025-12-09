import java.awt.*;
import javax.swing.*;
import java.util.Arrays;

public class FitnessTracker {

    static int[] steps = new int[7]; // 7 days
    static boolean stepsEntered = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Step Sorter");
        frame.setSize(450, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextArea display = new JTextArea(20, 35);
        display.setEditable(false);

        JButton enterBtn = new JButton("Enter Steps");
        JButton bubbleBtn = new JButton("Bubble Sort");
        JButton selectionBtn = new JButton("Selection Sort");
        JButton insertionBtn = new JButton("Insertion Sort");
        JButton mergeBtn = new JButton("Merge Sort");
        JButton arraySortBtn = new JButton("Array Sort");

        // ENTER STEPS
        enterBtn.addActionListener(e -> {
            try {
                for (int i = 0; i < 7; i++) {
                    String input = JOptionPane.showInputDialog(frame, "Enter steps for Day " + (i + 1) + ":");
                    if (input == null) return; // Cancel pressed
                    steps[i] = Integer.parseInt(input);
                }
                stepsEntered = true;
                display.setText("Steps Entered:\n" + showSteps(steps));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers!");
            }
        });

        // Bubble Sort
        bubbleBtn.addActionListener(e -> {
            if (!stepsEntered) { showNoSteps(display); return; }
            int[] sorted = steps.clone();
            bubbleSort(sorted);
            display.setText("Bubble Sort:\n" + showSteps(sorted));
        });

        // Selection Sort
        selectionBtn.addActionListener(e -> {
            if (!stepsEntered) { showNoSteps(display); return; }
            int[] sorted = steps.clone();
            selectionSort(sorted);
            display.setText("Selection Sort:\n" + showSteps(sorted));
        });

        // Insertion Sort
        insertionBtn.addActionListener(e -> {
            if (!stepsEntered) { showNoSteps(display); return; }
            int[] sorted = steps.clone();
            insertionSort(sorted);
            display.setText("Insertion Sort:\n" + showSteps(sorted));
        });

        // Merge Sort
        mergeBtn.addActionListener(e -> {
            if (!stepsEntered) { showNoSteps(display); return; }
            int[] sorted = steps.clone();
            mergeSort(sorted, 0, sorted.length - 1);
            display.setText("Merge Sort:\n" + showSteps(sorted));
        });

        // Array Sort()
        arraySortBtn.addActionListener(e -> {
            if (!stepsEntered) { showNoSteps(display); return; }
            int[] sorted = steps.clone();
            Arrays.sort(sorted);
            display.setText("Array Sort:\n" + showSteps(sorted));
        });

        // Add components
        frame.add(new JLabel("Enter Steps for 7 Days and Sort:"));
        frame.add(enterBtn);
        frame.add(bubbleBtn);
        frame.add(selectionBtn);
        frame.add(insertionBtn);
        frame.add(mergeBtn);
        frame.add(arraySortBtn);
        frame.add(new JScrollPane(display));

        frame.setLocationRelativeTo(null); // center the window
        frame.setVisible(true);
    }

    static void showNoSteps(JTextArea display) {
        JOptionPane.showMessageDialog(null, "Please enter steps first!");
    }

    // Display steps
    static String showSteps(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append("Day ").append(i + 1).append(": ").append(arr[i]).append("\n");
        }
        return sb.toString();
    }

    // Bubble Sort
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Selection Sort
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // Insertion Sort
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Merge Sort
    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
