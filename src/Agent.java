public abstract class Agent {
	protected boolean isAlive;
	protected int cdRepro; //cooldown sur la repoduction, fertile quand  <0
	protected int hunger;
	protected String type;
	protected World monde;
	protected int x , y, xpre, ypre, xvis, yvis;
	
	public Agent(int x, int y, String type, World monde){
		isAlive=true;
		this.x=xpre=x;
		this.y=ypre=y;
		this.type=type;
		this.monde=monde;
		cdRepro=0;
		hunger=0;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public int getXVis(){
		return xvis;
	}
	
	public int getYVis(){
		return yvis;
	}
	
	
	public String getType(){
		return type;
	}
	
	public void die(){
		isAlive=false;
		System.out.println("mort "+type);
	}
	
	public void naitre(){
		isAlive=true;
		cdRepro=200;		//les enfants ne peuvent pas se reproduire tout de suite
		hunger=0;
	}
	
	public void resetCd(){
		cdRepro=100;
	}
	
	public int getCdRepro(){
		return cdRepro;
	}
	
	public int getHunger(){
		return hunger;
	}
	
	public void coolDown(){
		cdRepro--;
	}
	
	public void updateCpts(){
		coolDown();
		hunger++;
	}
	
	public double distance(Agent a){
		return Math.sqrt((a.x-this.x)*(a.x-this.x)+(a.y-this.y)*(a.y-this.y));
	}

	public void move(int i, int j){
		if(isAlive){
			xpre=x; ypre=y;
			this.x=(this.x+i+SpriteDemo.SIZE)%SpriteDemo.SIZE;
			this.y=(this.y+j+SpriteDemo.SIZE)%SpriteDemo.SIZE;
		}
	}
	
	public void fragMoveSlide(){ // connaitre la direction du deplacement
		if(xpre == x-1) xvis++;
		else if(ypre == y-1) yvis++;
		else if(xpre == x+1) xvis--;
		else if(ypre == y+1) yvis--;
		else{
			xvis = x*12;
			yvis = y*12;
		}
	}
	
	public void wonder(){
		move((int)(Math.random()*3)-1,(int)(Math.random()*3)-1);
	}
	
	public Agent getAgentLePlusProche(){
		double distMin=5;
		Agent closest=null;
		for(Agent a : monde.getAgents()){
			if(!a.isAlive()) continue;
			if(a==this) continue;	//ne pas se compter soi meme
			if(a.getType() == type && a.getCdRepro()>0 && cdRepro>0 ) continue;
			if(distance(a)<distMin){
				closest=a;
				distMin=distance(a);
			}
		}
		return closest;
	}
	
	public void reproduce(Agent a){
		a.resetCd();
		resetCd();
		for(Agent b : monde.getAgents()){			//on cherche à remplacer un agent mort
			if(!b.isAlive() && b.getType().equals(type)){		//recyclage
				b.setX(x);
				b.setY(y);
				b.naitre();	//ou plutot ressuciter...
				System.out.println("naissance "+type);
				return;
			}
		}
		if(type.equals("alpha"))
			monde.newBirth(new Alpha(a.getX(),a.getY(), monde));			//si on en a pas, on le creer dans une liste tampon,
		else if(type.equals("beta"))										//car on ne peut pas modifier l'arraylist qu'on parcourt
			monde.newBirth(new Beta(a.getX(),a.getY(), monde));
		else if(type.equals("gamma"))
			monde.newBirth(new Gamma(a.getX(),a.getY(), monde));
	}
	
	public void interact(){
		for(Agent a : monde.getAgents()){
			if(this !=a && a.isAlive() && a.getX()==x && a.getY()==y){	//bien comparer les references et pas les valeurs 	-> =  pas equals
				if(	type.equals("alpha") && a.getType().equals("beta") 	||
					type.equals("beta") && a.getType().equals("gamma") ||
					type.equals("gamma") && a.getType().equals("alpha")	){
					a.die();			//manger la proie
					hunger=0;		//satiété
					return;				//ne pas manger d'autre proie meme s'il y en a sur la meme case
				}
				if(a.getType().equals(type) && a.getCdRepro()<0 && cdRepro<0){ //reproduction
					reproduce(a);
				}
			}
		}
	}

	public abstract void move(Agent a);
	
	
}
