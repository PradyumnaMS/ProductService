package dev.pradyumna.ProductService.dtos;

import dev.pradyumna.ProductService.models.Category;
import lombok.Setter;

import java.awt.*;
@Setter
// since all the fields are private to create the objects
// at least it needs setters
// otherwise all the fields int be request body will be null
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

}
