import Controller.FileReader;
import Model.Game;

import static Model.Game.playFromFile;

public class Main {

    //PROPORTION DEFECTORS:
    static  double proportionDefectorsMin = 0.1;
    static double proportionDefectorsMax = 0.9;
    static   double proportionDefectorsIncrement = 0.1;

    //MEMORY:
    static int memoryMin = 5;
    static int memoryMax = 100;
    static  int memoryIncrement = 5;

    //NUMBER OF NEIGHBORS:
    static int nbNeighborsMin = 5;
    static int nbNeighborsMax = 100;
    static  int nbNeighborsIncrement = 1;

    //GRUDGE*:
    static boolean grudgeStar = false;


    public static void main(String[] args) {

        //RUN FROM FILE:
        //playFromFile("src/Resources/SimulationValues.txt");

        //RUN MULTIPLE SIMULATIONS
        runSimulations();
    }

    private static void runSimulations() {
        double proportionDefectors = proportionDefectorsMin;

        while (proportionDefectors <= proportionDefectorsMax) {
            int memory = memoryMin;
            while (memory <= memoryMax){
                int nbOfNeighbours = nbNeighborsMin;
                while (nbOfNeighbours <= nbNeighborsMax) {
                    new Game(memory, proportionDefectors, nbOfNeighbours, grudgeStar);
                    nbOfNeighbours+= nbNeighborsIncrement;
                }
                memory += memoryIncrement;
            }
            proportionDefectors += proportionDefectorsIncrement;
        }
    }
}
