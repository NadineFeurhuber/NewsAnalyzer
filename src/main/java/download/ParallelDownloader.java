package download;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.Future;

public class ParallelDownloader extends Downloader{

    // https://dzone.com/articles/java-concurrency-multi-threading-with-executorserv

    ExecutorService executer = Executors.newCachedThreadPool();

    @Override
    public int process(List<String> urls) {

        long startpoint = System.currentTimeMillis();
        int count = 0;
        for (String url : urls) {
            try{
                Future < ? > status = executer.submit(()->{saveUrl2File(url);});
                count++;
            }catch(Exception e){
                System.out.println("Not found.");
            }
        }

        long endpoint = System.currentTimeMillis();
        System.out.println("Time: " + ((endpoint-startpoint)) + " Milliseconds.");
        executer.shutdown();
        return count;
    }

}
