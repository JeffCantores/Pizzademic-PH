package utility;

import java.util.HashMap;
import java.util.Map;

import model.pizza.PizzademicPizza;
import model.upgrade.*;

public class UpgradePizzaFactory implements AbstractFactory{
	
	private static final Map<String, Upgrade> upgrade_prototypes = new HashMap<>();
	
	static {
		upgrade_prototypes.put("FOUR CHEESE", new FourCheeseUpgrade());
		upgrade_prototypes.put("PEPPERONI", new PepperoniUpgrade());
		upgrade_prototypes.put("TROPICALE", new TropicaleUpgrade());
		
	}
	
	public Upgrade getUpgrade(String pizzaFlavor) {
		
    	try {
            return upgrade_prototypes.get(pizzaFlavor).clone();
        } catch (NullPointerException ex) {
            System.out.println(pizzaFlavor + ", doesn't exist");
            return null;
        }
	}

	@Override
	public PizzademicPizza getPizza(String pizzaFlavor) {
		return null;
	}
	
}
