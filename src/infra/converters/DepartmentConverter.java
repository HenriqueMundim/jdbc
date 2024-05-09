package infra.converters;

import model.entities.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentConverter {

    public static Department convert(ResultSet result) throws SQLException {
        Department department = new Department();
        department.setId(result.getInt("DepartmentId"));
        department.setName(result.getString("depName"));

        return department;
    }

    public static Department convertOnlyDepartmentEntity(ResultSet result) throws SQLException {
        Department department = new Department();
        department.setId(result.getInt("Id"));
        department.setName(result.getString("Name"));

        return department;
    }
}
