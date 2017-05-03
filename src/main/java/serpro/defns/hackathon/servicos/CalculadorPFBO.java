package serpro.defns.hackathon.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.rits.cloning.Cloner;

import serpro.defns.hackathon.enums.ComplexidadeEnum;
import serpro.defns.hackathon.enums.TipoFuncaoEnum;
import serpro.defns.hackathon.vo.EntidadeVO;

public class CalculadorPFBO {

	private final int VALOR_EE = 4;
	private final int VALOR_SE = 5;
	private final int VALOR_CE = 4;
	private final int VALOR_ALI = 10;
	private final int VALOR_AIE = 7;

	private ServicoWatson servicoWatson = new ServicoWatson();
	private ContagemPFBO contagemBO = new ContagemPFBO();

	public List<EntidadeVO> processarTexto(String texto) {
		AnalysisResults response = servicoWatson.processar(texto);

		Cloner cloner = new Cloner();
		AnalysisResults cloneResponse = cloner.deepClone(response);

		List<EntitiesResult> entities = response.getEntities();

		List<EntidadeVO> entidadesCRUD = transformarCRUD(entities);

		List<EntidadeVO> entidades = new ArrayList<>();
		for (EntitiesResult entidade : entities) {
			EntidadeVO entidadeVO = new EntidadeVO(entidade.getText(),
			        TipoFuncaoEnum.fromString(entidade.getType()));
			entidadeVO.setComplexidade(ComplexidadeEnum.MEDIA);

			calcularPF(entidadeVO);
			entidades.add(entidadeVO);
		}

		entidades.addAll(entidadesCRUD);

		return entidades;

	}

	public void calcularPF(EntidadeVO entidade) {
		int pontos = contagemBO.contarPF(entidade);
		entidade.setPontos(pontos);
	}

	private List<EntidadeVO> transformarCRUD(List<EntitiesResult> entities) {
		List<EntitiesResult> itensCRUD = entities.stream()
		        .filter(line -> line.getType().equals("CRUD"))
		        .collect(Collectors.toList());

		entities.removeAll(itensCRUD);

		return gerarFuncoesParaCRUD(itensCRUD);
	}

	private List<EntidadeVO> gerarFuncoesParaCRUD(
	        List<EntitiesResult> itensCRUD) {
		List<EntidadeVO> novosItensParaCRUD = new ArrayList<>();
		for (EntitiesResult item : itensCRUD) {
			novosItensParaCRUD.add(gerarItem(TipoFuncaoEnum.EE,
			        "Incluir " + item.getText(), ComplexidadeEnum.MEDIA));
			novosItensParaCRUD.add(gerarItem(TipoFuncaoEnum.EE,
			        "Alterar " + item.getText(), ComplexidadeEnum.MEDIA));
			novosItensParaCRUD.add(gerarItem(TipoFuncaoEnum.EE,
			        "Excluir " + item.getText(), ComplexidadeEnum.BAIXA));
			novosItensParaCRUD.add(gerarItem(TipoFuncaoEnum.CE,
			        "Consultar Lista " + item.getText(),
			        ComplexidadeEnum.MEDIA));
			novosItensParaCRUD.add(gerarItem(TipoFuncaoEnum.CE,
			        "Consultar Detalhes " + item.getText(),
			        ComplexidadeEnum.BAIXA));
		}
		return novosItensParaCRUD;
	}

	private EntidadeVO gerarItem(TipoFuncaoEnum tipo, String nome,
	        ComplexidadeEnum complexidade) {
		EntidadeVO item = new EntidadeVO(nome, tipo);
		item.setComplexidade(complexidade);
		calcularPF(item);
		return item;
	}

}