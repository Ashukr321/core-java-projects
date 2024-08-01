import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class WordCount extends JFrame {
    private JTextArea textArea;
    private JButton countButton;
    private JButton resetButton;
    private JLabel wordCountLabel;
    private JLabel typingSpeedLabel;
    private JLabel averageWordLengthLabel;
    private JLabel mostCommonWordLabel;
    private JLabel headerLabel;
    private long startTime;
    private long lastKeyPressTime;
    private int wordCount;
    private double typingSpeed;
    private double averageWordLength;
    private String mostCommonWord;

    public WordCount() {
        // Set the title of the window
        super("Word Count created by Ashutosh");

        // Set the layout of the window to BorderLayout
        setLayout(new BorderLayout());

        // Create a header label to display the title of the application
        headerLabel = new JLabel("Word Count Application", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headerLabel, BorderLayout.NORTH);

        // Create a panel to hold the text area and the scroll pane
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());

        // Create a text area for user input
        textArea = new JTextArea(20, 40);
        textArea.setFont(new Font("Arial", Font.PLAIN, 28));

        // Add a key listener to the text area to record the start time when the user
        // starts typing
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (textArea.getText().trim().isEmpty()) {
                    startTime = System.currentTimeMillis();
                    lastKeyPressTime = startTime;
                } else {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - lastKeyPressTime;
                    lastKeyPressTime = currentTime;

                    // Calculate the typing speed
                    String text = textArea.getText().trim();
                    String[] words = text.split("\\s+");
                    wordCount = words.length;
                    typingSpeed = (wordCount / (elapsedTime / 1000.0)) * 60;

                    // Update the typing speed label with the new typing speed
                    typingSpeedLabel.setText("Typing Speed: " + String.format("%.2f", typingSpeed) + " words/min");

                    // Calculate the average word length
                    int totalCharacters = 0;
                    for (String word : words) {
                        totalCharacters += word.length();
                    }
                    averageWordLength = (double) totalCharacters / wordCount;
                    averageWordLengthLabel.setText("Average Word Length: " + String.format("%.2f", averageWordLength));

                    // Calculate the most common word
                    Map<String, Integer> wordFrequency = new HashMap<>();
                    for (String word : words) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                    String mostCommonWord = null;
                    int maxFrequency = 0;
                    for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                        if (entry.getValue() > maxFrequency) {
                            maxFrequency = entry.getValue();
                            mostCommonWord = entry.getKey();
                        }
                    }
                    mostCommonWordLabel.setText("Most Common Word: " + mostCommonWord);

                    // Update the word count label with the new word count
                    wordCountLabel.setText("Word Count: " + wordCount);
                }
            }
        });

        // Create a scroll pane to hold the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        textPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the text panel to the center of the window
        add(textPanel, BorderLayout.CENTER);

        // Create a panel to hold the count button, reset button and the word count
        // label
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create a button to count words
        countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });
        buttonPanel.add(countButton);

        // Create a reset button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        buttonPanel.add(resetButton);

        // Create a label to display word count
        wordCountLabel = new JLabel("Word Count: 0");
        wordCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        buttonPanel.add(wordCountLabel);

        // Create a label to display typing speed
        typingSpeedLabel = new JLabel("Typing Speed: 0 words/min");
        typingSpeedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        buttonPanel.add(typingSpeedLabel);

        // Create a label to display average word length
        averageWordLengthLabel = new JLabel("Average Word Length: 0");
        averageWordLengthLabel.setFont(new Font("Arial", Font.BOLD, 18));
        buttonPanel.add(averageWordLengthLabel);

        // Create a label to display most common word
        mostCommonWordLabel = new JLabel("Most Common Word: ");
        mostCommonWordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        buttonPanel.add(mostCommonWordLabel);

        // Add the button panel to the south of the window
        add(buttonPanel, BorderLayout.SOUTH);

        // Set the size of the window
        setSize(400, 300);

        // Set the default close operation to exit the application when the window is
        // closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Make the window visible
        setVisible(true);
    }

    private void countWords() {
        // Get the text from the text area
        String text = textArea.getText().trim();

        // Check if the text is empty
        if (text.isEmpty()) {
            wordCountLabel.setText("Word Count: 0");
        } else {
            // Split the text into words using the split method with a regular expression
            // that matches one or more whitespace characters
            String[] words = text.split("\\s+");

            // Get the length of the words array, which represents the number of words in
            // the text
            int wordCount = words.length;

            // Update the word count label with the new word count
            wordCountLabel.setText("Word Count: " + wordCount);
        }
    }

    private void reset() {
        // Clear the text area
        textArea.setText("");

        // Reset the word count label
        wordCountLabel.setText("Word Count: 0");

        // Reset the typing speed label
        typingSpeedLabel.setText("Typing Speed: 0 words/min");

        // Reset the average word length label
        averageWordLengthLabel.setText("Average Word Length: 0");

        // Reset the most common word label
        mostCommonWordLabel.setText("Most Common Word: ");

        // Reset the start time
        startTime = 0;
        lastKeyPressTime = 0;
    }

    public static void main(String[] args) {
        new WordCount();
    }
}

// +---------------+
// | WordCount |
// +---------------+
// | - textArea: JTextArea |
// | - countButton: JButton |
// | - resetButton: JButton |
// | - wordCountLabel: JLabel |
// | - typingSpeedLabel: JLabel |
// | - averageWordLengthLabel: JLabel |
// | - mostCommonWordLabel: JLabel |
// | - headerLabel: JLabel |
// | - startTime: long |
// | - lastKeyPressTime: long |
// | - wordCount: int |
// | - typingSpeed: double |
// | - averageWordLength: double |
// | - mostCommonWord: String |
// +---------------+
// | +WordCount() |
// | +countWords() |
// | +reset() |
// +---------------+

// textArea: A JTextArea where the user can input text.

// countButton: A JButton that triggers the word count calculation when clicked.

// resetButton: A JButton that resets the text area and labels when clicked.

// wordCountLabel, typingSpeedLabel, averageWordLengthLabel,

// mostCommonWordLabel, and headerLabel: JLabels that display various statistics
// about the input text.

// startTime and lastKeyPressTime: long variables that store the start time and
// last key press time, respectively, for calculating the typing speed.

// wordCount, typingSpeed, averageWordLength, and mostCommonWord: Variables that
// store the calculated word count, typing speed, average word length, and most

// common word, respectively.
// The class has three methods:

// WordCount(): The constructor that initializes the GUI components and sets up
// the event listeners.

// countWords(): A method that calculates the word count, typing speed, average
// word length, and most common word when the "Count Words" button is clicked.

// reset(): A method that resets the text area and labels when the "Reset"

// button is clicked.

// Web Search
// Follow This