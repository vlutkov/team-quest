package com.thebestgroup.teamquest.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
@Entity
@Table(name = "quest")
public class Quest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Short type;
    private Age age;
    private PersonNum personNum;
    @Column(nullable = false)
    private Short complexity;
    @Column(nullable = false)
    private Double rating;
    @Column(nullable = false)
    private BigDecimal startPrice;
    @Column(nullable = false)
    private Short spentTime;
    @Column(nullable = false)
    private String description;
}
