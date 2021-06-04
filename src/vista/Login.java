package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controlador.UsuarioDAO;


public class Login extends JFrame {
	JTextField user = new JTextField();
	JPasswordField pass = new JPasswordField();
	JButton acceder = new JButton();
	public static boolean indicador;
	public Login() {
		getContentPane().setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(255, 175, 175));
        setTitle("Login");
        
        JLabel lb2=new JLabel("USUARIO");
        lb2.setBounds(110, 50, 100, 25);
        add(lb2);

        JLabel lb3=new JLabel("CONTRASEÑA");
        lb3.setBounds(110, 100, 100, 25);
        add(lb3);
  
        user.setBounds(70, 70, 150, 20);
        add(user);

        pass.setBounds(70, 120, 150, 20);
        add(pass);
        
        acceder.setBounds(70, 180, 150, 30);
        acceder.setText("ACCEDER");
        add(acceder);
        acceder.addActionListener(new ActionListener() {
			UsuarioDAO uDAO = new UsuarioDAO();
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilito = new Thread(uDAO);
				uDAO.fil = user.getText();
				hilito.start();
				String[] pat = uDAO.buscar(user.getText());
				if(indicador==false && pat.length>0) {
					if((pat[0].equals(user.getText())&&pat[1].equals(pass.getText()))) {
						new VentanaPrincipal();
					}
				}else {
					
				}
				
			}
		});
	}
}
