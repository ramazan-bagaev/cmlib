package prog.ramazan.cmlib.block;

import java.util.Collection;
import java.util.HashSet;

public class BlockManager {

    private final Collection<Block> blocks;

    public BlockManager() {
        this.blocks = new HashSet<>();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void addBlock(String name, RepeatableAction repeatableAction) {
        Block block = new Block(name, repeatableAction);
        blocks.add(block);
    }

    public void launch() {
        for (Block block: blocks) {
            block.start();
        }
    }

    public void waitForEnd() {
        try {
            while (isActive()) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException ignored) {
        }
    }

    public void clean() {
        for (Block block: blocks) {
            block.stop();
        }

        blocks.clear();
    }

    private boolean isActive() {
        for (Block block: blocks) {
            if (!block.isStopped()) {
                return true;
            }
        }

        return false;
    }
}
