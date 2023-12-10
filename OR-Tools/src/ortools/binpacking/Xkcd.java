package ortools.binpacking;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

public class Xkcd {

  static {
    System.loadLibrary("jniortools");
  }

  /**
   * Solves https://xkcd.com/287/
   */
  private static void solve() {

    Solver solver = new Solver("Xkcd");

    // for price and total multiply by 100 to be able to use integers
    int[] prices = {215, 275, 335, 355, 420, 580};
    int nClasses = prices.length;
    int total = 1505;

    // Variables
    IntVar[] x = solver.makeIntVarArray(nClasses, 0, 10, "x");
    // Constraints
    solver.addConstraint(solver.makeEquality(solver.makeScalProd(x, prices).var(), total));
    // Search
    DecisionBuilder builder = solver.makePhase(x, solver.CHOOSE_FIRST_UNBOUND, solver.ASSIGN_MIN_VALUE);

    solver.newSearch(builder);

    while (solver.nextSolution()) {
      System.out.print("x: ");
      for(int i = 0; i < nClasses; i++) {
        System.out.print(x[i].value() + " ");
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
    Xkcd.solve();
  }
}
