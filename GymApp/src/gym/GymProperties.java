package gym;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class GymProperties {

	private static Properties appProp = new Properties();
	private static Properties messages = new Properties();
	public static final String LBLMESSAGE = "lbl";
	public static final String ERRMESSAGE = "err";
	public static final String INFOMESSAGE = "info";

	final Logger log = Logger.getLogger(GymProperties.class);

	public void initProperties(String propFile, String messageFile) {
		InputStream is;
		try {
			log.info("Loading properties");
			is = new FileInputStream(propFile);
			appProp.load(is);
			is.close();
			is = new FileInputStream(messageFile);
			messages.load(is);
			is.close();
			log.info("Properties loaded");
		} catch (FileNotFoundException e) {
			log.error("File " + propFile + " or " + messageFile + " not found", e);
		} catch (IOException e) {
			log.error("", e);
		}
	}

	public static String getPropertie(String key) {
		return appProp.getProperty(key);
	}

	public static String getMessage(String prefix,String key) {
		return appProp.getProperty(prefix+"."+key);
	}
}
