package entities;

public class Dollar {
	private Double dollar;
	
	public Dollar() {
	}
	
	public Dollar(Double dollar) {
		this.dollar = dollar;
	}
	
	public Double getDollar() {
		return dollar;
	}
	
	public void setDollar(Double dollar) {
		this.dollar = dollar;
	}
	
	public Double convertionReal() {
		return dollar * 5.03;
	}
	
	public Double convertionEuro() {
		return dollar * 0.95;
	}
	
	public Double convertionLibra() {
		return dollar * 30.90;
	}
	
	public Double convertionPeso() {
		return dollar * 350.02;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f", convertionReal());
	}
	
	public String toString1() {
		return String.format("%.2f", convertionEuro());
	}
	
	public String toString2() {
		return String.format("%.2f", convertionLibra());
	}
	
	public String toString3() {
		return String.format("%.2f", convertionPeso());
	}
}
