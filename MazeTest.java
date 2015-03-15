import java.util.Arrays;

/**
 * Created by cameronlunt on 12/11/14.
 */
public class MazeTest {

    public static void main(String[] args){
        System.out.println("Start");

        int[] start = {0,0};
        int[] goal = {9,4};
        int[][] maze =
                {
                        {0,1,0,0,0,0,1,0,1,0},
                        {0,1,0,1,0,1,0,0,0,0},
                        {0,0,0,0,0,0,1,0,1,0},
                        {0,1,0,1,0,1,0,0,1,0},
                        {1,0,0,0,0,0,0,0,1,0},
                        {1,0,0,1,0,1,0,0,1,0},
                        {0,0,0,1,1,0,1,0,1,0},
                        {0,1,0,0,0,0,1,0,0,0}
                };
        MazeSolver solver = new MazeSolver(start, goal, maze);
        if(solver.solve()) {
            //solver.printSolutionPath(System.out);

            char[][] annotatedMaze = solver.annotatedMaze();
            for (char[] array : annotatedMaze) {
                System.out.println(Arrays.toString(array));
            }
        }
        else{
            System.out.println("No Solution");
        }

    }
}
