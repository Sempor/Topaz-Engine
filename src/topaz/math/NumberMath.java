package topaz.math;

import topaz.math.collections.Set;

public class NumberMath {

    public static boolean isOdd(int num) {
        return num % 2 == 1;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static boolean isPrime(int num) {
        return num != 1 && getSumOfProperDivisors(num) == 1;
    }

    public static boolean isComposite(int num) {
        return isPrime(num) == false;
    }

    public static int getSumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static Set<Integer> getDivisors(int num) {
        Set<Integer> divisors = new Set<>();
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                divisors.add(i);
            }
        }
        divisors.add(num);
        return divisors;
    }

    public static Set<Integer> getProperDivisors(int num) {
        Set<Integer> divisors = new Set<>();
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                divisors.add(i);
            }
        }
        return divisors;
    }

    public static int getSumOfDivisors(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        sum += num;
        return sum;
    }

    public static int getSumOfProperDivisors(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static boolean isPerfectNumber(int num) {
        return getSumOfDivisors(num) == 2 * num;
    }

    public static boolean isAlmostPerfectNumber(int num) {
        return getSumOfDivisors(num) == 2 * num - 1;
    }

    public static boolean isQuasiperfectNumber(int num) {
        return getSumOfDivisors(num) == 2 * num + 1;
    }

    public static boolean isMultiplyPerfectNumber(int num, int k) {
        return getSumOfDivisors(num) == k * num;
    }

    /**
     * Finds the GCD of two numbers
     *
     * @param num1
     * @param num2
     * @return
     */
    public static int getGCD(int num1, int num2) {
        int greater, lesser;
        if (num1 > num2) {
            greater = num1;
            lesser = num2;
        } else {
            greater = num2;
            lesser = num1;
        }

        //Euclidean algorithm
        int remainder;
        while (true) {
            int quotient = greater / lesser;
            remainder = greater - quotient * lesser;

            if (remainder == 0) {
                break;
            }

            greater = lesser;
            lesser = remainder;
        }

        return lesser; //This is the GCD
    }

    /**
     * Determines whether two numbers are relatively prime
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean areRelativelyPrime(int num1, int num2) {
        int GCD = getGCD(num1, num2);
        if (GCD == 1) {
            return true;
        }
        return false;
    }

    public static boolean areAmicable(int num1, int num2) {
        return getSumOfProperDivisors(num1) == getSumOfProperDivisors(num2);
    }

    public static boolean areBetrothed(int num1, int num2) {
        return getSumOfProperDivisors(num1) == num2 + 1 && getSumOfProperDivisors(num2) == num1 + 1;
    }

    public static int toBinary(int num) {
        int binary = 0;
        for (int i = 1; num > 0; i *= 10) {
            binary += (num % 2) * i;
            num /= 2;
        }
        return binary;
    }

    public static void testMethods() {
        System.out.println("Test findGCD method:");
        System.out.println(getGCD(53, 77) + " = 1");
        System.out.println(getGCD(91, 77) + " = 7");
    }
}
