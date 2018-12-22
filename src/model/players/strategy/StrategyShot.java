package model.players.strategy;

import model.players.Player;

public interface StrategyShot {

    void shot(Player p);

    int getXattack();

    int getYattack();
}
