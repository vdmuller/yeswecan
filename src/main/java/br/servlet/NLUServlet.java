package br.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.rits.cloning.Cloner;

import br.dto.ContagemPF;
import br.util.OrdemPorTipo;


@WebServlet("/nlu")
public class NLUServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
    private static final String CRUD = "CRUD";
	private static final String EE = "EE";
	private static final String CE = "CE";
	private static final String SE = "SE";
	private static final String ALI = "ALI";
	private static final String AIE = "AIE";

	private static final String USER = "d232f093-85ce-48a8-8d16-889f5c24c8dc";
	private static final String PASS = "3J8TCYLaIKlo";
	private static final String LANGUAGE = "pt";


    
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("nlu.jsp").forward(req, resp);
	}

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	consultarNLU(req);
    	
    	req.getRequestDispatcher("nlu.jsp").forward(req, resp);
    }

	private void consultarNLU(HttpServletRequest request) {
		String modelId = request.getParameter("modelId");
		String text = request.getParameter("texto");
		
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				USER, PASS);

		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
				.emotion(true)
				.sentiment(true)
				.model(modelId)
				.build();
		
		Features features = new Features.Builder()
				.entities(entitiesOptions)
				.build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(text)
				.features(features)
				.language(LANGUAGE)
				.build();

		AnalysisResults response = service
				.analyze(parameters)
				.execute();
		
		Cloner cloner = new Cloner();
		AnalysisResults cloneResponse = cloner.deepClone(response);
		
		List<EntitiesResult> entities = response.getEntities();
		
		transformarCRUD(entities);
		
		ContagemPF contagemPF = contarPF(entities);
		
		entities.sort(new OrdemPorTipo());
		
		request.setAttribute("responseOriginal", cloneResponse);
		request.setAttribute("response", response);
		request.setAttribute("contagemPF", contagemPF);
	}

	private ContagemPF contarPF(List<EntitiesResult> entities) {
		ContagemPF contagemPF = new ContagemPF();
		
		for (EntitiesResult item : entities) {
			switch (item.getType()) {
			case CE:
				contagemPF.contarCE();
				break;
			case EE:
				contagemPF.contarEE();
				break;
			case SE:
				contagemPF.contarSE();
				break;
			case ALI:
				contagemPF.contarALI();
				break;
			case AIE:
				contagemPF.contarAIE();
				break;
			default:
				contagemPF.contarVazio();
			}
		}
		
		return contagemPF;
		
	}

	private void transformarCRUD(List<EntitiesResult> entities) {
		/*List<EntitiesResult> itensCRUD = entities.stream()            
                .filter(line -> line.getType().equals(CRUD)) 
                .collect(Collectors.toList()); 
		
		entities.removeAll(itensCRUD);	
		
		List<EntitiesResult> novosItensParaCRUD = gerarFuncoesParaCRUD(itensCRUD);
		entities.addAll(novosItensParaCRUD);*/
	}	

	private List<EntitiesResult> gerarFuncoesParaCRUD(List<EntitiesResult> itensCRUD) {
		List<EntitiesResult> novosItensParaCRUD = new ArrayList<>();
		for (EntitiesResult item : itensCRUD ) {
			novosItensParaCRUD.add(gerarItem(EE, "Incluir "+item.getText()));
			novosItensParaCRUD.add(gerarItem(EE, "Alterar "+item.getText()));
			novosItensParaCRUD.add(gerarItem(EE, "Excluir "+item.getText()));
			novosItensParaCRUD.add(gerarItem(CE, "Consultar Lista "+item.getText()));
			novosItensParaCRUD.add(gerarItem(CE, "Consultar Detalhes "+item.getText()));
		}
		return novosItensParaCRUD;
	}

	private EntitiesResult gerarItem(String tipo, String texto) {
		EntitiesResult item = new EntitiesResult();
		item.setType(tipo);
		item.setText(texto);
		item.setCount(1);
		return item;
	}

}
