import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class StudentManagerGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Manager");
        frame.setSize(550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center the frame on screen
		frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Labels and Text Fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 20, 80, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 25);
        frame.add(nameField);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(30, 60, 80, 25);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 60, 200, 25);
        frame.add(idField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(30, 100, 80, 25);
        frame.add(deptLabel);

        JTextField deptField = new JTextField();
        deptField.setBounds(120, 100, 200, 25);
        frame.add(deptField);

        // TextArea to show all student data
        JTextArea studentArea = new JTextArea();
        studentArea.setBounds(30, 180, 470, 150);
        studentArea.setEditable(false);
        frame.add(studentArea);

        // Save Button
        JButton saveButton = new JButton("Save Student");
        saveButton.setBounds(340, 20, 160, 30);
        frame.add(saveButton);

        // Load Button
        JButton loadButton = new JButton("Load All Students");
        loadButton.setBounds(340, 60, 160, 30);
        frame.add(loadButton);
		
		// Search Button
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(340, 100, 160, 30);
		frame.add(searchButton);

        // Save student info to file
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String id = idField.getText().trim();
                String dept = deptField.getText().trim();

                if (name.isEmpty() || id.isEmpty() || dept.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                    return;
                }

                try {
                    FileWriter writer = new FileWriter("students.txt", true);
                    writer.write("Name: " + name + ", ID: " + id + ", Dept: " + dept + "\n");
                    writer.close();

                    JOptionPane.showMessageDialog(frame, "Student saved.");
                    nameField.setText("");
                    idField.setText("");
                    deptField.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving student: " + ex.getMessage());
                }
            }
        });

        // Load all student info from file
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    reader.close();
                    studentArea.setText(content.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage());
                }
            }
        });
		
		
		// Add action listener for Search Button
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        String searchId = idField.getText().trim();
        if (searchId.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter an ID to search.");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ID: " + searchId)) {
                    studentArea.setText("Student Found:\n" + line);
                    found = true;
                    break;
                }
            }
            reader.close();

            if (!found) {
                studentArea.setText("No student found with ID: " + searchId);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage());
        }
    }
});
		
		

        frame.setVisible(true);
    }
}
