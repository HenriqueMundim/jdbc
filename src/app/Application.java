package app;

import db.DB;
import model.dao.ISellerDao;
import model.dao.SellerDaoJDBC;
import model.entities.Seller;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ISellerDao sellerDao = new SellerDaoJDBC(DB.getConnection());

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
    }
}
