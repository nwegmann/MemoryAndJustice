package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    static private long nbOfRounds;
    static private int memory;
    static private double proportionOfDefectors;
    static private boolean grudgeStar;
    static private int nbOfNeighbors;

    public FileReader(String filePath){
            try {
                Scanner scanner = new Scanner(new File(filePath));
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    if(data.startsWith("/") || data.startsWith("---")){
                     continue;
                    }
                    if(data.startsWith("PROPORTION DEFECTORS")){
                        proportionOfDefectors = Float.parseFloat(getNumberString(data));
                    }
                    if(data.startsWith("MEMORY")){
                        memory = Integer.parseInt(getNumberString(data));
                    }
                    if(data.startsWith("NB OF NEIGHBOURS")){
                        nbOfNeighbors = Integer.parseInt(data.split(":")[1].trim());
                    }
                    if(data.startsWith("NB OF ROUNDS")){
                        nbOfRounds = (long) Double.parseDouble(data.split(":")[1].trim());
                    }
                    if(data.startsWith("GRUDGE*")){
                        grudgeStar = Character.toString(data.split(":")[1].trim().charAt(0)).equalsIgnoreCase("t");
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

    public static int getNbOfNeighbors() {
        return nbOfNeighbors;
    }

    public boolean isGrudgeStar() { return grudgeStar;
    }
}

