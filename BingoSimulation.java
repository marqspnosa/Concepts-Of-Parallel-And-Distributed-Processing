import java.util.Random;



public class BingoSimulation {
    
    public static void main(String [] args) {
        BingoGame game = new BingoGame();

        Player p1 = new Player(game, "Player 1");
        Player p2 = new Player(game, "Player 2");
        Player p3 = new Player(game, "Player 3");

        p1.start();
        p2.start();
        p3.start();
    }
}

class BingoGame {
    private boolean winner = false;
    
    public synchronized void callNumber(int number, String playerName) {

        if (!winner) {
            System.out.println(playerName + " number " + number);
            if (number == 7) {
                winner = true;
                System.out.println("BINGO! " + playerName + " wins!");

            }
        }
    }
    public boolean hasWinner() {
        return winner;
    }
}

class Player extends Thread {
    private final BingoGame game;
    private final Random random = new Random();

    public Player(BingoGame game, String name) {
        super(name);
        this.game = game;
    }

    @Override
    public void run() {
        while (!game.hasWinner()) {
            int number = random.nextInt(10) + 1;
            game.callNumber(number, getName());

            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
                interrupt();
            }
        }

    }
}
