package serpro.defns.hackathon.web;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
public class CalculadorPFMB implements Serializable {

	private static final long serialVersionUID = 1L;

	// @Inject
	// private CalculadorPFBO calculadorBO;

	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
