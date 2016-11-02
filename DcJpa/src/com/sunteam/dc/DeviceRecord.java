package com.sunteam.dc;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.Date;


/**
 * The persistent class for the device_record database table.
 * 
 */
@Entity
@Table(name="device_record")
@NamedQuery(name="DeviceRecord.findAll", query="SELECT d FROM DeviceRecord d")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceRecord implements Serializable {
	@XmlTransient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="d_heart_rate")
	private int dHeartRate;

	@Column(name="d_temperature")
	private float dTemperature;

	@Column(name="device_id")
	private String deviceId;

	//bi-directional many-to-one association to Station
	@ManyToOne
	@XmlTransient
	private Station station;

	//@Transient
	@Column(name="station_id", insertable = false, updatable = false)
	private int stationId;
	
	public DeviceRecord() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getDHeartRate() {
		return this.dHeartRate;
	}

	public void setDHeartRate(int dHeartRate) {
		this.dHeartRate = dHeartRate;
	}

	public float getDTemperature() {
		return this.dTemperature;
	}

	public void setDTemperature(float dTemperature) {
		this.dTemperature = dTemperature;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {		
		this.station = station;		
	}

	public int getStationId() {
		return this.stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

}