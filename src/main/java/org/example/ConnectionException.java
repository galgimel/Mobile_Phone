package org.example;

public class ConnectionException extends Exception {
    String message;

    public ConnectionException() {
        if (super.equals("ClassNotFoundException")) {
            message = "Драйвер не найден.";
        } else if (super.equals("SQLException")) {
            message = "Невозможно установить соединение.";
        } else if (super.equals("IOException")) {
            message = "IOException";
        }
    }
}