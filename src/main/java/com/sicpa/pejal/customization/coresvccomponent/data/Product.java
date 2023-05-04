/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author azainal
 */
@Entity
@Table(name = "product")
@Data
@NamedQueries({
    @NamedQuery(name = "com.sicpa.pejal.customization.coresvccomponent.data.Product.findAll",
    query = "SELECT p FROM Product p"),
    @NamedQuery(name = "com.sicpa.pejal.customization.coresvccomponent.data.Product.findByProductId",
    query = "SELECT p FROM Product p WHERE p.productId = :productId")
})
public class Product implements ExtensibleSchema{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "siteid")
    private String siteid;
    private String status;
    @Column(name = "ext_schema")
    private String extSchema;

    
}
