package blackBoxIotMqtt.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;



public class BlackBoxProperties {
	private String resourceName = "config.properties";
	private HashMap<String, Object> propMap = new HashMap<String, Object>(); 
	ArrayList<String> propCollection = new ArrayList<String>();

	public BlackBoxProperties() {
	}
	
	//get props
	public HashMap<String, Object> getProperties()
	{
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			for (String string : IOUtils.toString(classLoader.getResourceAsStream(resourceName)).split("\n")) {
				propCollection.add(string.split(",")[0]);	
			}
			for (String string : propCollection) {
				String[] tmp = string.split("=");
				this.propMap.put(tmp[0], tmp[1]);		
			}
			return this.propMap;
		} catch (IOException e) {			
			System.out.println("\n\t Err at getProperties :: "+e);
			return null;
		}
		catch (Exception e) {
			System.out.println("\n\t Err at getProperties :: "+e);
			return null;		
			}
	}
	
}
