package com.sunteam.dc;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the rank database table.
 * 
 */
@Entity
@Table(name="rank")
@NamedQuery(name="Rank.findAll", query="SELECT r FROM Rank r")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rank implements Serializable {
	@XmlTransient
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	@XmlJavaTypeAdapter(DateXmlAdapter.class)
	private Date createTime;

	@Column(name="device_id")
	private String deviceId;

	@Id	
	private BigInteger id;

	@Column(name="station_id")
	private int stationId;

	public Rank() {
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public BigInteger getId() {
		return this.id;
	}

	public int getStationId() {
		return this.stationId;
	}



}