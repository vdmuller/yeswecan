package serpro.defns.hackathon.servicos;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import serpro.defns.hackathon.enums.ComplexidadeEnum;
import serpro.defns.hackathon.enums.TipoFuncaoEnum;
import serpro.defns.hackathon.vo.EntidadeVO;

public class ContagemPFBO {

	private Map<java.util.Map.Entry<TipoFuncaoEnum, ComplexidadeEnum>, Integer> mapaPontuacao = new HashMap<>();

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

	public ContagemPFBO() {
		adicionarValorMapa(TipoFuncaoEnum.ALI, ComplexidadeEnum.BAIXA, 7);
		adicionarValorMapa(TipoFuncaoEnum.ALI, ComplexidadeEnum.MEDIA, 10);
		adicionarValorMapa(TipoFuncaoEnum.ALI, ComplexidadeEnum.ALTA, 15);

		adicionarValorMapa(TipoFuncaoEnum.AIE, ComplexidadeEnum.BAIXA, 5);
		adicionarValorMapa(TipoFuncaoEnum.AIE, ComplexidadeEnum.MEDIA, 7);
		adicionarValorMapa(TipoFuncaoEnum.AIE, ComplexidadeEnum.ALTA, 10);

		adicionarValorMapa(TipoFuncaoEnum.EE, ComplexidadeEnum.BAIXA, 3);
		adicionarValorMapa(TipoFuncaoEnum.EE, ComplexidadeEnum.MEDIA, 4);
		adicionarValorMapa(TipoFuncaoEnum.EE, ComplexidadeEnum.ALTA, 5);

		adicionarValorMapa(TipoFuncaoEnum.SE, ComplexidadeEnum.BAIXA, 4);
		adicionarValorMapa(TipoFuncaoEnum.SE, ComplexidadeEnum.MEDIA, 5);
		adicionarValorMapa(TipoFuncaoEnum.SE, ComplexidadeEnum.ALTA, 7);

		adicionarValorMapa(TipoFuncaoEnum.CE, ComplexidadeEnum.BAIXA, 3);
		adicionarValorMapa(TipoFuncaoEnum.CE, ComplexidadeEnum.MEDIA, 4);
		adicionarValorMapa(TipoFuncaoEnum.CE, ComplexidadeEnum.ALTA, 6);
	}

	private void adicionarValorMapa(TipoFuncaoEnum tipo,
	        ComplexidadeEnum complexidade, Integer valor) {
		java.util.Map.Entry<TipoFuncaoEnum, ComplexidadeEnum> entrada = new AbstractMap.SimpleEntry(
		        tipo, complexidade);
		mapaPontuacao.put(entrada, valor);
	}

	private Integer obterValorMapa(TipoFuncaoEnum tipo,
	        ComplexidadeEnum complexidade) {
		java.util.Map.Entry<TipoFuncaoEnum, ComplexidadeEnum> entrada = new AbstractMap.SimpleEntry(
		        tipo, complexidade);
		return mapaPontuacao.get(entrada);
	}

	public int contarPF(EntidadeVO entidade) {
		return obterValorMapa(entidade.getTipo(), entidade.getComplexidade());
	}

}
