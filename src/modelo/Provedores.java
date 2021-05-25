package modelo;

public class Provedores {
	private int idP;
	private String nombreP;
	private String contacto;


	
	
	public Provedores(int idP, String nombreP,String contacto) {
		super();
		this.idP = idP;
		this.nombreP = nombreP;
		this.contacto = contacto;
	
	}
	public int getIdP() {
		return idP;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	public String getNombreP() {
		return nombreP;
	}
	public void setNombreP(String nombreP) {
		this.nombreP = nombreP;
	}
	
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	
	
	
	
	
}
