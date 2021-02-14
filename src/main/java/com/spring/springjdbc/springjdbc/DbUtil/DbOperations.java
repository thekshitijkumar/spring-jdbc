package com.spring.springjdbc.springjdbc.DbUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@Component
public class DbOperations {

private static Logger logger= LoggerFactory.getLogger(DbOperations.class);

public DbOperations() throws SQLException {

    this.getConnection();
    this.createTable();

}
private static Connection connection;

public  Connection getConnection() throws SQLException {
    if(connection==null)
    {

        connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users","root","root");
        logger.info("creating connection object");
    }
    return connection;
}
public void createTable() throws SQLException {
    String query="CREATE table if not exists user (id int auto_increment primary key,name varchar(30),country varchar(30),age int)";

    Statement statement= connection.createStatement();

    statement.execute(query);
//    statement.execute() //return true if has resultset else false
//    statement.executeQuery()//returns result set
//    statement.executeUpdate();//returns number of rows which are updated
}

}
