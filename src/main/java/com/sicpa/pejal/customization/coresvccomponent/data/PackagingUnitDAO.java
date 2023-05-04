/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.data;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author azainal
 */
public class PackagingUnitDAO extends AbstractDAO<PackagingUnit> {
    
    public PackagingUnitDAO (SessionFactory factory) {
        super(factory);
    }
    
    public PackagingUnit findById(Long id) {
        return get(id);
    }

    public long create(PackagingUnit pu) {
        return persist(pu).getId();
    }

    @SuppressWarnings("unchecked")
    public List<PackagingUnit> findAll() {
        return list((Query<PackagingUnit>) namedQuery("com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnit.findAll"));
    }
    
    public PackagingUnit findByPuId(String puId) {
        Query<PackagingUnit> query = (Query<PackagingUnit>) namedQuery("com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnit.findByPuId");
        query.setParameter("puId", puId);
        return query.getSingleResult();
    }
    
}
