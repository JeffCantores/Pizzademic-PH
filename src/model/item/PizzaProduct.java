package model.item;

import utility.*;

public interface PizzaProduct {
	public String viewFlavor();
	public Double viewPrice();
	public String viewDescription();
	public int viewQuantity();
	
	public Packing packing();
}
