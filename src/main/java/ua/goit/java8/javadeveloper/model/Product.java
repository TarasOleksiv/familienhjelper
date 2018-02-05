package ua.goit.java8.javadeveloper.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by t.oleksiv on 30/11/2017.
 */

@Entity
@Table(name = "products")
@Proxy(lazy = false)
public class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne  // продукт може виробляти лише один виробник
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

    public Product(){

    }

    public Product(String name, Manufacturer manufacturer, BigDecimal price){
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Product withId(UUID id){
        this.id = id;
        return this;
    }

    public Product withName(String name){
        this.name = name;
        return this;
    }

    public Product withPrice(BigDecimal price){
        this.price = price;
        return this;
    }

    public Product withManufacturer(Manufacturer manufacturer){
        this.manufacturer = manufacturer;
        return this;
    }

    public String printPureProduct(){
        return "Product{" +
                "id=" + id.toString() +
                ", name='" + name + '\'' +
                ", price= " + price +
                "}";
    }

    @Override
    public String toString(){
        return "Product{" +
                "id=" + id.toString() +
                ", name='" + name + '\'' +
                ", price= " + price +
                ", manufacturer= " + manufacturer +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;

        Product obj2 = (Product) obj;
        if((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int tmp = 0;
        tmp = ( id + name).hashCode();
        return tmp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
