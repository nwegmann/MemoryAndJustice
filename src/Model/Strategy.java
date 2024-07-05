package Model;

public enum Strategy {
    ALWAYS_COOPERATE,
    ALWAYS_DEFECT;



    public enum Move {
        DEFECT,
        COOPERATE;
    }

    private Move nextMove;
    public Move nextMove(){
        return nextMove;
    }

}
