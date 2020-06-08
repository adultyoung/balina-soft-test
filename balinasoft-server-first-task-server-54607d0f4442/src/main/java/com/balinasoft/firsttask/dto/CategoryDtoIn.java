package com.balinasoft.firsttask.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoIn {

    @NotNull
    @NotBlank
    @ApiModelProperty(required = true, notes = "Category name", example = "Category")
    @Size(min = 1,max = 16)
    private String category;
}
