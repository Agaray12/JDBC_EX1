package tienda.persistencia;

import tienda.entidades.Fabricante;


public final class FabricanteDAO extends DAO {
    
    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar un producto");
            }
            String sql = "INSERT INTO Fabricante (nombre)"
                    + "VALUES ( '" + fabricante.getNombre() + "' );";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            System.out.println("Error al crear el producto");
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    
    
    
}
