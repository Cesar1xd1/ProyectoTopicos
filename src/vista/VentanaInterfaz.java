package vista;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controlador.ProductosDAO;
import modelo.Productos;

class AltasP extends JInternalFrame implements ActionListener{
	public AltasP() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(600, 480);
		setTitle("Agregar Producto");
		
		JLabel titulo = new JLabel("Añadir Producto");
		titulo.setBounds(200, 30, 300,20 );
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		add(titulo);
		
		JLabel lId = new JLabel("Id:");
		lId.setBounds(80,100,50,20);
		add(lId);
		
		JLabel lNombre = new JLabel("Nombre:");
		lNombre.setBounds(80,150,50,20);
		add(lNombre);
		
		JLabel lPrecio = new JLabel("Precio:");
		lPrecio.setBounds(80,200,50,20);
		add(lPrecio);
		
		JTextField tId = new JTextField();
		tId.setBounds(105,100,205,20);
		add(tId);
		
		JTextField tNombre = new JTextField();
		tNombre.setBounds(140,150,170,20);
		add(tNombre);
		
		JTextField tPrecio = new JTextField();
		tPrecio.setBounds(130,200,180,20);
		add(tPrecio);
		
		JButton bAgregar = new JButton("Agregar");
		bAgregar.setBounds(400, 90, 80, 20);
		bAgregar.addActionListener(this);
		add(bAgregar);
		
		JButton bBorrar = new JButton("Limpiar");
		bBorrar.setBounds(400, 150, 80, 20);
		add(bBorrar);
		
		JButton bCancelar = new JButton("Limpiar");
		bCancelar.setBounds(400, 210, 80, 20);
		add(bCancelar);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
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

