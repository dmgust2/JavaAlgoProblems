package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-comparator-sorting/problem
 * @author Gusto
 */


import java.util.*;


/**
 * Comparators are used to compare two objects. In this challenge, you'll create a comparator and use it to sort an array.
 * The Player class is provided in the editor below; it has two fields:
 *   - A string name
 *   - An integer score
 * Task:
 *   Given an array of n Player objects, write a comparator that sorts them in order of decreasing score
 *   If 2 or more players have the same score, sort those players alphabetically by name.
 *   To do this, you must create a Checker class that implements the Comparator interface,
 *    then write an int compare(Player a, Player b) method implementing the Comparator.compare(T o1, T o2) method.
 *
 * Input Format:
 *   Locked stub code in the Solution class handles the following input from STDIN: 
 *     The first line contains an integer n denoting the number of players. 
 *     Each of the n subsequent lines contains a player's respective name and score.
 *
 * Constraints:
 *   0 <= score <= 1000
 *   Two or more players can have the same name
 *   Player names consist of lowercase English alphabetic letters {a-z}
 *
 * Output:
 *   You are not responsible for printing any output to STDOUT. Locked stub code in Solution will create a Checker object,
 *    use it to sort the Player array, and print each sorted element.
 */

class Checker implements Comparator<Player> {

	/**
	 * Compares two Player objects, highest score is first (so this is like descending or reverse sort order)
	 */
	@Override
	public int compare(Player o1, Player o2) {
		// First player's score is greater than the second
		if (o1.score > o2.score) {
		    return -1;
		}
		else if (o1.score < o2.score) {
			return 1;
		}
		else {
			// Scores are equal so now compare by name
			return o1.name.compareTo(o2.name);
		}
	}
}


class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}


public class ComparatorSolution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();

        for (int i = 0; i < n; i++) {
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        // Sort the array of players in descending order (highest score first) using our Checker comparator
        Arrays.sort(player, checker);

        // Print out the list of sorted players
        for (int i = 0; i < player.length; i++) {
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
	}

	/**
	 *
	 ** TEST INPUT 1:
5
amy 100
david 100
heraldo 50
aakansha 75
aleksa 150
	 ** OUTPUT 1:
aleksa 150
amy 100
david 100
aakansha 75
heraldo 50
	 *
	 *
	 ** TEST INPUT 2:
?
	 ** OUTPUT 2:
?
	 *
	 */
}
