package Model;

import Model.Strategies.Grudge;
import Model.Strategies.GrudgeStar;
import Model.Strategies.Strategy;
import java.util.ArrayList;
import java.util.Random;

import static Model.Game.*;
import static Model.Strategies.Strategy.StrategyType;
import static Model.Strategies.Strategy.*;



public class Player {
    int[] defectorsArray;
    Strategy strategy;

    Game game;
    Position pos;
    int score = 0;

    int playerIndex;
    public Player(int playerIndex, Game game) {
        //defectorsList = new HashMap<>();
        this.game = game;
        defectorsArray = new int[game.nbOfPlayers];
        //DEFAULT STRATEGY
        strategy = new Grudge(this);
        this.playerIndex = playerIndex;
    }

    public Player getRandomNeighbour() {
        Random rand = new Random();
        int index = ((rand.nextInt() & Integer.MAX_VALUE) % game.nbOfNeighbours) - game.nbOfNeighbours / 2;
        if (this.playerIndex + index < 0) {
            return game.playingField[game.nbOfPlayers + index];
        }
        if (index + this.playerIndex >= game.nbOfPlayers) {
            index = (index + this.playerIndex) - game.nbOfPlayers;
            return game.playingField[index];
        }
        return game.playingField[this.playerIndex + index];
    }

    public int[] getDefectorsArray(){
        return defectorsArray;
    }

    public void incrementScore(Payoff payoff) {
        score = score + payoff.getReward();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public StrategyType getStrategyType(){
        return this.getStrategy().getStrategyType();
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public Move nextMove(Player opponent){
        if(this.strategy == null){
            System.out.println("Strategy not initialized");
            return null;
        }
        return this.strategy.nextMove(opponent);
    }

    public void gotDefectedBy(Player defector) {
        defectorsArray[defector.playerIndex] = game.memory;
    }

    public int getScore() {
        return score;
    }

    public boolean isDefector(){
        return (this.strategy.getStrategyType() == StrategyType.ALWAYS_DEFECT);
    }

    public void initializeDefectors() {
        for(int i = 1; i <= game.memory; i++){
            Player p = getRandomNeighbour();
            if(p.isDefector()){
                defectorsArray[p.playerIndex]= i;
            }
        }
    }
}
