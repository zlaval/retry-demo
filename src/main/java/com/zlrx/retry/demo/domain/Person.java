package com.zlrx.retry.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@ToString(exclude = "id")
public class Person implements Serializable {

    @Id
    @GeneratedValue
    @Basic
    private Long id;

    @Basic
    private String name;

}
