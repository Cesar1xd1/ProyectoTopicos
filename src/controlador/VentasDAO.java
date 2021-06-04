package controlador;
import conexionBD.ConexionBD;
import modelo.Venta;

//DAD - Data Access Objetc


public class VentasDAO {

	ConexionBD conexion;
	
	public VentasDAO() {
		conexion = new ConexionBD();
	}
	
	public boolean insertarRegistro(Venta v) {
		boolean resultado = false;
		String sql = "INSERT INTO ventas VALUES('"+v.getIdVenta()+"','"+v.getIdProducto()+"','"+v.getNombreProducto()+"','"+v.getPrecio()+"','"+v.getFecha()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	public boolean eliminarRegistro(String nc) {
		boolean resultado = false;
		String sql = "DELETE FROM ventas WHERE idVenta = \""+nc+"\"";
		resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	public boolean modificarRegistro(Venta ve) {
		boolean resultado = false;
		String sql = "UPDATE ventas SET idProducto="+ve.getIdProducto()+",nombreProducto='"+ve.getNombreProducto()+"',precio=" + ve.getPrecio()+",fecha='"+ve.getFecha()
                + "'       WHERE idVenta="+ve.getIdVenta()+";";
		resultado = conexion.ejecutarInstruccion(sql);
		return resultado;
	}
	
	
	
	
	
}
