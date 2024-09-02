package com.dstech.powercomerce.dto;


import com.dstech.powercomerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

public class ProductDTO {

    private Long id;
    @NotBlank(message = "campo obrigatório")
    @Size(min = 3, max = 80, message = "o campo deve ter entre 3 e 80 caracteres")
    private String name;
    @Size(min = 10, message = "o campo precisa ter no minimo 10 caracteres")
    @NotBlank
    private String description;
    @Positive(message = "o campo só aceita valores positivos")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "Deve conter pelo menos 1 categoria")
    private List<CategoryDTO> categories;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.description = produto.getDescription();
        this.price = produto.getPrice();
        this.imgUrl = produto.getImgUrl();

        this.categories = produto.getCategories().stream().map(x -> new CategoryDTO(x)).toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDTO that = (ProductDTO) o;

        return Objects.equals(id, that.id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
