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
@Table(name = "packaging_unit")
@Data
@NamedQueries({
    @NamedQuery(name = "com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnit.findAll",
    query = "SELECT p FROM PackagingUnit p"),
    @NamedQuery(name = "com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnit.findByPuId",
    query = "SELECT p FROM PackagingUnit p WHERE p.puId = :puId")
})
public class PackagingUnit implements ExtensibleSchema{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String puId;
    @Column(name = "start_range")
    private String startRange;
    @Column(name = "end_range")
    private String endRange;
    private String siteId;
    private String status;
    @Column(name = "ext_schema")
    private String extSchema;
}
