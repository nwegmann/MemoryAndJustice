package Model.Strategies;

import Model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;

import static Model.Strategies.Strategy.Move.COOPERATE;
import static Model.Strategies.Strategy.Move.DEFECT;
import static Model.Strategies.Strategy.StrategyType.GRUDGE;
import static Model.Strategies.Strategy.StrategyType.GRUDGE_STAR;

public class GrudgeStar extends Strategy{

    int[] defectorsArray;
    @Override
    public Move nextMove(Player opponent) {
        if(defectorsArray[opponent.getPlayerIndex()]>0){
            decrementDefectorsList();
            return DEFECT;
        }else{
            return COOPERATE;}
    }

    private void decrementDefectorsList() {
//        Iterator<Entry<Player, Integer>> defectorsIterator = defectorsList.entrySet().iterator();
//        Entry<Player,Integer> defector;
//        while(defectorsIterator.hasNext()){
//            defector = defectorsIterator.next();
//            if(defector.getValue() <= 0){
//                defectorsIterator.remove();
//            }else{
//                defector.setValue(defector.getValue()-1);
//            }
//        }
        for(int i = 0; i < defectorsArray.length; i++){
            if (defectorsArray[i] > 0){
                defectorsArray[i]--;
            }
        }
    }

    public GrudgeStar(Player player){
        this.strategyType = GRUDGE_STAR;
        //defectors list before
        defectorsArray = player.getDefectorsArray();
    }
}
