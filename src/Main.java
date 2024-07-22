import Model.Game;

public class Main {
    public static void main(String[] args) {

       double proportionDefectors = 0.1;

        while (proportionDefectors < 0.99) {
            int memory = 5;
            while (memory<= 200){
                int nbOfNeighbours = 10;
                while (nbOfNeighbours < 100) {
                    new Game(memory, proportionDefectors, nbOfNeighbours);
                    nbOfNeighbours+= 10;
                }
                memory += 5;
            }
            proportionDefectors += 0.05;


/*
        int memory = 10;
        while(memory <= 20){
            new Game(memory, 1);
            memory++;
        }
*/

        }
    }
}
