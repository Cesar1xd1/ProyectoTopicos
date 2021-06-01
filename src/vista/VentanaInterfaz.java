package vista;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
			if(id.equals("")||nombre.equals("")||precio.equals("")) {
				JOptionPane.showMessageDialog(null, "Algun campo quedo sin ser llenado");
			}
			else {
				boolean posible = true;
				int dId = Integer.parseInt(id);
				try {
					dPrecio = Double.parseDouble(precio);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Precio invalido");
					posible = false;
				}
				if(posible = true) {
					Productos p = new Productos(dId, nombre, dPrecio);
					ProductosDAO pDAO = new ProductosDAO();
					pDAO.insertarRegistro(p);
					atuaclizaTabla(tabla);
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
}


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

