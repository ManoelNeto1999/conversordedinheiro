package entities;

public class Euro {
	private Double euro;
	
	public Euro() {
	}
	
	public Euro(Double euro) {
		this.euro = euro;
	}
	
	public Double getEuro() {
		return euro;
	}
	
	public void setEuro(Double euro) {
		this.euro = euro;
	}
	
	public Double convertionReal() {
		return euro * 5.30;
	}
	
	public Double convertionDollar() {
		return euro * 1.06;
	}
	
	public Double convertionLibra() {
		return euro * 32.66;
	}
	
	public Double convertionPeso() {
		return euro * 369.99;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f", convertionReal());
	}
	
	public String toString1() {
		return String.format("%.2f", convertionDollar());
	}
	
	public String toString2() {
		return String.format("%.2f", convertionLibra());
	}
	
	public String toString3() {
		return String.format("%.2f", convertionPeso());
	}
}
