package entities;

public class Libra {
	private Double libra;
	
	public Libra() {
	}
	
	public Libra(Double libra) {
		this.libra = libra;
	}
	
	public Double getLibra() {
		return libra;
	}
	
	public void setLibra(Double libra) {
		this.libra = libra;
	}
	
	public Double convertionReal() {
		return libra * 0.16;
	}
	
	public Double convertionDollar() {
		return libra * 0.03;
	}
	
	public Double convertionEuro() {
		return libra * 0.03;
	}
	
	public Double convertionPeso() {
		return libra * 11.33;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f", convertionReal());
	}
	
	public String toString1() {
		return String.format("%.2f", convertionDollar());
	}
	
	public String toString2() {
		return String.format("%.2f", convertionEuro());
	}
	
	public String toString3() {
		return String.format("%.2f", convertionPeso());
	}
}
