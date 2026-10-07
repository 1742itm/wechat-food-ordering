package my.food.service.impl;

import my.food.entity.Category;
import my.food.mapper.CategoryMapper;
import my.food.mapper.FoodMapper;
import my.food.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;



}
