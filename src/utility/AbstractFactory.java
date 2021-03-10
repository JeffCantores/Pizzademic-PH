package utility;

import model.pizza.PizzademicPizza;
import model.upgrade.Upgrade;

public interface AbstractFactory {
		
	PizzademicPizza getPizza(String pizzaFlavor);
	Upgrade getUpgrade(String pizza);
}
