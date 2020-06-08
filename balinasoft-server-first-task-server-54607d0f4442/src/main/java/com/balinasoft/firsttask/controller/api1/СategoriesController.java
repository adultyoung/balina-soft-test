package com.balinasoft.firsttask.controller.api1;

import com.balinasoft.firsttask.dto.CategoryDtoIn;
import com.balinasoft.firsttask.dto.CategoryDtoOut;
import com.balinasoft.firsttask.dto.ResponseDto;
import com.balinasoft.firsttask.service.CategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.balinasoft.firsttask.system.StaticWrapper.wrap;

@RestController
@RequestMapping("/api/categories")
@Api(tags = "Categories")
public class СategoriesController {

    private final CategoryServiceImpl categoryService;

    public СategoriesController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "Add category", response = CategoryDtoOut.class)
    @ApiResponses({
            @ApiResponse(code = 500, message = "category-add-error")
    })
    public ResponseDto addCategory(@RequestBody @Valid CategoryDtoIn categoryDtoIn) {
        return wrap(categoryService.addCategory(categoryDtoIn));
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete category", response = CategoryDtoOut.class)
    public ResponseDto deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return wrap();
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Get categories for user", response = CategoryDtoOut.class, responseContainer = "List")
    public ResponseDto getCategories() {
        return wrap(categoryService.getCategories());
    }
}