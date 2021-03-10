package model.upgrade;

public abstract class Upgrade {
	
	public abstract Upgrade clone();
	
	public abstract String viewUpgradeType();
	public abstract String viewUpgradeDetails();
}
