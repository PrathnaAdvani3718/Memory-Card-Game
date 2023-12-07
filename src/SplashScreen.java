import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SplashScreen extends JFrame {
    public SplashScreen() {
        setTitle("Welcome to Your Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(610, 400);
        setLocationRelativeTo(null); // Center the window

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("splash.jpeg"));
        Image i3 = i1.getImage().getScaledInstance(610, 400, Image.SCALE_DEFAULT); // Resize the image to match window size
        ImageIcon i2 = new ImageIcon(i3);

        // Create a JLabel to hold the background image
        JLabel backgroundLabel = new JLabel(i2);
        setContentPane(backgroundLabel); // Set as the content pane to cover the whole frame

        // Set layout to BorderLayout to place buttons at the bottom
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(1, 3, 20, 0));

        JButton playButton = new JButton("Play");
        stylizeButton(playButton);
        playButton.addActionListener(e -> {
            // Code to start the game goes here
            dispose();
            SwingUtilities.invokeLater(() -> new Game.Controller(new Game.Model(4))); // Start the game by creating its controller
        });

        JButton helpButton = new JButton("Help");
        stylizeButton(helpButton);
        helpButton.addActionListener(e -> {
            // Code to show help/instructions goes here
            dispose();
            SwingUtilities.invokeLater(Help::new);
        });

        JButton exitButton = new JButton("Exit");
        stylizeButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0)); // Code to exit the application goes here

        buttonPanel.add(playButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    // Method to stylize buttons
    private void stylizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new);
    }
}
