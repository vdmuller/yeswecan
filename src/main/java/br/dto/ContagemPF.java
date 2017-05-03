package br.dto;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;

public class ContagemPF {

	private int contadorCE = 0;
	private int contadorEE = 0;
	private int contadorSE = 0;
	private int contadorALI = 0;
	private int contadorAIE = 0;
	private int contadorVazio = 0;
	
	private int VALOR_EE = 4;
	private int VALOR_SE = 5;
	private int VALOR_CE = 4;
	private int VALOR_ALI = 10;
	private int VALOR_AIE = 7;
	
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

	public void contarCE(EntitiesResult item) {
		Integer valorPF = VALOR_CE;
		if (item.getRelevance()!=null) {
			valorPF = item.getRelevance().intValue();
		}
		contadorCE += item.getCount() * valorPF;		
	}

	public void contarEE(EntitiesResult item) {
		Integer valorPF = VALOR_EE;
		if (item.getRelevance()!=null) {
			valorPF = item.getRelevance().intValue();
		}
		contadorEE += item.getCount() * valorPF;
	}

	public void contarSE(EntitiesResult item) {
		Integer valorPF = VALOR_SE;
		if (item.getRelevance()!=null) {
			valorPF = item.getRelevance().intValue();
		}
		contadorSE += item.getCount() * valorPF;
	}

	public void contarALI(EntitiesResult item) {
		Integer valorPF = VALOR_ALI;
		if (item.getRelevance()!=null) {
			valorPF = item.getRelevance().intValue();
		}
		contadorALI += item.getCount() * valorPF;
	}

	public void contarAIE(EntitiesResult item) {
		Integer valorPF = VALOR_AIE;
		if (item.getRelevance()!=null) {
			valorPF = item.getRelevance().intValue();
		}
		contadorAIE += item.getCount() * valorPF;
	}

	public void contarVazio() {
		contadorVazio++;
	}
	
	public int getTotalPF() {
		return contadorAIE+contadorALI+contadorCE+contadorEE+contadorSE;
	}
	
}
