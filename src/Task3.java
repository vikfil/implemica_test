import java.math.BigInteger;

public class Task3 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println(digitSumOffactorial(n));

    }
    // BigInteger in order that not be an overflow of int type
    public static int digitSumOffactorial(int number) {
        BigInteger sum = BigInteger.valueOf(0);
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        while (factorial.compareTo(BigInteger.valueOf(0)) > 0) {
            //divide factorial by module
            //and add this number to sum
            sum = sum.add(factorial.mod(BigInteger.valueOf(10)));
            //decrement the factorial
            factorial = factorial.divide(BigInteger.valueOf(10));
        }
       return sum.intValue();
    }
}
