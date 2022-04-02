package topics.patterns.factorymethod.burger;

/**
 * Let's create the BurgerStore. We will stick to the good old classic and simply create a burger with
 * Bun, Cutlet and Sauce. Do not forget that FactoryMethod does not include details, it only knows the general creation process.
 */
class BurgerStore extends BurgerFactory {
    @Override
    Burger createBurger(String type) {
        switch (type) {
            case "Chinese":
                return new ChineseBurger();
            case "American":
                return new AmericanBurger();
            case "Russian":
                return new RussianBurger();
            default:
                return null;
        }
    }
}

