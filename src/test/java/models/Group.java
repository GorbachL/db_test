package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Group {

    int id;
    String name;
    Date startDate;

    public Group(String name, String startDate) {

        this.name = name;
        this.startDate = Date.valueOf(startDate);
    }
}
