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
public class ProductDAO extends AbstractDAO<Product> {
    
    public ProductDAO (SessionFactory factory) {
        super(factory);
    }
    
    public Product findById(Long id) {
        return get(id);
    }

    public long create(Product product) {
        return persist(product).getId();
    }
    
    public void save(Product product) {
        persist(product);
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        return list((Query<Product>) namedQuery("com.sicpa.pejal.customization.coresvccomponent.data.Product.findAll"));
    }

    public Product findByProductId(String productId) {
        Query<Product> query = (Query<Product>) namedQuery("com.sicpa.pejal.customization.coresvccomponent.data.Product.findByProductId");
        query.setParameter("productId", productId);
        return query.getSingleResult();
    }
    
}
