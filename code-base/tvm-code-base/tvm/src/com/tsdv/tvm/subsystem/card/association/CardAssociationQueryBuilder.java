package com.tsdv.tvm.subsystem.card.association;

import java.util.HashMap;
import java.util.Map;

import com.card.association.CardAssociation;

class CardAssociationQueryBuilder {
	private final Map<String, Object> parameters;

	public CardAssociationQueryBuilder() {
		this.parameters = new HashMap<>();
	}

	public void addParameter(String key, Object value) {
		parameters.put(key, value);
	}

	public String create() {
		StringBuilder queryURL;
		queryURL = new StringBuilder(CardAssociation.CARD_ASSOCIATION_LINK + '?');
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			queryURL.append(entry.getKey().replace(" ", "%20"));
			queryURL.append("=");
			queryURL.append(entry.getValue().toString().replace(" ", "%20"));
			queryURL.append("&");
		}
		return queryURL.toString();
	}
}
