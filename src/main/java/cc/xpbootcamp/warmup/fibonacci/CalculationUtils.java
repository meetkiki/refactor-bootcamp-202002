package cc.xpbootcamp.warmup.fibonacci;

public class CalculationUtils {

    /**
     * Fibonacci calculation tools
     * @param number
     * @return
     */
    public static long fibonacci(long number){
        if (number < 1 || number > 50){
            throw new IllegalArgumentException("position is out of bounding");
        } else if (number == 1 || number == 2){
            return 1;
        } else {
            long tempOne = 1,tempTwo = 1,result = 0;
            for (int i = 3; i <= number; i++) {
                result = tempOne + tempTwo;
                tempTwo = tempOne;
                tempOne = result;
            }
            return result;
        }
    }


}
