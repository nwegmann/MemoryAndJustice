package Model;

import java.util.ArrayList;
import static Model.Game.*;
import static Model.Strategy.*;



public class Player {
    Position pos;
    Move move;
    int score = 0;
    private ArrayList<Player> neighbours;

    int getX(){return pos.getX();}
    int getY(){return pos.getY();}

    public Player(Position pos) {
        neighbours = new ArrayList<Player>();
        this.pos = pos;
    }

    public ArrayList<Player> getNeighbours() {
        return neighbours;
    }

    public ArrayList<Player> getCloseNeighbours(){
        ArrayList<Player> closeNeighbours = new ArrayList<>();
        closeNeighbours.add(getLeftNeighbour());
        closeNeighbours.add(getRightNeighbour());
        closeNeighbours.add(getTopNeighbour());
        closeNeighbours.add(getUnderNeighbour());
        return closeNeighbours;
    }

    public Player getLeftNeighbour(){
        int x = this.getX() == 0 ? Game.GRIDSIZE-1 : this.getX()-1;
        return getPlayer(x, this.getY());
    }
    public Player getRightNeighbour(){
        int x = this.getX() == GRIDSIZE-1 ? 0 : this.getX()+1;
        return getPlayer(x, this.getY());
    }
    public Player getTopNeighbour(){
        int y = this.getY() == 0 ? GRIDSIZE-1 : this.getY()-1;
        return getPlayer(this.getX(), y);
    }
    public Player getUnderNeighbour(){
        int y = this.getY() == GRIDSIZE-1 ? 0 : this.getY()+1;
        return getPlayer(this.getX(), y);
    }

    public Move getStrategy() {
        return move;
    }

    public void incrementScore(Payoff payoff) {
        score += payoff.getReward();
    }
}
