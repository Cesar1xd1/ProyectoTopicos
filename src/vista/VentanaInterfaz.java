package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import controlador.ProductosDAO;
import controlador.VentasDAO;
import modelo.Productos;
import modelo.Venta;

class AltasV extends JInternalFrame implements ActionListener{
	JLabel titulo = new JLabel("Realizar Venta");
	ImageIcon icono = new ImageIcon("./recursos/AV.png");
	ImageIcon iconoBuscar = new ImageIcon("./recursos/buscar-barras.png");
	JLabel lImg = new JLabel();
	JLabel lId = new JLabel("Id:");
	JLabel lIdP = new JLabel("Id Producto");
	JLabel lNombre = new JLabel("Nombre:");
	JLabel lPrecio = new JLabel("Precio:");
	JLabel lFecha = new JLabel("Fecha:");
	JTextField tId = new JTextField();
	JTextField tIdP = new JTextField();
	JTextField tNombre = new JTextField();
	JTextField tPrecio = new JTextField();
	JComboBox cD = new JComboBox();
	JComboBox cM = new JComboBox();
	JComboBox cA = new JComboBox();
	JLabel dia = new JLabel("Dia");
	JLabel mes = new JLabel("Mes");
	JLabel anio = new JLabel("Año");
	JButton bAgregar = new JButton("Agregar");
	JButton bBorrar = new JButton("Limpiar");
	JButton bCancelar = new JButton("Cancelar");
	JButton bBuscar = new JButton();
	JTable tabla = new JTable();
	JTable tablaP = new JTable();
	JButton bHoy = new JButton("Hoy");
	java.util.Date fecha = new Date();
	
	public void atuaclizaTabla(JTable tabla) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			String Consulta = "SELECT * FROM ventas";		
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
	
	public void atuaclizaTablaP(String sql) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, sql);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tablaP.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
	}
	
	public void obtenerRegistro1() {
		try {
			tNombre.setText((String) tablaP.getValueAt(0, 1));
			BigDecimal p = (BigDecimal) tablaP.getValueAt(0, 2);
			tPrecio.setText((p+""));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Producto no existe");
			tNombre.setText("");
			tPrecio.setText("");
		}
		
	}
	public String laFecha() {
		String d = (String) cD.getSelectedItem();
		String m = "";
		if(cM.getSelectedIndex()==0) {
			m = "01";
		}else if(cM.getSelectedIndex()==1) {
			m = "02";
		}else if(cM.getSelectedIndex()==2) {
			m = "03";
		}else if(cM.getSelectedIndex()==3) {
			m = "04";
		}else if(cM.getSelectedIndex()==4) {
			m = "05";
		}else if(cM.getSelectedIndex()==5) {
			m = "06";
		}else if(cM.getSelectedIndex()==6) {
			m = "07";
		}else if(cM.getSelectedIndex()==7) {
			m = "08";
		}else if(cM.getSelectedIndex()==8) {
			m = "09";
		}else if(cM.getSelectedIndex()==9) {
			m = "10";
		}else if(cM.getSelectedIndex()==10) {
			m = "11";
		}else if(cM.getSelectedIndex()==11) {
			m = "12";
		}
		String a = (String) cA.getSelectedItem();
		return a+"-"+m+"-"+d;
	}
	
	
	public AltasV(){
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(683, 538);
		setTitle("Realizar Venta");
		setBackground(new Color(255,255,255));
		
		titulo.setBounds(160, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		lImg.setBounds(390, 15, 50, 50);
		lImg.setIcon(new ImageIcon(icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		add(lImg);
		
		lId.setBounds(100, 100, 50, 20);
		add(lId);
		lIdP.setBounds(100, 150, 100, 20);
		add(lIdP);
		lNombre.setBounds(100, 200, 50, 20);
		add(lNombre);
		lPrecio.setBounds(100, 250, 50, 20);
		add(lPrecio);
		lFecha.setBounds(100, 310, 50, 20);
		add(lFecha);
		dia.setBounds(160, 290, 50, 20);
		add(dia);
		mes.setBounds(230, 290, 50, 20);
		add(mes);
		anio.setBounds(320, 290, 50, 20);
		add(anio);
		
		
		tId.setBounds(125, 100, 300, 20);
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
		tIdP.setBounds(180, 150, 245, 20);
		tIdP.setBackground(new Color(230,230,230));
		tIdP.addKeyListener(new KeyAdapter(){
			   public void keyTyped(KeyEvent e){
				      char caracter = e.getKeyChar();
				      if(((caracter < 48) || (caracter > 57)) &&(caracter != '\b')){
				         e.consume(); 
				      }
				   }
				});
		add(tIdP);
		tNombre.setBounds(160, 200, 265, 20);
		tNombre.setBackground(new Color(230,230,230));
		add(tNombre);
		tPrecio.setBounds(150, 250, 275, 20);
		tPrecio.setBackground(new Color(230,230,230));
		add(tPrecio);
		cD.setBounds(150, 310, 50, 20);
		for(int i = 1;i!=32;i=i+1) {
			if(i<10) {
				cD.addItem("0"+i);
			}else {
				cD.addItem(""+i);
			}
			
		}
		add(cD);
		
		cM.setBounds(200, 310, 100, 20);
		cM.addItem("ENERO");
		cM.addItem("FEBRERO");
		cM.addItem("MARZO");
		cM.addItem("ABRIL");
		cM.addItem("MAYO");
		cM.addItem("JUNIO");
		cM.addItem("JULIO");
		cM.addItem("AGOSTO");
		cM.addItem("SEPTIEMBRE");
		cM.addItem("OCTUBRE");
		cM.addItem("NOVIEMBRE");
		cM.addItem("DICIEMBRE");
		add(cM);
		cA.setBounds(300, 310, 80, 20);
		for(int i = 2021;i!=2051;i=i+1) {
			cA.addItem(""+i);
		}
		add(cA);
		bHoy.setBounds(380, 310, 70, 20);
		add(bHoy);
		bHoy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cD.setSelectedIndex(fecha.getDay()-2);
				cM.setSelectedIndex(fecha.getMonth());
				cA.setSelectedItem(""+(1900+fecha.getYear()));
			}
		});
	
		
		
		bAgregar.setBounds(550, 90, 100, 30);
		bAgregar.setBackground(new Color(100,255,170));
		bAgregar.addActionListener(this);
		add(bAgregar);
		
		bBorrar.setBounds(550, 150, 100, 30);
		bBorrar.setBackground(new Color(0,170,255));
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bCancelar.setBounds(550, 210, 100, 30);
		bCancelar.setBackground(new Color(255,200,0));
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		bBuscar.setBounds(440, 145, 50, 30);
		bBuscar.setIcon(iconoBuscar);
		bBuscar.addActionListener(this);
		add(bBuscar);
		
		tNombre.setEnabled(false);
		tPrecio.setEnabled(false);
		
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/compu1xd1";
		String Consulta = "SELECT * FROM ventas";
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
		escroll.setBounds(40, 350, 600, 130);
		add(escroll);
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			String s = "SELECT * FROM productos WHERE id = '"+tIdP.getText()+"'";
				atuaclizaTablaP(s);
				obtenerRegistro1();
		
		}else if(e.getSource()==bAgregar) {
			int id ;
			int idP;
			Double precio = 0.0;
			if (tId.getText().equals("")) {
				id = 0;
			}else {
				id = Integer.parseInt(tId.getText());
			}
			if (tIdP.getText().equals("")) {
				idP = 0;
			}else {
				 idP = Integer.parseInt(tIdP.getText());
			}
			String nombre = tNombre.getText();
			try {
				 precio = Double.parseDouble(tPrecio.getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "no dejes campos vacios");
			}
			
			String fecha = laFecha();
			if(id==0||idP==0||nombre.equals("")||precio.equals("")) {
				
			}else {
				Venta v  = new Venta(id,idP, nombre, precio,fecha);
				VentasDAO vDAO = new VentasDAO();
				if(vDAO.insertarRegistro(v)) {
					
				}else {
					JOptionPane.showMessageDialog(null, "El ID ya existe");
				}
				atuaclizaTabla(tabla);
				
			}
		}else if(e.getSource()==bBorrar) {
			tId.setText("");
			tIdP.setText("");
			tNombre.setText("");
			tPrecio.setText("");
		}else if(e.getSource()==bCancelar) {
			setVisible(false);
		}
		
	}
	
}
class BajasV extends JInternalFrame implements ActionListener{
	JLabel titulo = new JLabel("Realizar Devolución");
	ImageIcon icono = new ImageIcon("./recursos/BV.png");
	ImageIcon iconoBuscar = new ImageIcon("./recursos/buscar-barras.png");
	JLabel lImg = new JLabel();
	JLabel lId = new JLabel("Id:");
	JLabel lIdP = new JLabel("Id Producto");
	JLabel lNombre = new JLabel("Nombre:");
	JLabel lPrecio = new JLabel("Precio:");
	JLabel lFecha = new JLabel("Fecha:");
	JTextField tId = new JTextField();
	JTextField tIdP = new JTextField();
	JTextField tNombre = new JTextField();
	JTextField tPrecio = new JTextField();
	JComboBox cD = new JComboBox();
	JComboBox cM = new JComboBox();
	JComboBox cA = new JComboBox();
	JLabel dia = new JLabel("Dia");
	JLabel mes = new JLabel("Mes");
	JLabel anio = new JLabel("Año");
	JButton bEliminar = new JButton("Eliminar");
	JButton bBorrar = new JButton("Limpiar");
	JButton bCancelar = new JButton("Cancelar");
	JButton bBuscar = new JButton();
	JTable tabla = new JTable();
	JTable tablaP = new JTable();
	JButton bHoy = new JButton("Hoy");
	java.util.Date fecha = new Date();
	
	public void obtenerRegistroTabla() {
		int i = (int)tabla.getValueAt(tabla.getSelectedRow(), 0);
		tId.setText(i+""); 
		int iP = (int)tabla.getValueAt(tabla.getSelectedRow(), 1);
		tIdP.setText(iP+"");
		tNombre.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 2));
		BigDecimal p = (BigDecimal) tabla.getValueAt(tabla.getSelectedRow(), 3);
		tPrecio.setText((p+""));
	}
	public void atuaclizaTabla(JTable tabla) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			String Consulta = "SELECT * FROM ventas";		
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
	
	public void obtenerRegistro() {
		try {
			tNombre.setText((String) tablaP.getValueAt(0, 1));
			BigDecimal p = (BigDecimal) tablaP.getValueAt(0, 2);
			tPrecio.setText((p+""));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Producto no existe");
			tNombre.setText("");
			tPrecio.setText("");
		}
		
	}
	
	
	
	public BajasV(){
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(683, 538);
		setTitle("Realizar Devolucion");
		setBackground(new Color(255,255,255));
		
		titulo.setBounds(140, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		lImg.setBounds(430, 15, 50, 50);
		lImg.setIcon(new ImageIcon(icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		add(lImg);
		
		lId.setBounds(100, 100, 50, 20);
		add(lId);
		lIdP.setBounds(100, 150, 100, 20);
		add(lIdP);
		lNombre.setBounds(100, 200, 50, 20);
		add(lNombre);
		lPrecio.setBounds(100, 250, 50, 20);
		add(lPrecio);
		lFecha.setBounds(100, 310, 50, 20);
		add(lFecha);
		dia.setBounds(160, 290, 50, 20);
		add(dia);
		mes.setBounds(230, 290, 50, 20);
		add(mes);
		anio.setBounds(320, 290, 50, 20);
		add(anio);
		
		
		tId.setBounds(125, 100, 300, 20);
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
		tIdP.setBounds(180, 150, 245, 20);
		tIdP.setBackground(new Color(230,230,230));
		
		add(tIdP);
		tNombre.setBounds(160, 200, 265, 20);
		tNombre.setBackground(new Color(230,230,230));
		add(tNombre);
		tPrecio.setBounds(150, 250, 275, 20);
		tPrecio.setBackground(new Color(230,230,230));
		add(tPrecio);
		cD.setBounds(150, 310, 50, 20);
		for(int i = 1;i!=32;i=i+1) {
			if(i<10) {
				cD.addItem("0"+i);
			}else {
				cD.addItem(""+i);
			}
			
		}
		add(cD);
		
		
		
		cM.setBounds(200, 310, 100, 20);
		cM.addItem("ENERO");
		cM.addItem("FEBRERO");
		cM.addItem("MARZO");
		cM.addItem("ABRIL");
		cM.addItem("MAYO");
		cM.addItem("JUNIO");
		cM.addItem("JULIO");
		cM.addItem("AGOSTO");
		cM.addItem("SEPTIEMBRE");
		cM.addItem("OCTUBRE");
		cM.addItem("NOVIEMBRE");
		cM.addItem("DICIEMBRE");
		add(cM);
		cA.setBounds(300, 310, 80, 20);
		for(int i = 2021;i!=2051;i=i+1) {
			cA.addItem(""+i);
		}
		add(cA);
	
	
		
		
		bEliminar.setBounds(550, 90, 100, 30);
		bEliminar.setBackground(new Color(255,10,10));
		bEliminar.addActionListener(this);
		add(bEliminar);
		
		bBorrar.setBounds(550, 150, 100, 30);
		bBorrar.setBackground(new Color(0,170,255));
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bCancelar.setBounds(550, 210, 100, 30);
		bCancelar.setBackground(new Color(255,200,0));
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		bBuscar.setBounds(450, 95, 50, 30);
		bBuscar.setIcon(iconoBuscar);
		bBuscar.addActionListener(this);
		add(bBuscar);
		
		tIdP.setEnabled(false);
		tNombre.setEnabled(false);
		tPrecio.setEnabled(false);
		cD.setEnabled(false);
		cM.setEnabled(false);
		cA.setEnabled(false);
		
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/compu1xd1";
		String Consulta = "SELECT * FROM ventas";
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
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {@Override public void mouseClicked(java.awt.event.MouseEvent evt) { obtenerRegistroTabla();}         });
		escroll.setBounds(40, 350, 600, 130);
		add(escroll);
		
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
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			String sql = "SELECT * FROM ventas ";
			boolean primero=true;
				if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
				primero=false;
				sql+=("idVenta = '"+tId.getText()+"'");
				atuaclizaTabla(sql);
		}else if(e.getSource()==bBorrar) {
			tId.setText("");
			tIdP.setText("");
			tNombre.setText("");
			tPrecio.setText("");
		}else if(e.getSource()==bCancelar) {
			setVisible(false);
		}else if(e.getSource()==bEliminar) {
			String i  = (tId.getText());
			VentasDAO vDAO = new VentasDAO();
			if(vDAO.eliminarRegistro(i)) {
			}else {
				JOptionPane.showMessageDialog(null,"El registro no existe y no puede ser eliminado");
			}
			tId.setText("");
			tIdP.setText("");
			tNombre.setText("");
			tPrecio.setText("");
			atuaclizaTabla(tabla);
		}
		
	}
	
}

class CambiosV extends JInternalFrame implements ActionListener{
	JLabel titulo = new JLabel("Modificar Venta");
	ImageIcon icono = new ImageIcon("./recursos/C1V.png");
	ImageIcon iconoBuscar = new ImageIcon("./recursos/buscar-barras.png");
	JLabel lImg = new JLabel();
	JLabel lId = new JLabel("Id:");
	JLabel lIdP = new JLabel("Id Producto");
	JLabel lNombre = new JLabel("Nombre:");
	JLabel lPrecio = new JLabel("Precio:");
	JLabel lFecha = new JLabel("Fecha:");
	JTextField tId = new JTextField();
	JTextField tIdP = new JTextField();
	JTextField tNombre = new JTextField();
	JTextField tPrecio = new JTextField();
	JComboBox cD = new JComboBox();
	JComboBox cM = new JComboBox();
	JComboBox cA = new JComboBox();
	JLabel dia = new JLabel("Dia");
	JLabel mes = new JLabel("Mes");
	JLabel anio = new JLabel("Año");
	JButton bCambiar = new JButton("Guardar Cambios");
	JButton bBorrar = new JButton("Limpiar");
	JButton bCancelar = new JButton("Cancelar");
	JButton bBuscar = new JButton();
	JButton bBuscarP = new JButton();
	JTable tabla = new JTable();
	JTable tablaP = new JTable();
	JButton bHoy = new JButton("Hoy");
	java.util.Date fecha = new Date();
	
	public void obtenerRegistroTabla() {
		int i = (int)tabla.getValueAt(tabla.getSelectedRow(), 0);
		tId.setText(i+""); 
		int iP = (int)tabla.getValueAt(tabla.getSelectedRow(), 1);
		tIdP.setText(iP+"");
		tNombre.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 2));
		BigDecimal p = (BigDecimal) tabla.getValueAt(tabla.getSelectedRow(), 3);
		tPrecio.setText((p+""));
		Object fecha =  tabla.getValueAt(tabla.getSelectedRow(), 4);
		String x = String.valueOf(fecha);
		String [] parteFecha = x.split("-");
		String d = parteFecha[2];
		String m = parteFecha[1];
		String a = parteFecha[0];
		cD.setSelectedItem(d);
		cA.setSelectedItem(a);
		if(m.equals("01")) {
			cM.setSelectedIndex(0);
		}else if(m.equals("02")){
			cM.setSelectedIndex(1);
		}else if(m.equals("03")){
			cM.setSelectedIndex(2);
		}else if(m.equals("04")){
			cM.setSelectedIndex(3);
		}else if(m.equals("05")){
			cM.setSelectedIndex(4);
		}else if(m.equals("06")){
			cM.setSelectedIndex(5);
		}else if(m.equals("07")){
			cM.setSelectedIndex(6);
		}else if(m.equals("08")){
			cM.setSelectedIndex(7);
		}else if(m.equals("09")){
			cM.setSelectedIndex(8);
		}else if(m.equals("10")){
			cM.setSelectedIndex(9);
		}else if(m.equals("11")){
			cM.setSelectedIndex(10);
		}else if(m.equals("12")){
			cM.setSelectedIndex(11);
		}
		
		
		
	}
	public void atuaclizaTabla(JTable tabla) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			String Consulta = "SELECT * FROM ventas";		
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
	
	public void obtenerRegistro() {
		try {
			tNombre.setText((String) tablaP.getValueAt(0, 1));
			BigDecimal p = (BigDecimal) tablaP.getValueAt(0, 2);
			tPrecio.setText((p+""));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Producto no existe");
			tNombre.setText("");
			tPrecio.setText("");
		}
		
	}
	
	
	
	public CambiosV(){
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(683, 538);
		setTitle("Realizar Devolucion");
		setBackground(new Color(255,255,255));
		
		titulo.setBounds(140, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		lImg.setBounds(430, 15, 50, 50);
		lImg.setIcon(new ImageIcon(icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		add(lImg);
		
		lId.setBounds(100, 100, 50, 20);
		add(lId);
		lIdP.setBounds(100, 150, 100, 20);
		add(lIdP);
		lNombre.setBounds(100, 200, 50, 20);
		add(lNombre);
		lPrecio.setBounds(100, 250, 50, 20);
		add(lPrecio);
		lFecha.setBounds(100, 310, 50, 20);
		add(lFecha);
		dia.setBounds(160, 290, 50, 20);
		add(dia);
		mes.setBounds(230, 290, 50, 20);
		add(mes);
		anio.setBounds(320, 290, 50, 20);
		add(anio);
		
		
		tId.setBounds(125, 100, 300, 20);
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
		tIdP.setBounds(180, 150, 245, 20);
		tIdP.setBackground(new Color(230,230,230));
		
		add(tIdP);
		tNombre.setBounds(160, 200, 265, 20);
		tNombre.setBackground(new Color(230,230,230));
		add(tNombre);
		tPrecio.setBounds(150, 250, 275, 20);
		tPrecio.setBackground(new Color(230,230,230));
		add(tPrecio);
		cD.setBounds(150, 310, 50, 20);
		for(int i = 1;i!=32;i=i+1) {
			if(i<10) {
				cD.addItem("0"+i);
			}else {
				cD.addItem(""+i);
			}
			
		}
		add(cD);
		
		
		
		cM.setBounds(200, 310, 100, 20);
		cM.addItem("ENERO");
		cM.addItem("FEBRERO");
		cM.addItem("MARZO");
		cM.addItem("ABRIL");
		cM.addItem("MAYO");
		cM.addItem("JUNIO");
		cM.addItem("JULIO");
		cM.addItem("AGOSTO");
		cM.addItem("SEPTIEMBRE");
		cM.addItem("OCTUBRE");
		cM.addItem("NOVIEMBRE");
		cM.addItem("DICIEMBRE");
		add(cM);
		cA.setBounds(300, 310, 80, 20);
		for(int i = 2021;i!=2051;i=i+1) {
			cA.addItem(""+i);
		}
		add(cA);
	
	
		
		
		bBorrar.setBounds(550, 95, 100, 30);
		bBorrar.setBackground(new Color(0,170,255));
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bCambiar.setBounds(520, 150, 135, 30);
		bCambiar.setBackground(new Color(255,175,175));
		bCambiar.addActionListener(this);
		add(bCambiar);
		
		bCancelar.setBounds(500, 210, 100, 30);
		bCancelar.setBackground(new Color(255,200,0));
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		bBuscar.setBounds(440, 95, 50, 30);
		bBuscar.setIcon(iconoBuscar);
		bBuscar.addActionListener(this);
		add(bBuscar);
		
		bBuscarP.setBounds(440, 145, 50, 30);
		bBuscarP.setIcon(iconoBuscar);
		bBuscarP.addActionListener(this);
		add(bBuscarP);
		
	
		
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/compu1xd1";
		String Consulta = "SELECT * FROM ventas";
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
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {@Override public void mouseClicked(java.awt.event.MouseEvent evt) { obtenerRegistroTabla();}         });
		escroll.setBounds(40, 350, 600, 130);
		add(escroll);
		
		tNombre.setEnabled(false);
		tPrecio.setEnabled(false);
		System.out.println("xd");
		
		
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
	public void atuaclizaTablaP(String sql) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/compu1xd1";
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, sql);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tablaP.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
	}
	
	public void obtenerRegistro1() {
		try {
			tNombre.setText((String) tablaP.getValueAt(0, 1));
			BigDecimal p = (BigDecimal) tablaP.getValueAt(0, 2);
			tPrecio.setText((p+""));
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Producto no existe");
			tNombre.setText("");
			tPrecio.setText("");
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			String sql = "SELECT * FROM ventas ";
			boolean primero=true;
				if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
				primero=false;
				sql+=("idVenta = '"+tId.getText()+"'");
				atuaclizaTabla(sql);
		}else if(e.getSource()==bBorrar) {
			tId.setText("");
			tIdP.setText("");
			tNombre.setText("");
			tPrecio.setText("");
		}else if(e.getSource()==bCancelar) {
			setVisible(false);
		}else if(e.getSource()==bBuscarP) {
			String s = "SELECT * FROM productos WHERE id = '"+tIdP.getText()+"'";
			atuaclizaTablaP(s);
			obtenerRegistro1();
			
		}else if(e.getSource()==bCambiar) {
			String s = "SELECT * FROM productos WHERE id = '"+tIdP.getText()+"'";
			atuaclizaTablaP(s);
			obtenerRegistro1();
			if(tNombre.getText().equals("")||tPrecio.getText().equals("")||tIdP.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"No debe Haber campos vacios");
			}else {
				String id = tId.getText();
				String idP= tIdP.getText();
				String nombre = tNombre.getText();
				String precio = tPrecio.getText();
				String dia = (String) cD.getSelectedItem();
				String mes = "";
				if (cM.getSelectedIndex()==0) {
					mes = "01";
				}else if(cM.getSelectedIndex()==1) {
					mes = "02";
				}else if(cM.getSelectedIndex()==2) {
					mes = "03";
				}else if(cM.getSelectedIndex()==3) {
					mes = "04";
				}else if(cM.getSelectedIndex()==4) {
					mes = "05";
				}else if(cM.getSelectedIndex()==5) {
					mes = "06";
				}else if(cM.getSelectedIndex()==6) {
					mes = "07";
				}else if(cM.getSelectedIndex()==7) {
					mes = "08";
				}else if(cM.getSelectedIndex()==8) {
					mes = "09";
				}else if(cM.getSelectedIndex()==9) {
					mes = "10";
				}else if(cM.getSelectedIndex()==10) {
					mes = "11";
				}else if(cM.getSelectedIndex()==11) {
					mes = "12";
				}
				String año = (String) cA.getSelectedItem();
				String fecha = año+"-"+mes+"-"+dia;
				int iid = Integer.parseInt(id);
				int iidp = Integer.parseInt(idP);
				double dp = Double.parseDouble(precio);
				System.out.println(iid+" "+iidp+" "+nombre+" "+dp+" "+fecha);
				Venta v = new Venta(iid,iidp, nombre,dp, fecha);
				VentasDAO vDAO = new VentasDAO();
				if(vDAO.modificarRegistro(v)) {
				}else {
					JOptionPane.showMessageDialog(null, "Nombre no");
				}
				atuaclizaTabla(tabla);
			}
			
			
		}
		
	}
	
}



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
			if(pDAO.eliminarRegistro(i)) {
						}else {
				JOptionPane.showMessageDialog(null,"El registro no existe y no puede ser eliminado");
			}
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
				addVenta.addActionListener(this);
				deleteVenta.addActionListener(this);
				updateVenta.addActionListener(this);
				searchVenta.addActionListener(this);
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
		}else if(e.getSource()==addVenta) {
			AltasV av = new AltasV();
			dp.add(av);
			av.setVisible(true);
			add(dp);
		}else if(e.getSource()==deleteVenta) {
			BajasV bv = new BajasV();
			dp.add(bv);
			bv.setVisible(true);
			add(dp);
		}else if(e.getSource()==updateVenta) {
			CambiosV cv = new CambiosV();
			dp.add(cv);
			cv.setVisible(true);
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

