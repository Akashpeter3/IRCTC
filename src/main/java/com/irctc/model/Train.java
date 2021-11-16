package com.irctc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Train")
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRAIN_ID")
	private Integer trainId;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ROUTE_ID", foreignKey = @ForeignKey(name = "train_route_mapp_key"), referencedColumnName = "ROUTE_ID")
	@JsonBackReference
	private Route route;
	private String trainName;
	private String arrivalTime;
	private String departureTime;
	private double fare;

	public Train() {
	}

	public Train(String trainName, String arrivalTime, String departureTime, double fare) {
		super();

		this.trainName = trainName;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.fare = fare;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "Train [trainId=" + trainId + ", route=" + route + ", trainName=" + trainName + ", arrivalTime="
				+ arrivalTime + ", departureTime=" + departureTime + ", fare=" + fare + "]";
	}

}
