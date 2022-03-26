package topics.innerclasses;

/**
 * Inside your Vehicle create a new inner class Body.
 * Body should have field String color, its constructor and method void printColor for printing the color.
 * The output of the method printColor for a vehicle called Dixi with a red body will look like:
 * Vehicle Dixi has red body.
 */
public class Vehicle {

    private String name;

    Vehicle(String name) {
        this.name = name;
    }

    class Body {

        private String color;

        Body(String color) {
            this.color = color;
        }

        void printColor() {
            System.out.println("Vehicle " + name + " has " + color + " body.");
        }
    }
    // create constructor

    class Engine {

        void start() {
            System.out.println("RRRrrrrrrr....");
        }

    }

    // create class Body
}

