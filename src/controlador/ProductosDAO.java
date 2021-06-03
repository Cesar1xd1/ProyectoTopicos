package controlador;
import conexionBD.ConexionBD;
import modelo.Productos;

//DAD - Data Access Objetc


public class ProductosDAO {

	ConexionBD conexion;
	
	public ProductosDAO() {
		conexion = new ConexionBD();
	}
	
	public boolean insertarRegistro(Productos p) {
		boolean resultado = false;
		String sql = "INSERT INTO productos VALUES('"+p.getId()+"','"+p.getNombre()+"','"+p.getPrecio()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean eliminarRegistro(String nc) {
		boolean resultado = false;
		String sql = "DELETE FROM productos WHERE id = \""+nc+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	public boolean modificarRegistro(Productos a) {
		boolean resultado = false;
		
		String sql = "UPDATE productos SET nombre='"+a.getNombre()+"', precio='"+a.getPrecio()+"'"+" "
                + " WHERE id = '"+a.getId()+"';";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
	
	
	
}
