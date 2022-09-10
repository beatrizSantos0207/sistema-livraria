package com.livraria.livraria.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_toys")
public class Toy extends Item implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String type;


}



