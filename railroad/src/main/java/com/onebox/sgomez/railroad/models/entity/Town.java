package com.onebox.sgomez.railroad.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class Town implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4070511747279285575L;

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
