import javax.swing.*;
import java.awt.event.*;

public class ButtonExample {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Button Action Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center the frame on screen
		frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Create button
        JButton button = new JButton("Click Me");
        button.setBounds(100, 80, 100, 30);

        // Add action listener
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Button was clicked!");
            }
        });

        // Add button to frame
        frame.add(button);

        // Show frame
        frame.setVisible(true);
    }
}
