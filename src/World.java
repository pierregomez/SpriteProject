import java.util.ArrayList;
public class World {
	private int [][][] terrain; //1ere entreée :
								// 0 : nature
								// 1 : altitude
	private int [][][] nouveauTerrain; //tampon
	public final int DX,DY;
	private ArrayList<Agent> agents;
	double densite = 0.25; // plus faible en Moore
	double population = 50;
	private double pi;	// proba d'incendie spontane
	private double pr1;	// proba de pousse sur herbe
	private double pr2;	// proba de repousse sur cendres
	
	public World(int dx, int dy){
		DX=dx;
		DY=dy;
		pi=0.0001/DX;
		pr1=0.002/DX;
		pr2=0.1/DX;
		agents = new ArrayList<Agent>();
		terrain= new int[2][dx][dy];
		nouveauTerrain= new int[2][dx][dy];
			for(int x=0; x<dx ; x++)
				for(int y=0 ; y<dy ; y++){
					if (Math.random()<densite)
						terrain[0][x][y]=2; // arbre
					else if(Math.random()>0.20)
						terrain[0][x][y]=1; // herbe
					else terrain[0][x][y]=0; //eau
				}
		terrain[0][DX/2][DY/2]=3; //burning tree
		
		for(int i=0; i!= population; i++){
			if(Math.random()<0.33)
				agents.add(new Poule((int)(Math.random()*DX),(int)(Math.random()*DY)));
			else if(Math.random()<0.66)
				agents.add(new Renard((int)(Math.random()*DX),(int)(Math.random()*DY)));
			else
				agents.add(new Vipere((int)(Math.random()*DX),(int)(Math.random()*DY)));
		}
	}
	
	public int[][][] getTerrain(){
		return terrain;
	}
	
	public ArrayList<Agent> getAgents(){
		return agents;
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
	
	public void eat(Agent a){
		for(Agent b : agents){
			if(b.isAlive() && a.getX()==b.getX() && a.getY()==b.getY()){
				if(a.getType()=="poule" && b.getType()=="renard") a.die();
				if(a.getType()=="poule" && b.getType()=="vipere") b.die();
				if(a.getType()=="renard" && b.getType()=="vipere") a.die();
			}
		}
	}
	public void update(){
		// 1 - mise a jour de l'automate (dans le Terrain en tampon)
		for ( int x = 0 ; x != terrain[0].length ; x++ )
			for ( int y = 0 ; y != terrain[0][0].length ; y++ )
			{
				if (terrain[0][x][y]==1){ //herbe
					nouveauTerrain[0][x][y]=1;
					if (Math.random()<pr1)		 //repousses spontanées
						nouveauTerrain[0][x][y]=2;
				}
				if (terrain[0][x][y]==7){ //cendres
					nouveauTerrain[0][x][y]=7;
					if (Math.random()<pr2)		 //repousses spontanées
						nouveauTerrain[0][x][y]=2;
				} 
				if (terrain[0][x][y]==2){ //arbre
					nouveauTerrain[0][x][y]=2;
					for(int i=x-1 ; i<=x+1; i++)
						for(int j=y-1;j<=y+1;j++){					//	propagation du feu, voisingage : MOORE
							if(x==i && y==j) continue;
							if(	terrain[0][(i+DX)%DX][(j+DY)%DY]==3 || terrain[0][(i+DX)%DX][(j+DY)%DY]==4 ||
								terrain[0][(i+DX)%DX][(j+DY)%DY]==5)
								nouveauTerrain[0][x][y]=3;
						}
				}
					if(Math.random()<pi) //incendie spontané
						nouveauTerrain[0][x][y]=3;

				if(terrain[0][x][y]==3)			//evolution du de l'arbre en feu
					nouveauTerrain[0][x][y]=4;
				if(terrain[0][x][y]==4)
					nouveauTerrain[0][x][y]=5;
				if(terrain[0][x][y]==5)
					nouveauTerrain[0][x][y]=6;
				if(terrain[0][x][y]==6)
					nouveauTerrain[0][x][y]=7;
			}
		
		// 2 - met a jour le Terrain affichable
		
		for ( int x = 0 ; x != terrain[0].length ; x++ )
			for ( int y = 0 ; y != terrain[0][0].length ; y++ )
				terrain[0][x][y] = nouveauTerrain[0][x][y];
			
		for(Agent a : agents){
			eat(a);						//manger la proie s'ils sont sur la meme case 
			a.move(getAgentLePlusProche(a));
			eat(a);
		}
	}
	
	
	
}
