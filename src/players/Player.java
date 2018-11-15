package players;

public abstract class Player {
	
	private final static int SIZE = 10;
	
	protected int [][] shipGrill = new int[SIZE][SIZE];
	protected int [][] historyGrill = new int[SIZE][SIZE];
	protected String playerName ="";
	protected static int nbTireToucher = 0;
	protected static int nbTireMiss = 0;
	
	public Player(String name){
		this.playerName = name;
	}
}
