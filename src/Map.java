import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

//Das Spielfeld
public class Map extends JPanel{
	private Snake s;
	private Food f;
	public Map(Snake s, Food f) {
		this.s=s;
		this.f=f;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameController.WIDTH *GameController.SCALE, GameController.HEIGHT *GameController.SCALE);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,20));

		g.drawString("Score: "+s.getScore(), GameController.WIDTH *GameController.SCALE-100,20);
		
		g.setColor(Color.WHITE);
		for(Point p : s.getPoints()) {
			g.setColor(Color.ORANGE);
			g.drawOval(p.x*GameController.SCALE-1, p.y*GameController.SCALE-1, GameController.SCALE+2, GameController.SCALE+2);
			g.drawOval(p.x*GameController.SCALE+1, p.y*GameController.SCALE+1, GameController.SCALE-2, GameController.SCALE-2);
		}
		g.setColor(Color.YELLOW);
		if(f.aktuellerPunkt()!=null)
			g.fillOval(f.aktuellerPunkt().x*GameController.SCALE, f.aktuellerPunkt().y*GameController.SCALE, GameController.SCALE, GameController.SCALE);
	
	}
	
	
}
