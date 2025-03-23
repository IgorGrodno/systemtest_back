package com.example.systemstest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dishs")
@Data
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

@NotNull
    @Min(1)
    private Integer calories;

    @NotNull
    @Min(0)
    private Double proteins;

    @NotNull
    @Min(0)
    private Double fats;

    @NotNull
    @Min(0)
    private Double carbohydrates;

    public Dish() {
    }

    public Dish(String name, int calories, double proteins, double fats, double carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
}

