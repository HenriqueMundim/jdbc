package model.factory;

import db.DB;
import model.dao.DepartmentDaoJDBC;
import model.dao.IDepartmentDao;
import model.dao.ISellerDao;
import model.dao.SellerDaoJDBC;

public class DaoFactory {
    public static IDepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC();
    }

    public static ISellerDao createSellerDao() {
        return  new SellerDaoJDBC(DB.getConnection());
    }
}
