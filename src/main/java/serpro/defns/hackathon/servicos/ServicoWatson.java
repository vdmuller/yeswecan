package serpro.defns.hackathon.servicos;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

public class ServicoWatson {

	private static final String MODEL_ID = "20:f678f91e-5fde-4a19-b6e7-98aff4414bf2";
	private static final String USUARIO = "d232f093-85ce-48a8-8d16-889f5c24c8dc";
	private static final String SENHA = "3J8TCYLaIKlo";
	private static final String LINGUAGEM = "pt";

	public AnalysisResults processar(String texto) {
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
		        NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, USUARIO,
		        SENHA);

		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
		        .emotion(true).sentiment(true).model(MODEL_ID).build();

		Features features = new Features.Builder().entities(entitiesOptions)
		        .build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(texto)
		        .features(features).language(LINGUAGEM).build();

		return service.analyze(parameters).execute();
	}

}
