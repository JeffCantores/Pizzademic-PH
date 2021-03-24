package model.iterator.bean;

public class PizzaBean {
	
	private String pizzaName;
	private String pizzaPhoto;
	private String pizzaDescription;
	
	
	public PizzaBean(String pizzaName, String pizzaPhoto, String pizzaDescription) {
		super();
		this.pizzaName = pizzaName;
		this.pizzaPhoto = pizzaPhoto;
		this.pizzaDescription = pizzaDescription;
	}
	
	public String getPizzaName() {
		return pizzaName;
	}
	
	public String getPizzaPhoto() {
		return pizzaPhoto;
	}
	
	public String getPizzaDescription() {
		return pizzaDescription;
	}
	
	
	
}
