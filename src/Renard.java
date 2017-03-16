
public class Renard extends Agent {

	public Renard(int x, int y) {
		super(x, y, "renard");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Agent a) {
		if(a.getType()=="vipere")
			this.move((int) Math.signum(getX() - a.getX()), (int) Math.signum(getY() - a.getY()));
		
		if(a.getType()=="poule")
			this.move((int) Math.signum(-getX() + a.getX()), (int) Math.signum(-getY() + a.getY()));

	}

}
