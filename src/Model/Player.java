package Model;

import Model.Strategies.Grudge;
import Model.Strategies.Strategy;
import java.util.ArrayList;

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
    private ArrayList<Player> neighbours;
    int getX(){return pos.getX();}
    int getY(){return pos.getY();}

    public Player(Position pos, int playerIndex, Game game) {
        //defectorsList = new HashMap<>();
        this.game = game;
        defectorsArray = new int[game.nbOfPlayers];
        strategy = new Grudge(this);
        neighbours = new ArrayList<Player>();
        this.pos = pos;
        this.playerIndex = playerIndex;
    }

    public ArrayList<Player> getNeighbours() {
        return neighbours;
    }

    public Player getRandomNeighbour(){return neighbours.get((rand.nextInt() & Integer.MAX_VALUE)%neighbours.size());}

    public ArrayList<Player> getCloseNeighbours(){
        ArrayList<Player> closeNeighbours = new ArrayList<>();
        closeNeighbours.add(getLeftNeighbour());
        closeNeighbours.add(getRightNeighbour());
        closeNeighbours.add(getTopNeighbour());
        closeNeighbours.add(getUnderNeighbour());
        return closeNeighbours;
    }
    public int[] getDefectorsArray(){
        return defectorsArray;
    }

    public Player getLeftNeighbour(){
        int x = this.getX() == 0 ? game.gridSize-1 : this.getX()-1;
        return game.getPlayer(x, this.getY());
    }
    public Player getRightNeighbour(){
        int x = this.getX() == game.gridSize-1 ? 0 : this.getX()+1;
        return game.getPlayer(x, this.getY());
    }
    public Player getTopNeighbour(){
        int y = this.getY() == 0 ? game.gridSize-1 : this.getY()-1;
        return game.getPlayer(this.getX(), y);
    }
    public Player getUnderNeighbour(){
        int y = this.getY() == game.gridSize-1 ? 0 : this.getY()+1;
        return game.getPlayer(this.getX(), y);
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
}
