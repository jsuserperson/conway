import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.*;
import org.w3c.dom.events.MouseEvent;

public class ConwaysGame extends JPanel{
    private JFrame frame;
    private boolean started = false;

    private int width = 10;
    private int height = 10;
    boolean[][] grid = new boolean[height][width];

    JLabel inputLabel = new JLabel("width: ");

    NumberFormat inputFormatter = NumberFormat.getIntegerInstance();
    JFormattedTextField input = new JFormattedTextField(inputFormatter);

    public ConwaysGame() {
        frame = new JFrame("Conway's Game of Life");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(300, 200);
        frame.setVisible(true);
        this.add(inputLabel);
        input.setPreferredSize(new Dimension(50, 20));
        this.add(input);
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int inputInt = Integer.parseInt(input.getText());
                // System.out.println(inputLabel.getText() + inputInt);
                if (inputLabel.getText().equals("width: ")) {
                    width = inputInt;
                    input.setText("");
                    inputLabel.setText("height: ");
                } else if (inputLabel.getText().equals("height: ")) {
                    height = inputInt;
                    gameStart(width, height);
                }
            }
        });

    }

    public void gameStart(int width, int height) {
        grid = new boolean[height][width];
        for (int h = 0; h < height; h++) {
            // String row = "";
            for (int w = 0; w < width; w++) {
                // row += grid[h][w] + " ";
            }
            // System.out.println(row);
            this.remove(inputLabel);
            this.remove(input);
            frame.setSize(width * 10 + 30, height * 10 + 50);
            started = true;
        }
    }

    public void MouseClicked(MouseEvent e) {
        
    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (started) {
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    if (!grid[h][w]) {
                        g.setColor(Color.white);
                    }
                    g.fillRect(w * 10, h * 10, 10, 10);
                    g.setColor(Color.black);
                    g.drawRect(w * 10, h * 10, 10, 10);
                }
            }
        }
    }
}