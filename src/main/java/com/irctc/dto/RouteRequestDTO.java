package com.irctc.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RouteRequestDTO implements Serializable {
	@NotEmpty(message = "{source.notempty}")
	@Pattern(regexp = "[a-zA-Z]*$", message = "{only.alphabets}")
	private String source;
	@NotEmpty(message = "{destination.notempty}")
	@Pattern(regexp = "[a-zA-Z]*$", message = "{only.alphabets}")
	private String destination;
	@NotEmpty(message = "{trainList.notempty}")
	private List<TrainDTO> trainList;

	public RouteRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public RouteRequestDTO(
			@NotEmpty(message = "{source.notempty}") @Pattern(regexp = "[a-zA-Z]*$", message = "{only.alphabets}") String source,
			@NotEmpty(message = "{destination.notempty}") @Pattern(regexp = "[a-zA-Z]*$", message = "{only.alphabets}") String destination,
			@NotEmpty(message = "{trainList.notempty}") List<TrainDTO> trainList) {
		super();
		this.source = source;
		this.destination = destination;
		this.trainList = trainList;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<TrainDTO> getTrainList() {
		return trainList;
	}

	public void setTrainList(List<TrainDTO> trainList) {
		this.trainList = trainList;
	}

	@Override
	public String toString() {
		return "RouteRequestDTO [source=" + source + ", destination=" + destination + ", trainList=" + trainList + "]";
	}

}
