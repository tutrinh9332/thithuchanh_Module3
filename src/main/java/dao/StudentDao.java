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

public class StudentDao implements IService<Student>{
    private static final String INSERT_STUDENTS_SQL = "INSERT INTO students (name,dateOfBirth,address,phoneNumber,email,idClass) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_STUDENT_BY_ID = "SELECT name,dateOfBirth,address,phoneNumber,email,idClass from students where id= ?";
    private static final String SELECT_STUDENT_BY_NAME = "SELECT * from students where name like concat('%',?,'%')";
    private static final String SELECT_ALL_STUDENTS = "SELECT * from students";
    private static final String DELETE_STUDENTS_SQL = "DELETE from students where id=?;";
    private static final String UPDATE_STUDENTS_SQL = "UPDATE students set name = ?,dateOfBirth = ?,address = ?,phoneNumber = ?,email = ?,idClass= ? where id = ?;";

    ClassStudentDao classStudentDao = new ClassStudentDao();

    @Override
    public List getAll() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = Connect_MySQL.getConnect()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                ClassStudent classStudent = classStudentDao.findById(rs.getInt("idClass"));

                students.add(new Student(id, name, dateOfBirth, address, email, phoneNumber, classStudent));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return students;
    }

    @Override
    public boolean create(Student student) {

        try (Connection connection = Connect_MySQL.getConnect()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, String.valueOf(student.getDateOfBirth()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setInt(6, student.getClassStudent().getId());

            return preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }

    }

    @Override
    public boolean edit(int id, Student student) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS_SQL);
            preparedStatement.setInt(7, student.getId());
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, String.valueOf(student.getDateOfBirth()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassStudent().getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }



    @Override
    public boolean delete(int id) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS_SQL);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public Student findById(int id) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateOfBirth"));
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phoneNumber");
            ClassStudent classStudent = classStudentDao.findById(resultSet.getInt("idClass"));

            return new Student(id, name, dateOfBirth, address, email, phoneNumber, classStudent);

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public List<Student> getAllByName(String name) {
        List<Student> students = new ArrayList<>();

        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameS = resultSet.getString("name");
                LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateOfBirth"));
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                ClassStudent classStudent = classStudentDao.findById(resultSet.getInt("idClass"));

                students.add(new Student(id, nameS, dateOfBirth, address, email, phoneNumber, classStudent));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace();
                System.out.println("SQLState:" + ((SQLException) e).getSQLState());
                System.out.println("Error Code:" + ((SQLException) e).getSQLState());
                System.out.println("Message:" + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause:" + t);
                    t = t.getCause();
                }
            }
        }
    }
}
