package utility;

public interface DBOps {
	
	//query for instantiating the table values for pizza
	String INITIALIZE_PIZZA_TABLE = "CREATE TABLE IF NOT EXISTS `pizza` (" + 
			"`id` INT NOT NULL AUTO_INCREMENT , \r\n" + 
			"`pizza_flavor` VARCHAR(20) NOT NULL ,\r\n" + 
			"`pizza_price` FLOAT NOT NULL , \r\n" + 
			"`pizza_description` VARCHAR(500) NOT NULL , \r\n" + 
			"`quantity` INT NOT NULL , PRIMARY KEY (`id`));";
	
	String INITIALIZE_UPGRADES_TABLE = "CREATE TABLE IF NOT EXISTS `upgrade` (" + 
			"`id` INT NOT NULL AUTO_INCREMENT ," + 
			"`upgrade_type` VARCHAR(20) NOT NULL ," + 
			"`upgrade_price` FLOAT NOT NULL ," + 
			"`upgrade_description` VARCHAR(500) NOT NULL , PRIMARY KEY (`id`));";

	String INITIALIZE_TRANSACTION_TABLE = "CREATE TABLE IF NOT EXISTS `transactions` (" + 
			"`transaction_id` INT NOT NULL AUTO_INCREMENT , \r\n" + 
			"`customer_name` VARCHAR(50) NOT NULL ,\r\n" + 
			"`street` VARCHAR(20) NOT NULL ,\r\n" +
			"`brgy` VARCHAR(20) NOT NULL ,\r\n" +
			"`city` VARCHAR(20) NOT NULL ,\r\n" +
			"`zip_code` VARCHAR(20) NOT NULL ,\r\n" +
			"`pizza_flavor` VARCHAR(20) NOT NULL ,\r\n" +
			"`total_price` FLOAT NOT NULL , \r\n" +  
			"`upgrade_quantity` INT NOT NULL , \r\n" + 
			"`quantity` INT NOT NULL , PRIMARY KEY (`transaction_id`));";
	
	String INSERT_PIZZA_VALUES = "INSERT IGNORE INTO pizza (id, pizza_Flavor, pizza_price, pizza_description, quantity)" + 
			"VALUES" + 
			"(1, 'Four Cheese', 165.00, 'Satisfy your cravings with this creamy four cheese pizza with a composition of Mozzarella, Gorgonzola, Fontina and Parmigiano cheese.', 10)," + 
			"(2, 'Pepperoni', 185.00, 'Savory smell of meaty Pepperonis combined with a lot of cheese, on top of a rich and delicious tomato sauce.', 10)," + 
			"(3, 'Tropicale', 205.00, 'Refreshing combination of Fresh Pineapple, savory bell peppers, meaty goodness of hams, and a lot of cheese, altogether on top of a rich and delicious tomato sauce.', 10);";
			
	String INSERT_UPGRADE_VALUES = "INSERT IGNORE INTO upgrade (id, upgrade_type, upgrade_price, upgrade_description)" + 
			"VALUES " + 
			"(1, 'Four Cheese Supreme', 42.00, 'Additional Cheese! for Greater Satisfaction')," + 
			"(2, 'Pepperoni Supreme', 42.00, 'Additional Pepperoni! for Greater Satisfaction')," + 
			"(3, 'Tropicale Supreme', 42.00, 'Additional Pineapples! for Greater Satisfaction');";
	
	//Query for getting all the records in the database
	String GET_ALL_RECORDS = "select * from pizza";
	
	//Query for selecting a pizza product
	String SELECT_PIZZA_PRODUCT = "select * from pizza where pizza_flavor like ?";
	
	//query for selecting the pizza upgrade
	String SELECT_UPGRADE_PRODUCT = "select * from upgrade where upgrade_type like ?";
	
}
