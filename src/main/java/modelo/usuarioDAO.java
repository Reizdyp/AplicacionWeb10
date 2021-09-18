package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

import controlador.Conexion;

public class usuarioDAO {
	
	Conexion con=new Conexion();
	Connection cnn=con.conexiondb();
	PreparedStatement ps;
	ResultSet rs;
	usuarioDTO usu;
	
	
	
	//INSERTAR--------------------------------------------------------------

	public boolean insertarusuario(usuarioDTO us) {
		int x;
		boolean dat=false;
		try {
			usu=consultarusuario(us);
				if(us==null) {
				ps=cnn.prepareStatement("INSERT INTO usuario VALUES (?,?,?,?,?)");
				ps.setInt(1, us.getDocumento());
				ps.setString(2, us.getNomusuario());
				ps.setString(3, us.getClave());
				ps.setString(4, us.getRol());
				ps.setString(5, us.getEstado());
				x=ps.executeUpdate();
				if(x>0) {
					dat=true;
				}
			}
			else {
					JOptionPane.showMessageDialog(null, "El usuario ya existe");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al insertar"+e);
			e.printStackTrace();
		}
		
		
		return dat;
	}
	
	
	
	//CONSULTAR--------------------------------------------------------------

public usuarioDTO consultarusuario(usuarioDTO us) {
	try {
		ps=cnn.prepareStatement("SELECT * FROM usuario WHERE documento=?");
		ps.setInt(1, us.getDocumento());
		rs=ps.executeQuery();
		if(rs.next()) {
			usu=new usuarioDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return usu;
}
	
//ELIMINAR--------------------------------------------------------------
public int eliminar(usuarioDTO us) {
	int x=0;
	try {
		ps=cnn.prepareStatement("DELETE FROM usuario WHERE documento=?");
		ps.setInt(1, us.getDocumento());
		x=ps.executeUpdate();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return x;
}

//ACTUALIZAR--------------------------------------------------------------

public boolean actualizar(usuarioDTO us) {
	boolean dat=false;
	int x=0;
	try {
		ps=cnn.prepareStatement("UPDATE usuario SET nomusu=?,clave=?,rol=?,estado=? WHERE Documento=?");
		ps.setString(1, us.getNomusuario());
		ps.setString(2, us.getClave());
		ps.setString(3, us.getRol());
		ps.setString(4, us.getEstado());
		ps.setInt(5, us.getDocumento());
		x=ps.executeUpdate();
		if(x>0){
			dat=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return dat;
}

}
