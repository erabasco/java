/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IES TRASSIERRA
 */
public class ControladorBBDD {

    private Connection con;

    public boolean conexion(DatosAcceso d)  {
        boolean exito=false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/"+d.getBbdd(),d.getUsuario(),d.getPassword());
             exito=true;   
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }

    public Connection getConnection() {
        return con;
    }

    public void desconexion() {
        con = null;
        System.out.println("Conexion cerrada");
    }

    public int insertarPersona(Persona p) {
        int exito = 0;
        System.out.println("INSERT INTO personas VALUES (" + p.getCodigo() + ", '" + p.getNombre() + "', '" + p.getApellidos() + "', " + p.getEstatura()
                    + " , " + p.getEdad() + " ,'" + p.getSexo() + "')");
        try {
            Statement st;
            st = con.createStatement();
            st.executeUpdate("INSERT INTO personas VALUES (" + p.getCodigo() + ", '" + p.getNombre() + "', '" + p.getApellidos() + "', " + p.getEstatura()
                    + " , " + p.getEdad() + " ,'" + p.getSexo() + "')");
            exito = 1;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }

    public int borrarPersona(Persona p) {
        int exito = 0;
        int codigo = p.getCodigo();
        try {
            Statement st;
            st = con.createStatement();
            st.executeUpdate("DELETE FROM personas WHERE codigo=" + codigo);
            exito = 1;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exito;
    }

    public Persona buscarPersona(int codigo) {
        Persona p = new Persona();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personas WHERE codigo=" + codigo);
            if (rs.next()) {
                p.setApellidos(rs.getString("apellidos"));
                p.setCodigo(rs.getInt("codigo"));
                p.setEdad(rs.getInt("edad"));
                p.setEstatura(rs.getDouble("estatura"));
                p.setNombre(rs.getString("nombre"));
                p.setSexo(rs.getString("sexo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public ArrayList listarPersonas() {
        ArrayList<Persona> personas = new ArrayList();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personas ORDER BY sexo");
            while (rs.next()) {
                Persona p = new Persona();
                p.setApellidos(rs.getString("apellidos"));
                p.setCodigo(rs.getInt("codigo"));
                p.setEdad(rs.getInt("edad"));
                p.setEstatura(rs.getDouble("estatura"));
                p.setNombre(rs.getString("nombre"));
                p.setSexo(rs.getString("sexo"));
                personas.add(p);

            }
            System.out.println(personas);
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return personas;
    }
}
