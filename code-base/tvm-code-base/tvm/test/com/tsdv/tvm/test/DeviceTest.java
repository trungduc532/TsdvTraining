package com.tsdv.tvm.test;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.tsdv.device.CardIssuer;
import com.tsdv.device.LocalCardIssuer;
import com.tsdv.device.LocalTicketPrinter;
import com.tsdv.device.TicketPrinter;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class DeviceTest extends TestCase {

    @Test
    public void testTicketPrinter() {
        TicketPrinter ticketPrinter = LocalTicketPrinter.instance();
        assertSame(ticketPrinter, LocalTicketPrinter.instance());

        assertTrue(ticketPrinter.hasEnoughPapers(19));
        assertTrue(ticketPrinter.hasEnoughPapers(20));
        assertFalse(ticketPrinter.hasEnoughPapers(21));

        String gtin1 = ticketPrinter.print("-- formatted string 1 --");
        String gtin2 = ticketPrinter.print("-- formatted string 2 --");

        assertNotEquals(gtin1, gtin2);

        assertTrue(ticketPrinter.hasEnoughPapers(18));
        assertFalse(ticketPrinter.hasEnoughPapers(19));
    }

    @Test
    public void testCardIssuer() {
        CardIssuer cardIssuer = LocalCardIssuer.instance();
        assertSame(cardIssuer, LocalCardIssuer.instance());

        assertTrue(cardIssuer.hasEnoughCards(19));
        assertTrue(cardIssuer.hasEnoughCards(20));
        assertFalse(cardIssuer.hasEnoughCards(21));

        String gtin1 = cardIssuer.issueCard(6);
        String gtin2 = cardIssuer.issueCard(5);
        assertNotEquals(gtin1, gtin2);
        
        try {
        	String gtin3 = cardIssuer.issueCard(-4);
        	assertTrue(false);
        } catch (Exception e) {
        	assertTrue(true);
        }

        assertTrue(cardIssuer.hasEnoughCards(9));
    }
}
