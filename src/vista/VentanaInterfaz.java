package vista;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.swing.*;


import controlador.ProductosDAO;
import modelo.Productos;

class AltasP extends JInternalFrame implements ActionListener{
	JLabel titulo = new JLabel("Añadir Producto");
	ImageIcon icono = new ImageIcon("./recursos/A.png");
	JLabel lImg = new JLabel();
	JLabel lId = new JLabel("Id:");
	JLabel lNombre = new JLabel("Nombre:");
	JLabel lPrecio = new JLabel("Precio:");
	JTextField tId = new JTextField();
	JTextField tNombre = new JTextField();
	JTextField tPrecio = new JTextField();
	JButton bAgregar = new JButton("Agregar");
	JButton bBorrar = new JButton("Limpiar");
	JButton bCancelar = new JButton("Cancelar");
	
	
	JTable tabla = new JTable();
	
	
	public void atuaclizaTabla(JTable tabla) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			String Consulta = "SELECT * FROM productos";
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tabla.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
	}
	
	public AltasP() {
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(600, 480);
		setTitle("Agregar Producto");
		setBackground(new Color(255,255,255));
		
		
		titulo.setBounds(130, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		
		lImg.setBounds(360, 15, 50, 50);
		lImg.setIcon(new ImageIcon(icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		add(lImg);
		
		
	
		lId.setBounds(80,100,50,20);
		add(lId);
		
		
		lNombre.setBounds(80,150,50,20);
		add(lNombre);
		
		
		lPrecio.setBounds(80,200,50,20);
		add(lPrecio);
		
		
		tId.setBounds(105,100,205,20);
		tId.setBackground(new Color(230,230,230));
		tId.addKeyListener(new KeyAdapter(){
		   public void keyTyped(KeyEvent e){
		      char caracter = e.getKeyChar();
		      if(((caracter < '0') || (caracter > '9')) &&(caracter != '\b')){
		         e.consume(); 
		      }
		   }
		});
		
		
		
		add(tId);
		
		
		tNombre.setBounds(140,150,170,20);
		tNombre.setBackground(new Color(230,230,230));
		add(tNombre);
		
		
		tPrecio.setBounds(130,200,180,20);
		tPrecio.setBackground(new Color(230,230,230));
		add(tPrecio);
		
		
		bAgregar.setBounds(400, 90, 100, 30);
		bAgregar.setBackground(new Color(100,255,170));
		bAgregar.addActionListener(this);
		add(bAgregar);
		
		
		bBorrar.setBounds(400, 150, 100, 30);
		bBorrar.setBackground(new Color(0,170,255));
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		
		bCancelar.setBounds(400, 210, 100, 30);
		bCancelar.setBackground(new Color(255,200,0));
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/compu1xd1";
		String Consulta = "SELECT * FROM productos";
		
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane escroll = new JScrollPane(tabla);
		tabla.setModel(modeloDatos);
		
		escroll.setBounds(40, 270, 500, 130);
		add(escroll);
		
		
		
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bAgregar){
			String id = tId.getText();
			String nombre = tNombre.getText();
			String precio = tPrecio.getText();
			
			double dPrecio = 0;
			boolean posible = true;
			if(id.equals("")||nombre.equals("")||precio.equals("")) {
				JOptionPane.showMessageDialog(null, "Algun campo quedo sin ser llenado");
			}else {
				int dId = Integer.parseInt(id);
				try {
					dPrecio = Double.parseDouble(precio);
				}catch(Exception ex){
					posible = false;
					JOptionPane.showMessageDialog(null, "Precio invalido");
	
				}
				
				
				if(posible == true || dPrecio == 0) {
					Productos p = new Productos(dId, nombre, dPrecio);
					ProductosDAO pDAO = new ProductosDAO();
					if(pDAO.insertarRegistro(p)) {
						
					}else {
						JOptionPane.showMessageDialog(null, "Id existente, si desea modificarlo vaya a MODIFICAR");
					}
					atuaclizaTabla(tabla);
				}else {
					JOptionPane.showMessageDialog(null, "Necesitas ingresar una cantidad de dinero correcta");
				}
				
				
			}
			
		}else if(e.getSource()==bBorrar) {
			tId.setText("");
			tNombre.setText("");
			tPrecio.setText("");
		}
		else if(e.getSource()==bCancelar) {
			setVisible(false);
		}
		
	}//Listener
}//======ALTA-PRODUTOS======

class BajasP extends JInternalFrame implements ActionListener{
	JLabel titulo = new JLabel("Eliminar Producto");
	ImageIcon icono = new ImageIcon("./recursos/B.png");
	JLabel lImg = new JLabel();
	JLabel lId = new JLabel("Id:");
	JLabel lNombre = new JLabel("Nombre:");
	JLabel lPrecio = new JLabel("Precio:");
	JTextField tId = new JTextField();
	JTextField tNombre = new JTextField();
	JTextField tPrecio = new JTextField();
	JButton bEliminar = new JButton("Eliminar");
	JButton bBorrar = new JButton("Limpiar");
	JButton bCancelar = new JButton("Cancelar");
	JButton bBuscar = new JButton();
	ImageIcon iconoBuscar = new ImageIcon("./recursos/buscar-barras.png");
	JTable tabla = new JTable();
	
	public void obtenerRegistroTabla() {
		int i = (int)tabla.getValueAt(tabla.getSelectedRow(), 0);
		tId.setText(i+""); 
		tNombre.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
		BigDecimal p = (BigDecimal) tabla.getValueAt(tabla.getSelectedRow(), 2);
		tPrecio.setText((p+""));
	}
	
	public void atuaclizaTabla(String sql) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, sql);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tabla.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
	}
	
	public BajasP() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(600, 480);
		setTitle("Agregar Producto");
		setBackground(new Color(255,255,255));
		
		titulo.setBounds(130, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		lImg.setBounds(360, 10, 100, 60);
		lImg.setIcon(new ImageIcon(icono.getImage().getScaledInstance(100,60, Image.SCALE_SMOOTH)));
		add(lImg);
		
		lId.setBounds(80,100,50,20);
		add(lId);
		lNombre.setBounds(80,150,50,20);
		add(lNombre);
		lPrecio.setBounds(80,200,50,20);
		add(lPrecio);
		
		tId.setBounds(105,100,205,20);
		tId.setBackground(new Color(230,230,230));
		tId.addKeyListener(new KeyAdapter(){
		   public void keyTyped(KeyEvent e){
		      char caracter = e.getKeyChar();
		      if(((caracter < '0') || (caracter > '9')) &&(caracter != '\b')){
		         e.consume(); 
		      }
		   }
		});
		add(tId);
		
		tNombre.setBounds(140,150,170,20);
		tNombre.setBackground(new Color(230,230,230));
		tNombre.setEnabled(false);
		add(tNombre);
		
		tPrecio.setBounds(130,200,180,20);
		tPrecio.setBackground(new Color(230,230,230));
		tPrecio.setEnabled(false);
		add(tPrecio);
		
		bBuscar.setBounds(340, 90, 100, 30);
		bBuscar.setIcon(iconoBuscar);
		bBuscar.addActionListener(this);
		add(bBuscar);
		
		bEliminar.setBounds(460, 90, 100, 30);
		bEliminar.setBackground(new Color(255,10,10));
		bEliminar.addActionListener(this);
		add(bEliminar);
		
		bBorrar.setBounds(400, 150, 100, 30);
		bBorrar.setBackground(new Color(0,170,255));
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bCancelar.setBounds(400, 210, 100, 30);
		bCancelar.setBackground(new Color(255,200,0));
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/compu1xd1";
		String Consulta = "SELECT * FROM productos";
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane escroll = new JScrollPane(tabla);
		tabla.setModel(modeloDatos);
		
		escroll.setBounds(40, 270, 500, 130);
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {@Override public void mouseClicked(java.awt.event.MouseEvent evt) { obtenerRegistroTabla();}         });
		add(escroll);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bEliminar){
			String i  = (tId.getText());
			ProductosDAO pDAO = new ProductosDAO();
			pDAO.eliminarRegistro(i);
			atuaclizaTabla("SELECT * FROM productos");
			tId.setText("");
			tNombre.setText("");
			tPrecio.setText("");			
		}else if(e.getSource()==bBorrar) {
			tId.setText("");
			tNombre.setText("");
			tPrecio.setText("");
		}
		else if(e.getSource()==bCancelar) {
			setVisible(false);
		}else if(e.getSource()==bBuscar) {
			String sql = "SELECT * FROM productos ";
			boolean primero=true;
				if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
				primero=false;
				sql+=("id = '"+tId.getText()+"'");
				
				atuaclizaTabla(sql);
		}
		
	}//Listener
}//BAJA-PRODUCTOS

class CambiosP extends JInternalFrame implements ActionListener{
	JLabel titulo = new JLabel("Modificar Producto");
	ImageIcon icono = new ImageIcon("./recursos/C1.png");
	JLabel lImg = new JLabel();
	JLabel lId = new JLabel("Id:");
	JLabel lNombre = new JLabel("Nombre:");
	JLabel lPrecio = new JLabel("Precio:");
	JTextField tId = new JTextField();
	JTextField tNombre = new JTextField();
	JTextField tPrecio = new JTextField();
	JButton bBorrar = new JButton("Limpiar");
	JButton bGuardar = new JButton("Guardar Cambios");
	JButton bCancelar = new JButton("Cancelar");
	JButton bBuscar = new JButton();
	ImageIcon iconoBuscar = new ImageIcon("./recursos/buscar-barras.png");
	JTable tabla = new JTable();
	
	public void obtenerRegistroTabla() {
		int i = (int)tabla.getValueAt(tabla.getSelectedRow(), 0);
		tId.setText(i+""); 
		tNombre.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
		BigDecimal p = (BigDecimal) tabla.getValueAt(tabla.getSelectedRow(), 2);
		tPrecio.setText((p+""));
	}
	
	public void atuaclizaTabla(String sql) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, sql);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tabla.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
	}
	
	public CambiosP() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(600, 480);
		setTitle("Agregar Producto");
		setBackground(new Color(255,255,255));
		
		titulo.setBounds(110, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		lImg.setBounds(390, 10, 50, 50);
		lImg.setIcon(new ImageIcon(icono.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH)));
		add(lImg);
		
		lId.setBounds(80,100,50,20);
		add(lId);
		lNombre.setBounds(80,150,50,20);
		add(lNombre);
		lPrecio.setBounds(80,200,50,20);
		add(lPrecio);
		
		tId.setBounds(105,100,205,20);
		tId.setBackground(new Color(230,230,230));
		tId.addKeyListener(new KeyAdapter(){
		   public void keyTyped(KeyEvent e){
		      char caracter = e.getKeyChar();
		      if(((caracter < '0') || (caracter > '9')) &&(caracter != '\b')){
		         e.consume(); 
		      }
		   }
		});
		add(tId);
		
		tNombre.setBounds(140,150,170,20);
		tNombre.setBackground(new Color(230,230,230));
		add(tNombre);
		
		tPrecio.setBounds(130,200,180,20);
		tPrecio.setBackground(new Color(230,230,230));
		add(tPrecio);
		
		bBuscar.setBounds(340, 90, 100, 30);
		bBuscar.setIcon(iconoBuscar);
		bBuscar.addActionListener(this);
		add(bBuscar);
		
		bBorrar.setBounds(460, 90, 100, 30);
		bBorrar.setBackground(new Color(0,170,255));
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bGuardar.setBounds(375, 150, 150, 30);
		bGuardar.setBackground(new Color(255,175,175));
		bGuardar.addActionListener(this);
		add(bGuardar);
		
		bCancelar.setBounds(400, 210, 100, 30);
		bCancelar.setBackground(new Color(255,200,0));
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/compu1xd1";
		String Consulta = "SELECT * FROM productos";
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane escroll = new JScrollPane(tabla);
		tabla.setModel(modeloDatos);
		
		escroll.setBounds(40, 270, 500, 130);
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {@Override public void mouseClicked(java.awt.event.MouseEvent evt) { obtenerRegistroTabla();}         });
		add(escroll);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bGuardar){
			
		}
		else if(e.getSource()==bCancelar) {
			setVisible(false);
		}else if(e.getSource()==bBuscar) {
			String sql = "SELECT * FROM productos ";
			boolean primero=true;
				if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
				primero=false;
				sql+=("id = '"+tId.getText()+"'");
				
				atuaclizaTabla(sql);
		}
		
	}//Listener
}//CAMBIOS-PRODUCTOS






class VentanaPrincipal extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JMenu mProductos,mVentas;
	JMenuItem addProducto,deleteProducto,updateProducto,searchProducto;
	JMenuItem addVenta,deleteVenta,updateVenta,searchVenta;
	JInternalFrame prodAltas,prodBajas,prodCambios,prodConsultas;
	JInternalFrame ventasAltas,ventasBajas,ventasCambios,ventasConsultas;
	JDesktopPane dp = new JDesktopPane();
	
	public VentanaPrincipal() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(700,600);
		setLocationRelativeTo(null);
		setTitle("Compu1xd1");
		setVisible(true);
		
		
		
		menuBar = new JMenuBar();
			mProductos = new JMenu("Productos");
			mVentas = new JMenu("Ventas");
				addProducto = new JMenuItem("Agregar");
				deleteProducto = new JMenuItem("Eliminar");
				updateProducto = new JMenuItem("Modificar");
				searchProducto = new JMenuItem("Buscar");
				
				addVenta = new JMenuItem("Agregar");
				deleteVenta = new JMenuItem("Eliminar");
				updateVenta = new JMenuItem("Modificar");
				searchVenta = new JMenuItem("Buscar");
		
			mProductos.add(addProducto);
			addProducto.addActionListener(this);
			deleteProducto.addActionListener(this);
			updateProducto.addActionListener(this);
			searchProducto.addActionListener(this);
			mProductos.add(deleteProducto);
			mProductos.add(updateProducto);
			mProductos.add(searchProducto);
			mVentas.add(addVenta);
			mVentas.add(deleteVenta);
			mVentas.add(updateVenta);
			mVentas.add(searchVenta);
			
			menuBar.add(mProductos);
			menuBar.add(mVentas);
			setJMenuBar(menuBar);
			
			
			dp.setBounds(0, 0, 700, 600);
			dp.setBackground(new Color(192, 192, 192));
			add(dp);		
	}//Constructor ventana principal


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addProducto) {
			AltasP ap = new AltasP();
			dp.add(ap);
			ap.setVisible(true);
			add(dp);
		}else if(e.getSource()==deleteProducto) {
			BajasP bp = new BajasP();
			dp.add(bp);
			bp.setVisible(true);
			add(dp);
		}else if(e.getSource()==updateProducto) {
			CambiosP cp = new CambiosP();
			dp.add(cp);
			cp.setVisible(true);
			add(dp);
		}
		
	}
}




public class VentanaInterfaz {

	public static void main(String[] args) {
		/*
		Productos p = new Productos(5, "RAM 8GB", 500.00);
		ProductosDAO pDAO = new ProductosDAO();
		if(pDAO.insertarRegistro(p)) {
			System.out.println("Agregado");
		}else {
			System.out.println("No agregado");
		}
		*/
		
		SwingUtilities.invokeLater(new Runnable() {
			
			
			
			public void run() {
				new VentanaPrincipal();
			}
		});
	}

}

