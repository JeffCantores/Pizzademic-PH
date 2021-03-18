package model.item;

import model.upgrade.Upgrade;
import utility.*;

public interface PizzaProduct {
	
	public abstract void setUpgrade(Upgrade upgrade);
	public abstract Upgrade getUpgrade();
	public String viewFlavor();
	public Double viewPrice();
	public String viewDescription();
	public int viewQuantity();
	
	public Packing packing();//implemented the packing functionality
}
