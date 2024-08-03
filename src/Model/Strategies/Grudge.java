package Model.Strategies;

import Model.Player;

import static Model.Strategies.Strategy.Move.COOPERATE;
import static Model.Strategies.Strategy.Move.DEFECT;
import static Model.Strategies.Strategy.StrategyType.GRUDGE;

public class Grudge extends Strategy{

    int[] defectorsArray;

    @Override
    public void initializeDefectors(Player player) {
        for(int i = 1; i <= player.getGame().memory; i++){
            Player p = player.getRandomNeighbor();
            if(p.isDefector()){
                defectorsArray[p.getPlayerIndex()]= i;
            }
        }
    }

    @Override
    public Move nextMove(Player opponent) {
        if(defectorsArray[opponent.getPlayerIndex()]>0){
            decrementDefectorsList();
            return DEFECT;
        }else{
            decrementDefectorsList();
            return COOPERATE;}
    }

    private void decrementDefectorsList() {
        for(int i = 0; i < defectorsArray.length; i++){
            if (defectorsArray[i] > 0){
                defectorsArray[i]--;
            }
        }
    }

    public Grudge(Player player){
        this.strategyType = GRUDGE;
        defectorsArray = player.getDefectorsArray();
    }
}
