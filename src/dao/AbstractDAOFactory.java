package dao;

public abstract class AbstractDAOFactory {

    //Type d'objet
    public final static int CSV = 1;

        public abstract GameDAO getGameDAO ();

        public static AbstractDAOFactory getAbstractDAOFactory(int format){

            AbstractDAOFactory abstractDAOFactory = null;

            switch (format){
                case CSV : abstractDAOFactory = new CSVFactory();break;
            }
            return  abstractDAOFactory;
        }
}
