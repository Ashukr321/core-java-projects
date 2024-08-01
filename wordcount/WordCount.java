import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WordCount extends JFrame {
    private JTextArea textArea;
    private JButton countButton;
    private JButton resetButton;
    private JLabel wordCountLabel;
    private JLabel headerLabel;

    public WordCount() {
        // Set the title of the window
        super("Word Count created  by Ashutosh");

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
    }

    public static void main(String[] args) {
        // Create a new instance of the WordCount class, which will create and display
        // the window
        new WordCount();
    }
}
/*
 * 
 * class diagram
 * +---------------+
 * | WordCount |
 * +---------------+
 * | -headerLabel: JLabel |
 * | -textArea: JTextArea |
 * | -countButton: JButton |
 * | -wordCountLabel: JLabel|
 * +---------------+
 * | +WordCount() |
 * | +countWords() |
 * +---------------+
 */

/*
 * 
 * 
 * +---------------+
 * | JFrame |
 * +---------------+
 * | +getTitle(): String |
 * | +setLayout(LayoutManager) |
 * | +add(Component, Object) |
 * +---------------+
 * ^
 * |
 * +---------------+
 * | WordCount |
 * +---------------+
 * | -headerLabel: JLabel |
 * | -textArea: JTextArea |
 * | -countButton: JButton |
 * | -wordCountLabel: JLabel|
 * +---------------+
 * | +WordCount() |
 * | +countWords() |
 * +---------------+
 * ^
 * |
 * +---------------+
 * | JLabel |
 * +---------------+
 * | +setText(String) |
 * | +setFont(Font) |
 * +---------------+
 * ^
 * |
 * +---------------+
 * | JTextArea |
 * +---------------+
 * | +getText(): String |
 * | +setFont(Font) |
 * +---------------+
 * ^
 * |
 * +---------------+
 * | JButton |
 * +---------------+
 * | +addActionListener(ActionListener) |
 * +---------------+
 * ^
 * |
 * +---------------+
 * | ActionListener |
 * +---------------+
 * | +actionPerformed(ActionEvent) |
 * +---------------+
 * 
 */

/*
 * ⭐⭐⭐⭐ code explanation
 * import java.awt.*;
 * 
 * AWT stands for Abstract Window Toolkit.
 * This line imports all classes from the java.awt package, which provides a set
 * of classes for creating graphical user interfaces (GUIs) in Java. The * is a
 * wildcard character that imports all classes in the package.
 * GUI components: AWT provides a set of pre-built GUI components, such as
 * ⭐⭐buttons, labels, text fields, and more, that can be used to create GUI
 * applications.
 * 
 * ⭐⭐Event handling: AWT provides a way for Java programs to handle user input,
 * such as mouse clicks and keyboard input, through event listeners.
 * ⭐⭐ Graphics: AWT provides a way for Java programs to render graphics, such as
 * images and shapes, on the screen.
 * 
 * 
 * import java.awt.event.ActionEvent;
 * This line imports the ActionEvent class from the java.awt.event package,
 * which is used to handle events such as button clicks.
 * 
 * 
 * import java.awt.event.ActionListener;
 * This line imports the ActionListener interface from the java.awt.event
 * package, which is used to handle events such as button clicks.
 * 
 * 
 * import javax.swing.*;
 * This line imports all classes from the javax.swing package, which provides a
 * set of classes for creating GUI components such as buttons, labels, and text
 * areas.
 * 
 */

/*
 * 
 * JFrame: This is the parent class that our WordCount class is extending.
 * JFrame is a class in the Java Swing library that represents a top-level
 * window with a title bar and borders.
 * 
 * 
 * By extending JFrame, our WordCount class gets access to all the methods and
 * fields of JFrame, such as:
 * 
 * setTitle(): sets the title of the window
 * setSize(): sets the size of the window
 * setVisible(): makes the window visible or invisible
 * add(): adds components to the window
 * 
 * 
 * public class WordCount extends JFrame {
 * public WordCount() {
 * setTitle("Word Count");
 * setSize(300, 200);
 * setVisible(true);
 * // add custom code here to display the word count application
 * }
 * }
 */