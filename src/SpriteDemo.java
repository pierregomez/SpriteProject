import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpriteDemo extends JPanel {


	private JFrame frame;
	
	private Image waterSprite;
	private Image water1Sprite;
	private Image water2Sprite;
	private Image water3Sprite;
	private Image water4Sprite;
	private Image water5Sprite;
	private Image water6Sprite;
	private Image water7Sprite;
	private Image water8Sprite;
	private Image grassSprite;
	private Image centerSprite;
	private Image volcanoSprite;
	private Image treeSprite;
	private Image fire1Sprite;
	private Image fire2Sprite;
	private Image fire3Sprite;
	private Image fire4Sprite;
	private Image ashesSprite;
	private Image alphaSprite;
	private Image betaSprite;
	private Image gammaSprite;
	private int spriteLength = 24;
	public static final int SIZE=32;
	private World world = new World(SIZE,SIZE);
	
	public SpriteDemo()
	{
		try
		{
			waterSprite = ImageIO.read(new File("src/water.png"));
			water1Sprite = ImageIO.read(new File("src/water1.png"));
			water2Sprite = ImageIO.read(new File("src/water2.png"));
			water3Sprite = ImageIO.read(new File("src/water3.png"));
			water4Sprite = ImageIO.read(new File("src/water4.png"));
			water5Sprite = ImageIO.read(new File("src/water5.png"));
			water6Sprite = ImageIO.read(new File("src/water6.png"));
			water7Sprite = ImageIO.read(new File("src/water7.png"));
			water8Sprite = ImageIO.read(new File("src/water8.png"));
			treeSprite = ImageIO.read(new File("src/tree.png"));
			grassSprite = ImageIO.read(new File("src/grass.png"));
			volcanoSprite = ImageIO.read(new File("src/grass.png"));
			centerSprite = ImageIO.read(new File("src/center.png"));
			fire1Sprite = ImageIO.read(new File("src/firetree1.png"));
			fire2Sprite = ImageIO.read(new File("src/firetree2.png"));
			fire3Sprite = ImageIO.read(new File("src/firetree3.png"));
			fire4Sprite = ImageIO.read(new File("src/firetree4.png"));
			ashesSprite = ImageIO.read(new File("src/ashes.png"));
			alphaSprite = ImageIO.read(new File("src/alpha.png"));
			betaSprite = ImageIO.read(new File("src/beta.png"));
			gammaSprite = ImageIO.read(new File("src/gamma.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(768,790);
		frame.setVisible(true);
		
	}

	public void paint(Graphics g)
	{
		int [][][] terrain = world.getTerrain();
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = 0 ; i < SIZE ; i++ )
			for ( int j = 0 ; j < SIZE ; j++ )
			{
				g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				switch (terrain[0][i][j]){
					case 0 :
//						if(terrain[0][(i-1+2*SIZE)%SIZE][(j+2*SIZE)%SIZE]==0 && terrain[0][(i-1+2*SIZE)%SIZE][(j+1+2*SIZE)%SIZE]==0 && terrain[0][(i+2*SIZE)%SIZE][(j+1+2*SIZE)%SIZE]==0)
//							g2.drawImage(water1Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
//						else if(terrain[0][(i-1+2*SIZE)%SIZE][(j-1+2*SIZE)%SIZE]==0 && terrain[0][(i-1+2*SIZE)%SIZE][(j+2*SIZE)%SIZE]==0 && terrain[0][(i+2*SIZE)%SIZE][(j-1+2*SIZE)%SIZE]==0)
//							g2.drawImage(water2Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
//						else if(terrain[0][(i+2*SIZE)%SIZE][(j-1+2*SIZE)%SIZE]==0 && terrain[0][(i+1+2*SIZE)%SIZE][(j-1+2*SIZE)%SIZE]==0 && terrain[0][(i+1+2*SIZE)%SIZE][(j+2*SIZE)%SIZE]==0)
//							g2.drawImage(water3Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
//						else if(terrain[0][(i+1+2*SIZE)%SIZE][(j+2*SIZE)%SIZE]==0 && terrain[0][(i+1+2*SIZE)%SIZE][(j+1+2*SIZE)%SIZE]==0 && terrain[0][(i+2*SIZE)%SIZE][(j+1+2*SIZE)%SIZE]==0)
//							g2.drawImage(water4Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
//						else if(terrain[0][(i+2*SIZE)%SIZE][(j-1+2*SIZE)%SIZE]==0 && terrain[0][(i+1+2*SIZE)%SIZE][(j-1+2*SIZE)%SIZE]==0 && terrain[0][(i+1+2*SIZE)%SIZE][(j+2*SIZE)%SIZE]==0)
//							g2.drawImage(water3Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
//						else
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
					case 8 :
						g2.drawImage(volcanoSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 9 :
						g2.drawImage(centerSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
						
				}
			}
		ArrayList<Agent> bis = new ArrayList<Agent>(world.getAgents()); //fix inexplicable à des pb d'arraylist
		for(Agent a : bis){
			if(a.isAlive()){
				if(a.getType().equals("alpha"))
					g2.drawImage(alphaSprite,spriteLength*a.getX(),spriteLength*a.getY(),spriteLength,spriteLength, frame);
				else if(a.getType().equals("beta"))
					g2.drawImage(betaSprite,spriteLength*a.getX(),spriteLength*a.getY(),spriteLength,spriteLength, frame);
				else if(a.getType().equals("gamma"))
					g2.drawImage(gammaSprite,spriteLength*a.getX(),spriteLength*a.getY(),spriteLength,spriteLength, frame);
			}
		}
	}
	
	private void step(){
		repaint();
		world.update();
//		for(int i=0; i<spriteLength/2;i++){
//			for(Agent a : world.getAgents())
//				if(a.isAlive())
//					a.fragMoveSlide();
//			repaint();
//			try {
//				Thread.sleep(delai);
//			} catch (InterruptedException e){}
//		}
		
	}

	public static void main(String[] args) {
		int delai=300;
		SpriteDemo launch = new SpriteDemo();
		for(int i=0 ; i<10000; i++){
			launch.step();
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e){}
			
		}
		
	}
}