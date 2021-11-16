package com.irctc.dto;

import java.io.Serializable;

public class TrainDTO implements Serializable {
	private int trainId;
	private String trainName;
	private String trainType;
	private String arrivalTime;
	private String departureTime;
	private double trainFare;

	public TrainDTO() {
	}

	public TrainDTO(int trainId, String trainName, String trainType, String arrivalTime, String departureTime,
			double trainFare) {
		super();
		this.trainId = trainId;
		this.trainName = trainName;
		this.trainType = trainType;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.trainFare = trainFare;

	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
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

	public double getTrainFare() {
		return trainFare;
	}

	public void setTrainFare(double trainFare) {
		this.trainFare = trainFare;
	}

	@Override
	public String toString() {
		return "TrainDTO [trainId=" + trainId + ", trainName=" + trainName + ", trainType=" + trainType
				+ ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", trainFare=" + trainFare
				+ "]";
	}

}
