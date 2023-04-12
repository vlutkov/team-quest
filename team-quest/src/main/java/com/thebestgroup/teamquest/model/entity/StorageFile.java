package com.thebestgroup.teamquest.model.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
//@Entity
@Table(name = "storage_file")
public class StorageFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private byte[] data;
    @Column(nullable = false)
    private Integer size;
    private String extension;
}
