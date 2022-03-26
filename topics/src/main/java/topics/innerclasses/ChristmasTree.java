package topics.innerclasses;

/**
 * You have an outer class ChristmasTree and an inner class TreeTopper. Both classes have the field color.
 * In TreeTopper class create method void sparkle.
 * For silver tree topper and green Christmas tree the output of sparkle will be:
 * Sparkling silver tree topper looks stunning with green Christmas tree!
 * In the outer class create a method void putTreeTopper with one string parameter color.
 * In this method, you should create an instance of an inner class with parameter color, then call method sparkle of TreeTopper.
 */
public class ChristmasTree {

    private String color;

    public ChristmasTree(String color) {
        this.color = color;
    }

    // create method putTreeTopper()
    public void putTreeTopper(String color) {
        TreeTopper treeTopper = this.new TreeTopper(color);
        treeTopper.sparkle();
    }

    public String getColor() {
        return color;
    }

    class TreeTopper {

        private String color;

        public TreeTopper(String color) {
            this.color = color;
        }

        public void sparkle() {
            System.out.println("Sparkling " + color + " tree topper looks stunning with "
                    + getColor() + " Christmas tree!");
        }
        // create method sparkle()
    }
}

