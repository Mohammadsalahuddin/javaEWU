import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopwatchGUI extends JFrame {

    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;
    private Timer timer;
    private long startTime;

    public StopwatchGUI() {
        setTitle("Stopwatch");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                resetButton.setEnabled(true);
                startTime = System.currentTimeMillis();
                timer.start();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
                timer.stop();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButton.setEnabled(false);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                timer.stop();
                timeLabel.setText("00:00:00");
            }
        });

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                updateTimerLabel(elapsedTime);
            }
        });

        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        stopButton.setEnabled(false);
        resetButton.setEnabled(false);
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
                StopwatchGUI stopwatch = new StopwatchGUI();
                stopwatch.setVisible(true);
            }
        });
    }
}
