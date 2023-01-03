package org.example;

import java.sql.Connection;

public class StoreDAO {
    ConnectionFactory connectionFactory;
    public StoreDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

}
