package com.tsdv.tvm.test;

import java.text.ParseException;

import org.junit.Test;

import com.card.association.CardAssociation;

import junit.framework.TestCase;

public class CardAssociationTest extends TestCase {

	@Test
    public void testSuccessTransaction() throws ParseException {
        CardAssociation cardAssociation = CardAssociation.connect();
        
        if (cardAssociation == null)
        	return;

        StringBuilder queryBuilder = generateCardAssociationQueryString();
        addParameter(queryBuilder,"cardNumber", "0123456789");

        String transactionResult = cardAssociation.processTransaction(queryBuilder.toString());
        assertTrue(transactionResult.contains("code=0&"));
        assertTrue(transactionResult.contains("transactionId"));
    }

	@Test
    public void testE1Transaction() throws ParseException {
        CardAssociation cardAssociation = CardAssociation.connect();
        if (cardAssociation == null)
        	return;

        StringBuilder queryBuilder = generateCardAssociationQueryString();
        addParameter(queryBuilder,"cardNumber", "E1");

        String transactionResult = cardAssociation.processTransaction(queryBuilder.toString());
        assertTrue(transactionResult.contains("code=1&"));
        assertFalse(transactionResult.contains("transactionId"));
    }

	@Test
    public void testE2Transaction() throws ParseException {
        CardAssociation cardAssociation = CardAssociation.connect();
        if (cardAssociation == null)
        	return;

        StringBuilder queryBuilder = generateCardAssociationQueryString();
        addParameter(queryBuilder,"cardNumber", "E2");

        String transactionResult = cardAssociation.processTransaction(queryBuilder.toString());
        assertTrue(transactionResult.contains("code=2&"));
        assertFalse(transactionResult.contains("transactionId"));
    }

	@Test
    public void testE3Transaction() throws ParseException {
        CardAssociation cardAssociation = CardAssociation.connect();
        if (cardAssociation == null)
        	return;

        StringBuilder queryBuilder = generateCardAssociationQueryString();
        addParameter(queryBuilder,"cardNumber", "E3");

        String transactionResult = cardAssociation.processTransaction(queryBuilder.toString());
        assertTrue(transactionResult.contains("code=3&"));
        assertFalse(transactionResult.contains("transactionId"));
    }

	@Test
    public void testE4Transaction() throws ParseException {
        CardAssociation cardAssociation = CardAssociation.connect();
        if (cardAssociation == null)
        	return;

        StringBuilder queryBuilder = generateCardAssociationQueryString();
        addParameter(queryBuilder,"cardNumber", "E4");

        String transactionResult = cardAssociation.processTransaction(queryBuilder.toString());
        assertTrue(transactionResult.contains("code=1&"));        
        assertFalse(transactionResult.contains("transactionId"));
    }

    private StringBuilder generateCardAssociationQueryString() throws ParseException {
       
		StringBuilder queryBuilder = new StringBuilder(CardAssociation.CARD_ASSOCIATION_LINK + '?');
		addParameter(queryBuilder, "owner", "user1");
		addParameter(queryBuilder, "cvvCode", "123");
		addParameter(queryBuilder, "dateExpired", "24/04/2025");
		addParameter(queryBuilder, "command", "pay");
		addParameter(queryBuilder, "amount", "20.1");
		addParameter(queryBuilder, "createdDate", "26/12/2022");
		addParameter(queryBuilder, "transactionContent", "Pay for TVM");

		return queryBuilder;
	}

	public static void addParameter(StringBuilder queryBuilder, String key, String value) {
		queryBuilder.append(key.replace(" ", "%20"));
		queryBuilder.append("=");
		queryBuilder.append(value.replace(" ", "%20"));
		queryBuilder.append("&");
	}
}