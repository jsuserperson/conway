import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class ConwaysGame extends JPanel {
    private JFrame frame;

    private int width = 50;
    private int height = 50;

    JLabel inputLabel = new JLabel(" by ");

    JTextField widthInput = new JTextField();
    
    JTextField heightInput = new JTextField();

    public ConwaysGame() {
        JFrame frame = new JFrame("Conway's Game of Life");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this);
        this.add(widthInput);
        widthInput.setBounds(50, 5, 50, 50);
        // frame.add(inputLabel);
        this.add(heightInput);
        heightInput.setBounds(50, 5, 50, 50);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

    // private static void paintComponents(Graphics g) {

    // }
}