package com.codeforce.shop.repository;

import com.codeforce.shop.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    ProductCategory getById(Long id);

    @Query(value = "WITH RECURSIVE subordinates AS (\n" +
            "\tSELECT\n" +
            "\t\temployee_id,\n" +
            "\t\tmanager_id,\n" +
            "\t\tfull_name\n" +
            "\tFROM\n" +
            "\t\temployees\n" +
            "\tWHERE\n" +
            "\t\temployee_id = 2\n" +
            "\tUNION\n" +
            "\t\tSELECT\n" +
            "\t\t\te.employee_id,\n" +
            "\t\t\te.manager_id,\n" +
            "\t\t\te.full_name\n" +
            "\t\tFROM\n" +
            "\t\t\temployees e\n" +
            "\t\tINNER JOIN subordinates s ON s.employee_id = e.manager_id\n" +
            ") SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tsubordinates\n" +
            "\torder by employee_id", nativeQuery = true)
    List<ProductCategory> getCategoryListNative();

    List<ProductCategory> findAllBy();

    ProductCategory getByParentIdIsNull();

}
