package com.vladimir.curso.springboot.app.springbootcrudjwt.entities;

import com.vladimir.curso.springboot.app.springbootcrudjwt.validation.IsExistsDB;
import com.vladimir.curso.springboot.app.springbootcrudjwt.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="{NotEmpty.product.name}")
    @Size(min = 3, max = 30)
    private String name;

    @Min(500)                                                // Ya CON ESTE VALIDA SI ES NUMERO, se le peude agregra message de error igual 
    @NotNull                                                // Este dejamos el error por defecto 
    private Integer price;

    //@NotBlank(message = "{NotBlank.product.description}")  // error personalizado
    @IsRequired
    private String description;

    @IsRequired
    @IsExistsDB
    private String sku;

    //? No se crea los constructores, ya que se usara un API REST

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }

    
}
