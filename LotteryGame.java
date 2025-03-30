import java.util.Scanner;
import java.util.Random;

public class LotteryGame {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Random random = new Random();

        int rounds = 3;
        int bonusRoundCount = 1;

        System.out.println("Welcome to Crayon Muncher's Lottery Game!");
        System.out.println("Rules:\n1. 3 main rounds\n2. Each round increases the numbers you input\n3. Bonus Round included\n4. Have fun!");

        for (int round = 1; round <= rounds + bonusRoundCount; round++) {
            boolean isBonus = (round > rounds);
            int numCount = round + 1;
            int[] userNumbers = new int[numCount];
            int[] numWinners = new int[numCount];

            System.out.println("\n" + (isBonus ? "BONUS ROUND! " : "Round " + round) + " - Enter " + numCount + " numbers (1-10):");

            for (int i = 0; i < numCount; i++) {
                System.out.print("Enter number " + (i + 1) + " (or press Enter twice to exit): ");
                String input = kb.nextLine();

                if (input.equals("")) {
                    System.out.println("Game has ended.\nThank you for playing!");
                    kb.close();
                    return;
                }

                int userInput = 0;
                boolean isNumber = true;

                for (int j = 0; j < input.length(); j++) {
                    char c = input.charAt(j);
                    if (c >= '0' && c <= '9') {
                        userInput = userInput * 10 + (c - '0');
                    } else {
                        isNumber = false;
                        break;
                    }
                }

                if (isNumber && userInput >= 1 && userInput <= 10) {
                    userNumbers[i] = userInput;
                } else {
                    System.out.println("Invalid number. Try again.");
                    i--;
                }
            }

            for (int i = 0; i < numCount; i++) {
                numWinners[i] = random.nextInt(10) + 1; 
            }

            System.out.print("Your numbers: ");
            for (int i = 0; i < numCount; i++) {
                System.out.print(userNumbers[i] + " ");
            }
            System.out.println();

            System.out.print("Winning numbers: ");
            for (int i = 0; i < numCount; i++) {
                System.out.print(numWinners[i] + " ");
            }
            System.out.println();

            int matchCount = 0;
            for (int i = 0; i < numCount; i++) {
                for (int j = 0; j < numCount; j++) {
                    if (userNumbers[i] == numWinners[j]) {
                        matchCount++;
                        break;
                    }
                }
            }

            System.out.println("You matched " + matchCount + " number(s).");

            double totalCombinations = 1;
            for (int i = 0; i < numCount; i++) {
                totalCombinations *= 10;
            }

            double winChance = (1 / totalCombinations) * 100;
            System.out.printf("Chance of winning (exact match): %.10f%%\n", winChance);
        }

        System.out.println("\nThanks for playing! Hope you enjoyed the game.");
        kb.close();
    }
}