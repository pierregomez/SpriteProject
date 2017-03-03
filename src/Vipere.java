
public class Vipere extends Agent {

	public Vipere(int x, int y) {
		super(x, y, "vipere");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Agent a) {
		if(a.getType()=="renard")
			this.move((int) Math.signum(getX() - a.getX()), (int) Math.signum(getY() - a.getY()));

		if(a.getType()=="poule")
			this.move((int) Math.signum(-getX() + a.getX()), (int) Math.signum(-getY() + a.getY()));

	}

}
