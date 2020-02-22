package com.example.demo.beans;

import java.io.Serializable;


public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	
	public Role(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Role() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
 