package com.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "authority")
public class Authority {
    @NotNull
    @Id
    @Column(name = "name")
    private String name;
}
