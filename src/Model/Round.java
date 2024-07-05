package Model;

import java.util.ArrayList;
import java.util.Random;

import static Model.Strategy.*;
import static Model.Strategy.Move.*;
import static Model.Payoff.*;


public class Round {

    public Round(ArrayList<Player> players) {
        int nbPlayers = players.size();
        Random rand = new Random();
        Player p1 = players.get((rand.nextInt() & Integer.MAX_VALUE) % nbPlayers);
        Player p2 = p1.getNeighbours().get((rand.nextInt() & Integer.MAX_VALUE)%p1.getNeighbours().size());
        playPD(p1,p2);
        }

    private void playPD(Player p1, Player p2) {
        Move p1Move = p1.getStrategy();
        Move p2Move = p2.getStrategy();
        if(p1Move == COOPERATE) {
            if (p2Move == COOPERATE) {
                p1.incrementScore(REWARD);
                p2.incrementScore(REWARD);
            } else {
                p1.incrementScore(SUCKER);
                p2.incrementScore(TEMPTATION);
            }
        }
        if(p1Move == DEFECT){
            if(p2Move == DEFECT){
                p1.incrementScore(PUNISHMENT);
                p2.incrementScore(PUNISHMENT);
            }else{
                p1.incrementScore(TEMPTATION);
                p2.incrementScore(SUCKER);
            }
        }
    }
}
