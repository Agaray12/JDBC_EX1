package tienda;

import java.util.Scanner;
import tienda.servicios.ServicioFabricante;
import tienda.servicios.ServicioProducto;

public class Tienda {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        final String SALIR = "s";
        String opcion;
        ServicioProducto sp = new ServicioProducto();
        ServicioFabricante sf = new ServicioFabricante();
        
        do {
            System.out.println("----------------------------");
            System.out.println("Ingrese la opción que desea realizar");
            System.out.println("a) Lista de todos los productos \nb) Lista de todos los productos con sus precios");
            System.out.println("c) Lista de todos los productos cuyo precio esté entre 120 y 202");
            System.out.println("d) Lista de todos los portátiles \ne) Nombre y precio del producto más barato");
            System.out.println("f) Ingresar un nuevo producto a la base de datos");
            System.out.println("g) Ingresar un nuevo fabricante a la base de datos");
            System.out.println("h) Editar los datos de un producto");
            System.out.println("s) Salir");
            System.out.println("----------------------------");
            opcion = leer.next();
            
            try{
                switch(opcion){
                case "a":
                    sp.listarProductos(opcion);
                    break;
                case "b":
                    sp.listarProductos(opcion);
                    break;
                case "c":
                    sp.listarProductos(opcion);
                    break;
                case "d":
                    sp.mostrarPortatiles();
                    break;
                case "e":
                    sp.mostrarProductoMasBarato();
                    break;
                case "f":
                    sp.crearProducto();
                    break;
                case "g":
                    sf.crearFabricante();
                    break;
                case "h":
                    sp.modificarProducto();
                }
                    
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }while(!opcion.equalsIgnoreCase(SALIR));
    }
}
