package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import modelo.usuarioDAO;
import modelo.usuarioDTO;

/**
 * Servlet implementation class ServletGestionUsuario
 */
@WebServlet("/ServletGestionUsuario")
public class ServletGestionUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGestionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String u,c,r,e;
		int d;
		boolean res;
		usuarioDAO usdao;
		usuarioDTO usdto;
		usuarioDTO recdatos;
		
		
		//INSERTAR--------------------------------------------------------------
		if (request.getParameter("btnins")!=null) {
			d=Integer.parseInt(request.getParameter("doc"));
			u=request.getParameter("usu");
			c=request.getParameter("cla");
			r=request.getParameter("rol");
			e=request.getParameter("est");
			
			usdto=new usuarioDTO(d, u, c, r, e);
			usdao=new usuarioDAO();
			res=usdao.insertarusuario(usdto);
			if(res) {
				JOptionPane.showMessageDialog(null, "Usuario registrado");
				response.sendRedirect("usuario.jsp");
			}
			else {
				JOptionPane.showMessageDialog(null, "Usuario NO registrado");
				response.sendRedirect("usuario.jsp");
			}
				
		}
		
		
		//CONSULTAR-------------------------------------------------------------
		if (request.getParameter("btncon")!=null) {
			int doc;
			d=Integer.parseInt(request.getParameter("doc"));
			usdto=new usuarioDTO(d);
			usdao=new usuarioDAO();
			recdatos=usdao.consultarusuario(usdto);
			doc=recdatos.getDocumento();
			u=recdatos.getNomusuario();
			c=recdatos.getClave();
			r=recdatos.getRol();
			e=recdatos.getEstado();
			
			response.sendRedirect("usuario.jsp?do="+doc+"&&us="+u+"&&cl="+c+"&&ro="+r+"&&es="+e);
							
		}
		
		
		//ACTUALIZAR------------------------------------------------------------
		if (request.getParameter("btnact")!=null) {
			boolean dat;
			
			d=Integer.parseInt(request.getParameter("doc"));
			u=request.getParameter("usu");
			c=request.getParameter("cla");
			r=request.getParameter("rol");
			e=request.getParameter("est");
			usdto=new usuarioDTO(d, u, c, r, e);
			usdao=new usuarioDAO();
			dat=usdao.actualizar(usdto);
			 if(dat==true) {
				 JOptionPane.showMessageDialog(null, "El usuario fue actualzado");
				 response.sendRedirect("usuario.jsp");
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "El usuario NO fue actualzado");
				 response.sendRedirect("usuario.jsp");
			 }
		}
		
		
		//ELIMINAR--------------------------------------------------------------
		if (request.getParameter("btneli")!=null) {
			int y;
			d=Integer.parseInt(request.getParameter("doc"));
			usdto=new usuarioDTO(d);
			usdao=new usuarioDAO();
			y=usdao.eliminar(usdto);
			if(y>0) {
				JOptionPane.showMessageDialog(null, "el usuario fue eliminado");
				response.sendRedirect("usuario.jsp");
			}
			else {
				JOptionPane.showMessageDialog(null, "el usuario NO fue eliminado");
				response.sendRedirect("usuario.jsp");
			}
											
		}
		
		
	}

}
