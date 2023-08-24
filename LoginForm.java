import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class LoginForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel logoLabel;

    public LoginForm() {
        setTitle("Login Form");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load and resize the logo
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResource("logo.png")); // Replace with your logo file path
            int newWidth = 200; // New width for the logo
            int newHeight = (int) (((double) newWidth / originalImage.getWidth()) * originalImage.getHeight());
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(resizedImage));
        } catch (IOException e) {
            e.printStackTrace();
            logoLabel = new JLabel("Logo Image Not Found");
        }

        add(logoLabel, BorderLayout.NORTH);

        // Center panel for text fields and button
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Padding

        usernameField = new JTextField(20); // 20 columns width
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        constraints.gridx = 0;
        constraints.gridy = 0;
        centerPanel.add(new JLabel("Username:"), constraints);

        constraints.gridx = 1;
        centerPanel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        centerPanel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        centerPanel.add(passwordField, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        centerPanel.add(loginButton, constraints);

        // Create a panel to center the centerPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel); // Add the mainPanel to the JFrame

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login successful");
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login failed");
                }

                // Clear fields after login attempt
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    private boolean validateLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
