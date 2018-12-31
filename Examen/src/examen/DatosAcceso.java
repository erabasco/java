/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.io.Serializable;

/**
 *
 * @author IES TRASSIERRA
 */
public class DatosAcceso implements Serializable{

	
	private String usuario;
	
	private String password;
	
	private String bbdd;

	/**
	 * @param usuario
	 * @param password
	 * @param bbdd
	 */
	public DatosAcceso(String usuario, String password, String bbdd) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.bbdd = bbdd;
	}

	/**
	 * 
	 */
	public DatosAcceso() {
		this.usuario = "root";
		this.password = "";
		this.bbdd = "test";
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the bbdd
	 */
	public String getBbdd() {
		return bbdd;
	}

	/**
	 * @param bbdd the bbdd to set
	 */
	public void setBbdd(String bbdd) {
		this.bbdd = bbdd;
	}

	@Override
	public String toString() {
		return "DatosAcceso: \n\t\tBase de Datos: " + bbdd + "\n\t\tPassword: " + password
				+ "\n\t\tUsuario: " + usuario;
	}
	
}
