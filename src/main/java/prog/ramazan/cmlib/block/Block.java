package prog.ramazan.cmlib.block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Block implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Block.class);

    private final ExecutorService executorService;
    private final AtomicBoolean stopped;
    private final String name;
    private final RepeatableAction repeatableAction;

    public Block(String name, RepeatableAction repeatableAction) {
        this.name = name;
        this.repeatableAction = repeatableAction;
        this.executorService = Executors.newSingleThreadExecutor();
        this.stopped = new AtomicBoolean(false);
    }

    public void start() {
        logger.info("staring {}", name);
        executorService.submit(this);
    }

    public void stop() {
        logger.info("stopping {}", name);
        executorService.shutdown();
        stopped.set(true);
        repeatableAction.stop();
    }

    public boolean isStopped() {
        return stopped.get();
    }

    @Override
    public void run() {
        logger.info("{} started", name);
        while (!stopped.get()) {
            try {
                repeatableAction.action();
            } catch (Exception e) {
                logger.error("unexpected error", e);
            }
        }
    }
}
