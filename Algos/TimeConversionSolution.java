package Algos;
/**
 * HackerRank Algos:
 * https://www.hackerrank.com/challenges/time-conversion/problem
 * @author Gusto
 */


import java.util.*;


/**
 * Given a time in 12-hour AM/PM format, convert it to military (24-hour) time format.
 * Input Format:
 *   A single string containing a time in 12-hour clock format (hh:mm:ssAM or hh:mm:ssPM)
 *   Where 01 <= hh <= 12 and 00 <= mm,ss <= 59
 * Output:
 *   Convert and print the given time in 24-hour format
 *   Where 01 <= hh <= 24 and 00 <= mm,ss <= 59
 */
public class TimeConversionSolution {

	/**
	 * Notes:
	 *   12AM = 00
	 *   12PM = 12
	 *   01-11AM = 01-11
	 *   01-11PM = 13-23
	 *
	 *   if s is AM
	 *     if s_hh = 12, then m_hh = 00
	 *     else then m_hh = s_hh
	 *   else (PM)
	 *     if s_hh = 12, then m_hh = s_hh
	 *     else then m_hh = s_hh + 12
	 * @param standardTime
	 * @return
	 */
	public static String timeConversionChar(String standardTime) {
		// First parse the String into time chars
		char sh1 = standardTime.charAt(0);
		char sh2 = standardTime.charAt(1);
		String sh12 =  "" + sh1 + sh2;
		int standardHour = Integer.parseInt(sh12);
		// Discard 2 ':'
		char sm1 = standardTime.charAt(3);
		char sm2 = standardTime.charAt(4);
		// Discard 5 ':'
		char ss1 = standardTime.charAt(6);
		char ss2 = standardTime.charAt(7);
		char sd1 = standardTime.charAt(8);

		// Determine the military equivalent
		String militaryTime = null;
		if (sd1 == 'A') { // AM
		    if (standardHour == 12) {
		    	militaryTime = "00" + ":" + sm1 + sm2 + ":" + ss1 + ss2;
		    }
		    else {
		    	militaryTime = sh12 + ":" + sm1 + sm2 + ":" + ss1 + ss2;
		    }
		}
		else { //PM
			if (standardHour == 12) {
				militaryTime = sh12 + ":" + sm1 + sm2 + ":" + ss1 + ss2;
			}
			else {
				int militaryHour = standardHour + 12;
				militaryTime = militaryHour + ":" + sm1 + sm2 + ":" + ss1 + ss2;
			}
		}

        return militaryTime;
    }


	/**
	 * Using String functions like substring instead of chars like above...
	 * @param standardTime
	 * @return
	 */
	public static String timeConversionString(String standardTime) {
		// Parse out various substrings from the passed standard time input string
		// The passed standard time designation string ("AM" or "PM")
		String standardTimeDesignation = standardTime.substring(standardTime.length() - 2);
		// Standard time string with the "AM" or "PM" removed
		String standardTimeNoDesignation = standardTime.substring(0, (standardTime.length() - 2));
		String standardHourString = standardTimeNoDesignation.substring(0, 2);
		String standardMinSecString = standardTimeNoDesignation.substring(2);
		int standardHour = Integer.parseInt(standardHourString);

		// DEBUG
		System.out.println("Standard time input designation: " + standardTimeDesignation);
		System.out.println("Standard time input string (no designation): " + standardTimeNoDesignation);
        System.out.println("Standard time input hour string: " + standardHourString);
        System.out.println("Standard time input :mm:ss string: " + standardMinSecString);
        System.out.println("Standard time input hour Integer: " + standardHour);

		// Determine the military equivalent
		String militaryTime = null;
		if (standardTimeDesignation.contains("AM")) {
		    if (standardHour == 12) {
		    	militaryTime = "00" + standardMinSecString;
		    }
		    else {
		    	militaryTime = standardTimeNoDesignation;
		    }
		}
		else { //PM
			if (standardHour == 12) {
				militaryTime = standardTimeNoDesignation;
			}
			else {
				int militaryHour = standardHour + 12;
				militaryTime = militaryHour + standardMinSecString;
			}
		}

        return militaryTime;
    }


	/**
	 * MAIN
	 * @param args
	 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        in.close();

        // Print the input standard time in 24-hour (military) format
        //String result = timeConversionChar(s);
        String result = timeConversionString(s);
        System.out.println(result);
    }

	/**
	 *
	 ** TEST INPUT 1:
07:05:45PM
     ** OUTPUT 1:
19:05:45
     *
     ** TEST INPUT 2:
12:00:00AM
     ** OUTPUT 2:
00:00:00
     *
     ** TEST INPUT 3:
12:00:00PM
     ** OUTPUT 3:
12:00:00
     *
     ** TEST INPUT 4:
01:12:23AM
     ** OUTPUT 3:
01:12:23
     *
	 */
}
