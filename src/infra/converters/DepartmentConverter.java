package infra.converters;

import model.entities.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentConverter {

    public static Department DepartmentConverter(ResultSet result) throws SQLException {
        Department department = new Department();
        department.setId(result.getInt("Id"));
        department.setName(result.getString("Name"));

        return department;
    }
}
