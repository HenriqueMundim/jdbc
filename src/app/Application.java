package app;

import db.DB;
import model.dao.ISellerDao;
import model.dao.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ISellerDao sellerDao = new SellerDaoJDBC(DB.getConnection());
        Department department = new Department(2, null);

        System.out.println("TEST seller findById");
        System.out.println("Enter with ID:");
        int id = sc.nextInt();
        Seller seller = sellerDao.findById(id);
        System.out.println(seller);

        System.out.println("TEST seller findAll");
        List<Seller> sellers = sellerDao.findAll();
        for (Seller s : sellers) {
            System.out.println(s);
        }

        System.out.println("TEST seller insert");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", LocalDate.now(), 4000.0, department);
        sellerDao.insert(newSeller);

        System.out.println("TEST seller Update");
        Seller updatedSeller = new Seller(1, "Henrique", "henrique@gmail.com", LocalDate.now(), 150000.00, department);
        sellerDao.update(updatedSeller);

        System.out.println("TEST seller deleteById");
        System.out.println("Enter with ID:");
        int deleteId = sc.nextInt();
        sellerDao.deleteById(deleteId);

    }
}
