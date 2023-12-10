package edu.Maze.Input;
import edu.Maze.Generators.DFSMazeGenerator;
import edu.Maze.Generators.MazeGenerator;
import edu.Maze.Generators.OldMazeGenerator;
import edu.Maze.Generators.SidewinderMazeGenerator;
import java.util.Scanner;

public class MazeGeneratorSelector {
    public static MazeGenerator selectMazeGenerator(int rows, int cols) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose maze generation algorithm:");
            System.out.println("1. DFS");
            System.out.println("2. Sidewinder");
            System.out.println("3. Oldos-Broder");

            int choice = scanner.nextInt();

            if (choice == 1) {
                return new DFSMazeGenerator(rows, cols);
            } else if (choice == 2) {
                return new SidewinderMazeGenerator(rows, cols);
            } else if (choice == 3) {
                return new OldMazeGenerator(rows, cols);
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
