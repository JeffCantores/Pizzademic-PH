package model.pizza;

import model.upgrade.*;

public abstract class PizzademicPizza{
	
	public abstract PizzademicPizza clone();
	
	public abstract void setUpgrade(Upgrade upgrade);
	public abstract Upgrade getUpgrade();
	
	public abstract String viewFlavor();
	public abstract Double viewPrice();
	public abstract String viewDescription();
	public abstract int viewQuantity();

	
	
	
	
	
}

