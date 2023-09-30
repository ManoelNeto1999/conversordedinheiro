package entities;

public class Real {
	private Double real;

	public Real() {
	}
	
	public Real(Double real) {
		this.real = real;
	}

	public Double getReal() {
		return real;
	}

	public void setReal(Double real) {
		this.real = real;
	}
	
	public Double convertionDollar() {
		return real * 0.20;
	}
	
	public Double convertionEuro() {
		return real * 0.19;
	}
	
	public Double convertionLibra() {
		return real * 6.24;
	}
	
	public Double convertionPeso() {
		return real * 70.90;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f", convertionDollar());
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