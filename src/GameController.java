import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.Timer;

public class GameController implements GameControllerInterface, ActionListener, KeyListener {
    private Dimension screenSize;
    private Snake snake;
    private Food food;
    private Map map; // Spielfeld auf dem sich die Snake bewegen soll
    private JFrame frame;
    private int direction;

    public static int HEIGHT, WIDTH, SCALE;
    public static boolean WALL;
    public Timer timer;
    private boolean pause, gameOver, change;
    private boolean hasFood;
    private InputFrame inputFrame;

    public GameController(){
        inputFrame = new InputFrame(this);
        snake = new Snake();
        food = new Food();
        map = new Map(snake, food);
        frame = new JFrame();
        frame.add(map);
        frame.addKeyListener(this);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        pause = true;
        gameOver = true;
        hasFood = false;
        change = false;

    }

    public static void main(String[] args) {
        new GameController();
    }

    public List<Point> getSnake() {
        return snake.getPoints();
    }

    public void startGame(boolean start_new) {
        timer = new Timer(inputFrame.getBuffer(), this);
        timer.start();
        pause = start_new;
        gameOver = false;
        direction = DOWN;
        snake.remote();

        food.newFood();
        hasFood = false;
        HEIGHT = inputFrame.getHidht();
        WIDTH = inputFrame.getWidht();
        SCALE = inputFrame.getScale();
        WALL = inputFrame.getWall();

        frame.setSize((int) (WIDTH * SCALE + 22), (int) (HEIGHT * SCALE + 56));

        frame.setLocation(((int) screenSize.getWidth() - frame.getWidth()) / 2, ((int) screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_SPACE)
            if (gameOver)
                startGame(false);
            else
                pause = !pause;
        if (!change) {
            switch (i) {
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if (direction != RIGHT)
                        direction = LEFT;
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if (direction != LEFT)
                        direction = RIGHT;
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if (direction != UP)
                        direction = DOWN;
                    break;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    if (direction != DOWN)
                        direction = UP;
                    break;
            }
            change = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (!pause) {
            map.repaint();
            change = false;
            if (hasFood) {
                food.newFood();
            }
            try {
                snake.move(hasFood, direction);
            } catch (GameOverExeption e) {
                timer.stop();
                gameOver = true;
            }
            hasFood = food.aktuellerPunkt().equals(snake.aktuellerPunkt());
        }
    }
}
