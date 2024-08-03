package Analysis;

import Controller.ResultsWriter;
import Model.Game;
import Model.Player;

import Model.Strategies.Strategy.StrategyType;

import java.util.ArrayList;
import java.util.HashMap;

public class GameOutcome {

    public double avgScore = 0;
    public long totalScore = 0;
    public long nbOfPlayers = 0;

    private final Game game;

    public HashMap<StrategyType, StrategyOutcome> scoreMap;

    public GameOutcome(Game game) {
        this.game = game;
    }

    void initScoreMap() {
        scoreMap = new HashMap<>();
        for (StrategyType s : StrategyType.values()) {
            scoreMap.put(s, new StrategyOutcome(s, game));
        }
    }

    void integratePlayers(ArrayList<Player> players) {
        for (Player p : players) {
            StrategyOutcome so = scoreMap.get(p.getStrategyType());
            so.integratePlayer(p);
        }
    }

    public void analyze(ArrayList<Player> players, ResultsWriter rw) {
        initScoreMap();
        integratePlayers(players);
        aggregateStrategies();
        printResults();
        rw.writeResults(this);

    }

    private void printResults() {
        for (StrategyType st : StrategyType.values()) {
            System.out.println(scoreMap.get(st));
        }
    }

    private void aggregateStrategies() {
        for (StrategyType s : StrategyType.values()) {
            totalScore = scoreMap.get(s).getTotalScore() + totalScore;
            nbOfPlayers = scoreMap.get(s).getNbOfPlayers() + nbOfPlayers;
            avgScore = nbOfPlayers <= 0 ? avgScore : (double) totalScore / nbOfPlayers;
        }
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (StrategyType st : StrategyType.values()) {
            temp.append(scoreMap.get(st)).append("\n");
        }
        return temp.toString();
    }

}
