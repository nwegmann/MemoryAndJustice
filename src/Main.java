import Controller.FileReader;
import Model.Game;

import static Model.Game.playFromFile;

public class Main {
    public static void main(String[] args) {
        //playFromFile("src/Resources/SimulationValues.txt");
        runSimulations();
    }

    private static void runSimulations() {
        double proportionDefectors = 0.1;

        while (proportionDefectors < 0.99) {
            int memory = 10;
            while (memory<= 90){
                int nbOfNeighbours = 10;
                while (nbOfNeighbours <= 90) {
                    new Game(memory, proportionDefectors, nbOfNeighbours, true);
                    nbOfNeighbours+= 1;
                }
                memory += 1;
            }
            proportionDefectors += 0.1;
        }
    }
}
