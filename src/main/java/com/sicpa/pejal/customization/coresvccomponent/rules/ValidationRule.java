/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicpa.pejal.customization.coresvccomponent.data.ExtensibleSchema;
import com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnit;
import com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnitDAO;
import com.sicpa.pejal.customization.coresvccomponent.data.Product;
import com.sicpa.pejal.customization.coresvccomponent.data.ProductDAO;
import com.sicpa.pejal.customization.coresvccomponent.services.ValidationRequest;
import io.dropwizard.hibernate.AbstractDAO;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author azainal
 */
@Slf4j
public abstract class ValidationRule extends PluginRule{
    

    
    protected ValidationRule() {
        
    }

    public String getDescription() {
        return "General ValidationRule";
    }

    public abstract boolean execute(ValidationRequest request) ;
    
    protected final <T extends ExtensibleSchema> String getExtensionSchema( T item){
        return item.getExtSchema();
    }
    protected final <T extends ExtensibleSchema, U> U getExtensionSchema( T item, Class<U> clazz){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(item.getExtSchema(), clazz);
    }
    
    
    protected final Product getProduct(String productId) {
        return entryPoint.findProductByProductId(productId);
    }
    
    protected final PackagingUnit getPackagingUnit(String puId) {
        return entryPoint.findPackagingUnitByPuId(puId);
    }
    
}
