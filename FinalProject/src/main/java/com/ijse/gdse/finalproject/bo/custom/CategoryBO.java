package com.ijse.gdse.finalproject.bo.custom;

import com.ijse.gdse.finalproject.bo.SuperBO;
import com.ijse.gdse.finalproject.dto.CategoryDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryBO extends SuperBO {

     String getNextCategoryId() throws SQLException;
     ArrayList<CategoryDTO> getAllCategory() throws SQLException;
     boolean saveCategory(CategoryDTO category) throws SQLException;
     boolean deleteCategory(String categoryId) throws SQLException;
     boolean updateCategory(CategoryDTO category) throws SQLException;
}
