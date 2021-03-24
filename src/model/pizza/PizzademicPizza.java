package model.pizza;

import utility.*;
import model.item.*;

public abstract class PizzademicPizza implements PizzaProduct{

	public abstract PizzademicPizza clone();
	
	@Override
	public Packing packing() {
		return new Wrapper();
	}
	
}
