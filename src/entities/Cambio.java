package entities;

public class Cambio {
	private Double real;

	public Cambio() {
	}
	
	public Cambio(Double real) {
		this.real = real;
	}

	public Double getReal() {
		return real;
	}

	public void setReal(Double real) {
		this.real = real;
	}
	
	public Double convertionDollar() {
		return real * 0.2031;
	}

	@Override
	public String toString() {
		return String.format("%.2f", convertionDollar());
	}
}