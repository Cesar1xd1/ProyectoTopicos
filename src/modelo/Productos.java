package modelo;

public class Productos {
	private int id;
	private String nombre;
	private double precio;
	private String provedor;

	
	
	public Productos(int id, String nombre, double precio, String provedor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.provedor = provedor;
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getProvedor() {
		return provedor;
	}
	public void setProvedor(String provedor) {
		this.provedor = provedor;
	}
	
	
	
	
	
	
}
