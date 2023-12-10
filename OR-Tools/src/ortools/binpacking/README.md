# 287: NP-Complete

![General solutions get you a 50% tip.](img/np_complete.png)

**Explanation**

Another entry in the ["My Hobby"](https://www.explainxkcd.com/wiki/index.php/My_Hobby) series of comics. [Cueball](https://www.explainxkcd.com/wiki/index.php/Cueball) is embedding [NP-complete problems](https://en.wikipedia.org/wiki/NP-completeness) in restaurant orders. Specifically, he is ordering appetizers not by explicitly stating the names, but by the total price of them all. This is a simplified example of the [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem). This is a problem in combinatorial optimization, as follows: If you have a knapsack (backpack or rucksack) that can hold a specific amount of weight, and you have a set of items, each with its own assigned value and weight, you have to select items to put into the knapsack so that the weight does not exceed the capacity of the knapsack, and the combined value of all the items is maximized.

In [computational complexity theory](https://en.wikipedia.org/wiki/Computational_complexity_theory), NP stands for "nondeterministic polynomial time," which means that problems that are NP take polynomial running time (i.e. the time a CPU would take to run the program would be polynomial in the input size) to verify a solution, but it is unknown whether finding any or all solutions can be done in polynomial time. Polynomial time is considered efficient; exponential and higher times are considered unfeasible for computation. NP-complete problems are ones that, if a polynomial time algorithm is found for any of them, then all NP problems have polynomial time solutions. In short, particular guesses in NP-complete problems can be checked easily, but systematically finding solutions is far more difficult.

The waiter's problem is NP-complete, since a given order's price can be found and checked quickly, but finding an order to match a price is much harder. This causes the order to effectively be an application layer denial-of-service attack / algorithmic complexity attack on the waiter, similar to Slowloris or ReDoS. (Formal proofs of the NP-completeness of the knapsack problem can be found at the above link.) The most straightforward way for a human to find a solution is to methodically start by first listing all the (6) ways of choosing one appetizer, and their total costs, then list all the (21) ways of choosing two appetizers (allowing repeats), and then list all the (56) ways of choosing three appetizers, and so forth. As any combination of eight appetizers would be more than $15.05, the process need not extend beyond listing all the (1715) ways of choosing seven appetizers.

Another famous NP-complete problem is the [travelling salesman problem](https://en.wikipedia.org/wiki/Travelling_salesman_problem), mentioned by Cueball at the end, referring to the waiter's claim that he has 6 more tables to get to. (see also [399: Travelling Salesman Problem](https://xkcd.com/399/README.md)).

The title text refers to the fact that NP-complete problems have no known polynomial time general solutions, and it is unknown if such a solution can ever be found. If the waiter can find an efficient general solution to this, he will have solved one of the most famous problems in mathematics. This problem is one of the six remaining unsolved [Millennium Prize Problems](https://en.wikipedia.org/wiki/Millennium_Prize_Problems) stated by the Clay Mathematics Institute in 2000, for which a correct solution (including proving that such a solution doesn't exist) is worth US$1,000,000. A 50% tip is slightly less than fair.

For those curious, there are exactly two combinations of appetizers that total $15.05 and solve the problem posed in the comic strip:

A combination of two orders of hot wings, one order of mixed fruit, and one sampler plate
Seven mixed fruit orders (this solution was not intended - see trivia below)

**Trivia**

- "Chotchkies" (slightly misspelt) is Yiddish slang for little accessories and trinkets. It is also the name of the restaurant in the 1999 Mike Judge-directed comedy Office Space.
- In an [interview](https://www.maa.org/press/periodicals/math-horizons/the-mathematics-behind-xkcd-a-conversation-with-randall-munroe-0) with the Mathematical Association of America, Randall said that the trivial answer to this problem was a mistake.

# Discussion

**Unique deciphering requires unique pricetags**

Shame this only works in restaurants that price all their appetizers differently. Davidy22 (talk) 03:18, 13 October 2012 (UTC)

Not necessarily because the NP-problem allows for any equivocally competing sum certifying how the total can be reached. Shared pricetags as well as a nonpositive would add degrees of freedom and make it impossible to rule out surprise deliveries even through exponential pretesting. Unless the waiter is running into the exponential worst case, the six waiting tables can be attended to immediately upon finding the first feasible combination: Roman Czyborra (talk) 15:44, 15 May 2014 (UTC)

**Trivial solution first found**

I have a hunch that the seven fruit cups are pretty intentional as the first item on the menu and the simplest solution possible. I was about to write a script to solve the problem through random selections and was going to optimize for speed by limiting the maximum times an item could be order to floor(15.05/price). Thus, one could order up to 2 sample plates, 3 moz sticks, 5 of the hot wings/side salad/french fries or 7 fruit cups without going over budget. (side note: you can always with these prices squeeze in a fruit cup with the exception of the 7 fruit cups). I found the "trivial" solution on the first step of the "preliminary" work for that script and then took a catnap. Of course, since the nontrivial solution involves the same item as the trivial solution, one could just pick a number, multiply by that number, subtract one unit, and pick two other items, whose prices were not set yet, and adjust their prices to add up accordingly just to ensure both trivial and nontrivial solutions lest anyone actually write a program to solve the problem through brute force as oppose to through wit. Why seed? Because to not have a nontrivial solution would be so much like Blackhat. Note to self: try this sometime in the real world using a real menu. Katya (talk) 02:17, 23 November 2012 (UTC)

**Traveling Salesman Problem**

Note: Traveling Salesman Problem might be mentioned also because both this problem and the Knapsack problem to be solved belong to set of NP-complete problems; a Knapsack problem can be transformed in polynomial time to Traveling Salesman Problem, and solution of Traveling Salesman Problem can be transformed in polynomial time to Knapsack problem solution. --JakubNarebski (talk) 16:00, 11 December 2012 (UTC)

Yes, indeed! I think both meanings are intended to fully get the joke. The TSP:={(n,d,M)∈ℕ×({0…n}²→ℕ)×ℕ|∃c∈{1…n}ⁿ:{1…n}=⋃{cₙ|n∈{1…n}}∧∑{d(cₙ,c₍ₙ₊₁₎)|n∈{0…n}}<M} can both help to timely attend to the six waiting tables and to reduce the ORDERSUM:={(a,b)∈ℕ*×ℕ|∃c∈ℕ*:∑{cₙaₙ|n∈ℕ}=b} problem to. Plus, the "as fast as possible" pun seems to allude to the again six ridiculous inputs any trained human will rearrange to a near-exact solution quicker than they are entered into a computer who can quickly exhaust this tiny search space for an exact solution: Roman Czyborra (talk) 15:44, 15 May 2014 (UTC)

**Trivial solution was not intended**

In an interview with the Mathematical Association of America Randall said that the trivial answer to this problem was a mistake. Xrays Knock Charms Down (talk) 03:00, 6 May 2013 (UTC)

I added this very interesting info to the explanation - at first as a trivia, but then I realized that it would not be seen by everyone - as you often do not read below the transcript. Why would you, you do not need to see what was in the comic again... So I moved it up to the solution part, because to me it is a very important fact about this comic. An error by Randall... But Dgbrt keeps moving this info away from the solution. I have understood now that the trivia should be below the transcript - although I cannot see why this should be so - as I have just described. But who says that this info should be a trivia item? It was I who put it there (by mistake?) at first. I will try not to start an editing fight here, but still think there should at least be a mention in the explanation that it was a mistake - in case you do not realize there is a trivia section below. I have used this page a lot lately, and had not found out before, that it was always below. There is not that many pages with trivia sections Kynde (talk) 11:02, 10 March 2014 (UTC)
Cool reference, thanks! Roman Czyborra (talk) 15:44, 15 May 2014 (UTC)

**Complex solution found in a second**

I was bored and tried to find a solution for fun. I found the more complex one quite fast by chance. It was actually the second combination I tried. I did not realize you could just add seven fruit cups because I was so set on starting with the sampler plate. Now I am not sure if I should be glad, because I was so lucky, or annoyed that my fight-the-boredom-idea did not work out, or even more annoyed that I never have that kind of luck in the lab where I could really use it for finding the one thing out of a thousand possible causes for "why-does-my-experiment-not-work" which actually will give me some usable data. 84.56.77.11

**Not the knapsack problem**

This explanation is thorough, and I like being thorough, but it seems to be a bit of overkill. I copy-edited it a bit, but I have a couple qualms. This is not really the knapsack problem, as it does not attach values to the items (as mentioned). It is more of a subset sum problem, which admittedly could be considered a variant of the knapsack problem. Secondly, I don't see why we need to go into detail about the movie Office Space. --Quicksilver (talk) 18:34, 22 August 2013 (UTC)

I did some clean-ups, but the the "In computational complexity theory" still needs a review.--Dgbrt (talk) 20:19, 22 August 2013 (UTC)
The Wikipedia article on Karp's 21 NP-complete problems hints that Karp originally defined KNAPSACK:={(a,b)∈ℤ*×ℤ|∃c∈𝔹*:∑{cₙaₙ|n∈ℕ}=b} closer to today's shape of SUBSETSUM:={Z⊂ℤ|∃s⊆Z:∑s=0∧s≠∅} than that of the Unbounded Knapsack Problem UKP:={(v,w,V,W)∈ℤ*×ℤ*×ℤ×ℤ|∃c∈ℕ*:{∑{cₙvₙ|n∈ℕ},∑{cₙwₙ|n∈ℕ}}⊆{V…W}} with the former via Z:={b,-a₁…-aₙ,-2a₁…-2aₙ,…} and the latter via (v,w,V,W):=(a,a,b,b) coming close enough to what we really need here, namely ORDERSUM:={(a,b)∈ℕ*×ℕ|∃c∈ℕ*:∑{cₙaₙ|n∈ℕ}=b}. So Randall did hit it bull's eye after all! Roman Czyborra (talk) 15:44, 15 May 2014 (UTC)

**NP Food**

Inspired by this comic, somebody has actually created an ordering site which tries to give you an order from a restaurant in your area (US only I think) totalling a specific amount NP Food. Worth including above? -- Copito (talk) 20:43, 8 November 2013 (UTC)

That site doesn't work for me. —TobyBartels (talk) 10:07, 19 November 2013 (UTC)

I do get more than nothing: a redirect to the HTTPS port whose certificate is signed only to .np-food.com without WWW and whose HTML and PNG and JS suggest that either solutions for San Francisco, Austin, Saint Louis, Miami, and New York menues have been memoized and that you may order by entering your credit card credentials or that only fools wait for a computer to calculate an NP-hard problem on too large a search space. Roman Czyborra (talk) 15:44, 15 May 2014 (UTC)
Exhaustive Solution
Roman Czyborra did post this at the explain:

**The Solution**

… can be calculated as

```bash
let totaling total menu = if total == 0 then [[]]
 else if total < 0 || null menu then []
 else totaling total (tail menu) ++ map (
 head menu :) (totaling (total - head menu) menu)
in totaling 1505 [215,275,335,355,420,580]
== [[215,355,355,580],[215,215,215,215,215,215,215]]
```
I don't think this is a helpful explain. --Dgbrt (talk) 19:11, 14 May 2014 (UTC)

Yes, I did. Because I did think it was helpful. Not just because an (effective if not efficient) general solution earns you a 50% on $15.05 tip.

Moreover to demonstrate that and how a complete search finds those two solutions.

And that the search tree can branch exponentially with each additional menu item. Or with additional dollar bills to be spent. Notwithstanding that any constructive proof of NP=P would let us replace this straightforward bad NP-implementation with an equivalent better P-implementation. Before Donald Knuth coined the name NP-Complete, the class was suggested to be named PET for the (Probably(while NP?P)|(Proven(if NP>P)|Previously(if NP=P))) Exponential Time pet problems.

What is so confusing about the calculation? The whole cent amounts instead of dollar floats? My naming of variables? Should totaling be renamed to solutions or orders? Or menu to menu_items or appetizers or pricetags?

```bash
 type Cents = Int
orders :: [Cents] -> Cents -> [ [Cents] ]
orders menu total =
 total == 0 | [ [] ]
 menu == [] | []
 total < 0  | []
 total > 0  | orders (tail menu) total ++ map (
 head menu :) orders menu (total - head menu)

orders [215,275,335,355,420,580] 1505
== [[215,355,355,580],[215,215,215,215,215,215,215]]

calls menu total = if null menu || total < 1
 then 1 else 1 + calls (tail menu) total + 
                 calls       menu (total - head menu)
calls [] 1505
== 1
calls [580] 1505
== 7
calls [420,580] 1505
== 25
calls [355,420,580] 1505
== 73
calls [335,355,420,580] 1505
== 181
calls [275,335,355,420,580] 1505
== 437
calls [215,275,335,355,420,580] 1505
== 1153
```
Or is it the committee language Haskell that is causing problems? What other well-defined language would you formulate a general solution in?

Discussing all of this is helpful. Leaving a "Thus" result without its afferent reasoning (and its deleted heading) is not, is it? Cheers: Roman Czyborra (talk) 15:44, 15 May 2014 (UTC)

Please let's keep this code at the discussion page. No common reader would understand; the explain is not only for programmers. I'm a programmer, knowing many languages like BASIC, Pascal, C, C++, Java, Bash, Perl... also HTML, JavaScript... RPG, Databases and SQL... and much more. And if you like to buy an IBM Power 8 I can tell you the proper configuration for your needs.
But these details are not helpful to explain the comic. There is math that has to be explained. Findings on program codes do even not belong to a trivia section. Nevertheless it seems I have to take a closer look on Haskell, which is not used by many people. --Dgbrt (talk) 21:22, 15 May 2014 (UTC)
A 50% tip on a $ 15.05 order is not possible, is it? --108.162.231.186 21:08, 1 November 2014 (UTC)

If I were the waiter my response would, at best, be "I'll come back when you're ready to order". At worse it would probably involve burns. -Pennpenn 108.162.250.162 04:27, 7 October 2015 (UTC)

---Easiest response: "Excellent, Sir. I'll raise the price of the french fries to $15.05 - Ruffy314 (talk) 18:19, 21 November 2015 (UTC)

If we assume that "general solutions" implies that it's a polynomial-time solution, is a 50% tip $7.55, $500 000, or $500 007.55? Hppavilion1 (talk) 02:32, 16 September 2017 (UTC)

**A similar situation in real life**

Nobody would do that in real life, right? But look at [http://www.numberphile.com/videos/43_nuggets.html](http://www.numberphile.com/videos/43_nuggets.html) . A guy orders 43 chicken nuggets, which come in boxes of 6, 9 and 20. It is also a Knapsack problem in a menu order. But in that case there is no solution.
