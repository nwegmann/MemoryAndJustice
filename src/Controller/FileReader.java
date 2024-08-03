package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private long nbOfRounds;
    private int memory;
    private double proportionOfDefectors;
    private boolean grudgeStar;
    private int nbOfNeighbors;

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

    public long getNbOfRounds() {
        return nbOfRounds;
    }

    public int getMemory() {
        return memory;
    }

    public double getProportionOfDefectors() {
        return proportionOfDefectors;
    }

    public int getNbOfNeighbors() {
        return nbOfNeighbors;
    }

    public boolean isGrudgeStar() { return grudgeStar;
    }
}

