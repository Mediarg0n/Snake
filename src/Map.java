import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

//Das Spielfeld
public class Map extends JPanel {
    private Snake snake;
    private Food food;

    public Map(Snake snake, Food food) {
        this.snake = snake;
        this.food = food;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GameController.WIDTH * GameController.SCALE, GameController.HEIGHT * GameController.SCALE);

        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));

        graphics.drawString("Score: " + snake.getScore(), GameController.WIDTH * GameController.SCALE - 100, 20);

        graphics.setColor(Color.WHITE);
        for (Point point : snake.getPoints()) {
            graphics.setColor(Color.ORANGE);
            graphics.drawOval(point.x * GameController.SCALE - 1, point.y * GameController.SCALE - 1, GameController.SCALE + 2, GameController.SCALE + 2);
            graphics.drawOval(point.x * GameController.SCALE + 1, point.y * GameController.SCALE + 1, GameController.SCALE - 2, GameController.SCALE - 2);
        }
        graphics.setColor(Color.YELLOW);
        if (food.currentPoint() != null)
            graphics.fillOval(food.currentPoint().x * GameController.SCALE, food.currentPoint().y * GameController.SCALE, GameController.SCALE, GameController.SCALE);

    }


}
