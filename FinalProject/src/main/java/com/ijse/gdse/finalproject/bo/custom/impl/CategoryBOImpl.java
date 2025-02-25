package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.CategoryBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.CategoryDAO;
import com.ijse.gdse.finalproject.dto.CategoryDTO;
import com.ijse.gdse.finalproject.entity.Category;


import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryBOImpl implements CategoryBO {

    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CATEGORY);

    @Override
    public String getNextCategoryId() throws SQLException {
        return categoryDAO.getNext();
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategory() throws SQLException {
        ArrayList<Category> all = categoryDAO.getAll();

        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : all) {
            CategoryDTO categoryDTO = new CategoryDTO(
                    category.getCategoryId(),
                    category.getCategoryName()
            );
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    public boolean saveCategory(CategoryDTO category) throws SQLException {
        return categoryDAO.save(new Category(category.getCategoryId(), category.getCategoryName()));
    }

    @Override
    public boolean deleteCategory(String categoryId) throws SQLException {
        return categoryDAO.delete(categoryId);
    }

    @Override
    public boolean updateCategory(CategoryDTO category) throws SQLException {
        return categoryDAO.update(new Category(category.getCategoryId(), category.getCategoryName()));
    }
}
