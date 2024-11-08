package ru.sklad.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * Сущность товара.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class Product {

    private UUID id;

    private String name;

    private String description;

    private int price;

    private boolean isAvailable;

}
