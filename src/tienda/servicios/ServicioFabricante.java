package tienda.servicios;

import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;


public class ServicioFabricante {
    FabricanteDAO dao = new FabricanteDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    public void crearFabricante() throws Exception{
        try {
            Fabricante fabricante = new Fabricante();
            System.out.println("Ingrese el nombre del fabricante");
            fabricante.setNombre(leer.next());
            dao.guardarFabricante(fabricante);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
