import Model.Game;

public class Main {
    public static void main(String[] args) {

       double proportionDefectors = 0.2;

        while (proportionDefectors < 0.85) {
            int memory = 7;
            while (memory <= 20) {
                new Game(memory, proportionDefectors);
                memory++;
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
