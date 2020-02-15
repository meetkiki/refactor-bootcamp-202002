package cc.xpbootcamp.warmup.fibonacci;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {


    @Test
    public void should_return_1_when_calculate_given_position_is_1(){
        int number = 1;
        int fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(1,fibonacci);
    }

}
