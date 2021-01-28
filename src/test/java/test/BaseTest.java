package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;
import utils.PropertiesManager;

public class BaseTest {

	public static final Logger logger = Logger.getLogger(BaseTest.class);
	protected static String accessToken;


	@BeforeSuite(alwaysRun=true)
	public void BeforeSuite() throws Exception
	{
        PropertyConfigurator.configure("src\\main\\resources\\log4j.properties");
		PropertiesManager.initializeProperties();
	}
}