import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreeDStopwatch extends JPanel {

    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private long startTime;

    public ThreeDStopwatch() {
        setLayout(new BorderLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        timeLabel.setForeground(Color.RED);

        startButton = createButton("Start", new Color(0, 170, 0), new Color(0, 120, 0));
        stopButton = createButton("Stop", new Color(170, 0, 0), new Color(120, 0, 0));
        resetButton = createButton("Reset", new Color(0, 0, 170), new Color(0, 0, 120));

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                updateTimerLabel(elapsedTime);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(timeLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    private JButton createButton(String text, Color topColor, Color bottomColor) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Start")) {
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    resetButton.setEnabled(true);
                    startTime = System.currentTimeMillis();
                    timer.start();
                } else if (text.equals("Stop")) {
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);
                    timer.stop();
                } else if (text.equals("Reset")) {
                    resetButton.setEnabled(false);
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    timer.stop();
                    timeLabel.setText("00:00:00");
                }
            }
        });
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        // button.setBackground(new GradientPaint(0, 0, topColor, 0, (button.getHeight()), bottomColor));
		button.setBackground(new Color(60, 255, 120));


        return button;
    }

    private void updateTimerLabel(long elapsedTime) {
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("3D Stopwatch");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.add(new ThreeDStopwatch());
                frame.setVisible(true);
            }
        });
    }
}
