package serpro.defns.hackathon.enums;

public enum ComplexidadeEnum {

	BAIXA("Baixa"), MEDIA("Média"), ALTA("Alta");

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	private ComplexidadeEnum(String descricao) {
		this.descricao = descricao;
	}

}
