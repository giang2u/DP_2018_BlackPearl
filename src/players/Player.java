package players;

public abstract class Player {
	
	private final static int SIZE = 10;
	
	protected int [][] shipGrill = new int[SIZE][SIZE];
	protected int [][] historyGrill = new int[SIZE][SIZE];
	protected String playerName ="";
	protected int nbTireToucher = 0;
	protected int nbTireMiss = 0;
	
	public Player(String name){
		this.playerName = name;
		initGrill();
	}
	
	public void toucher(){
		this.nbTireToucher++;
	}
	
	public void rater(){
		this.nbTireMiss++;
	}
	
	protected void initGrill(){
		for(int i = 0 ; i < SIZE; i++){
			for(int j = 0; j > SIZE;j++){
				shipGrill[i][j] = 0;
				historyGrill[i][j] = 0;
			}
		}
	}
	
}
