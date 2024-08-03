package Model;

import Analysis.GameOutcome;
import Controller.FileReader;
import Controller.ResultsWriter;

import static Model.Strategies.AlwaysDefect.AlwaysDefectFactory;
import static Model.Strategies.Strategy.StrategyType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    public long nbOfRounds = (long) 1e7;
    public int nbOfPlayers = 400;

    public int memory;
    public double proportionOfDefectors;
    public int nbOfNeighbours;
    public boolean grudgeStar;

    Player[] playingField;
    ArrayList<Player> players;
    static Random rand;

    public Game(int memory, double proportionOfDefectors, int nbOfNeighbours, boolean grudgeStar) {
        this.nbOfNeighbours = nbOfNeighbours;
        this.memory = memory;
        this.proportionOfDefectors = proportionOfDefectors;
        intializeValues();
        createGame(grudgeStar);
        this.launch();
        GameOutcome gameOutcome = new GameOutcome(this);
        gameOutcome.analyze(this.players, new ResultsWriter(), this);
    }

    public Game() {
    }

    public static void playFromFile(String path) {
        Game game = new Game();
        game.intializeValues(new FileReader(path));
        game.createGame(game.grudgeStar);
        game.launch();
        GameOutcome gameOutcome = new GameOutcome(game);
        gameOutcome.analyze(game.players, new ResultsWriter(), game);
    }

    private void intializeValues(FileReader fileReader) {
        rand = new Random();
        grudgeStar = fileReader.isGrudgeStar();
        nbOfNeighbours = fileReader.getNbOfNeighbors();
        nbOfRounds = fileReader.getNbOfRounds();
        memory = fileReader.getMemory();
        proportionOfDefectors = fileReader.getProportionOfDefectors();
        playingField = new Player[nbOfPlayers];
        players = new ArrayList<>();
    }

    private void intializeValues() {
        rand = new Random();
        playingField = new Player[nbOfPlayers];
        players = new ArrayList<>();
    }

    private void launch() {
        for (int i = 0; i < nbOfRounds; i++) {
            new Round(players);
        }
    }

    public static Player getRandomPlayer(ArrayList<Player> players) {
        return players.get((rand.nextInt() & Integer.MAX_VALUE) % players.size());
    }

    public void createGame(boolean grudgeStar) {
        initPlayers(grudgeStar);
        initStrategies(proportionOfDefectors);
    }

    private void initStrategies(double proportionDefectors) {
        if (!(proportionDefectors <= 1 && proportionDefectors >= 0)) {
            throw new RuntimeException("Proportion of defectors has to be between 0 and 1.");
        }
        int nbOfDefectors = (int) (nbOfPlayers * proportionDefectors);
        int nbOfDefectorsInit = 0;
        while (nbOfDefectorsInit < nbOfDefectors) {
            Player player = getRandomPlayer(players);
            if (player.getStrategy().getStrategyType() == ALWAYS_DEFECT) {
                continue;
            }
            player.setStrategy(AlwaysDefectFactory());
            nbOfDefectorsInit++;
        }
    }

    private void initPlayers(boolean grudgeStar) {
        for (int i = 0; i < nbOfPlayers; i++) {
            Player p = new Player(i, this, grudgeStar);
            playingField[i] = p;
            players.add(p);
        }
        for (Player p : players) {
            if (p.getStrategyType() != ALWAYS_DEFECT) p.initializeDefectors();
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "nbOfRounds=" + nbOfRounds +
                ", memory=" + memory +
                ", proportionOfDefectors=" + proportionOfDefectors +
                ", nbOfPlayers=" + nbOfPlayers +
                ", playingField=" + Arrays.toString(playingField) +
                ", players=" + players +
                '}';
    }
}
