package br.dto;

public class ContagemPF {

	private int contadorCE = 0;
	private int contadorEE = 0;
	private int contadorSE = 0;
	private int contadorALI = 0;
	private int contadorAIE = 0;
	private int contadorVazio = 0;
	
	public ContagemPF() {
		contadorCE = 0;
		contadorEE = 0;
		contadorSE = 0;
		contadorALI = 0;
		contadorAIE = 0;
		contadorVazio = 0;
	}

	public int getContadorCE() {
		return contadorCE;
	}

	public void setContadorCE(int contadorCE) {
		this.contadorCE = contadorCE;
	}

	public int getContadorEE() {
		return contadorEE;
	}

	public void setContadorEE(int contadorEE) {
		this.contadorEE = contadorEE;
	}

	public int getContadorSE() {
		return contadorSE;
	}

	public void setContadorSE(int contadorSE) {
		this.contadorSE = contadorSE;
	}

	public int getContadorALI() {
		return contadorALI;
	}

	public void setContadorALI(int contadorALI) {
		this.contadorALI = contadorALI;
	}

	public int getContadorAIE() {
		return contadorAIE;
	}

	public void setContadorAIE(int contadorAIE) {
		this.contadorAIE = contadorAIE;
	}

	public int getContadorVazio() {
		return contadorVazio;
	}

	public void setContadorVazio(int contadorVazio) {
		this.contadorVazio = contadorVazio;
	}

	public void contarCE() {
		contadorCE++;		
	}

	public void contarEE() {
		contadorEE++;
	}

	public void contarSE() {
		contadorSE++;
	}

	public void contarALI() {
		contadorALI++;
	}

	public void contarAIE() {
		contadorAIE++;
	}

	public void contarVazio() {
		contadorVazio++;
	}
	
	public int getTotalPF() {
		return contadorAIE+contadorALI+contadorCE+contadorEE+contadorSE;
	}
	
}
