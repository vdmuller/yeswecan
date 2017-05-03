package serpro.defns.hackathon.vo;

import serpro.defns.hackathon.enums.ComplexidadeEnum;
import serpro.defns.hackathon.enums.TipoFuncaoEnum;

public class EntidadeVO {

	private String nome;
	private TipoFuncaoEnum tipo;
	private ComplexidadeEnum complexidade;
	private String referencia;
	private int pontos;

	public EntidadeVO(String nome, TipoFuncaoEnum tipo) {
		this.nome = nome;
		this.tipo = tipo;
		complexidade = ComplexidadeEnum.MEDIA;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoFuncaoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoFuncaoEnum tipo) {
		this.tipo = tipo;
	}

	public ComplexidadeEnum getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(ComplexidadeEnum complexidade) {
		this.complexidade = complexidade;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
