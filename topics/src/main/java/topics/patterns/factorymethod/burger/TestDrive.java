package topics.patterns.factorymethod.burger;

import java.util.ArrayList;
import java.util.List;

class TestDrive {
    public static void main(String[] args) throws InterruptedException {
        List<Burger> takeOut = new ArrayList<>();
        /* write your code here */
        BurgerStore burgerStore = new BurgerStore();
        takeOut.add(burgerStore.orderBurger("Chinese"));
        takeOut.add(burgerStore.orderBurger("American"));
        takeOut.add(burgerStore.orderBurger("Russian"));
    }
}
