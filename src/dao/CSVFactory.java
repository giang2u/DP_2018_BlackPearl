package dao;

public class CSVFactory extends AbstractDAOFactory {

    @Override
    public GameDAO getGameDAO() {
        return GameCSVDAO.getInstance();
    }
}
