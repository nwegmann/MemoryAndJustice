package Model.Strategies;

import Model.Player;

public abstract class Strategy {
    StrategyType strategyType;

    abstract public void initializeDefectors(Player player);

    public enum StrategyType {
        ALWAYS_DEFECT,
        GRUDGE
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public enum Move {
        DEFECT,
        COOPERATE
    }

    public abstract Move nextMove(Player opponent);

}
