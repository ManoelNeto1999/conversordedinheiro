package entities;

public class Peso {
	private Double peso;
	
	public Peso() {
	}
	
	public Peso(Double peso) {
		this.peso = peso;
	}
	
	public Double getPeso() {
		return peso;
	}
	
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public Double convertionReal() {
		return peso * 0.01;
	}
	
	public Double convertionDollar() {
		return peso * 0.0029;
	}
	
	public Double convertionEuro() {
		return peso * 0.0027;
	}
	
	public Double convertionLibra() {
		return peso * 0.08;
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
		return String.format("%.2f", convertionLibra());
	}
}
