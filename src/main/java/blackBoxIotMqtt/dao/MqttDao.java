//package
package blackBoxIotMqtt.dao;

//import
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//package imports

//class for mqtt db oprations
public class MqttDao {
	private PostgresDb postgresDb;
	private Statement statement;
	private int toggle;
	private int resultSet;
	public MqttDao() {
			System.out.println("\n============== MqttDao Constructor ==========");
			this.setPostgresDb(PostgresDb.getInstance());
	}
	public int getToggle() {
		return toggle;
	}
	public void setToggle(int toggle) {
		this.toggle = toggle;
	}
	//setters getters postgresDb
	public PostgresDb getPostgresDb() {
		return postgresDb;
	}
	public void setPostgresDb(PostgresDb postgresDb) {
		this.postgresDb = postgresDb;
	}
	//setters getters postgresDb
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public Statement getStatement() {
		return statement;
	}
	//setters and getter resultset
	public void setResultSet(int i) {
		this.resultSet = i;
	}
	public int getResultSet() {
		return resultSet;
	}
	
	public boolean insertPlcData(JSONObject map) {
		System.out.println("\n ================== inside insert plc details ===================");
		this.getPostgresDb().connect();
		if(this.getPostgresDb().isConnect()==true)
		{
			try {
				System.out.println(":: "+map.getJSONObject("latest_value"));
				JSONObject latestValue = map.getJSONObject("latest_value");
				Map< String, Object> latestValueMap=this.toMap(latestValue);
			    Set< String> latestValueKey = latestValueMap.keySet();
			    for (String string : latestValueKey) {
			    
			    	System.out.println("latestValueKey  ::  "+string+"\n\n");
			    	
			    	
			    	
				}
			    Map<String, Object> machineStatus = this.toMap(latestValue.getJSONObject("machine_status"));
			    Set<String> machineStatusKey = machineStatus.keySet();
			    for (String string : machineStatusKey) {
					System.out.println(" machineStatusKey :  "+string);
				}
			    Map<String, Object > machine1 =this.toMap(latestValue.getJSONObject("machine_status").getJSONObject("machine2"));
			    System.out.println("m1"+machine1);
				this.setStatement(this.getPostgresDb().getConnection().createStatement());
				Map< String, Object> insertData  = new HashMap<String, Object>();
				try {
					insertData.put("automation_cell_status", latestValue.get("automation_cell_status"));
					System.out.println("automation_cell_status :: "+latestValue.get("automation_cell_status"));
					
				} catch (Exception e) {
					System.err.println("automation_cell_status not present ");
				}
				
				try {
				
					insertData.put("machine_running_status_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_running_status_1"));
					System.out.println("machine_running_status_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_running_status_1"));
					
					
				} catch (Exception e) {
					System.err.println("machine_running_status_1 not present ");
				}	
				try {
					insertData.put("machine_running_status_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_running_status_2"));
					System.out.println("machine_running_status_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_running_status_2"));
					
					
				} catch (Exception e) {
					System.err.println("machine_running_status_2 not present ");
				}
				try {
				
					insertData.put("machine_production_count_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_production_count_1"));
					System.out.println("machine_production_count_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_production_count_1"));
		
					
				} catch (Exception e) {
					System.err.println("machine_production_count_1 not present ");
				}
				//new tags m1
				
				try {
					
					insertData.put("machine_ok_count_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_ok_count_1"));
					System.out.println("machine_ok_count_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_ok_count_1"));
		
					
				} catch (Exception e) {
					System.err.println("machine_ok_count_1 not present ");
				}
				
				try {
					
					insertData.put("machine_ok_count_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_ok_count_2"));
					System.out.println("machine_ok_count_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_ok_count_2"));
		
					
				} catch (Exception e) {
					System.err.println("machine_ok_count_2 not present ");
				}
				
				//not ok
				try {
					
					insertData.put("machine_not_ok_count_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_not_ok_count_1"));
					System.out.println("machine_not_ok_count_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_not_ok_count_1"));
		
					
				} catch (Exception e) {
					System.err.println("machine_not_ok_count_1 not present ");
				}
				
				try {
					
					insertData.put("machine_not_ok_count_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_not_ok_count_2"));
					System.out.println("machine_not_ok_count_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_not_ok_count_2"));
		
					
				} catch (Exception e) {
					System.err.println("machine_not_ok_count_2 not present ");
				}
				
				
				
				
				try {
					insertData.put("machine_production_count_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_production_count_2"));
					System.out.println("machine_production_count_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_production_count_2"));
					
				} catch (Exception e) {
					System.err.println("machine_production_count_2 not present ");
				}
				try {
					insertData.put("machine_collate_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_collate_1"));
					System.out.println("machine_collate_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_collate_1"));
					
				} catch (Exception e) {
					System.err.println("machine_collate_1 not present ");
				}
				
				try {
					
					insertData.put("machine_collate_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_collate_2"));
					System.out.println("machine_collate_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_collate_2"));
				
				} catch (Exception e) {
					System.err.println("machine_collate_2 not present ");
				}
				try {
					insertData.put("machine_shift_production_count_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_shift_production_count_1"));
					System.out.println("machine_shift_production_count_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_shift_production_count_1"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_shift_production_count_1 not present ==============================");
				}
				try 
				{
					insertData.put("machine_shift_production_count_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_shift_production_count_2"));
					System.out.println("machine_shift_production_count_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_shift_production_count_2"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_shift_production_count_2 not present ==============================");
				}
				//============================================ Running Time ===================================================
				try {
					insertData.put("machine_running_time_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_running_time_1"));
					System.out.println("machine_running_time_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_running_time_1"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_running_time_1 not present ==============================");
				}
				try 
				{
					insertData.put("machine_running_time_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_running_time_2"));
					System.out.println("machine_running_time_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_running_time_2"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_running_time_2 not present ==============================");
				}
				//============================================ error Time ===================================================
				try {
					insertData.put("machine_error_time_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_error_time_1"));
					System.out.println("machine_error_time_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_error_time_1"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_error_time_1 not present ==============================");
				}
				try 
				{
					insertData.put("machine_error_time_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_error_time_2"));
					System.out.println("machine_error_time_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_error_time_2"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_error_time_2 not present ==============================");
				}
				//============================================ idle Time ===================================================
				try {
					insertData.put("machine_idle_time_1", latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_idle_time_1"));
					System.out.println("machine_idle_time_1 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine1").get("machine_idle_time_1"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_idle_time_1 not present ==============================");
				}
				try 
				{
					insertData.put("machine_idle_time_2", latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_idle_time_2"));
					System.out.println("machine_idle_time_2 :: "+ latestValue.getJSONObject("machine_status").getJSONObject("machine2").get("machine_idle_time_2"));
		
					
				} catch (Exception e) {
					System.err.println("======================== machine_idle_time_2 not present ==============================");
				}
				
				try {
					insertData.put("accepted_unit_1", latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine1").get("accepted_unit_1"));
					System.out.println("accepted_unit_1 :: "+ latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine1").get("accepted_unit_1"));
			
					
				} catch (Exception e) {
					System.err.println("======================== accepted_unit_1 not present ==============================");
				}
				
				try {
					insertData.put("accepted_unit_2", latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine2").get("accepted_unit_2"));
					System.out.println("accepted_unit_2 :: "+ latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine2").get("accepted_unit_2"));
					
				} catch (Exception e) {
					System.err.println("======================== accepted_unit_2 not present ==============================");
				}
				try {
			
					insertData.put("rejected_unit_1", latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine1").get("rejected_unit_1"));
					System.out.println("rejected_unit_1 :: "+ latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine1").get("rejected_unit_1"));
				
				} catch (Exception e) {
					System.err.println("======================== rejected_unit_1 not present ==============================");
				}
			
				try {
					insertData.put("rejected_unit_2", latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine2").get("rejected_unit_2"));
					System.out.println("rejected_unti_2 :: "+ latestValue.getJSONObject("last_inspected_component_details").getJSONObject("machine2").get("rejected_unit_2"));
				
				} catch (Exception e) {
					System.err.println("======================== rejected_unit_2 not present ==============================");
				}
				
				try {
					insertData.put("component_value", latestValue.getJSONObject("last_checked_component").get("component_value"));
					System.out.println("component_value :: "+ latestValue.getJSONObject("last_checked_component").get("component_value"));
				
				} catch (Exception e) {
					System.err.println("======================== component_value not present ==============================");
				}
				
				try {
					insertData.put("component_name", latestValue.getJSONObject("last_checked_component").get("component_name"));
					System.out.println("component_name :: "+ latestValue.getJSONObject("last_checked_component").get("component_name"));
				
						
				} catch (Exception e) {
					System.err.println("======================== component_name not present ==============================");
				}
			
				Set<String> insertDataKey = insertData.keySet(); 
				for (String key : insertDataKey) {
				try {
						
					this.setResultSet(this.getStatement().executeUpdate("INSERT INTO device_tansactions_latest_info(plc_id, device_id, config_id, plc_component_address_config_id, config_desc, latest_value, created_by, last_updated_by)\n" + 
							"VALUES ("+map.get("plc_id")+", "+map.get("device_id")+", '"+map.get("config_id")+"', '"+map.get("plc_component_address_config_id")+"', '"+key+"', '"+insertData.get(key)+"', '"+map.get("user_name")+"', '"+map.get("user_name")+"')"));
					
				} catch (Exception e) {
					
					System.out.println("\n\t Error at MqttDao insert letest tran. :: "+e);
				}	
		
				try {
					this.setResultSet(this.getStatement().executeUpdate("INSERT INTO public.device_tansactions_archive_info(\n" + 
							"            id, plc_id, device_id, config_id, plc_component_address_config_id, \n" + 
							"            config_desc, latest_value, created_by, last_updated_by, created_date, \n" + 
							"            last_updated_date)\n" + 
							"    SELECT id, plc_id, device_id, config_id, plc_component_address_config_id, \n" + 
							"       config_desc, latest_value, created_by, last_updated_by, created_date, last_updated_date FROM public.device_tansactions_latest_info \n" + 
							"       where config_desc = '"+key+"' and id < (SELECT max(id)  FROM public.device_tansactions_latest_info\n" + 
							"        where config_desc = '"+key+"') and device_id ="+map.get("device_id")+" and plc_id="+map.get("plc_id")+""));
					try {
						this.setResultSet(this.getStatement().executeUpdate("\n" + 
								"delete from device_tansactions_latest_info where config_desc = '"+key+"' and id < (SELECT max(id)  FROM public.device_tansactions_latest_info\n" + 
								"        where config_desc = '"+key+"') and device_id = "+map.get("device_id")+" and plc_id="+map.get("plc_id")+""));
					
						
					} catch (Exception e) {
						System.out.println("\n\t Error at MqttDao delete device_tansactions_latest_info. :: "+e);
					}
						
				} catch (Exception e) {
					
					System.out.println("\n\t Error at MqttDao insert device_tansactions_archive_info. :: "+e);
				}
					
				}
				//this.setResultSet(this.getStatement().executeUpdate("INSERT INTO device_tansactions_latest_info(plc_id, device_id, config_id, plc_component_address_config_id, config_desc, latest_value, created_by, last_updated_by)\n" + 
				//		"				   VALUES ("+map.get("plc_id")+", "+map.get("device_id")+", '"+map.get("config_id")+"', '"+map.get("plc_component_address_config_id")+"', '"+map.get("config_desc")+"', '"+map.get("latest_value")+"', '"+map.get("user_name")+"', '"+map.get("user_name")+"')"));
				System.out.println("\n ==================insertPlcData success ===================\n\n");

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("cretate statement exception :: "+e);
				System.out.println("\nUnable to set statement at insertUrl() method ");
				System.out.println("\n ================== Faild ===================\n\n");

			}
			finally {
				 if (this.getPostgresDb().isConnect()==true) {
					 try{
						 	
					 		this.getStatement().close();
							this.getPostgresDb().getConnection().close();
							this.getPostgresDb().disconnect();
							System.err.println("================ PG disconnect ====================");
							
					 	} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
			}
			
		}
		return false;
	}
	
	public boolean insertUrl(JSONObject map) {
		System.out.println("\n ================== inside insert url details ===================");
		this.getPostgresDb().connect();
		if(this.getPostgresDb().isConnect()==true)
		{
			try {
				this.setStatement(this.getPostgresDb().getConnection().createStatement());
				this.setResultSet(this.getStatement().executeUpdate("INSERT INTO public.device_camera_url_latest_info(\n" + 
						" plc_id, org_id, device_id, config_id, url, created_by, \n" + 
						"last_updated_by)\n" + 
						"VALUES  ( "+map.get("plc_id")+","+map.get("org_id")+" , "+map.get("device_id")+", '"+map.get("config_id")+"'"
								+ ",'"+map.get("url")+"' , '"+map.get("user_name")+"','"+map.get("user_name")+"' \n"+")"));
				System.out.println("\n ==================insertUrl success ===================\n\n");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("cretate statement exception :: "+e);
				System.out.println("\n\nUnable to set statement at insertUrl() method ");
				System.out.println("\n ================== Faild ===================\n\n");

			}
			finally {
				 if(this.getPostgresDb().isConnect()==true) {
					 try{
						 	
					 		this.getStatement().close();
							this.getPostgresDb().getConnection().close();
							this.getPostgresDb().disconnect();
							System.err.println("================ PG disconnect ====================");
							
					 	} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				 
				 
			}
		}
		return false;
	}
	
	public boolean updateUrl() {
		this.getPostgresDb().connect();
		if(this.getPostgresDb().isConnect()==true)
		{
			try {
				this.setStatement(this.getPostgresDb().getConnection().createStatement());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("cretate statement exception :: "+e);
				System.out.println("\n\nUnable to set statement at insertUrl() method ");
			}
			finally {
				 if (this.getPostgresDb().isConnect()==true) {
					 try{
						 	
					 		this.getStatement().close();
							this.getPostgresDb().getConnection().close();
							this.getPostgresDb().disconnect();
							System.err.println("================ PG disconnect ====================");
							
					 	} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
			}
			//this.setResultSet(this.getStatement().executeQuery(""));
		}
		return false;
	
	}
	public boolean getUrl() {
		this.getPostgresDb().connect();
		if(this.getPostgresDb().isConnect()==true)
		{
			try {
				this.setStatement(this.getPostgresDb().getConnection().createStatement());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("cretate statement exception :: "+e);
				System.out.println("\n\nUnable to set statement at insertUrl() method ");
			}
			finally {
				 if (this.getPostgresDb().isConnect()==true) {
					
					try{
					 	
					 		this.getStatement().close();
							this.getPostgresDb().getConnection().close();
							this.getPostgresDb().disconnect();
							System.err.println("================ PG disconnect ====================");
							
					 	} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
			}
			
			//this.setResultSet(this.getStatement().executeQuery(""));
		}
		return false;
	}
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	
	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
	//get latest value for production
	public void getLatestValue() {
		System.out.println("\n ================== inside getLatestValue ===================");
		this.getPostgresDb().connect();
		if(this.getPostgresDb().isConnect()==true)
		{
			try {
				this.setStatement(this.getPostgresDb().getConnection().createStatement());
				this.getStatement().execute("");
				System.out.println("\n ================== success ===================\n\n");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("cretate statement exception :: "+e);
				System.out.println("\n\nUnable to set statement at insertUrl() method ");
				System.out.println("\n ================== Faild ===================\n\n");

			}
			finally {
				 if(this.getPostgresDb().isConnect()==true) {
					 try{
						 	
					 		this.getStatement().close();
							this.getPostgresDb().getConnection().close();
							this.getPostgresDb().disconnect();
							System.err.println("================ PG disconnect ====================");
							
					 	} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				 
				 
			}
		}
		
		
	}
	public boolean deleteUrl() {
		this.getPostgresDb().connect();
		if(this.getPostgresDb().isConnect()==true)
		{
			try {
				this.setStatement(this.getPostgresDb().getConnection().createStatement());
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("cretate statement exception :: "+e);
				System.out.println("\n\nUnable to set statement at insertUrl() method ");
			}finally {
				 if (this.getPostgresDb().isConnect()==true) {
					 try{
						 	
					 		this.getStatement().close();
							this.getPostgresDb().getConnection().close();
							this.getPostgresDb().disconnect();
							System.err.println("================ PG disconnect ====================");
							
					 	} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
			}
			
			//this.setResultSet(this.getStatement().executeQuery(""));
		}
		return false;
	}
}
