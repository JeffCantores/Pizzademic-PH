package utility;

import model.upgrade.*;

import java.util.HashMap;
import java.util.Map;

//import java.util.ArrayList;
//import java.util.List;

import model.pizza.*;
//import model.item.*;

public class PizzaFactory implements AbstractFactory {
	
//	private List<PizzaProduct> pizzaProducts = new ArrayList<PizzaProduct>();
	private static final Map<String, PizzademicPizza> pizza_prototypes = new HashMap<>();
	
	static {
		pizza_prototypes.put("FOUR CHEESE", new FourCheese());
		pizza_prototypes.put("PEPPERONI", new Pepperoni());
		pizza_prototypes.put("TROPICALE", new Tropicale());
		
	}
	
	
	public PizzademicPizza getPizza(String pizzaFlavor) {	
		
    	try {
            return pizza_prototypes.get(pizzaFlavor).clone();
        } catch (NullPointerException ex) {
            System.out.println(pizzaFlavor + ", doesn't exist");
            return null;
        }
			
	}
	
	
	@Override
	public Upgrade getUpgrade(String pizza) {
		return null;
	}

}
