package model.pizza;

import model.upgrade.*;
import model.item.*;

public abstract class PizzademicPizza implements PizzaProduct{
	
	public abstract PizzademicPizza clone();
	
	public abstract void setUpgrade(Upgrade upgrade);
	public abstract Upgrade getUpgrade();
	
}

