package org.example.managementproduct.model;

import org.example.managementproduct.DBconnect.DBconnect;

import java.sql.Connection;

public class basemodel {
    protected Connection connection = null;

    public basemodel() {
        DBconnect dbConnect = new DBconnect();
        connection = dbConnect.getConnection();
    }
}
