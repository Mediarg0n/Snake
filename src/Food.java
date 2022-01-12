import java.awt.Point;

public class Food extends GameObjekt {
	public Food() {
		super();
	}

	public Point getPoint() {
		return aktuellerPunkt();
	}

	public void newFood() {
		setPoint((int)(Math.random()*GameController.WIDTH),(int)(Math.random()*GameController.HEIGHT));
	}
	
	
}
