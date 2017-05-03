package serpro.defns.hackathon.enums;

public enum TipoFuncaoEnum {

	ALI, AIE, EE, SE, CE, CRUD;

	public static TipoFuncaoEnum fromString(String tipo) {
		switch (tipo) {
			case "CE":
				return CE;
			case "EE":
				return EE;
			case "SE":
				return SE;
			case "ALI":
				return ALI;
			case "AIE":
				return AIE;
			default:
				return null;
		}
	}

}
