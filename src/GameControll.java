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

public class GameControll implements ActionListener, KeyListener{
	private Dimension screenSize;
	private Snake s;
	private Food f;
	private Map map; // Spielfeld auf dem sich die Snake bewegen soll
	private JFrame frame;
	private int direction;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;	
	public static int HIGHT, WIDHT,SCALE;
	public static boolean WALL;
	public Timer timer;
	private boolean pause, gameover, change;
	private boolean hasFood;
	private EingabeFrame eingabe;
	public GameControll() {
		eingabe = new EingabeFrame(this);
		s = new Snake();
		f= new Food();
		map = new Map(s,f);
		frame = new JFrame();
		frame.add(map);
		frame.addKeyListener(this);
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pause = true;
		gameover=true;
		hasFood=false;
		change=false;
		
	}
	public static void main(String[] args) {
		new GameControll();	
	}
	
	public List<Point> getSnake() {
		return s.getPoints();
	}

	public void startGame(boolean newstart) {
		timer = new Timer(eingabe.getBuffer(),this);
		timer.start();
		pause=newstart;
		gameover=false;
		direction = DOWN; 
		s.remote();;
		f.newFood();
		hasFood=false;
		HIGHT=eingabe.getHidht();
		WIDHT=eingabe.getWidht();
		SCALE=eingabe.getScale();
		WALL = eingabe.getWall();
		
		frame.setSize((int)(WIDHT*SCALE+22), (int)(HIGHT*SCALE+56));

		frame.setLocation(((int)screenSize.getWidth()-frame.getWidth())/2, ((int)screenSize.getHeight()-frame.getHeight())/2);
		frame.setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int i=e.getKeyCode();
		if(i==KeyEvent.VK_SPACE)
			if(gameover)
				startGame(false);
			else	
				pause = !pause;
		if(!change) {
			switch(i) {
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT: if(direction!=RIGHT)	
										direction =LEFT;break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT: if(direction!=LEFT)	
										direction = RIGHT;break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN: if(direction!=UP)	
										direction = DOWN;break;
			case KeyEvent.VK_W: 
			case KeyEvent.VK_UP: if(direction!=DOWN)	
										direction = UP;break;
			}
			change=true;
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
		if(!pause) {
			map.repaint();
			change=false;
			if(hasFood) {
				f.newFood();
			}
			try {
				s.move(hasFood,direction);
			} catch (GameOverExeption e) {
				timer.stop();
				gameover =true;
			}
			hasFood=f.aktuellerPunkt().equals(s.aktuellerPunkt());
		}
	}
}
