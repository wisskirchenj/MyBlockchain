package topics.nestedclasses;

/**
 * You have an outer class ArrayCalc and a static nested class MinMaxPair inside.
 * Complete the method MinMaxPair that finds the min and max elements of an array.
 * Motivation: our goal is to find the minimum and the maximum element of an array. Of course, it can be done by two
 * different methods, but a special structure MinMaxPair will allow going through an array only once, which is more effective.
 */
class ArrayCalc {

    // static nested class
    public static class MinMaxPair {
        private int min;
        private int max;

        public MinMaxPair(int first, int second) {
            this.min = first;
            this.max = second;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }

    // find min and max elements
    public static MinMaxPair findMinMax(int[] array) {

        // write your code
        int min = array[0];
        int max = min;
        for (int x: array) {
            min = x < min ? x : min;
            max = x > max ? x : max;
        }

        return new MinMaxPair(min, max);
    }
}

