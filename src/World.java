import java.util.ArrayList;
public class World {
	private int [][] terrain;
	private int [][] altitude;
	protected int dx, dy;
	private ArrayList<Agent> agents;
	
	public World(int dx, int dy){
		this.dx=dx;
		this.dy=dy;
		agents = new ArrayList<Agent>();
		terrain= new int[dx][dy];
		for(int i=0; i<dx ; i++){
			for(int j=0 ; j<dy ; j++){
				terrain[i][j]=(int)(Math.random()*3.0);
				
			}
		}
	}
	
	public int getDx(){
		return dx;
	}
	
	public int getDy(){
		return dy;
	}
	
	public int[][] getTerrain(){
		return terrain;
	}
	
	public void step(){
		
	}
	
	
	
}
