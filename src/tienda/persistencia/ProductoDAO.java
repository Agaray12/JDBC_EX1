package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;

public final class ProductoDAO extends DAO {
//CRUD (CREATE-READ-UPDATE-DELETE)

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar un producto");
            }
            String sql = "INSERT INTO Producto (nombre, precio, codigo_fabricante)"
                    + "VALUES ( '" + producto.getNombre() + "' , '" + producto.getPrecio() + "' , '" + producto.getCodigoFabricante() + "' );";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            System.out.println("Error al crear el producto");
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    public void modificarNombreProducto(Producto producto) throws Exception{
        try {
            if (producto == null){
                throw new Exception("Debe indicar un producto");
            }
            
            String sql = "UPDATE Producto SET " + "nombre = '" + producto.getNombre() + "' WHERE codigo = " + producto.getCodigo();
            insertarModificarEliminar(sql);
            
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    public void modificarPrecioProducto (Producto producto) throws Exception {
        try{
            if(producto == null){
                throw new Exception("Debe indicar un producto");
            }
            
            String sql = "UPDATE Producto SET " + "precio = " + producto.getPrecio() + " WHERE codigo = " + producto.getCodigo();
            insertarModificarEliminar(sql);
        } catch(Exception e){
            throw e;
        } finally{
            desconectarBase();
        }
    }
    
    public void modificarFabricanteProducto (Producto producto) throws Exception {
        try{
            if(producto == null){
                throw new Exception("Debe indicar un producto");
            }
            
            String sql = "UPDATE Producto SET " + "codigo_fabricante = '" + producto.getCodigoFabricante() + "' WHERE codigo = " + producto.getCodigo();
            insertarModificarEliminar(sql);
        }catch(Exception e){
            throw e;
        }finally{
            desconectarBase();
        }
    }
    
    public Producto buscarProductoPorId(int codigo) throws Exception {
        try {

            String sql = "SELECT * FROM Producto "
                    + "WHERE codigo = " + codigo + ";";

            consultarBase(sql);
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public double precioMasBajo()throws Exception{
        try {
            String sql = "SELECT min(precio) FROM Producto;";
            consultarBase(sql);
            Double precio = null;
            while(resultado.next()){
                precio = resultado.getDouble(1);
            }
            desconectarBase();
            return precio;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Producto buscarProductoPorPrecio(Double precio) throws Exception{
        try {
            String sql = "SELECT * FROM Producto " + "WHERE precio = " + precio;
            
            consultarBase(sql);
            Producto producto = null;
            while(resultado.next()){
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    
    public Collection<Producto> buscarPortatiles()throws Exception{
        try{
            String sql = "SELECT * FROM Producto " + "WHERE nombre LIKE 'Portatil%'";
            
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while(resultado.next()){
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        }catch(Exception e){
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }
    
    public Collection<Producto> listarProductos()throws Exception{
        try{
           String sql = "SELECT * FROM Producto";
            
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while(resultado.next()){
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos; 
        }catch(Exception e){
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");
        }
    }
    
    public Collection<Producto> listarProductosPorPrecio()throws Exception{
        try{
           String sql = "SELECT * FROM Producto WHERE precio BETWEEN 120 AND 202";
            
            consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();
            while(resultado.next()){
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            desconectarBase();
            return productos; 
        }catch(Exception e){
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error del sistema");
        }
    } 

}
