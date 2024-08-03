package Model.Strategies;

import Model.Player;

import java.util.ArrayList;

public abstract class Strategy {
    StrategyType strategyType;

    public void gotDefectedBy(Player defector) {
    }

    public enum StrategyType{
        ALWAYS_DEFECT,
        GRUDGE,
        GRUDGE_STAR

    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public enum Move {
        DEFECT,
        COOPERATE;
    }

    public abstract Move nextMove(Player opponent);

}
