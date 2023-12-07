import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Help extends JFrame {
    public Help() {
        setTitle("Memory Matching Game - Instructions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null); // Center the window

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                fitImageToScreen();
            }
        });

        setVisible(true);
    }

    private void fitImageToScreen() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(getContentPane().getSize());

        ImageIcon helpImage = loadImage("bg.jpg");
        if (helpImage != null) {
            int frameWidth = getContentPane().getWidth();
            int frameHeight = getContentPane().getHeight();

            JLabel imageLabel = new JLabel(helpImage);
            Image scaledImage = helpImage.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
            helpImage = new ImageIcon(scaledImage);
            imageLabel.setIcon(helpImage);
            imageLabel.setBounds(0, 0, frameWidth, frameHeight);
            layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);
        } else {
            JLabel errorLabel = new JLabel("Image not found.");
            errorLabel.setBounds(0, 0, 600, 400);
            layeredPane.add(errorLabel, JLayeredPane.DEFAULT_LAYER);
        }

        JLabel textLabel = new JLabel("<html>\n\n\n\n\nINSTRUCTIONS<br>"+"Players tend to choose two cards at a time and try to find two cards that are exactly the same.<br>"
                + "If you find two cards that match then those cards remain opened until you match remaining cards.<br>"
                + "There are total 10 tries by which you have to complete the game, if those tries get over, game overs.<br>"
                + "If players are unable to match two cards together then they have to turn the cards back over face down.<br>"
                + "If you restart the game everytime it will generate random images.<br>"
                + "You will win once all pairs of cards will be guessed within the tries given.</html>");
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Arial", Font.BOLD, 18));
        textLabel.setBounds(50, 50, getWidth() - 100, getHeight() - 100);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setOpaque(false);
        layeredPane.add(textLabel, JLayeredPane.PALETTE_LAYER);

        JButton exitButton = new JButton("Exit");
        stylizeButton(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new SplashScreen());
            }
        });

        exitButton.setBounds((getWidth() - 80) / 2, getHeight() - 70, 80, 30);
        layeredPane.add(exitButton, JLayeredPane.POPUP_LAYER);

        setContentPane(layeredPane);
        revalidate();
        repaint();
    }

    private ImageIcon loadImage(String filename) {
        try {
            return new ImageIcon(getClass().getResource(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to stylize buttons
    private static void stylizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Help());
    }
}
