import Model.Position;

public class Test {

    @org.junit.Test
    public void positionCompareToTest(){
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,0);
        assert pos1.compareTo(pos2) == 0;
        pos1.setX(1);
        assert pos1.compareTo(pos2) > 0;
        pos1.setY(1);
        pos2.setX(1);
        assert pos1.compareTo(pos2) > 0;
        pos2.setY(1);
        assert pos1.compareTo(pos2) == 0;
        pos2.setY(2);
        assert pos1.compareTo(pos2) < 0;
    }

    @org.junit.Test
    public void gridTest(){


    }
}