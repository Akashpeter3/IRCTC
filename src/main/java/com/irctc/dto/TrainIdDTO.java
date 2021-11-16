package com.irctc.dto;

public class TrainIdDTO {
	private Integer trainId;

	public TrainIdDTO(Integer trainId) {
		super();
		this.trainId = trainId;
	}

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	@Override
	public String toString() {
		return "TrainIdDTO [trainId=" + trainId + "]";
	}

}
