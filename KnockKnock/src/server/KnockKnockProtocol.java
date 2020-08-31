package server;

public class KnockKnockProtocol {
	private static final int waiting = 0;
	private static final int sentknockknock = 1;
	private static final int sentclue = 2;
	private static final int another = 3;
	private static final int numjokes = 2;

	private int state = waiting;
	private int currJoke = 0;

	private String[] clues = { "Ach", "Hawaii", "Alcohol" };
	private String[] punchlines = { "Bless you.", "I'm fine, hawaii you?", "Alcohol you later..." };

	public String processInput(String input) {
		String output = null;

		if (state == waiting) {
			output = "Knock! Knock!";
			state = sentknockknock;
		} else if (state == sentknockknock) {
			if (input.equalsIgnoreCase("who's there?") || input.equalsIgnoreCase("who's there")) {
				output = clues[currJoke];
				state = sentclue;
			} else {
				output = "No, now you say \"Who's there?\"";
			}
		} else if (state == sentclue) {
			if (input.equalsIgnoreCase(clues[currJoke]+" who?") || input.equalsIgnoreCase(clues[currJoke]+" who")) {
				output = punchlines[currJoke] + "Want another? (y/n)";
				state = another;
			} else {
				output = "No, you're supposed to say "+clues[currJoke]+" who?";
			}
		} else if (state == another) {
			if (input.equalsIgnoreCase("y")) {
				output = "Knock! Knock!";
				if (currJoke == (numjokes - 1))
					currJoke = 0;
				else
					currJoke++;
				state = sentknockknock;
			} else {
				output = "Ok, well, bye.";
				state = waiting;
			}
		}
		return output;
		// switch (state) {
		// 	case waiting:
		// 		output = "Knock! Knock!";
		// 		state = sentknockknock;
		// 		break;
		// 	case sentknockknock:
		// 		if (input.equalsIgnoreCase("who's there?") || input.equalsIgnoreCase("who's there")) {
		// 			output = clues[currJoke];
		// 			state = sentclue;
		// 		} else {
		// 			output = "No, now you say \"Who's there?\"";
		// 		}
		// 		break;
		// 	case sentclue:
		// 		if (input.equalsIgnoreCase(clues[currJoke]+" who?") || input.equalsIgnoreCase(clues[currJoke]+" who")) {
		// 			output = punchlines[currJoke] + "\n Want another? (y/n)";
		// 			state = another;
		// 		} else {
		// 			output = "No, you're supposed to say "+clues[currJoke]+" who?";
		// 		}
		// 		break;
		// 	case another:
		// 		if (input.equalsIgnoreCase("y")) {
		// 			output = "Knock! Knock!";
		// 			if (currJoke == (numjokes - 1))
		// 				currJoke = 0;
		// 			else
		// 				currJoke++;
		// 			state = sentknockknock;
		// 		} else {
		// 			output = "Ok, well, bye.";
		// 			state = waiting;
		// 		}
		// 		break;
		// 	}
		// return output;
	}
}
