# Constraint Optimization

Constraint optimization, or [constraint programming](https://en.wikipedia.org/wiki/Constraint_programming) (CP), is the name given to identifying feasible solutions out of a very large set of candidates, where the problem can be modeled in terms of arbitrary constraints. CP problems arise in many scientific and engineering disciplines. (The word "programming" is a bit of a misnomer, similar to how "computer" once meant "a person who computes". Here, "programming" refers to the arrangement of a plan, rather than programming in a computer language.)


Constraint Optimization
Constraint optimization, or constraint programming (CP), is the name given to identifying feasible solutions out of a very large set of candidates, where the problem can be modeled in terms of arbitrary constraints. CP problems arise in many scientific and engineering disciplines. (The word "programming" is a bit of a misnomer, similar to how "computer" once meant "a person who computes". Here, "programming" refers to the arrangement of a plan, rather than programming in a computer language.)

CP is based on feasibility (finding a feasible solution) rather than optimization (finding an optimal solution) and focuses on the constraints and variables rather than the objective function. In fact, a CP problem may not even have an objective function — the goal may simply be to narrow down a vary large set of possible solutions to a more manageable subset by adding constraints to the problem.

An example of a problem that is well-suited for CP is employee scheduling. The problem arises when companies that operate continuously — such as factories — need to create weekly schedules for their employees. Here's a very simple example: a company runs three 8-hour shifts per day and assigns three of its four employees to different shifts each day, while giving the fourth the day off. Even in such a small case, the number of possible schedules is huge: each day, there are 4! = 4 · 3 · 2 · 1  = 24 possible employee assignments, so the number of possible weekly schedules is 247, which is over 4.5 billion. Usually there will be other constraints that reduce the number of feasible solutions — for example, that each employee works at least a minimum number of days per week. The CP method keeps track of which solutions remain feasible when you add new constraints, which makes it a powerful tool for solving large, real-world scheduling problems.

The section [Employee Scheduling](https://developers.google.com/optimization/scheduling/employee_scheduling) presents an example of this problem and shows how to solve it using the OR-Tools [CP solver](https://developers.google.com/optimization/cp/cp_solver).

We should point out that you can also use CP to solve standard optimization problems, which have an objective function, by simply comparing the value of the objective for all feasible solutions. See The [Job shop problem](https://developers.google.com/optimization/scheduling/job_shop) for an example of this.

CP is a relatively new field, but has a widespread and very active community around the world with dedicated scientific journals, conferences, and an arsenal of different solving techniques. CP has been successfully applied in planning, scheduling, and numerous other domains with heterogeneous constraints.

If your problem can be modeled with a linear objective and linear constraints, then you have a linear programming problem and should consider [Glop](https://developers.google.com/optimization/lp). (However, routing problems are typically best solved with our vehicle routing library even if they can be represented with a linear model.)

Two classic CP problems are the [N-queens problem](https://developers.google.com/optimization/cp/queens) and [cryptarithmetic puzzles](https://developers.google.com/optimization/puzzles/cryptarithmetic).

---

_Except as otherwise noted, the content of this page is licensed under the [Creative Commons Attribution 3.0 License](https://creativecommons.org/licenses/by/3.0/), and code samples are licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0). For details, see our [Site Policies](https://developers.google.com/terms/site-policies). Java is a registered trademark of Oracle and/or its affiliates._

_Last updated May 2, 2017._
