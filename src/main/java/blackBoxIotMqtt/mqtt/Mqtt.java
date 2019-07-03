/**
 * 
 */
package blackBoxIotMqtt.mqtt;
/**
 * @author sudo
 *
 */

import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import blackBoxIotMqtt.dao.*;
import blackBoxIotMqtt.pojo.*;

public class Mqtt implements MqttCallback {
	private PostgresDb postgresDb; 
	private MqttDao mqttDao ; 
	MqttClient client;
	public static HashMap<String, String> data;
	public camera_url camera = new camera_url(); 
	public Mqtt() {
		data = new HashMap<String,String>();
	}
	//======================= getters / setters ================================
	
	public PostgresDb getPostgresDb() {
		return postgresDb;
	}
	public void setPostgresDb(PostgresDb postgresDb) {
		this.postgresDb = postgresDb;
	}	
	public void setMqttDao(MqttDao mqttDao) {
		this.mqttDao = mqttDao;
	}
	public MqttDao getMqttDao() {
		return mqttDao;
	}
	//=============== methods =====================

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	public void messageArrived (String topic, MqttMessage message)throws Exception {
		try {
			JSONObject mqttJsonData = new JSONObject(message.toString());		
			if(topic.equals("pi108/liveUrl")==true)
				{		
					System.out.println("================ pi108/liveUrl ===================");
					this.setMqttDao(new MqttDao());
					this.getMqttDao().insertUrl(mqttJsonData);
				}
				if(topic.equals("pi108/flexydata"))
				{	
					System.out.println("================ pi108/flexydata1 ===================");
					this.setMqttDao(new MqttDao());
					this.getMqttDao().insertPlcData(mqttJsonData);								
				}		
			} 
		catch (Exception e)
			{
				System.out.println(" Mqtt.java -> messageArrived() Exception at arrived msg "+e);
			}	
		
	}
	
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}
	

	
	public boolean subscriber()
	{
		try {
			client = new MqttClient("tcp://35.173.37.208:1883", "Sending");
			client.connect();
			client.setCallback(this);
			client.subscribe("pi108/flexydata");
			client.subscribe("pi108/liveUrl");
			return true;

		} catch (MqttException e) {
			System.out.println("\n\t Err at subscription : "+e);
			return false;
		}
		
	}

	
	
	
	public HashMap<String, String> msg()
	{
		System.out.println(" "+data);
		return data==null?null:data;
	}
	
	public static void main(String[] args) {
		Mqtt mqtt = new Mqtt();
		
		try 
		{
			if(mqtt.subscriber()==true)
			{
				System.out.println("\n\t\t==================== subscribed =======================\n\n");
			}
			else
			{
				System.out.println("\n\t\t==================== unable to subscribe =======================\n\n");
			}
			
		}catch (Exception e) {
		
			System.out.println("\n\t Err at subscription : "+e);

		}	
		
	}

	
	
}
