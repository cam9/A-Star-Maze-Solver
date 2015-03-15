import java.io.PrintStream;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by cameronlunt on 12/11/14.
 */
public class MazeSolver{

    private int[] start;
    private int[] goal;
    private int[][] maze;
    private ExpansionList solution;
    private static final char WALL = 1;
    private static final char PATH = 0;

    public void setStart(int[] start) {
        this.start = start;
    }

    public void setGoal(int[] goal) {
        this.goal = goal;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze(){
        return maze;
    }

    public int[] getStart(){
        return start;
    }

    public int[] getGoal(){
        return goal;
    }

    public ExpansionList getSolution(){
        return solution;
    }

    public MazeSolver(int[] start, int[] goal, int[][] maze){
        this.start = start;
        this.goal = goal;
        this.maze = maze;
    }

    public void printSolutionPath(PrintStream output){
        if(getSolution() == null){
            output.println("No solution found. Call 'solve' to generate a solution path");
        }
        else{
            Stack<ExpansionList> stack = new Stack<ExpansionList>();
            output.println("Solution="+getSolution().toString());
            output.println("getSolution().hasParent() =" + getSolution().hasParent());

            for(ExpansionList current = getSolution(); current.hasParent(); current = current.getParent()){
                stack.push(current);
            }
            while(!stack.empty()){
                output.println(stack.pop());
            }

        }
    }

    public char[][] annotatedMaze(){
        char[][]annotatedMaze = new char[maze.length][];

        for(int i = 0; i < maze.length; i++){
            annotatedMaze[i] = new char[maze[i].length];
            for(int j = 0; j < maze[i].length; j++){
                switch(maze[i][j]){
                    case 0:
                        annotatedMaze[i][j] = ' ';
                        break;
                    case 1:
                        annotatedMaze[i][j] = '█';
                }
            }
        }

        if(getSolution() != null){
            for(ExpansionList current = getSolution(); current.hasParent(); current = current.getParent()){
                annotatedMaze[current.getParent().getY()][current.getParent().getX()] = current.getDirectionEntered();
            }
        }

        annotatedMaze[goal[1]][goal[0]] = '*';
        annotatedMaze[start[1]][start[0]] = 'S';

        return annotatedMaze;
    }

    public boolean solve(){
        ExpansionList goalNode = new ExpansionList(goal[0], goal[1], 0, goal, null, ' ');
        ExpansionList current = new ExpansionList(start[0], start[1], 0, goal, null, ' ');
        PriorityQueue<ExpansionList> open = new PriorityQueue<ExpansionList>();
        HashSet<ExpansionList> explored = new HashSet<ExpansionList>();

        while(!current.equals(goalNode)){
            //Up
            if(current.getY() > 0){
                ExpansionList node = new ExpansionList(
                        current.getX(),
                        current.getY()-1,
                        current.getExpansionLevel() + 1,
                        goal,
                        current,
                        '^'
                );

                if(maze[current.getY()-1][current.getX()] == PATH && !explored.contains(node) && !open.contains(node)) {
                    open.add(node);
                }
            }
            //Down
            if(current.getY() < maze.length-1){
                ExpansionList node = new ExpansionList(
                        current.getX(),
                        current.getY() + 1,
                        current.getExpansionLevel() + 1,
                        goal,
                        current,
                        'v'
                );

                if(maze[current.getY()+1][current.getX()] == PATH && !explored.contains(node) && !open.contains(node)) {
                    open.add(node);
                }
            }
            //Left
            if(current.getX() > 0){
                ExpansionList node = new ExpansionList(
                        current.getX() - 1,
                        current.getY(),
                        current.getExpansionLevel() + 1,
                        goal,
                        current,
                        '<'
                );

                if(maze[current.getY()][current.getX()-1] == PATH && !explored.contains(node) && !open.contains(node)) {
                    open.add(node);
                }
            }
            //Right
            if(current.getX() < maze[current.getY()].length-1){
                ExpansionList node = new ExpansionList(
                        current.getX()+1,
                        current.getY(),
                        current.getExpansionLevel() + 1,
                        goal,
                        current,
                        '>'
                );

                if(maze[current.getY()][current.getX()+1] == PATH && !explored.contains(node) && !open.contains(node)) {
                    open.add(node);
                }
            }
            explored.add(current);
            current = open.poll();
        }

            this.solution = current;
            return true;

    }

}
