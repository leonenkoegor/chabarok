package codes.purple.chabarok.services;

import codes.purple.chabarok.dtos.CategoryDTO;
import codes.purple.chabarok.models.Category;
import codes.purple.chabarok.models.Dish;
import codes.purple.chabarok.repositories.CategoryRepository;
import codes.purple.chabarok.services.exceptions.CantAddDishToCategoryException;
import codes.purple.chabarok.services.exceptions.CategoryAlreadyExistsException;
import codes.purple.chabarok.services.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) throws CategoryNotFoundException {
        return categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
    }

    public Category findById(Long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public void createCategory(CategoryDTO categoryDTO) throws CategoryAlreadyExistsException {
        try {
            findByName(categoryDTO.getName());
            throw new CategoryAlreadyExistsException();
        } catch (CategoryNotFoundException e) {
            categoryRepository.save(new Category(categoryDTO));
        }
    }

    public void deleteCategory(String name) {
        categoryRepository.deleteByName(name);
    }

    public void addDishToCategory(Dish dish, Long categoryId) throws CantAddDishToCategoryException {
        try {
            Category categoryEntity = findById(categoryId);
            categoryEntity.getDishes().add(dish);
            categoryRepository.save(categoryEntity);
        } catch (CategoryNotFoundException e) {
            throw new CantAddDishToCategoryException();
        }
    }
}
