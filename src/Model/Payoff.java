package Model;

public enum Payoff {
    TEMPTATION(5),
    REWARD(3),
    PUNISHMENT(1),
    SUCKER(0);

    private final int reward;

    Payoff(int reward) {
        this.reward = reward;
    }

    public int getReward() {
        return reward;
    }
}
