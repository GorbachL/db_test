import database.DBConnection;
import models.Group;
import org.testng.annotations.Test;

import java.sql.Date;

public class newDBConnection {

    @Test
    public void connectTest() throws Exception {
        DBConnection connection = new DBConnection();
        connection.selectFromTable("qa02.group");
//        connection.insertIntoTable("qa02.group",
//                new Group("qa03", "2020-01-01"));
//        connection.updateTable("qa02.group", 2,
//                new Group("qa03_new", "2020-02-02"));
     //   connection.deleteFromTable("qa02.group", 4);

        System.out.println(connection.selectFromTable("qa02.group"));

//        connection.selectFromTable("qa02.student");
//        connection.selectFromTable("qa02.homeworks");
    }

}
