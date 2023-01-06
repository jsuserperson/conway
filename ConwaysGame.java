import java.awt.*;
import java.text.NumberFormat;
import java.awt.event.*;
import javax.swing.*;

public class ConwaysGame extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private JFrame frame;
    private boolean setup = false;

    private int width;
    private int height;
    boolean[][] grid = new boolean[height][width];

    JLabel inputLabel = new JLabel("width: ");

    NumberFormat inputFormatter = NumberFormat.getIntegerInstance();
    JFormattedTextField input = new JFormattedTextField(inputFormatter);

    public ConwaysGame() {
        frame = new JFrame("Conway's Game of Life");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        frame.addKeyListener(this);
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
                    gameSetup(width, height);
                }
            }
        });

    }

    public void gameSetup(int width, int height) {
        grid = new boolean[height][width];
        this.remove(inputLabel);
        this.remove(input);
        frame.setSize(width * 10 + 17, height * 10 + 40);
        setup = true;
        frame.setTitle("Conway's Game of Life - Hold SPACE to Progress!");
    }

    public void tick() {
        boolean[][] newGrid = new boolean[height][width];
        for (int h = 0; h < height; h++) {
            System.arraycopy(grid[h], 0, newGrid[h], 0, height);
        }
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int neighbors = 0;
                if (h > 0 && w > 0 && grid[h - 1][w - 1]) {neighbors++;}
                if (h > 0 && grid[h - 1][w]) {neighbors++;}
                if (h > 0 && width > w + 1 && grid[h - 1][w + 1]) {neighbors++;}
                if (width > w + 1 && grid[h][w + 1]) {neighbors++;}
                if (height > h + 1 && width > w + 1 && grid[h + 1][w + 1]) {neighbors++;}
                if (height > h + 1 && grid[h + 1][w]) {neighbors++;}
                if (height > h + 1 && w > 0 && grid[h + 1][w - 1]) {neighbors++;}
                if (w > 0 && grid[h][w - 1]) {neighbors++;}

                if (grid[h][w]) {
                    if (neighbors > 3 || neighbors < 2) {
                        newGrid[h][w] = false;
                    }
                } else {
                    if (neighbors == 3) {
                        newGrid[h][w] = true;
                    }
                }
            }
        }
        for (int h = 0; h < height; h++) {
            System.arraycopy(newGrid[h], 0, grid[h], 0, height);
        }
        frame.repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (setup) {
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

    public void mouseClicked(MouseEvent e) {
        int gridX = e.getX() / 10;
        int gridY = e.getY() / 10;
        if (!grid[gridY][gridX] && gridX <= width && gridY <= height) {
            grid[gridY][gridX] = true;
        } else {
            grid[gridY][gridX] = false;
        }
        frame.repaint();
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {
        int gridX = e.getX() / 10;
        int gridY = e.getY() / 10;
        if (!grid[gridY][gridX] && gridX <= width && gridY <= height) {
            grid[gridY][gridX] = true;
        } else {
            grid[gridY][gridX] = false;
        }
        frame.repaint();
    }

    public void mouseMoved(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());
        if (setup && e.getKeyCode() == 32) {
            tick();
        }
    }

    public void keyReleased(KeyEvent e) {}
}