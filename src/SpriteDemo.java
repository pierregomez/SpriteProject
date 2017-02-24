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
	private Image fireSprite;
	private int spriteLength = 64;
	private World world = new World(64,64);
	public SpriteDemo()
	{
		try
		{
			waterSprite = ImageIO.read(new File("src/water.png"));
			treeSprite = ImageIO.read(new File("src/tree.png"));
			grassSprite = ImageIO.read(new File("src/grass.png"));
			fireSprite = ImageIO.read(new File("src/fire.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(900,900);
		frame.setVisible(true);
		
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < world.getDx() ; i++ )
			for ( int j = 0 ; j < world.getDy() ; j++ )
			{
				switch (world.getTerrain()[i][j]){
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
					g2.drawImage(fireSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				}
			}
	}
	
	private void step(){
		repaint();
		world.step();
	}

	public static void main(String[] args) {

		SpriteDemo launch = new SpriteDemo();
		for(int i=0 ; i<10000; i++){
			launch.step();
		}
		
	}
}