package com.irctc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.core.serializer.Deserializer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.deser.Deserializers;

@Entity
@Table(name = "route")
public class Route implements Serializable  {
	@Id
	@SequenceGenerator(name ="sequence_rout", initialValue = 100,allocationSize = 1000)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROUTE_ID",length = 3)
	private Integer routeId;
	@Pattern(regexp = "[a-zA-Z]*$")
	@NotEmpty(message = "{source.notempty}")
	private String source;
	@NotEmpty(message = "{destination.notempty}")
	@Pattern(regexp = "[a-zA-Z]*$")
	private String destination;
	@OneToMany(mappedBy ="route", cascade =  CascadeType.ALL)
	@JsonManagedReference
	private List<Train> trains;

public Route() {
	
}
	
	public Route(Integer routeId, String source, String destination, List<Train> trains) {
		super();
		this.routeId = routeId;
		this.source = source;
		this.destination = destination;
		this.trains = trains;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
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

	public List<Train> getTrains() {
		return trains;
	}

	public void setTrain(List<Train> trains) {
		this.trains = trains;
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", source=" + source + ", destination=" + destination + ", trains=" + trains
				+ "]";
	}

}
