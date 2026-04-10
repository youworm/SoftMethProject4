package org.example.project4.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BuildYourOwnTest {

    @Test
    void testSmallBasePriceNoToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);

        assertEquals(10.99, pizza.price(), 0.001);
    }

    @Test
    void testMediumBasePriceNoToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.MEDIUM);

        assertEquals(12.99, pizza.price(), 0.001);
    }

    @Test
    void testLargeBasePriceNoToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.LARGE);

        assertEquals(14.99, pizza.price(), 0.001);
    }

    @Test
    void testPriceWithToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);

        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.MUSHROOM);

        assertEquals(14.37, pizza.price(), 0.001);
    }

    @Test
    void testMaxToppingsLimit() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.SMALL);

        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PROVOLONE);
        pizza.addTopping(Topping.CHEDDAR);

        assertEquals(5, pizza.getToppings().size());
    }
}