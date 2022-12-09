import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;

public class ConwaysGame extends JPanel {
    private JFrame frame;
    private int winWidth = 300;
    private int winHeight = 200;

    private int width = 1;
    private int height = 1;
    boolean[][] grid;

    private int inputInt;

    JLabel inputLabel = new JLabel("width: ");

    NumberFormat inputFormatter = NumberFormat.getIntegerInstance();
    JFormattedTextField input = new JFormattedTextField(inputFormatter);
    

    public ConwaysGame() {
        JFrame frame = new JFrame("Conway's Game of Life");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.add(inputLabel);
        input.setPreferredSize(new Dimension(50, 20));
        this.add(input);
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputInt = Integer.parseInt(input.getText());
                System.out.println(inputLabel.getText() + inputInt);
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
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    public void gameStart(int width, int height) {
        grid = new boolean[height][width];
        for (int h = 0; h < height; h++) {
            String row = "";
            for (int w = 0; w < width; w++) {
                row += grid[h][w] + " ";
            }
            System.out.println(row);
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (grid[h][w]) {
                    g.setColor(Color.black);
                } else {
                    g.setColor(Color.white);
                }
                g.fillRect(w * 10, h * 10, 10, 10);
            }
        }
    }
}