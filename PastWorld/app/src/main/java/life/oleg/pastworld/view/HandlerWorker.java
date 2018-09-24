package life.oleg.pastworld.view;


import android.os.Handler;
import android.os.HandlerThread;

public class HandlerWorker extends HandlerThread {

    private Handler mWorkerHandler;

    public HandlerWorker(String name) {
        super(name);
    }

    public void postTask(Runnable task){
        mWorkerHandler.post(task);
    }

    public void prepareHandler(){
        mWorkerHandler = new Handler(getLooper());
    }
}
