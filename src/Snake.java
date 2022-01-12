import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObjekt {
    private List<Point> points;// Alle Positionen
    private int score;

    public Snake() {
        super(new Point(2, 2));
        this.points = new ArrayList<Point>();
        this.points.add(super.currentPoint());
        score = 0;
    }

    public Point move(boolean food, int direction) throws GameOverExeption {
        switch (direction) {
            case GameControllerInterface.LEFT:
                moveLeft();
                break;
            case GameControllerInterface.RIGHT:
                moveRight();
                break;
            case GameControllerInterface.DOWN:
                moveDown();
                break;
            case GameControllerInterface.UP:
                moveUp();
        }


        if ((points.contains(currentPoint())         //Wenn der Aktuelle Punkt bereits von der Schlange belegt ist
                || currentPoint().x == GameController.WIDTH    //Oder er aus dem Feld läuft
                || currentPoint().y == GameController.HEIGHT
                || currentPoint().x < 0
                || currentPoint().y < 0))
            throw new GameOverExeption(score);    //schmeißt er die GameOverExeption

        points.add(super.currentPoint()); // Fügt den neuen Punkt ein
        if (!food)
            points.remove(0); //Löscht den Letzten Punkt
        else
            score++;

        return super.currentPoint();
    }

    public int getScore() {
        return score;
    }

    private void moveUp() throws GameOverExeption {
        if (GameController.WALL)
            setPoint(super.currentPoint().x, super.currentPoint().y - 1);
        else
            setPoint(super.currentPoint().x, (super.currentPoint().y - 1 + GameController.HEIGHT) % GameController.HEIGHT);

    }

    private void moveDown() throws GameOverExeption {
        if (GameController.WALL)
            setPoint(super.currentPoint().x, super.currentPoint().y + 1);
        else
            setPoint(super.currentPoint().x, (super.currentPoint().y + 1) % GameController.HEIGHT);
    }

    private void moveLeft() throws GameOverExeption {
        if (GameController.WALL)
            setPoint(super.currentPoint().x - 1, super.currentPoint().y);
        else
            setPoint((super.currentPoint().x - 1 + GameController.WIDTH) % GameController.WIDTH, super.currentPoint().y);
    }

    private void moveRight() throws GameOverExeption {
        if (GameController.WALL)
            setPoint(super.currentPoint().x + 1, super.currentPoint().y);
        else
            setPoint((super.currentPoint().x + 1) % GameController.WIDTH, super.currentPoint().y);
    }

    public void remote() {
        points.clear();
        setPoint(2, 2);
        points.add(currentPoint());
        score = 0;

    }

    public List<Point> getPoints() {
        return points;
    }

}
