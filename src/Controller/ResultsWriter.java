package Controller;


import Analysis.GameOutcome;
import Model.Game;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;


public class ResultsWriter {
    FileWriter fileWriter;
    public void writeResults(GameOutcome gameOutcome, Game game){
        try {
            fileWriter = new FileWriter(new File("src/Resources", "results.txt"), true);
            fileWriter.write(gameOutcome.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    public void close(){
        try{
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
