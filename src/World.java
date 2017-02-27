import java.util.ArrayList;
public class World {
	private int [][][] terrain; //1ere entreée :
								// 0 : nature
								// 1 : altitude
	private int [][][] nouveauTerrain; //tampon
	public final int DX,DY;
	private ArrayList<Agent> agents;
	double densite = 0.55; //0.55; // seuil de percolation à 0.55
	double population = 0.33;
	private double pi;	// proba d'incendie spontane
	private double pr1;	// proba de repousse sur herbe
	
	public World(int dx, int dy){
		DX=dx;
		DY=dy;
		pi=0.0001/DX;
		pr1=0.002/DX;
		agents = new ArrayList<Agent>();
		terrain= new int[2][dx][dy];
		nouveauTerrain= new int[2][dx][dy];
			for(int x=0; x<dx ; x++)
				for(int y=0 ; y<dy ; y++){
					terrain[0][x][y]=(int)(Math.random()*2+1); //(0 eau) 1 herbe 2 arbre
				}
		terrain[0][DX/2][DY/2]=3; //burning tree
	}
	
	public int[][][] getTerrain(){
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
		// 1 - mise a jour de l'automate (dans le Terrain en tampon)
		for ( int x = 0 ; x != terrain[0].length ; x++ )
			for ( int y = 0 ; y != terrain[0][0].length ; y++ )
			{
				if (terrain[0][x][y]==1){ //herbe
					nouveauTerrain[0][x][y]=1;
					if (Math.random()<pr1) //repousse spontanée
						nouveauTerrain[0][x][y]=2;
				}
				if (terrain[0][x][y]==2){ //arbre
					nouveauTerrain[0][x][y]=2;
					if (terrain[0][(x-1+DX)%DX][y]==3 || terrain[0][(x+1+DX)%DX][y]==3 ||
						terrain[0][x][(y-1+DY)%DY]==3 || terrain[0][x][(y+1+DY)%DY]==3 ||
						terrain[0][(x-1+DX)%DX][y]==4 || terrain[0][(x+1+DX)%DX][y]==4 || 	//		propagation du feu
						terrain[0][x][(y-1+DY)%DY]==4 || terrain[0][x][(y+1+DY)%DY]==4 ||
						terrain[0][(x-1+DX)%DX][y]==5 || terrain[0][(x+1+DX)%DX][y]==5 ||
						terrain[0][x][(y-1+DY)%DY]==5 || terrain[0][x][(y+1+DY)%DY]==5){
							nouveauTerrain[0][x][y]=3;
					}
					if(Math.random()<pi) //incendie spontané
						nouveauTerrain[0][x][y]=2;
				}
				if(terrain[0][x][y]==3)
					nouveauTerrain[0][x][y]=4;
				if(terrain[0][x][y]==4)
					nouveauTerrain[0][x][y]=5;
				if(terrain[0][x][y]==5)
					nouveauTerrain[0][x][y]=6;
			}
		
		// 2 - met a jour le Terrain affichable
		
		for ( int x = 0 ; x != terrain[0].length ; x++ )
			for ( int y = 0 ; y != terrain[0][0].length ; y++ )
				terrain[0][x][y] = nouveauTerrain[0][x][y];
	}
	
	
	
}
