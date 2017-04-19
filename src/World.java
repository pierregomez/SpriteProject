import java.util.ArrayList;

public class World {
	private int [][][] terrain; //1ere entreée :
								// 0 : element nature
								// 1 : altitude
	private int [][][] nouveauTerrain; //tampon
	public final int DX,DY;
	private static ArrayList<Agent> agents;
	private ArrayList<Agent> births;	//tampon pour les naissances
	double densite = 0.25; //densitee d'arbre plus faible en Moore
	double population = 50;
	private double pi;	// proba d'incendie spontane
	private double pr1;	// proba de pousse sur herbe
	private double pr2;	// proba de repousse sur cendres
	private double pre; // proba d'eruption
	private boolean onEruption;
	
	
	public World(int dx, int dy){
		DX=dx;
		DY=dy;
		pi=0.0001/DX;
		pr1=0.002/DX;
		pr2=0.1/DX;
		pre=0.1;
		onEruption=false;
		agents = new ArrayList<Agent>();
		births = new ArrayList<Agent>();
		terrain= new int[2][dx][dy];
		nouveauTerrain= new int[2][dx][dy];
		for(int x=0; x<dx ; x++){
			for(int y=0 ; y<dy ; y++){
				if (Math.random()<densite)
					terrain[0][x][y]=2; 	// arbre
				else if(Math.random()>0.20)
					terrain[0][x][y]=1; 	// herbe
				else terrain[0][x][y]=0;	// eau
			}
		}
		
		
//		for(int i=DX/3; i<DX-DX/3;i++){
//			for(int j=DY/3; j<DY-DY/3;j++)
//				if(Math.random()>(Math.sqrt((i-DX/2)*(i-DX/2)+(j-DY/2)*(j-DY/2)))/10) //distance par rapport au centre du volcan
//					terrain[0][i][j]=8; //volcan
//		}
//		for(int i=DX/3; i<DX-DX/3;i++){
//			for(int j=DY/3; j<DY-DY/3;j++){
//				if(terrain[0][i][j]!=8 && terrain[0][i][j-1]==8 && terrain[0][i][j+1]==8 && terrain[0][i-1][j]==8 && terrain[0][i+1][j]==8)
//					terrain[0][i][j]=8;
//				else if(terrain[0][i][j]==8 && terrain[0][i][j-1]!=8 && terrain[0][i][j+1]!=8 && terrain[0][i-1][j]!=8 && terrain[0][i+1][j]!=8)
//					terrain[0][i][j]=1;
//			}
//		}
		terrain[0][DX/2][DY/2]=9; 		//centre du volcan
		
		terrain[0][DX/2][DY/2]=3;			// burning tree
		
		for(int i=0; i!= population; i++){		//creation des agents
			if(Math.random()<0.33)
				agents.add(new Alpha((int)(Math.random()*DX),(int)(Math.random()*DY),this));
			else if(Math.random()<0.66)
				agents.add(new Beta((int)(Math.random()*DX),(int)(Math.random()*DY),this));
			else
				agents.add(new Gamma((int)(Math.random()*DX),(int)(Math.random()*DY),this));
		}
	}
	
	public int[][][] getTerrain(){
		return terrain;
	}
	
	public ArrayList<Agent> getAgents(){
		return agents;
	}
	
	public void newBirth(Agent a){
		births.add(a);
	}
	
	public void eruption(){
		onEruption=true;
	}
	
	
	public void update(){
		// 1 - mise a jour de l'automate (dans le Terrain en tampon)
		for ( int x = 0 ; x != terrain[0].length ; x++ )
			for ( int y = 0 ; y != terrain[0][0].length ; y++ )
			{
				if (terrain[0][x][y]==1){ //herbe
					nouveauTerrain[0][x][y]=1;
					if (Math.random()<pr1)		 //pousses spontanées
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
						for(int j=y-1;j<=y+1;j++){			//	propagation du feu, voisingge : MOORE, plus realiste mais plus rapide
							if(x==i && y==j) continue;
							if(	terrain[0][(i+DX)%DX][(j+DY)%DY]==3 || terrain[0][(i+DX)%DX][(j+DY)%DY]==4 ||
								terrain[0][(i+DX)%DX][(j+DY)%DY]==5)
								nouveauTerrain[0][x][y]=3;
						}
				}
				if(Math.random()<pi) //incendie spontané
						nouveauTerrain[0][x][y]=3;

				if(terrain[0][x][y]==3)			//evolution de l'arbre en feu
					nouveauTerrain[0][x][y]=4;
				if(terrain[0][x][y]==4)
					nouveauTerrain[0][x][y]=5;
				if(terrain[0][x][y]==5)
					nouveauTerrain[0][x][y]=6;
				if(terrain[0][x][y]==6)
					nouveauTerrain[0][x][y]=7; //cendres
			}
		
		// 2 - met a jour le Terrain affichable
		
		for ( int x = 0 ; x != terrain[0].length ; x++ )
			for ( int y = 0 ; y != terrain[0][0].length ; y++ )
				terrain[0][x][y] = nouveauTerrain[0][x][y];
		
		if(onEruption){
			terrain[0][(int)(Math.random()*DX)][(int)(Math.random()*DY)]=6; // chute de rochers
		}
		else if (Math.random()<pre){
			eruption();
		}

		for(Agent a : agents){
			if(a.isAlive()){
				if(a.getHunger()>200 || terrain[0][a.getX()][a.getY()]==3 || terrain[0][a.getX()][a.getY()]==4 ||
						terrain[0][a.getX()][a.getY()]==5)		//mourir de faim ou brulé
					a.die();
				Agent alpp=a.getAgentLePlusProche();
				a.interact();						//interagir avec un agent s'ils sont sur la meme case
				if(Math.random()>0.2){ //maj asynchrone randomisée pour les deplacements
					if(alpp==null)
						a.wonder();
					else
						a.move(alpp);
				}
				a.interact();
				a.updateCpts(); //compteurs : faim, reproduction
			}
		}
		
		agents.addAll(births);		//ajouter les naissances
		births.clear();				//vider le tampon
//		System.out.println(agents.size()+" agents");
	}
	
	
	
}
