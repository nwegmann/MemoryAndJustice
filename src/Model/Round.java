package Model;

import java.util.ArrayList;
import java.util.Random;

import static Model.Strategies.Strategy.*;
import static Model.Strategies.Strategy.Move.*;
import static Model.Payoff.*;

public class Round {
    static Random rand = new Random();

    public Round(ArrayList<Player> players) {
        int nbPlayers = players.size();
        Player p1 = players.get((rand.nextInt() & Integer.MAX_VALUE) % nbPlayers);
        Player p2 = p1.getRandomNeighbor();
        playPD(p1,p2);
        }

    private void playPD(Player p1, Player p2) {
        Move p1Move = p1.nextMove(p2);
        Move p2Move = p2.nextMove(p1);
        if(p1Move == COOPERATE) {
            if (p2Move == COOPERATE) {
                p1.incrementScore(REWARD);
                p2.incrementScore(REWARD);
            }if(p2Move == DEFECT){
                p1.incrementScore(SUCKER);
                p2.incrementScore(TEMPTATION);
                p1.gotDefectedBy(p2);
            }
        }
        if(p1Move == DEFECT){
            if(p2Move == DEFECT){
                p1.incrementScore(PUNISHMENT);
                p2.incrementScore(PUNISHMENT);
                p1.gotDefectedBy(p2);
                p2.gotDefectedBy(p1);
            }if(p2Move == COOPERATE){
                p1.incrementScore(TEMPTATION);
                p2.incrementScore(SUCKER);
                p2.gotDefectedBy(p1);
            }
        }
    }
}
