package Model;

import Model.Strategies.Grudge;
import Model.Strategies.GrudgeStar;
import Model.Strategies.Strategy;
import java.util.Random;

import static Model.Strategies.Strategy.StrategyType;
import static Model.Strategies.Strategy.*;



public class Player {
    int[] defectorsArray;
    Strategy strategy;

    Game game;
    int score = 0;

    int playerIndex;
    public Player(int playerIndex, Game game, boolean grudgeStar) {
        this.game = game;
        defectorsArray = new int[game.nbOfPlayers];
        //DEFAULT STRATEGY
        strategy = grudgeStar ? new GrudgeStar(this) : new Grudge(this);
        this.playerIndex = playerIndex;
    }

    public Player getRandomNeighbor() {
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

    public Game getGame(){return game;}

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
        this.strategy.initializeDefectors(this);
    }
}
