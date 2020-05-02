import java.awt.Point;

public abstract class GameObjekt {
	private Point p;
	
	public GameObjekt(Point p) {
		this.p = p;
	}
	public GameObjekt() {
		
	}
	
	public Point aktuellerPunkt() {
		return p;
	}
	public void setPoint(int x,int y) {
		this.p= new Point(x,y);
	}
}
