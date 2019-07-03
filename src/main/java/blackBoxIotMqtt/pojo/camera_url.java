/**
 * 
 */
package blackBoxIotMqtt.pojo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.json.JSONObject;

import com.sun.jna.platform.win32.Sspi.TimeStamp;

/**
 * @author sudo this pojo contains camera and live streaming related properties
 *
 */
@Entity
public class camera_url {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="device_camera_url_latest_info_seq")
	@Column(name="id")
	private int id;
	
	@Column(name="plc_id")
	private int plcId;
	
	@Column(name="org_id")
	private int orgId;
	
	@Column(name="device_id")
	private int deviceId;
	
	@Column(name="config_id")
	private int configId;
	
	@Column(name="url")
	private JSONObject url;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="lastUpdatedBy")
	private String lastUpdatedby;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date",nullable=false,columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private LocalDateTime createddate;
	
	@Column(name="last_updated_date")
	private LocalDateTime lastUpdatedDate;

	public camera_url() {
		System.out.println("\n\t====== Inside camera_url constuctor ======");
	}

	// getters and setters
	
}
