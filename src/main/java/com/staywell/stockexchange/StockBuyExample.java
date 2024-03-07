package com.staywell.stockexchange;

import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;

public class StockBuyExample {
    public static void main(String[] args) throws ConfigError, InterruptedException {
        SessionSettings settings = new SessionSettings("src/main/resources/fix-protocol.txt");
        Application application = new StockBuyApplication();
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory logFactory = new FileLogFactory(settings);
        MessageFactory messageFactory = new DefaultMessageFactory();

        Initiator initiator = new SocketInitiator(application, storeFactory, settings, logFactory, messageFactory);
        initiator.start();

        // Wait for a few seconds to allow the connection to establish
        Thread.sleep(5000);

        // Check if the session is logged on
        boolean isLoggedOn = initiator.isLoggedOn();
        if (isLoggedOn) {
            System.out.println("FIX server is up and running.");
        } else {
            System.out.println("FIX server is not responding.");
        }

//        // Stop the initiator
//        initiator.stop();
//        // Wait for the FIX session to be connected
//        while (!initiator.isLoggedOn()) {
//            Thread.sleep(1000);
//        }

        // Create a NewOrderSingle message
        NewOrderSingle order = new NewOrderSingle(
                new ClOrdID("123456"),
                new Side(Side.BUY),
                new TransactTime(),
                new OrdType(OrdType.MARKET)
        );
        order.set(new Symbol("AAPL")); // Stock symbol
        order.set(new OrderQty(100)); // Order quantity

        // Define the session ID
        SessionID sessionID = new SessionID("FIX.4.4", "CLIENT", "SERVER");

        // Send the message
        try {
            Session.sendToTarget(order, sessionID);
        } catch (SessionNotFound e) {
            throw new RuntimeException(e);
        }

        try {
            application.fromAdmin(order, sessionID);
        } catch (FieldNotFound e) {
            throw new RuntimeException(e);
        } catch (IncorrectDataFormat e) {
            throw new RuntimeException(e);
        } catch (IncorrectTagValue e) {
            throw new RuntimeException(e);
        } catch (RejectLogon e) {
            throw new RuntimeException(e);
        }
        // Wait for a while before shutting down the initiator
        Thread.sleep(5000);

        initiator.stop();
    }
}
