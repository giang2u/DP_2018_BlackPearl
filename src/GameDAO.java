import main.Jeu;


public interface GameDAO {
    void save (Jeu j);

    Jeu load(Jeu j);
}
