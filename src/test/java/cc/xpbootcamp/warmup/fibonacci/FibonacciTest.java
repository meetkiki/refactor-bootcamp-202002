package cc.xpbootcamp.warmup.fibonacci;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {


    @Test
    public void should_return_1_when_calculate_given_position_is_1(){
        long number = 1;
        long fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(1,fibonacci);
    }

    @Test
    public void should_return_1_when_calculate_given_position_is_2(){
        long number = 2;
        long fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(1,fibonacci);
    }

    @Test
    public void should_return_2_when_calculate_given_position_is_3(){
        long number = 3;
        long fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(2,fibonacci);
    }



    @Test
    public void should_return_5_when_calculate_given_position_is_5(){
        long number = 5;
        long fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(5,fibonacci);
    }


    @Test
    public void should_return_12586269025L_when_calculate_given_position_is_50(){
        long number = 50;
        long fibonacci = CalculationUtils.fibonacci(number);
        Assert.assertEquals(12586269025L,fibonacci);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_when_calculate_given_position_less_1(){
        long number = 0;
        CalculationUtils.fibonacci(number);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_IllegalArgumentException_when_calculate_given_position_than_50(){
        long number = 60;
        CalculationUtils.fibonacci(number);
    }
}
