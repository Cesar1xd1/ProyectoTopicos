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
		      if(((caracter < 48) || (caracter > 57)) &&(caracter != '\b')){
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
				
				
				if(posible == true ) {
					Productos p = new Productos(dId, nombre, dPrecio);
					ProductosDAO pDAO = new ProductosDAO();
					if(pDAO.insertarRegistro(p)) {
					}else {
						JOptionPane.showMessageDialog(null, "Registro existente, si desea modificarlo vaya a MODIFICAR");
					}
					atuaclizaTabla(tabla);
				}else {
					JOptionPane.showMessageDialog(null, "Necesitas ingresar una cantidad de dinero correcta");
					tPrecio.setText("");
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
		      if(((caracter < 48) || (caracter > 57)) &&(caracter != '\b')){
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
	
	public void atuaclizaTabla2(JTable tabla) {
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
		      if(((caracter < 48) || (caracter > 57)) &&(caracter != '\b')){
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
				
				
				if(posible == true ) {
					Productos p = new Productos(dId, nombre, dPrecio);
					ProductosDAO pDAO = new ProductosDAO();
					if(pDAO.modificarRegistro(p)) {
					}else {
						JOptionPane.showMessageDialog(null, "xd");
					}
					atuaclizaTabla2(tabla);
				}else {
					JOptionPane.showMessageDialog(null, "Necesitas ingresar una cantidad de dinero correcta");
					tPrecio.setText("");
				}
				
				
			}
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
		}else if(e.getSource()==bBorrar){
			tId.setText("");
			tNombre.setText("");
			tPrecio.setText("");
		}
		
	}//Listener
}//CAMBIOS-PRODUCTOS

class ConsultasP extends JInternalFrame implements ActionListener{
	JLabel titulo = new JLabel("Consulta Producto");
	ImageIcon icono = new ImageIcon("./recursos/C2.png");
	JLabel lImg = new JLabel();
	JLabel lId = new JLabel("Id:");
	JLabel lNombre = new JLabel("Nombre:");
	JLabel lPrecio = new JLabel("Precio:");
	JTextField tId = new JTextField();
	JTextField tNombre = new JTextField();
	JTextField tPrecio = new JTextField();
	JButton bBorrar = new JButton("Limpiar");
	JButton bCancelar = new JButton("Cancelar");
	JButton bBuscar = new JButton();
	ImageIcon iconoBuscar = new ImageIcon("./recursos/buscar-barras.png");
	JTable tabla = new JTable();
	JLabel lFiltros = new JLabel("Filtro de busqueda");
	JRadioButton rbTodos = new JRadioButton("TODOS");
	JRadioButton rbId = new JRadioButton();
	JRadioButton rbNombre = new JRadioButton();
	JRadioButton rbPrecio = new JRadioButton();
	ButtonGroup bGrupo = new ButtonGroup();
	
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
	
	public ConsultasP() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(600, 480);
		setTitle("Agregar Producto");
		setBackground(new Color(255,255,255));
		
		
		
		
		titulo.setBounds(130, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		lImg.setBounds(400, 20, 40, 40);
		lImg.setIcon(new ImageIcon(icono.getImage().getScaledInstance(40,40, Image.SCALE_SMOOTH)));
		add(lImg);
		
		
		
		rbTodos.setBounds(30, 100, 70, 20);
		rbTodos.setBackground(new Color(255,255,255));
		bGrupo.add(rbTodos);
		rbTodos.setSelected(true);
		add(rbTodos);
		rbTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tId.setEnabled(true);
				tNombre.setEnabled(true);
				tPrecio.setEnabled(true);
				
			}
		});
		
		rbId.setBounds(135, 100, 20, 20);
		rbId.setBackground(new Color(255,255,255));
		bGrupo.add(rbId);
		add(rbId);
		rbId.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tId.setEnabled(true);
				tNombre.setEnabled(false);
				tPrecio.setEnabled(false);
				
			}
		});
		rbNombre.setBounds(135, 150, 20, 20);
		rbNombre.setBackground(new Color(255,255,255));
		bGrupo.add(rbNombre);
		add(rbNombre);
		rbNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tId.setEnabled(false);
				tNombre.setEnabled(true);
				tPrecio.setEnabled(false);
				
			}
		});
		rbPrecio.setBounds(135, 200, 20, 20);
		rbPrecio.setBackground(new Color(255,255,255));
		bGrupo.add(rbPrecio);
		add(rbPrecio);
		rbPrecio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tId.setEnabled(false);
				tNombre.setEnabled(false);
				tPrecio.setEnabled(true);
				
			}
		});
	
		
		lFiltros.setBounds(30, 70, 150, 20);
		add(lFiltros);
		lId.setBounds(160,100,50,20);
		add(lId);
		lNombre.setBounds(160,150,50,20);
		add(lNombre);
		lPrecio.setBounds(160,200,50,20);
		add(lPrecio);
		
		tId.setBounds(185,100,205,20);
		tId.setBackground(new Color(230,230,230));
		tId.addKeyListener(new KeyAdapter(){
		   public void keyTyped(KeyEvent e){
		      char caracter = e.getKeyChar();
		      if(((caracter < 48) || (caracter > 57)) &&(caracter != '\b')){
		         e.consume(); 
		      }
		   }
		});
		add(tId);
		
		tNombre.setBounds(220,150,170,20);
		tNombre.setBackground(new Color(230,230,230));
		tNombre.setEnabled(false);
		add(tNombre);
		
		tPrecio.setBounds(210,200,180,20);
		tPrecio.setBackground(new Color(230,230,230));
		tPrecio.setEnabled(false);
		add(tPrecio);
		
		bBuscar.setBounds(430, 90, 100, 30);
		bBuscar.setIcon(iconoBuscar);
		bBuscar.addActionListener(this);
		add(bBuscar);
		
		
		
		bBorrar.setBounds(430, 150, 100, 30);
		bBorrar.setBackground(new Color(0,170,255));
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bCancelar.setBounds(430, 210, 100, 30);
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
		if(e.getSource()==bBorrar) {
			tId.setText("");
			tNombre.setText("");
			tPrecio.setText("");
		}
		else if(e.getSource()==bCancelar) {
			setVisible(false);
			
		}else if(e.getSource()==bBuscar) {
			String sql = "SELECT * FROM productos ";
			if(rbId.isSelected()) {
				if(tId.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Ingresa Id para Consultarlo");
				}else {
					sql = sql + ("WHERE id LIKE'"+tId.getText()+"%'");
					atuaclizaTabla(sql);
					tNombre.setText("");
					tPrecio.setText("");
				}
			}else if(rbNombre.isSelected()) {
				if(tNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Un Nombre a consultar");
				}else {
					sql = sql + ("WHERE nombre LIKE'"+tNombre.getText()+"%'");
					atuaclizaTabla(sql);
					tId.setText("");
					tPrecio.setText("");
				}
			}else if(rbPrecio.isSelected()) {
				if(tPrecio.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Ingrese un precio a Consultar");
				}else {
					sql = sql + ("WHERE precio LIKE'"+tPrecio.getText()+"%'");
					boolean poder = true;
					try {
						double x = Double.parseDouble(tPrecio.getText());
					}catch(Exception es){
						poder = false;
						tPrecio.setText("");
						JOptionPane.showMessageDialog(null,"Precio invalido");
					}
					if(poder==true) {
						atuaclizaTabla(sql);
					}
					
					tId.setText("");
					tNombre.setText("");
				}
			}
		}
		
	}//Listener
}//CONSULTAS-PRODUCTOS




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
		}else if(e.getSource()==searchProducto) {
			ConsultasP cp = new ConsultasP();
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

