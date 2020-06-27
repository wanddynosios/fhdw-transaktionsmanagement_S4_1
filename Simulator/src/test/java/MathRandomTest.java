import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathRandomTest {
    @Test
    public void accessTest1(){
        Random random1 = new Random();
        Random random2 = new Random();
        random1.setSeed(13221l);
        random2.setSeed(13221l);

        for (int i = 0; i < 10000; i++) {
            if (random1.nextInt() != random2.nextInt())
                Assert.fail();
        }
        for (int i = 0; i < 10000; i++) {
            if (random1.nextBoolean() != random2.nextBoolean())
                Assert.fail();
        }
    }

    @Test
    public void accessTest2(){
        Random random1 = new Random();
        Random random2 = new Random();
        for (int i = 0; i < 1000000; i++) {
            if (random1.nextInt() == random2.nextInt())
                if (random1.nextInt() == random2.nextInt())
                    Assert.fail("It is highly unlikely that this test fails multiple times");
        }
    }

}
