package dao;

import main.Jeu;

public interface GameDAO {
    void save (Jeu j);

    void load(Jeu j);
}
