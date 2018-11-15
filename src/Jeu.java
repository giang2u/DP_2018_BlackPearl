public class Jeu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void loadGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().load(this);
	}

	public void saveGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().save(this);
	}
}
