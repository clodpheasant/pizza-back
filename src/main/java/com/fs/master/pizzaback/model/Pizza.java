package com.fs.master.pizzaback.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "pizzas")
public class Pizza implements Serializable {

    private static final long serialVersionUID = 4587214062178953896L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    private Set<Topping> toppings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_pizza", nullable = false)
    private Set<Price> prices;

    protected Pizza() {}

    public Pizza(String name) {
        this.name = name;
        this.toppings = new HashSet<>();
        this.prices = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    public boolean addPrice(Price price) {
        Set<Price> thisSizePrices = this.prices.stream()
                .filter(x -> x.getSize().equals(price.getSize()))
                .collect(Collectors.toSet());
        this.prices.removeAll(thisSizePrices);

        return this.prices.add(price);
    }

    public boolean addTopping(Topping topping) {
        return !this.toppings.stream().anyMatch(x -> x.equals(topping))
                ? this.toppings.add(topping)
                : false;
    }

    public boolean removeTopping(Topping topping) {
        return this.toppings.remove(topping);
    }
}
