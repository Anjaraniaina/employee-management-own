package school.apihei.demo.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import school.apihei.demo.model.Groups;
import school.apihei.demo.model.Students;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentsRepositoryImpl implements StudentRepository{


    private Connection con = DriverManager.getConnection(url, user, password);

    @Override
    public List<Students> findAll() {
        ResultSet result = null;
        List<Students> studentList= new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Students");
            result = stm.executeQuery();
            while (result.next()) {
            Long id = result.getLong("id");
            String name = result.getString("name");
            Groups group = result.getString("group").getName();
            Students s = new Students(id, name, group);
            studentList.add(s);
            }

        } catch (SQLException e){
            System.out.println("The error is :"+e);

        }
        return studentList;
    }

    @Override
    public  Students add(Students s) {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Students VALUES (?, ?, ?)");
            stm.setLong(1, s.getId());
            stm.setString(2, s.getName());
            stm.setString(3, s.getGroup().getName());
        } catch (SQLException e) {
            System.out.println("The error is :"+e);
        }
        return s;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Students updateNameById(Long id, String newName) {
        return null;
    }

    @Override
    public List<Students> findWhereNameLike(String query) {
        return null;
    }
}
