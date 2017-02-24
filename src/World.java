import java.util.ArrayList;
public class World {
	private int [][] terrain;
	private int [][] altitude;
	public final int DX,DY;
	private ArrayList<Agent> agents;
	
	public World(int dx, int dy){
		DX=dx;
		DY=dy;
		agents = new ArrayList<Agent>();
		terrain= new int[dx][dy];
		for(int i=0; i<dx ; i++){
			for(int j=0 ; j<dy ; j++){
				terrain[i][j]=(int)(Math.random()*2.0+1);				
			}
		}
		terrain[DX/2][DY/2]=3; //burning tree
	}
	
	public int[][] getTerrain(){
		return terrain;
	}
	
	public Agent getAgentLePlusProche(Agent requete){
		double distMin=2*DX;
		Agent closest=requete; //mauvaise idee mais c'est juste pour init
		for(Agent a : agents){
			if(!a.isAlive()) continue;
			if(a==requete) continue;
			if(a.distance(requete)<distMin){
				closest=a;
				distMin=a.distance(requete);
			}
		}
		return closest;
	}
	
	public void update(){
		
	}
	
	
	
}
