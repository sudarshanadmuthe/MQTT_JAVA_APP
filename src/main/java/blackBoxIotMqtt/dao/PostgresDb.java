/**
 * 
 */
package blackBoxIotMqtt.dao;
/**
 * @author ubuntu
 *
 */

// imports 
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import blackBoxIotMqtt.properties.*;
import blackBoxIotMqtt.algorithms.*;

public class PostgresDb {
	
	// param 
	Connection connection;
	BlackBoxProperties blackBoxProperties;
	static PostgresDb  single_instance;
	private PostgresDb() {	
	//test postgres driver
		this.dbDriverTest();
		this.connection = null;		
	}
	public static PostgresDb getInstance() 
    { 
        if (single_instance == null) 
        		single_instance = new PostgresDb(); 
  
        return single_instance; 
    } 
	//setter getter connection
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public Connection getConnection() {
		return connection;
	}
	//setter getter for props
	public void setBlackBoxProperties(BlackBoxProperties blackBoxProperties) {
		this.blackBoxProperties = blackBoxProperties;
	}
	public BlackBoxProperties getBlackBoxProperties() {
		return blackBoxProperties;
	}
	// methods 
	private boolean dbDriverTest() 
	{
		System.out.println("\t00 =========== PostgreSQL Con Test ============");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.err.println("\t\tclass not found :: "+e );
			System.out.println("\t\tPlease check your PostgreSQL JDBC Driver? "
					+ "Also include in your library path!");
			e.printStackTrace();
			return false;

		}

		System.out.println("\t\tPostgreSQL JDBC Driver Registered!");
		return true;
		}
	public Connection connect() {	
		try {
			this.setBlackBoxProperties(new BlackBoxProperties());
	/*		System.out.println(" url :: "+AES.encrypt("jdbc:postgresql://172.16.1.32:5432/iot_black_box_dev", "12332133212332"));
			System.out.println(" pass :: "+AES.encrypt("asdfghjkl","12332133212332"));*/
			//System.out.println(">>>>>>>>>> "+AES.decrypt(this.getBlackBoxProperties().getProperties().get("URL").toString(),"12332133212332"));
			//this.setConnection(DriverManager.getConnection(AES.decrypt(this.getBlackBoxProperties().getProperties().get("URL").toString(),"12332133212332"),AES.decrypt(this.getBlackBoxProperties().getProperties().get("Uname").toString(), "12332133212332"),AES.decrypt(this.getBlackBoxProperties().getProperties().get("password").toString(), "12332133212332")));
			//this.setConnection(DriverManager.getConnection("jdbc:postgresql://172.16.1.32:5432/black_box_iot_dev","postgres","asdfghjkl"));
			//this.setConnection(DriverManager.getConnection("jdbc:postgresql://localhost:5432/black_box_iot_dev","postgres","asdfghjkl"));
			this.setConnection(DriverManager.getConnection("jdbc:postgresql://localhost:5432/black_box_dev","postgres","123"));

			System.out.println("\t\tPostgres DB connection established successfully ");

			return this.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("\t\tunable to crerate object :: "+e );
			System.out.println("\t\tPlease check your PostgreSQL connection params? ");
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("\t\tunable to crerate object :: "+e );
			System.out.println("\t\tPlease check your PostgreSQL connection params? ");
			return null;	
		}
	}
	public boolean disconnect() {
		
		if(this.isConnect()==true)
		{
			this.setConnection(null);
			try {
				this.connection.close();
			} catch (SQLException e) {
				System.err.println("============= unable to  close ==============");
				e.printStackTrace();
			}
			System.err.println("============= disconnected ==============");
			return true; 
			
		}
		
		return false;
	}	
	public boolean isConnect() {	
		if(this.getConnection()==null)
			return false;
		else 
			return true;
	}

}
