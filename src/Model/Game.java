package Model;

import java.util.ArrayList;

public class Game {
    static public final int GRIDSIZE = 10;
    static public final int NB_OF_ROUNDS = 10;
    static public final int NB_PLAYERS = (int) Math.pow(GRIDSIZE,2);
    static Player[][] grid = new Player[GRIDSIZE][GRIDSIZE];
    static ArrayList<Player> players = new ArrayList<>();
    static int range = 3;

    public static void main(String[] args) {
        createGame();
        launch();
    }

    private static void launch() {
        new Round(players);
    }

    public static Player getPlayer(int x, int y){return grid[x][y];}
    public static void createGame() {
        initPlayers();
        determineNeighbours();
        for(int i = 0; i< NB_OF_ROUNDS; i++){
            new Round(players);
        }
    }
    private static void initPlayers(){
        int x = 0;
        int y = 0;
        while(y < GRIDSIZE){
            while(x < GRIDSIZE){
                Player p = new Player(new Position(x,y));
                grid[x][y]=p;
                players.add(p);
                x++;
            }
            x = 0;
            y++;
        }
    }
    private static void determineNeighbours(){
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
}
