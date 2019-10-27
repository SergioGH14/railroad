package com.onebox.sgomez.railroad.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "railroads")
public class RailRoad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8870784532873597118L;

	@Id
	@SequenceGenerator(name = "SEQ_RAILROAD", sequenceName = "SEQ_RAILROAD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RAILROAD")
	private Long id;

	@OneToOne
	private Town origin;
	@OneToOne
	private Town destination;

	private Integer distance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Town getOrigin() {
		return origin;
	}

	public void setOrigin(Town origin) {
		this.origin = origin;
	}

	public Town getDestination() {
		return destination;
	}

	public void setDestination(Town destination) {
		this.destination = destination;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

}
