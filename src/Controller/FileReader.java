package Controller;

import Model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    static public int gridSize = 100;
    static public long nbOfRounds;
    static public int memory;
    static public double proportionOfDefectors;
    static int range = 3;

        public FileReader(String filePath){
            try {
                Scanner scanner = new Scanner(new File(filePath));
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    if(data.startsWith("/") || data.startsWith("---")){
                     continue;
                    }
                    if(data.startsWith("PROPORTION DEFECTORS")){
                        float temp = Float.parseFloat(getNumberString(data));
                        System.out.println(temp);
                        proportionOfDefectors = temp;
                    }
                    if(data.startsWith("MEMORY")){
                        Integer temp = Integer.parseInt(getNumberString(data));
                        System.out.println(temp);
                        memory = temp;
                    }
                    if(data.startsWith("GRID SIZE")){
                        int temp = Integer.parseInt(getNumberString(data));
                        System.out.println(temp);
                        gridSize = temp;
                    }
                    if(data.startsWith("NB OF NEIGHBOURS")){
                        int temp = Integer.parseInt(data.split(":")[1].trim());
                        System.out.println(temp);
                        range = temp;
                    }
                    if(data.startsWith("NB OF ROUNDS")){
                        double temp = Double.parseDouble(data.split(":")[1].trim());
                        System.out.println(temp);
                        nbOfRounds = (long) temp;
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    private static String getNumberString(String data){
            return data.split(":")[1].trim();
    }

    public static int getGridSize() {
        return gridSize;
    }

    public static long getNbOfRounds() {
        return nbOfRounds;
    }

    public static int getMemory() {
        return memory;
    }

    public static double getProportionOfDefectors() {
        return proportionOfDefectors;
    }

    public static int getRange() {
        return range;
    }
}

