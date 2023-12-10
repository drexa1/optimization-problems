package ortools.constraintprogramming;

import com.google.ortools.constraintsolver.DecisionBuilder;
import com.google.ortools.constraintsolver.IntVar;
import com.google.ortools.constraintsolver.Solver;

/**
 * Solves Who killed Agatha problem.
 *
 * Someone in Dreadsbury Mansion killed Aunt Agatha.
 * Agatha, the butler, and Charles live in Dreadsbury Mansion, and are the only ones to live there.
 * A killer always hates, and is no richer than his victim.
 * Charles hates noone that Agatha hates.
 * Agatha hates everybody except the butler.
 * The butler hates everyone not richer than Aunt Agatha.
 * The butler hates everyone whom Agatha hates.
 * Noone hates everyone.
 * Who killed Agatha?
 *
 */
public class WhoKilledAgatha {

  static {
    System.loadLibrary("jniortools");
  }

  private static void solve() {

    Solver solver = new Solver("WhoKilledAgatha");

    // data
    final int n = 3;
    final int agatha = 0;
    final int butler = 1;
    final int charles = 2;
    String[] names = {"Agatha", "Butler", "Charles"};

    // variables
    IntVar the_killer = solver.makeIntVar(0, 2, "the_killer");
    IntVar the_victim = solver.makeIntVar(0, 2, "the_victim");

    IntVar[] all = new IntVar[2 * n * n]; // for branching

    IntVar[][] hates = new IntVar[n][n];
    IntVar[] hates_flat = new IntVar[n * n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        hates[i][j] = solver.makeIntVar(0, 1, "hates[" + i + "," + j + "]");
        hates_flat[i * n + j] = hates[i][j];
        all[i * n + j] = hates[i][j];
      }
    }

    IntVar[][] richer = new IntVar[n][n];
    IntVar[] richer_flat = new IntVar[n * n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        richer[i][j] = solver.makeIntVar(0, 1, "richer[" + i + "," + j + "]");
        richer_flat[i * n + j] = richer[i][j];
        all[(n * n) + (i * n + j)] = richer[i][j];
      }
    }

    // constraints

    // A killer always hates,
    // hates[the_killer, the_victim] == 1
    // hates_flat[the_killer * n + the_victim] == 1
    solver.addConstraint(
        solver.makeEquality(
            solver.makeElement(
                hates_flat,
                solver.makeSum(
                    solver.makeProd(the_killer, n).var(),
                    the_victim).var()).var(), 1));

    // ...and is no richer than his victim.
    // richer[the_killer, the_victim] == 0
    solver.addConstraint(
        solver.makeEquality(
            solver.makeElement(
                richer_flat,
                solver.makeSum(
                    solver.makeProd(the_killer, n).var(),
                    the_victim).var()).var(), 0));

    // define richer:

    // no one is richer than him-/herself...
    for(int i = 0; i < n; i++) {
      solver.addConstraint(solver.makeEquality(richer[i][i], 0));
    }

    // if i is richer than j then j is not richer than i
    //   if (i != j) =>
    //       ((richer[i,j] = 1) <=> (richer[j,i] = 0))
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        if (i != j) {
          IntVar bi = solver.makeIsEqualCstVar(richer[i][j], 1);
          IntVar bj = solver.makeIsEqualCstVar(richer[j][i], 0);
          solver.addConstraint(solver.makeEquality(bi, bj));
        }
      }
    }

    // Charles hates no one that Agatha hates.
    //    forall i in 0..2:
    //       (hates[agatha, i] = 1) => (hates[charles, i] = 0)
    for(int i = 0; i < n; i++) {
      IntVar b1a = solver.makeIsEqualCstVar(hates[agatha][i], 1);
      IntVar b1b = solver.makeIsEqualCstVar(hates[charles][i], 0);
      solver.addConstraint(
          solver.makeLessOrEqual(
              solver.makeDifference(b1a, b1b).var(), 0));
    }

    // Agatha hates everybody except the butler.
    solver.addConstraint(solver.makeEquality(hates[agatha][charles], 1));
    solver.addConstraint(solver.makeEquality(hates[agatha][agatha], 1));
    solver.addConstraint(solver.makeEquality(hates[agatha][butler], 0));

    // The butler hates everyone not richer than Aunt Agatha.
    //    forall i in 0..2:
    //       (richer[i, agatha] = 0) => (hates[butler, i] = 1)
    for(int i = 0; i < n; i++) {
      IntVar b2a = solver.makeIsEqualCstVar(richer[i][agatha], 0);
      IntVar b2b = solver.makeIsEqualCstVar(hates[butler][i], 1);
      solver.addConstraint(
          solver.makeLessOrEqual(
              solver.makeDifference(b2a, b2b).var(), 0));
    }

    // The butler hates everyone whom Agatha hates.
    //     forall i : 0..2:
    //         (hates[agatha, i] = 1) => (hates[butler, i] = 1)
    for(int i = 0; i < n; i++) {
      IntVar b3a = solver.makeIsEqualCstVar(hates[agatha][i], 1);
      IntVar b3b = solver.makeIsEqualCstVar(hates[butler][i], 1);
      solver.addConstraint(
          solver.makeLessOrEqual(
              solver.makeDifference(b3a, b3b).var(), 0));
    }

    // Noone hates everyone.
    //     forall i in 0..2:
    //         (sum j in 0..2: hates[i,j]) <= 2
    for(int i = 0; i < n; i++) {
      IntVar[] tmp = new IntVar[n];
      for(int j = 0; j < n; j++) {
        tmp[j] = hates[i][j];
      }
      solver.addConstraint(
          solver.makeLessOrEqual(solver.makeSum(tmp).var(), 2));
    }

    // Who killed Agatha?
    solver.addConstraint(solver.makeEquality(the_victim, agatha));

    // search
    DecisionBuilder builder = solver.makePhase(all, solver.CHOOSE_FIRST_UNBOUND, solver.ASSIGN_MIN_VALUE);
    solver.newSearch(builder);

    while (solver.nextSolution()) {
      System.out.println("the_killer: " + names[(int)the_killer.value()]);
    }
    solver.endSearch();

    // Statistics
    System.out.println("Solutions: " + solver.solutions());
    System.out.println("Failures: " + solver.failures());
    System.out.println("Branches: " + solver.branches());
    System.out.println("Wall time: " + solver.wallTime() + "ms");
  }

  public static void main(String[] args) throws Exception {
    WhoKilledAgatha.solve();
  }
}
