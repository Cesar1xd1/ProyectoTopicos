package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionBD;
import vista.Login;

public class UsuarioDAO implements Runnable {
	ConexionBD conexion;
	public String fil;
	
	
	public UsuarioDAO() {
		conexion = new ConexionBD();
	}
	public String[] buscar(String filtro){
        String sql="SELECT * FROM usuarios WHERE nombre ='"+filtro+"';";

        ResultSet rs=conexion.ejecutarConsulta(sql);
        String[] u = {"",""};
        try {
            if(rs.next()) {
            u[0]=rs.getString(1);
            u[1]=rs.getString(2);
            Login.indicador=false;
            }else {
            	Login.indicador=true;  
                return null;
            }
        } catch (SQLException e) {
        	Login.indicador=true;
        }
        return u;
    }
	@Override
	public void run() {
		buscar(fil);
	}

	
	
}
