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

    @Test
    public void should_return_1_when_calculate_given_position_is_2(){
        int number = 2;
        int fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(1,fibonacci);
    }

    @Test
    public void should_return_2_when_calculate_given_position_is_3(){
        int number = 3;
        int fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(2,fibonacci);
    }


    @Test
    public void should_return_3_when_calculate_given_position_is_4(){
        int number = 4;
        int fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(3,fibonacci);
    }


    @Test
    public void should_return_5_when_calculate_given_position_is_5(){
        int number = 5;
        int fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(5,fibonacci);
    }


    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_when_calculate_given_position_less_1(){
        int number = 0;
        CalculationUtils.fibonacci(number);
    }
}
