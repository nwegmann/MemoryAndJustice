import Model.Game;

public class Main {
    public static void main(String[] args) {

       double proportionDefectors = 0.1;

        while (proportionDefectors < 0.99) {
            int memory = 10;
            while (memory<= 90){
                int nbOfNeighbours = 10;
                while (nbOfNeighbours <= 90) {
                    new Game(memory, proportionDefectors, nbOfNeighbours);
                    nbOfNeighbours+= 1;
                }
                memory += 1;
            }
            proportionDefectors += 0.1;


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
