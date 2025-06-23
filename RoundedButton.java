import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private Color customColor;

    // 💡 New constructor with color support
    public RoundedButton(String label, Color color) {
        super(label);
        this.customColor = color;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    // 💡 Old constructor for default purple
    public RoundedButton(String label) {
        this(label, new Color(153, 102, 204)); // default soft purple
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(customColor.darker());
        } else {
            g2.setColor(customColor);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // no border
    }
}
