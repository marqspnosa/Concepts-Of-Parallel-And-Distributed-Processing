import java.util.Random;
import java.util.Scanner;

public class TelephoneGame {

    
    static String message = " ";

    static class Player extends Thread {
        private int playerNumber;

        public Player(int playerNumber) {
            this.playerNumber = playerNumber;
        }

        @Override
        public void run() {
            synchronized (TelephoneGame.class) {
                if (playerNumber == 1) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print(" Player 1, enter a message: ");
                    message = scanner.nextLine();
                } else {
                    message = nextMessage(message);
                    System.out.println(" Player " + playerNumber + " received and changed the message.");
                }

                System.out.println("Message now: " + message);
            }
        }

        private String nextMessage(String msg) {
            String[] words = msg.split(" ");
            if (words.length < 2) return msg;

            Random rand = new Random();
            int i = rand.nextInt(words.length);
            int j = rand.nextInt(words.length);

          
            String temp = words[i];
            words[i] = words[j];
            words[j] = temp;

            return String.join(" ", words);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numberOfPlayers = 5;
        Player[] players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(i + 1);
            players[i].start();
            players[i].join(); 
        }

        System.out.println(" Final message: ");
        System.out.println(message);
    }
}