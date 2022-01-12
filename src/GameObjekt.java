import java.awt.Point;

public abstract class GameObjekt {
    private Point point;

    public GameObjekt(Point point) {
        this.point = point;
    }

    public GameObjekt() {

    }

    public Point currentPoint() {
        return point;
    }

    public void setPoint(int x, int y) {
        this.point = new Point(x, y);
    }
}
