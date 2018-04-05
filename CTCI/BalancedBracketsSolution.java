package CTCI;
/**
 * HackerRank CTCI:
 * https://www.hackerrank.com/challenges/ctci-balanced-brackets/problem
 * @author Gusto
 */


import java.util.*;


/**
 * A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
 * Two brackets are considered to be a matched pair if the an opening bracket (i.e., (, [, or {) occurs to the left of a closing bracket (i.e., ), ], or }) 
 *   of the exact same type. There are three types of matched pairs of brackets: [], {}, and ().
 * A matching pair of brackets is not balanced if the set of brackets it encloses are not matched.
 * For example, {[(])} is not balanced because the contents in between { and } are not balanced.
 *   The pair of square brackets encloses a single, unbalanced opening bracket, (, and the pair of parentheses encloses a single, unbalanced closing 
 *   square bracket, ].
 * By this logic, we say a sequence of brackets is considered to be balanced if the following conditions are met:
 *   - It contains no unmatched brackets.
 *   - The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.
 * Given n strings of brackets, determine whether each sequence of brackets is balanced.
 * If a string is balanced, print YES on a new line; otherwise, print NO on a new line.
 *
 * Input Format:
 *   The first line contains a single integer n denoting the number of strings.
 *   Each line i of the n subsequent lines consists of a single string s denoting a sequence of brackets.
 *
 * Constraints:
 *   1 <= n <= 1000
 *   1 <= length(s) <= 1000
 *   Each character in the sequence will be a bracket i.e. {, }, (, ), [, and ]
 *
 * Output:
 *   For each string s, print whether or not the string of brackets is balanced on a new line.
 *   If the brackets are balanced, print YES; otherwise, print NO.
 */
public class BalancedBracketsSolution {

	/**
	 * Determines if the passed string of brackets is balanced
	 * @param expression
	 * @return
	 */
    public static boolean isBalanced(String expression) {
    	/** ALGO:
    	 * - Create a stack (Optional: the number of elements = 1/2 of the string.length)
    	 * - Iterate over the string/expression:
    	 *   - Push any left brackets i.e. {, (, [ on to a stack until you hit a right bracket i.e. }, ), ]
    	 *   - When you hit a right bracket, pop off the top bracket from the stack
    	 *     - If the bracket you pop off does NOT match, OR the stack is empty (nothing to pop), then return false
    	 *   * If we create a fixed size Stack: If the stack ever becomes full, then return false
    	 *   - At the end of the string/expression if the stack is empty, return true, else return false
    	 */


    	// Create a char Stack for brackets
    	Stack<Character> stack = new Stack<Character>();

//    	for (int i = 0; i < expression.length(); i++) {
//    		char bracket = expression.charAt(i);
    	for (char bracket : expression.toCharArray()) {
    		if (isLeftBracket(bracket)) {
    			// Push the left bracket on the stack
    			stack.push(bracket);
    		}
    		// Note: Since the input strings are guaranteed to contain only brackets, we don't need an explicit check for right brackets here...
    		else {
    			// If the stack is empty or the right bracket you pop off doesn't match, return false
    			if (stack.isEmpty() || !bracketsMatch(stack.pop(), bracket)) {
    				return false;
    			}
    		}
    	}

    	// If after iterating through the expression the Stack is empty, then the expression must be balanced
    	return stack.isEmpty();
    }


    /**
     * Helper method returns true of the passed char is a valid LEFT bracket
     * @param c
     * @return
     */
    public static boolean isLeftBracket(char c) {
    	if ((c == '{') || (c == '(') || (c == '[')) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }


    /**
     * Helper method determines if the passed left and right brackets match e.g. '{' & '}'
     * @param left
     * @param right
     * @return
     */
    public static boolean bracketsMatch(char left, char right) {
    	if ((left == '{') && (right == '}')) {
    	    return true;
    	}
    	else if ((left == '(') && (right == ')')) {
			return true;
	    }
    	else if ((left == '[') && (right == ']')) {
			return true;
	    }
    	else {
    		return false;
    	}
    }


    /**
	 * MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String expression = in.next();
            System.out.println((isBalanced(expression)) ? "YES" : "NO");
        }
        in.close();
	}

	/**
	 *
	 ** TEST INPUT 1:
3
{[()]}
{[(])}
{{[[(())]]}}
    ** OUTPUT 1:
YES
NO
YES
    *
    *
    ** TEST INPUT 2:
1
}
    ** OUTPUT 2:
NO
    *
    *
    ** TEST INPUT 3:
1
{
    ** OUTPUT 3:
NO
    *
    *
    ** TEST INPUT 4:
1
((({{{[[[((({{{}}})))]]]}}})))(
    ** OUTPUT 4:
NO
	 */
}
