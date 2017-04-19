
public class Alpha extends Agent {

	public Alpha(int x, int y, World monde) {
		super(x, y, "alpha", monde);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Agent a) {
		//fuire
		if(a.getType()=="gamma")
			this.move((int) Math.signum(getX() - a.getX()), (int) Math.signum(getY() - a.getY()));
		//chasser
		if(a.getType()=="beta")
			this.move((int) Math.signum(-getX() + a.getX()), (int) Math.signum(-getY() + a.getY()));
		//s'accoupler
		if(getCdRepro()>30)
			if(a.getType()=="alpha")
				this.move((int) Math.signum(-getX() + a.getX()), (int) Math.signum(-getY() + a.getY()));

	}
	
}
