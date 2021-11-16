package com.irctc.dto;

public class RoutIdDTO {
	private Integer routeId;

	public RoutIdDTO() {
		// TODO Auto-generated constructor stub
	}

	public RoutIdDTO(Integer routeId) {
		super();
		this.routeId = routeId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "RoutIdDTO [routeId=" + routeId + "]";
	}

}
