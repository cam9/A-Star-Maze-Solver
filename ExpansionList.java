import java.awt.*;

/**
 * Created by cameronlunt on 3/14/15.
 */
public class ExpansionList implements Comparable<ExpansionList>{
    private int x;
    private int y;
    private int expansionLevel;
    private int[] goal;
    private ExpansionList parent;
    private char directionEntered;

    public ExpansionList(int x, int y, int expansionLevel, int[] goal, ExpansionList parent, char directionEntered){
        this.x = x;
        this.y = y;
        this.expansionLevel = expansionLevel;
        this.goal = goal;
        this.parent = parent;
        this.directionEntered = directionEntered;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getExpansionLevel() {
        return expansionLevel;
    }

    public int heuristic(){
        return Math.abs(goal[0]-this.x)+Math.abs(goal[1]-this.y);
    }

    @Override
    public int compareTo(ExpansionList e){
        return this.heuristic() - e.heuristic();
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof ExpansionList)) return false;

        ExpansionList e = (ExpansionList)o;
        return this.getX() == e.getX() && this.getY() == e.getY();
    }

    @Override
    public int hashCode(){
        return (this.x * 257) * (this.y * 263);
    }

    public boolean hasParent(){
        return parent != null;
    }

    public ExpansionList getParent(){
        return parent;
    }

    public char getDirectionEntered(){
        return directionEntered;
    }

    public String toString(){
        return "{("+this.getX()+","+this.getY()+"),expansion level:"+this.getExpansionLevel()+"}";
    }
}
