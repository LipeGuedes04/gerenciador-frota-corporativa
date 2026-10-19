package entities;

public class Carro {

	private String modelo;
	private String placa;
	private double precoDiaria;
	private boolean alugado = false;

	public Carro() {

	}

	public Carro(String modelo, String placa, double precoDiaria) {
		this.modelo = modelo;
		this.placa = placa;
		this.precoDiaria = precoDiaria;

	}

	public String getModelo() {
		return modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public double getPrecoDiaria() {
		return precoDiaria;
	}

	public boolean isAlugado() {
		return alugado;
	}

	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}

	public boolean alugarCarro() {
		if (alugado == false) {
			alugado = true;
			return true;
		}
		return false;
	}

	public double calcularAluguel(int dias) {
		 return dias * precoDiaria; 
	}

	public String toString() {
		return "Modelo: " + modelo + " | Placa: " + placa + " | Diária: " + String.format("%.2f", precoDiaria)
				+ "| Status: " + (alugado == false ? "Disponível" : "Alugado");
	}

}
