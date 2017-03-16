public abstract class Agent {
	protected boolean isAlive;
	protected String type;
	protected int x , y;
	
	public Agent(int x, int y, String type){
		isAlive=true;
		this.x=x;
		this.y=y;
		this.type=type;
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
	
	public String getType(){
		return type;
	}
	
	public void die(){
		isAlive=false;
	}
	
	public double distance(Agent a){
		return Math.sqrt((a.x-this.x)*(a.x-this.x)+(a.y-this.y)*(a.y-this.y));
	}

	public void move(int i, int j){
		this.x=(this.x+i)%SpriteDemo.SIZE;
		this.y=(this.y+j)%SpriteDemo.SIZE;
	}

	public abstract void move(Agent a);
	
	
}
