package tienda.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;


public class ServicioProducto {
    ProductoDAO dao = new ProductoDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void crearProducto() throws Exception{
        try {
            Producto producto = new Producto();
            System.out.println("Ingrese el nombre del producto");
            String nombre = leer.next();
            System.out.println("Ingrese el precio del producto");
            Double precio = leer.nextDouble();
            System.out.println("Ingrese el codigo del fabricante");
            Integer codigoFabricante = leer.nextInt();
        
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(codigoFabricante);
            dao.guardarProducto(producto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarProducto(){
        System.out.println("Indique el número de codigo del producto que desea modificar");
        Integer codigo = leer.nextInt();
        System.out.println("¿Qué dato desea modificar?:");
        System.out.println("a) Nombre \nb) Precio \nc) Codigo del fabricante");
        String opcion = leer.next();
        
        try{
            switch(opcion){
                case "a":
                    System.out.println("Ingrese el nuevo nombre");
                    String nombre = leer.next();
                    modificarNombreProducto(codigo, nombre);
                    break;
                case "b":
                    System.out.println("Ingrese el nuevo precio");
                    Double precio = leer.nextDouble();
                    modificarPrecioProducto(codigo, precio);
                    break;
                case "c":
                    System.out.println("Ingrese el nuevo codigo de fabricante");
                    Integer codigoFabricante = leer.nextInt();
                    modificarFabricanteProducto(codigo, codigoFabricante);
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void modificarNombreProducto(Integer codigo, String nombre) throws Exception{
        try {
            //VALIDAMOS
            if (codigo == null) {
                throw new Exception("Debe indicar el id");
            }
            //BUSCAMOS
            Producto producto = buscarProductoPorId(codigo);

            //MODIFICAMOS
            producto.setNombre(nombre);

            dao.modificarNombreProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarPrecioProducto(Integer codigo, Double precio) throws Exception{
        try {
            if(precio == null){
                throw new Exception("Debe indicar el precio");
            }
            
            Producto producto = buscarProductoPorId(codigo);
            producto.setPrecio(precio);
            dao.modificarPrecioProducto(producto);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void modificarFabricanteProducto(Integer codigo, Integer codigoFabricante) throws Exception{
        try {
            if(codigoFabricante == null){
                throw new Exception("Debe indicar el codigo del fabricante");
            }
            
            Producto producto = buscarProductoPorId(codigo);
            producto.setCodigoFabricante(codigoFabricante);
            dao.modificarFabricanteProducto(producto);            
        } catch (Exception e) {
            throw e;
        }
    }

    
    public Producto buscarProductoPorId(Integer codigo) throws Exception{
        try{
            if (codigo == null) {
                throw new Exception("Debe indicar el id");
            }
            Producto producto = dao.buscarProductoPorId(codigo);
            return producto;
        }catch(Exception e){
            throw e;
        }
    }
    
    public void mostrarProductoMasBarato()throws Exception{
        try {
            Double precio = dao.precioMasBajo();
            Producto producto = buscarProductoPorPrecio(precio);
            System.out.println(producto.getNombre());
            System.out.println(producto.getPrecio());
        } catch (Exception e) {
            throw e;
        }
    }
    
 /*   public double precioMasBajo()throws Exception{
        try {
            Double precio = dao.precioMasBajo();
            return precio;
        } catch (Exception e) {
            throw e;
        }
    }*/
    
    public Producto buscarProductoPorPrecio(Double precio) throws Exception{
        try {
            Producto producto = dao.buscarProductoPorPrecio(precio);
            return producto;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void mostrarPortatiles()throws Exception{
        try {
            Collection<Producto> productos = dao.buscarPortatiles();
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listarProductos(String opcion)throws Exception{
        try {
            Collection<Producto> productos = dao.listarProductos();
            if(opcion.equalsIgnoreCase("a")){
                for (Producto producto : productos) {
                    System.out.println(producto.getNombre());
                }
            }else if(opcion.equalsIgnoreCase("b")){
                for (Producto producto : productos) {
                    System.out.println("-------------------------------");
                    System.out.println("Nombre: "+producto.getNombre());
                    System.out.println("Precio: "+producto.getPrecio());
                    System.out.println("-------------------------------");
                }
            }else if(opcion.equalsIgnoreCase("c")){
                for (Producto producto : productos) {
                    System.out.println("-------------------------------");
                    System.out.println(producto);
                    System.out.println("-------------------------------");
                }
            }
            
            
        } catch (Exception e) {
            throw e;
        }
    }
    
}
