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
   public int memory = 3;
    public double proportionOfDefectors= 0;
    public int nbOfPlayers = 400;
    public int nbOfNeighbours= 20;
    Player[] playingField;
    ArrayList<Player> players;
    int range = 2;

    static Random rand;

    public static void main(String[] args) {
        playFromFile();
    }

    public Game(int memory, double proportionOfDefectors, int nbOfNeighbours){
        this.nbOfNeighbours = nbOfNeighbours;
        this.memory = memory;
        this.proportionOfDefectors = proportionOfDefectors;
        intializeValues();
        createGame();
        this.launch();
        GameOutcome gameOutcome = new GameOutcome(this);
        gameOutcome.analyze(this.players, new ResultsWriter(), this);
    };

    public Game() {
    }

    private static void playFromFile() {
        Game game = new Game();
        GameOutcome gameOutcome = new GameOutcome(game);
        game.intializeValues(new FileReader("src/Resources/SimulationValues.txt"));
        game.createGame();
        game.launch();
        gameOutcome.analyze(game.players, new ResultsWriter(), game);
    }

    private void intializeValues(FileReader fileReader) {
        rand = new Random();
        nbOfRounds = fileReader.getNbOfRounds();
        memory = fileReader.getMemory();
        proportionOfDefectors = fileReader.getProportionOfDefectors();
        range = fileReader.getRange();
        playingField = new Player[nbOfPlayers];
        players = new ArrayList<>();
    }
    private void intializeValues() {
        rand = new Random();
        playingField = new Player[nbOfPlayers];
        players = new ArrayList<>();
    }


    private void launch() {
        for(int i = 0; i< nbOfRounds; i++){
            new Round(players);
        }
    }

    public static Player getRandomPlayer(ArrayList<Player> players){
        return players.get((rand.nextInt() & Integer.MAX_VALUE) % players.size());
    }

    public Player getPlayer(int index){return playingField[index];}
    public void createGame() {
        initPlayers();
        initStrategies(proportionOfDefectors);
    }

    private void initStrategies(double proportionDefectors) {
        if (!(proportionDefectors <= 1 && proportionDefectors >= 0)){throw new RuntimeException("Proportion of defectors has to be between 0 and 1.");}
        int nbOfDefectors = (int) (nbOfPlayers * proportionDefectors);
        int nbOfDefectorsInit = 0;
        while(nbOfDefectorsInit < nbOfDefectors){
            Player player = getRandomPlayer(players);
            if(player.getStrategy().getStrategyType() == ALWAYS_DEFECT){continue;}
            player.setStrategy(AlwaysDefectFactory());
            nbOfDefectorsInit++;
        }
    }

    private void initPlayers(){
        for (int i = 0; i< nbOfPlayers; i++){
            Player p = new Player(i, this);
            playingField[i]=p;
            players.add(p);
        }
        for(Player p : players){
            if(p.getStrategyType()!= ALWAYS_DEFECT)p.initializeDefectors();
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
                ", range=" + range +
                '}';
    }
}
