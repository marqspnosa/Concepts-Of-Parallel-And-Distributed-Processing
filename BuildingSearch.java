import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 


class RoomSearch implements Runnable {
    private final String room;

    public RoomSearch (String room) {
        this.room = room;
    }

    @Override
    public void run() {
        System.out.println("Searching " + room );
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Finished searching " + room );
        }
    }

    public class BuildingSearch {
        public static void main(String[] args) {
            String[] rooms = {"Rooms 100" , "Room 101", "Room 102" , "Room 103" , "Room 104"};
            ExecutorService executor = Executors.newFixedThreadPool(rooms.length);

            for (String room : rooms){
                executor.execute(new RoomSearch(room));
            }

            executor.shutdown();
            while (!executor.isTerminated()) {

            }
            System.out.println(" All rooms have been searched.");
        }
    }


        
    
