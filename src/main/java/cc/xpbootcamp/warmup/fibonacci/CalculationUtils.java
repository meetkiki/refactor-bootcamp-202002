package cc.xpbootcamp.warmup.fibonacci;

public class CalculationUtils {

    /**
     * Fibonacci calculation tools
     * @param number
     * @return
     */
    public static int fibonacci(int number){
        if (number < 1){
            throw new IllegalArgumentException("fibonacci cannot calculate data less than 1");
        } else if (number == 1 || number == 2){
            return 1;
        } else {
            return fibonacci(number - 1) + fibonacci(number - 2);
        }
    }


}
