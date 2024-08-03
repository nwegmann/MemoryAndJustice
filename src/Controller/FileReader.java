package Controller;

import Model.Strategies.GrudgeStar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    static public int gridSize = 100;
    static public long nbOfRounds;
    static public int memory;
    static public double proportionOfDefectors;

    static public boolean grudgeStar;
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
                        proportionOfDefectors = temp;
                    }
                    if(data.startsWith("MEMORY")){
                        Integer temp = Integer.parseInt(getNumberString(data));
                        memory = temp;
                    }
                    if(data.startsWith("GRID SIZE")){
                        int temp = Integer.parseInt(getNumberString(data));
                        gridSize = temp;
                    }
                    if(data.startsWith("NB OF NEIGHBOURS")){
                        int temp = Integer.parseInt(data.split(":")[1].trim());
                        range = temp;
                    }
                    if(data.startsWith("NB OF ROUNDS")){
                        double temp = Double.parseDouble(data.split(":")[1].trim());
                        nbOfRounds = (long) temp;
                    }
                    if(data.startsWith("GRUDGE*")){
                        grudgeStar = data.split(":")[1].trim().toLowerCase().equals("t");
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

    public boolean isGrudgeStar() { return grudgeStar;
    }
}

