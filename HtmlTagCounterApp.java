import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class HtmlTagCounterApp {
    public static void main(String[] args) {

        String[] files = {"demo.html", "webpage.html" };
        
        for (int i = 0; i < files.length; i++) {
            HtmlTagCounter t = new HtmlTagCounter(files[i], i + 1);
            t.start();
        }
    }
}


class HtmlTagCounter extends Thread{
    private final String fileName; 
    private final int threadNum;

    public HtmlTagCounter(String fileName, int threadNum) {
        this.fileName = fileName;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        int tagCount = countHtmlTags(fileName);
        System.out.println("Thread Number: " + threadNum + "," + "Tag Count: " + tagCount);
    }

        private int countHtmlTags(String file){
            int count = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = br.readLine()) != null) {
                count += line.split("<").length-1;
                }
            } catch (IOException e){
            }
            return count;
        }
    }


