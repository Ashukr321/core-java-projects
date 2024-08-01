import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TodoListApp {
	private JFrame frame;
	private JTextField titleField;
	private JTextArea descriptionArea;
	private JCheckBox completedCheckBox;
	private JButton addButton;
	private JButton deleteButton;
	private JList<TodoItem> list;
	private DefaultListModel<TodoItem> model;
	private ArrayList<TodoItem> todoItems;

	public TodoListApp() {
		// Create the UI components
		frame = new JFrame("To-Do List App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // add this to close the app when the window is closed

		// Create a panel for the input fields
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3, 2));
		inputPanel.add(new JLabel("Title:"));
		titleField = new JTextField(20);
		inputPanel.add(titleField);
		inputPanel.add(new JLabel("Description:"));
		descriptionArea = new JTextArea(5, 20);
		inputPanel.add(new JScrollPane(descriptionArea));
		inputPanel.add(new JLabel("Completed:"));
		completedCheckBox = new JCheckBox();
		inputPanel.add(completedCheckBox);

		// Create a panel for the buttons
		JPanel buttonPanel = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTodoItem();
			}
		});
		buttonPanel.add(addButton);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteTodoItem();
			}
		});
		buttonPanel.add(deleteButton);

		// Create a panel for the list
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		list = new JList<>();
		model = new DefaultListModel<>();
		list.setModel(model);
		listPanel.add(new JScrollPane(list), BorderLayout.CENTER);

		// Add a list selection listener to display the selected todo item's details
		list.addListSelectionListener(e -> {
			int selectedIndex = list.getSelectedIndex();
			if (selectedIndex!= -1) {
				TodoItem item = todoItems.get(selectedIndex);
				titleField.setText(item.getTitle());
				descriptionArea.setText(item.getDescription());
				completedCheckBox.setSelected(item.isCompleted());
			}
		});

		// Set up the UI layout
		frame.setLayout(new BorderLayout());
		frame.add(inputPanel, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.CENTER);
		frame.add(listPanel, BorderLayout.SOUTH);

		// Set the background color of the frame
		frame.getContentPane().setBackground(Color.decode("#F7F7F7")); // light gray

		// Display the UI
		frame.pack();
		frame.setVisible(true);

		// Initialize the todoItems list
		todoItems = new ArrayList<>();
	}

	private void addTodoItem() {
		// Get the values from the UI components
		String title = titleField.getText();
		String description = descriptionArea.getText();
		boolean completed = completedCheckBox.isSelected();

		// Create a new TodoItem object
		TodoItem item = new TodoItem(title, description);
		item.setCompleted(completed);

		// Add the item to the list
		todoItems.add(item);
		model.addElement(item);

		// Clear the UI components
		titleField.setText("");
		descriptionArea.setText("");
		completedCheckBox.setSelected(false);
	}

	private void deleteTodoItem() {
		// Get the selected item from the list
		int selectedIndex = list.getSelectedIndex();
		if (selectedIndex!= -1) {
			// Show a confirmation dialog before deleting
			int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this todo item?", "Delete Todo Item", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				TodoItem item = todoItems.get(selectedIndex);
				todoItems.remove(item);
				model.remove(selectedIndex);
			}
		}
	}

	public static void main(String[] args) {
		new TodoListApp();
	}
}