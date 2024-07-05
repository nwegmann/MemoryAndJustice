package Model;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private int x;
    private int y;
    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    //Return positive value if greater, 0 if equals or negative if smaller
    public int compareTo(Position otherPos) {
        if (otherPos == null) {
            System.out.println("Model.Position compared to null");
            return 1;
        }
        if (this.x >= otherPos.getX()) {
            if (this.x == otherPos.getX()) {
                if (this.y == otherPos.getY()) {
                    return 0;
                } else return this.y < otherPos.getY() ? -1 : 1;
            } else {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Model.Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }



}
