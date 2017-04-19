
public class Gamma extends Agent {

	public Gamma(int x, int y, World monde) {
		super(x, y, "gamma",monde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Agent a) {
		//fuire
		if(a.getType()=="beta")
			this.move((int) Math.signum(getX() - a.getX()), (int) Math.signum(getY() - a.getY()));
		//chasser
		if(a.getType()=="alpha")
			this.move((int) Math.signum(-getX() + a.getX()), (int) Math.signum(-getY() + a.getY()));
		//s'accoupler
		if(getCdRepro()>30)
			if(a.getType()=="gamma")
				this.move((int) Math.signum(-getX() + a.getX()), (int) Math.signum(-getY() + a.getY()));


	}

}
