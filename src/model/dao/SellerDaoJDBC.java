package model.dao;

import db.DB;
import db.DbException;
import infra.converters.DepartmentConverter;
import infra.converters.SellerConverter;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements ISellerDao{

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)"
            );
            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, Date.valueOf(seller.getBirthDate()));
            st.setDouble(4, seller.getSalary());
            st.setInt(5, seller.getDepartment().getId());

            st.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*, department.name depName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ? "
                    + "GROUP BY seller.name"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Department department = DepartmentConverter.convert(rs);
                return SellerConverter.convert(rs, department);
            }
            return  null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT seller.*, department.name depName "
                        + "FROM seller INNER JOIN department "
                        + "ON seller.DepartmentId = department.Id "
            );
            rs = st.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (rs.next()) {
                Department department = DepartmentConverter.convert(rs);
                if (departmentMap.get(department.getId()) == null) {
                    departmentMap.put(rs.getInt("DepartmentID"), department);
                }
                sellers.add(SellerConverter.convert(rs, departmentMap.get(rs.getInt("DepartmentID"))));
            }
            return sellers;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
