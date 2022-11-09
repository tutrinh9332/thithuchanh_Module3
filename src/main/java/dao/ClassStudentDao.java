package dao;

import connect_MySQL.Connect_MySQL;
import model.ClassStudent;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassStudentDao implements IService<ClassStudent>{
    private static final String SELECT_ALL_CLASSES = "SELECT * from classes";
    private static final String SELECT_CLASS_BY_ID = "SELECT * from classes where idClass = ?";
    private static final String SELECT_CLASS_BY_NAME = "SELECT * from classes where className like concat('%',?,'%')";
    @Override
    public List getAll() {
        List<ClassStudent> classStudents = new ArrayList<>();

        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLASSES)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idClass = rs.getInt(1);
                String nameClass = rs.getString(2);
                classStudents.add(new ClassStudent(idClass, nameClass));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classStudents;
    }


    @Override
    public boolean create(ClassStudent classStudent ) {
        return false;
    }

    @Override
    public boolean edit(int id, ClassStudent classStudent) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public ClassStudent findById(int id) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int idClass = rs.getInt(1);

            String nameClass = rs.getString(2);
            ClassStudent classStudent = new ClassStudent(idClass, nameClass);

            return classStudent;
        } catch (SQLException e) {
            System.out.println("sai3");
            e.printStackTrace();
            return null;
        }

    }

    public ClassStudent findByName(String name){
        ClassStudent classStudent = null;
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            classStudent = new ClassStudent(id, name);
            return classStudent;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
}