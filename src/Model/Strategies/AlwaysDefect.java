package Model.Strategies;

import static Model.Strategies.Strategy.Move.*;
import Model.Player;

public class AlwaysDefect extends Strategy{
    static boolean exist = false;

    static AlwaysDefect alwaysDefect = AlwaysDefectFactory();
    @Override
    public Move nextMove(Player opponent){
        return DEFECT;
    }

    private AlwaysDefect(){
        this.strategyType = StrategyType.ALWAYS_DEFECT;
    }

    public static AlwaysDefect AlwaysDefectFactory(){
        if(!exist){
            alwaysDefect = new AlwaysDefect();
            exist = true;
        }
        return alwaysDefect;
    }
}
