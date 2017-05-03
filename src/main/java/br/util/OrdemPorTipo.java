package br.util;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;

public class OrdemPorTipo implements Comparator<EntitiesResult> {

	public int compare(EntitiesResult o1, EntitiesResult o2) {
		return new CompareToBuilder()
				.append(o1.getType(), o2.getType())
				.append(o1.getText(), o2.getText())
				.toComparison();
	}

}
