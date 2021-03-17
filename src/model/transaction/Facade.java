package model.transaction;

import javax.servlet.ServletContext;

public interface Facade {

	public boolean process(ServletContext context);
}
