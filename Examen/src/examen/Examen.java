/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IES TRASSIERRA
 */
public class Examen {

    private static ControladorBBDD base_datos;
    public static ArrayList<Persona> personas = new ArrayList();
    private static File miFichero = new File("censo.dat");

    public static void main(String[] args) throws SQLException, IOException {
        base_datos = new ControladorBBDD();

        base_datos.conexion(new DatosAcceso());
        String cadena = "";

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        if (miFichero.exists()) {
            System.out.println("Cargando personas ...");
            deserializa();
        }

        do {
            menu();
            cadena = entrada.readLine();
            switch (cadena) {
                case "1":
                    insertarDatos();
                    break;
                case "2":
                    borrarDatos();
                    break;
                case "3":
                    buscarDatos();
                    break;
                case "4":
                    cargarDatos();
                    break;
                case "5":
                    listarDatos();
                    break;
                case "6":
                   //eliminarMenores();
                    break;
                case "0":
                    serializa();
                    break;

            }
        } while (!"0".equalsIgnoreCase(cadena));

    }

    private static void menu() {
        System.out.println("************* MENU *****************");
        System.out.println("* 1. Insertar Datos                 ");
        System.out.println("* 2. Borrar Datos                   ");
        System.out.println("* 3. Buscar Datos                   ");
        System.out.println("* 4. Cargar Datos                   ");
        System.out.println("* 5. Listar Datos                   ");
        System.out.println("* 6. Eliminar Menores               ");
        System.out.println("* 0. Guardar y salir                ");
        System.out.println("************************************");
    }

    private static void listarDatos() {
        System.out.println(personas);
    }

    private static void insertarDatos() throws IOException {
        int codigo = 0, edad = 0;
        String nombre, apellidos, sexo;
        double estatura = 0;
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        Persona personaActual;

        System.out.println("**Insertar Datos");

        System.out.println("Código: ");
        try {
            codigo = Integer.parseInt(entrada.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("Valor incorrecto introduzca un decimal");
        }

        System.out.println("Edad: ");
        try {
            edad = Integer.parseInt(entrada.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("Valor incorrecto introduzca un decimal");
        }

        System.out.println("Nombre: ");
        nombre = entrada.readLine();
        System.out.println("Apellidos: ");
        apellidos = entrada.readLine();
        System.out.println("Sexo: ");
        sexo = entrada.readLine();

        System.out.println("Estatura: ");
        try {
            estatura = Double.parseDouble(entrada.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("Valor incorrecto introduzca un decimal");
        }

        personaActual = new Persona(codigo, nombre, apellidos, estatura, edad, sexo);

        if (base_datos.insertarPersona(personaActual) == 1) {
            System.out.println("Persona Añadida correctamente");
        } else {
            System.out.println("Ha habido algún error");
        }

    }

    private static void cargarDatos() {
        personas = base_datos.listarPersonas();
        System.out.println(personas);
    }

    private static void borrarDatos() throws IOException {
        int codigo = 0;
        Persona p = new Persona();
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("*** Borrar");
        System.out.println("Código a borrar: ");
        try {
            codigo = Integer.parseInt(entrada.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("Valor incorrecto introduzca un entero");
        }
        p = base_datos.buscarPersona(codigo);
        if (p.getCodigo() != 0) {
            if (base_datos.borrarPersona(p) == 1) {
                System.out.println("Persona eliminada correctamente");
            } else {
                System.out.println("Ha habido un error al eliminar");
            }

        } else {
            System.out.println("Persona no encontrada");
        }

    }

    private static void buscarDatos() throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        Persona p = new Persona();
        int codigo = 0;
        System.out.println("*** Buscar");
        System.out.println("Código a buscar: ");
        try {
            codigo = Integer.parseInt(entrada.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("Valor incorrecto introduzca un entero");
        }
        p = base_datos.buscarPersona(codigo);
        if (p.getCodigo() != 0) {
            System.out.println(p);
        } else {
            System.out.println("No se ha encontrado esa persona");
        }

    }

    //Deserializamos el Objeto ArrayList desde un fichero
    private static void deserializa() {
        FileInputStream miFis = null;
        ObjectInputStream miOis = null;
        try {
            miFis = new FileInputStream(miFichero);
            miOis = new ObjectInputStream(miFis);
            boolean seguir = true;
            // El bucle, en este caso, no es necesario, se pone para que veais como recuperar objetos en caso de que
            // haya que recuperar mÃ¡s de uno. Evidentemente habrÃ­a que hacerlo en el mismo orden en el que se serializaron.
            while (seguir) {
                try {
                    personas = (ArrayList) miOis.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
                } catch (EOFException endOfFileException) {
                    seguir = false;
                } catch (IOException ex) {
                    Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            miOis.close();
            miFis.close();

        } catch (IOException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                miFis.close();
            } catch (IOException ex) {
                Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                miOis.close();
            } catch (IOException ex) {
                Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Serializamos el Objeto ArrayList a un fichero
    private static void serializa() {
        FileOutputStream miFos = null;
        ObjectOutputStream miOos = null;
        try {
            miFos = new FileOutputStream(miFichero);
            miOos = new ObjectOutputStream(miFos);
            miOos.writeObject(personas);
            miOos.close();
            miFos.close();
        } catch (IOException ex) {
            Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                miFos.close();
            } catch (IOException ex) {
                Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                miOos.close();
            } catch (IOException ex) {
                Logger.getLogger(Examen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
