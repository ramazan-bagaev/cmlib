package prog.ramazan.cmlib.block;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BlockTest {

    private RepeatableAction repeatableAction;

    @Before
    public void setup() {
        this.repeatableAction = mock(RepeatableAction.class);
    }

    @Test
    public void testStartAndStop() {
        Block block = new Block("testBlock", repeatableAction);
        block.start();
        block.stop();
        verify(repeatableAction).stop();
    }

    @Test
    public void testActionCalledWhileRunning() {
        Block block = new Block("testBlock", repeatableAction);
        block.start();
        verify(repeatableAction, timeout(100).atLeastOnce()).action();
        block.stop();
    }
}
