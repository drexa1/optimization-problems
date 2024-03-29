package ortools.constraintprogramming;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Sudoku {

  static {
    System.loadLibrary("jniortools");
  }

  /**
   * Solves a Sudoku.
   */
  private static void solve() {

    Solver solver = new Solver("Sudoku");

    int cell_size = 3;
    int n = cell_size * cell_size;

    // 0 is an unknown value
    int[][] initial_grid = new int[][] {{0, 6, 0, 0, 5, 0, 0, 2, 0},
                                        {0, 0, 0, 3, 0, 0, 0, 9, 0},
                                        {7, 0, 0, 6, 0, 0, 0, 1, 0},
                                        {0, 0, 6, 0, 3, 0, 4, 0, 0},
                                        {0, 0, 4, 0, 7, 0, 1, 0, 0},
                                        {0, 0, 5, 0, 9, 0, 8, 0, 0},
                                        {0, 4, 0, 0, 0, 1, 0, 0, 6},
                                        {0, 3, 0, 0, 0, 8, 0, 0, 0},
                                        {0, 2, 0, 0, 4, 0, 0, 5, 0}};

    // variables
    IntVar[][] grid = new IntVar[n][n];
    IntVar[] grid_flat = new IntVar[n * n];

    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        grid[i][j] = solver.makeIntVar(1, 9, "grid[" + i +"," + j + "]");
        grid_flat[i * n + j] = grid[i][j];
      }
    }

    // constraints

    // init and rows
    for(int i = 0; i < n; i++) {
      IntVar[] row = new IntVar[n];
      for(int j = 0; j < n; j++) {
        if (initial_grid[i][j] > 0) {
          solver.addConstraint(
              solver.makeEquality(grid[i][j], initial_grid[i][j]));
        }
        row[j] = grid[i][j];
      }
      solver.addConstraint(solver.makeAllDifferent(row));
    }

    // columns
    for(int j = 0; j < n; j++) {
      IntVar[] col = new IntVar[n];
      for(int i = 0; i < n; i++) {
        col[i] = grid[i][j];
      }
      solver.addConstraint(solver.makeAllDifferent(col));
    }

    // cells
    for(int i = 0; i < cell_size; i++) {
      for(int j = 0; j < cell_size; j++) {
        IntVar[] cell = new IntVar[n];
        for(int di = 0; di < cell_size; di++) {
          for(int dj = 0; dj < cell_size; dj++) {
            cell[di * cell_size + dj] =
              grid[i * cell_size + di][j * cell_size + dj];
          }
        }
        solver.addConstraint(solver.makeAllDifferent(cell));
      }
    }

    // Search
    DecisionBuilder builder = solver.makePhase(grid_flat, solver.INT_VAR_SIMPLE, solver.INT_VALUE_SIMPLE);

    solver.newSearch(builder);

    while (solver.nextSolution()) {
      for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          System.out.print(grid[i][j].value() + " ");
        }
        System.out.println();
      }
      System.out.println();
    }
    solver.endSearch();

    // Statistics
    System.out.println("Solutions: " + solver.solutions());
    System.out.println("Failures: " + solver.failures());
    System.out.println("Branches: " + solver.branches());
    System.out.println("Wall time: " + solver.wallTime() + "ms");
  }

  public static void main(String[] args) throws Exception {
    Sudoku.solve();
  }
}
