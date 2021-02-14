package com.spring.springjdbc.springjdbc.Service;

import com.spring.springjdbc.springjdbc.DbUtil.DbOperations;
import com.spring.springjdbc.springjdbc.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  private static Logger logger= LoggerFactory.getLogger(UserService.class);


//  @Autowired

    //constructor injection
  DbOperations dbOperations;
  public UserService(DbOperations dbOperations, @Value("${test.prop}") String my_prop)
  {
logger.info("value of my_prop is {}",my_prop);
      this.dbOperations=dbOperations;
  }


  public List<User> getUsers() throws SQLException {
    String query="select * from user";
    Statement statement= dbOperations.getConnection().createStatement();
    ResultSet resultSet=statement.executeQuery(query);
      List<User> users=new ArrayList<>();

    while(resultSet.next())
    {
        //id name country age
        int id=resultSet.getInt(1);
        String name=resultSet.getString(2);
        String country=resultSet.getString(3);
        int age=resultSet.getInt(4);
        User user=new User(id,name,country,age);
        users.add(user);
    }
    return users;
  }
  public User getUser(int id) throws SQLException {
        String query="select * from user where id= "+id;
        Statement statement= dbOperations.getConnection().createStatement();
        ResultSet resultSet=statement.executeQuery(query);
        while(resultSet.next())
        {
            //id name country age
            int my_id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            String country=resultSet.getString(3);
            int age=resultSet.getInt(4);
            User user=new User(my_id,name,country,age);
            return user;

        }
        return null;
    }

    public void insertUser(User user) throws SQLException {
        String query="insert into user(name,country,age) values (\"" +user.getName()+ "\" , \""+user.getCountry()+" \", "+user.getAge()+")";
        logger.info(query);
        Statement statement=dbOperations.getConnection().createStatement();

        int rows_modified=statement.executeUpdate(query);
        logger.info("modified rows are {}",rows_modified);


    }
    public void deleteUser(int id) throws SQLException {
        String query="delete from user where id="+id;
        logger.info(query);
        Statement statement=dbOperations.getConnection().createStatement();
        statement.executeUpdate(query);
        logger.info("Eexcuted delete query");


    }
    public void updateUser(User user) throws SQLException {
        String query="update user set name= \""+user.getName()+"\" where id="+user.getId();
        Statement statement=dbOperations.getConnection().createStatement();
        statement.executeUpdate(query);

    }
}
