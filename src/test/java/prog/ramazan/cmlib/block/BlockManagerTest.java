package prog.ramazan.cmlib.block;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class BlockManagerTest {

    @Test
    public void testStartAndStop() {
        BlockManager blockManager = new BlockManager();
        RepeatableAction action2 = mock(RepeatableAction.class);
        Block block1 = mock(Block.class);
        blockManager.addBlock(block1);
        blockManager.addBlock("block2", action2);

        blockManager.launch();

        verify(action2, timeout(100).atLeastOnce()).action();

        blockManager.clean();

        verify(block1).stop();
    }
}
