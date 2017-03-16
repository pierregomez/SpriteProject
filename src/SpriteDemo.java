import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpriteDemo extends JPanel {


	private JFrame frame;
	
	private Image waterSprite;
	private Image grassSprite;
	private Image treeSprite;
	private Image fire1Sprite;
	private Image fire2Sprite;
	private Image fire3Sprite;
	private Image fire4Sprite;
	private Image ashesSprite;
	private Image foxSprite;
	private Image chickenSprite;
	private Image snakeSprite;
	private int spriteLength = 32;
	public static final int SIZE=30;
	private World world = new World(SIZE,SIZE);
	public SpriteDemo()
	{
		try
		{
			waterSprite = ImageIO.read(new File("src/water1.png"));
			treeSprite = ImageIO.read(new File("src/tree1.png"));
			grassSprite = ImageIO.read(new File("src/grass.png"));
			fire1Sprite = ImageIO.read(new File("src/fire11.png"));
			fire2Sprite = ImageIO.read(new File("src/fire21.png"));
			fire3Sprite = ImageIO.read(new File("src/fire31.png"));
			fire4Sprite = ImageIO.read(new File("src/fire41.png"));
			ashesSprite = ImageIO.read(new File("src/ashes1.png"));
			foxSprite = ImageIO.read(new File("src/fox.png"));
			chickenSprite = ImageIO.read(new File("src/chicken.png"));
			snakeSprite = ImageIO.read(new File("src/snake.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(960,960);
		frame.setVisible(true);
		
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < world.DX ; i++ )
			for ( int j = 0 ; j < world.DY ; j++ )
			{
				switch (world.getTerrain()[0][i][j]){
				case 0 :
					g2.drawImage(waterSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				case 1 :
					g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				case 2 :
					g2.drawImage(treeSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				case 3 :
					g2.drawImage(fire1Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				case 4 :
					g2.drawImage(fire2Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				case 5 :
					g2.drawImage(fire3Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				case 6 :
					g2.drawImage(fire4Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				case 7 :
					g2.drawImage(ashesSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
					break;
				}
			}
		for(Agent a : world.getAgents()){
			if(a.isAlive()){
				if(a.getType()=="poule")
					g2.drawImage(chickenSprite,spriteLength*a.getX(),spriteLength*a.getY(),spriteLength,spriteLength, frame);
				else if(a.getType()=="renard")
					g2.drawImage(foxSprite,spriteLength*a.getX(),spriteLength*a.getY(),spriteLength,spriteLength, frame);
				else
					if(a.getType()=="vipere")
						g2.drawImage(snakeSprite,spriteLength*a.getX(),spriteLength*a.getY(),spriteLength,spriteLength, frame);
			}
		}
	}
	
	private void step(){
		repaint();
		world.update();
	}

	public static void main(String[] args) {

		SpriteDemo launch = new SpriteDemo();
		for(int i=0 ; i<10000; i++){
			launch.step();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e){}
		}
		
	}
}