import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;
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
		g.fillRect(0, 0, GameControll.WIDHT*GameControll.SCALE, GameControll.HIGHT*GameControll.SCALE);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,20));

		g.drawString("Score: "+s.getScore(), GameControll.WIDHT*GameControll.SCALE-100,20);
		
		g.setColor(Color.WHITE);
		for(Point p : s.getPoints()) {
			//g.setColor(Color.WHITE);
			//g.fillOval(p.x*GameControll.SCALE, p.y*GameControll.SCALE, GameControll.SCALE+2, GameControll.SCALE+3);
			g.setColor(Color.ORANGE);
			g.drawOval(p.x*GameControll.SCALE-1, p.y*GameControll.SCALE-1, GameControll.SCALE+2, GameControll.SCALE+2);
			g.drawOval(p.x*GameControll.SCALE+1, p.y*GameControll.SCALE+1, GameControll.SCALE-2, GameControll.SCALE-2);
			//g.fill3DRect(p.x*GameControll.SCALE, p.y*GameControll.SCALE, GameControll.SCALE, GameControll.SCALE,true);
		}
		g.setColor(Color.YELLOW);
		if(f.aktuellerPunkt()!=null)
			g.fillOval(f.aktuellerPunkt().x*GameControll.SCALE, f.aktuellerPunkt().y*GameControll.SCALE, GameControll.SCALE, GameControll.SCALE);
	
	}
	
	
}
