
public class Poule extends Agent {

	public Poule(int x, int y) {
		super(x, y, "poule");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Agent a) {
		if(a.getType()=="renard")
			this.move((int) Math.signum(getX() - a.getX()), (int) Math.signum(getY() - a.getY()));
		
		if(a.getType()=="vipere")
			this.move((int) Math.signum(-getX() + a.getX()), (int) Math.signum(-getY() + a.getY()));

	}
	
}
