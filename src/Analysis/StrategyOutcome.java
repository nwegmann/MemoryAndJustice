package Analysis;

import Model.Game;
import Model.Player;
import Model.Strategies.Strategy.StrategyType;

public class StrategyOutcome {
    int nbOfPlayers = 0;
    double avgScore = 0;
    long totalScore = 0;

    Game game;

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public int getNbOfPlayers() {
        return nbOfPlayers;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public long getTotalScore() {
        return totalScore;
    }

    StrategyType strategyType;

    public StrategyOutcome(StrategyType s, Game game) {
        this.game = game;
        strategyType = s;
    }

    public void integratePlayer(Player p) {
        nbOfPlayers++;
        totalScore += p.getScore();
        avgScore = (double) totalScore /nbOfPlayers;
    }

    @Override
    public String toString() {
        return game.proportionOfDefectors + "," + game.nbOfNeighbours + "," + game.memory + "," + strategyType + "," + avgScore;


//        return "{proportionDefectors=" + game.proportionOfDefectors +
//                ", memory=" + game.memory +
//                ", strategyType=" + strategyType +
//                ", nbOfPlayers=" + nbOfPlayers +
//                ", avgScore=" + avgScore +
//                ", totalScore=" + totalScore +
//                '}';
    }

  /*  public String printJson(){

    }*/
}
