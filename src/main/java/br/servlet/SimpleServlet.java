package br.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import br.OrdemPorTipo;


@WebServlet("/nlu")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    
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
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String modelId = request.getParameter("modelId");
		String language = request.getParameter("language");
		String text = request.getParameter("texto");
		
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				user, pass);

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
				.language(language)
				.build();

		AnalysisResults response = service
				.analyze(parameters)
				.execute();
		System.out.println(response);
		
		response.getEntities().sort(new OrdemPorTipo());
		
		request.setAttribute("response", response);
		//request.setAttribute("linhas", entities);
	}

}
