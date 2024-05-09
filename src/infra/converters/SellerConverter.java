package infra.converters;

import model.entities.Department;
import model.entities.Seller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerConverter {

    public static Seller convert(ResultSet result, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(result.getInt("Id"));
        seller.setName(result.getString("Name"));
        seller.setBirthDate(result.getDate("BirthDate").toLocalDate());
        seller.setSalary(result.getDouble("BaseSalary"));
        seller.setDepartment(department);

        return seller;
    }
}
