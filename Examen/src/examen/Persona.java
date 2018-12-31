/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

/**
 *
 * @author IES TRASSIERRA
 */
public class Persona {
    
    private int codigo;
    private String nombre;
    private String apellidos;
    private double estatura;
    private int edad;
    private String sexo;

    public Persona() {
    }

    public Persona(int codigo, String nombre, String apellidos, double estatura, int edad, String sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estatura = estatura;
        this.edad = edad;
        this.sexo = sexo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Persona{" + "codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", estatura=" + estatura + ", edad=" + edad + ", sexo=" + sexo + '}';
    }
    
    
}
