package topics.nestedclasses;

import java.util.Scanner;

/**
 * main for ArrayCalc.
 */
class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // input array
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = scanner.nextInt();
        }

        ArrayCalc.MinMaxPair p = ArrayCalc.findMinMax(array);

        System.out.println("min = " + p.getMin());
        System.out.println("max = " + p.getMax());
    }
}
