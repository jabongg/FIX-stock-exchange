package com.staywell.stockexchange;

import quickfix.*;

public class StockBuyApplication extends MessageCracker implements Application {
    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        crack(message, sessionID);
    }

    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println("Session created: " + sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        System.out.println("Logged on: " + sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        System.out.println("Logged out: " + sessionID);
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("Received message from admin: " + message);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("Sending message to admin: " + message);
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
    }

    // Handle execution report message
    public void onMessage(quickfix.fix44.ExecutionReport message, SessionID sessionID) {
        System.out.println("Execution Report received: " + message);
    }
}