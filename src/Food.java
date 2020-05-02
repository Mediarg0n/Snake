import java.awt.Point;
import java.util.List;

public class Food extends GameObjekt {
	public Food() {
		super();
		//newFood();
	}
	public Point getPoint() {
		return aktuellerPunkt();
	}
	public void newFood() {
		setPoint((int)(Math.random()*GameControll.WIDHT),(int)(Math.random()*GameControll.HIGHT));
	}
	
	
}
