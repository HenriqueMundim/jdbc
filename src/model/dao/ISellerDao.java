package model.dao;

import model.entities.Department;

import java.util.List;

public interface ISellerDao {
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();
}
