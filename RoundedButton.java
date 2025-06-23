import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    // New Constructor: With custom color
    public RoundedButton(String label, Color bgColor) {
        super(label);
        setBackground(bgColor);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    // Old Constructor: Defaults to soft purple (153, 102, 204)
    public RoundedButton(String label) {
        this(label, new Color(153, 102, 204)); // Soft purple as default
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border
    }
}
