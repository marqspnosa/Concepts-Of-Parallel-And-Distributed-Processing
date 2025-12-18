public class ProcessPatterns {
    
  static void simplePattern() {
    System.out.println(" SIMPLE PATTERN ");
    System.out.println(" Starting... ");
        
        Thread child = new Thread(() -> {
            System.out.println("  Child: Working ");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println("  Child: Done ");
        });
        
        child.start();
        try { child.join(); } catch (InterruptedException e) {}
        
        System.out.println(" Child finished, now I'm done");
    }
    
    
    static void fanPattern() {
        System.out.println(" ");
        System.out.println(" Starting... ");
        
        int numChildren = 4;
        Thread[] children = new Thread[numChildren];
        
    
        for (int i = 0; i < numChildren; i++) {
            final int id = i + 1;
            children[i] = new Thread(() -> {
                System.out.println("  Child " + id + " Working ");
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
                System.out.println("  Child " + id + " Done ");
            });
            children[i].start();
        }
        
        try {
            for (Thread child : children) {
                child.join();
            }
        } catch (InterruptedException e) {}
        
        System.out.println(" Parent All children finished, now I'm done ");
    }
    
    static void createChain(int id, int max) {
        System.out.println(" Thread " + id + " Starting");
        
        if (id < max) {
            Thread child = new Thread(() -> createChain(id + 1, max));
            child.start();
            try { child.join(); } catch (InterruptedException e) {}
        } else {
            System.out.println(" Thread " + id + " I'm the last one, working ");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
        
        System.out.println(" Thread " + id + ": Done");
    }
    
    static void chainPattern() {
        System.out.println(" CHAIN PATTERN ");
        System.out.println(" Starting chain... ");
        createChain(1, 5);
        System.out.println(" Chain complete ");
    }
    
    public static void main(String[] args) {
        System.out.println(" Processing.... ");
        System.out.println(" ");
        
        simplePattern();
        fanPattern();
        chainPattern();
        
        System.out.println(" COMPLETED ");
    }
}