package Model;

import Analysis.GameOutcome;
import Controller.FileReader;
import Controller.ResultsWriter;

import static Model.Strategies.AlwaysDefect.AlwaysDefectFactory;
import static Model.Strategies.Strategy.StrategyType.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
   public int gridSize = 20;
   public long nbOfRounds = (long) 1e7;
   public int memory = 3;
    public double proportionOfDefectors= 0;
    public int nbOfPlayers;
   Player[][] grid;
    ArrayList<Player> players;
    int range = 2;

    static Random rand;

    public static void main(String[] args) {
        playFromFile();
    }

    public Game(int memory, double proportionOfDefectors){
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
        gridSize = fileReader.getGridSize();
        nbOfRounds = fileReader.getNbOfRounds();
        memory = fileReader.getMemory();
        proportionOfDefectors = fileReader.getProportionOfDefectors();
        range = fileReader.getRange();
        nbOfPlayers = (int) Math.pow(gridSize,2);
        grid = new Player[gridSize][gridSize];
        players = new ArrayList<>();
    }
    private void intializeValues() {
        rand = new Random();
        nbOfPlayers = (int) Math.pow(gridSize,2);
        grid = new Player[gridSize][gridSize];
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

    public Player getPlayer(int x, int y){return grid[x][y];}
    public void createGame() {
        initPlayers();
        initStrategies(proportionOfDefectors);
        determineNeighbours(players);
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
        int playerId = 0;
        int x = 0;
        int y = 0;
        while(y < gridSize){
            while(x < gridSize){
                Player p = new Player(new Position(x,y), playerId, this);
                playerId++;
                grid[x][y]=p;
                players.add(p);
                x++;
            }
            x = 0;
            y++;
        }
    }
    private void determineNeighbours(ArrayList<Player> players){
        for(Player p: players){
            determineNeighbours(range, p);
        }
    }
    public static void determineNeighbours(int range, Player p){
        determineNeighbours(range, p, p);
    }
    public static void determineNeighbours(int range, Player neighbour, Player player){
        if (!player.getNeighbours().contains(neighbour) && !neighbour.equals(player)) {player.getNeighbours().add(neighbour);}
        if (range == 0) {return;}
        for(Player p : neighbour.getCloseNeighbours()){
            determineNeighbours(range-1, p, player);
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "gridSize=" + gridSize +
                ", nbOfRounds=" + nbOfRounds +
                ", memory=" + memory +
                ", proportionOfDefectors=" + proportionOfDefectors +
                ", nbOfPlayers=" + nbOfPlayers +
                ", range=" + range +
                '}';
    }
}
