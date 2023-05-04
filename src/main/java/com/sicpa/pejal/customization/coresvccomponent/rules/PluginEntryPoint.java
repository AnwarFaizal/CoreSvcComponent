/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicpa.pejal.customization.coresvccomponent.CoreSvcConfiguration;
import com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnit;
import com.sicpa.pejal.customization.coresvccomponent.data.Product;
import com.sicpa.pejal.customization.coresvccomponent.services.DeclarationRequest;
import com.sicpa.pejal.customization.coresvccomponent.services.ValidationRequest;
import com.sicpa.pejal.customization.coresvccomponent.services.ValidationStatus;
import com.sicpa.pejal.customization.coresvccomponent.services.DeclarationService;
import com.sicpa.pejal.customization.coresvccomponent.services.ValidationService;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author azainal
 */
@Slf4j
public class PluginEntryPoint {
    
    protected Environment env;
    protected CoreSvcConfiguration config;
    
    private PluginAdapter adapter;
    
    public String getDescription() {
        return "General PluginEntryPoint";
    }
    
    public PluginEntryPoint(){
        adapter = new PluginAdapter();
    }
    
    public PluginEntryPoint setEnvironment(Environment env) {
        this.env = env;
        return this;
    }
    
    public PluginEntryPoint setConfig(CoreSvcConfiguration config){
        this.config = config;
        return this;
    }
    
    public final PluginEntryPoint addValidationRule (ValidationRule rule) {
        adapter.getValidationRuleList().add(rule);
        rule.setEntryPoint(this);
        return this;
    }
    
    public final PluginEntryPoint addDeclarationPreProcessor (DeclarationPreProcessor preprocessor) {
        adapter.getDeclarationPreProcessorList().add(preprocessor);
        preprocessor.setEntryPoint(this);
        return this;
    }
    
    public final <T extends CoreSvcConfiguration> void  addBootstrapBundle(Bootstrap<T> bootstrap) {
       bootstrap.addBundle(adapter.getHibernate());
    }
    
    
    public final void run() {
        if (env != null) {
            adapter.intialize(env, config);
            env.jersey().register(new ValidationService(this));
            env.jersey().register(new DeclarationService(this, adapter));
        }
    }

    public final ValidationStatus runValidationRules(ValidationRequest request) {
        ValidationStatus status = new ValidationStatus();
        for(ValidationRule vRule: adapter.getValidationRuleList()) {
            boolean passed = vRule.execute(request);
            if(!passed){
                status.setMessage("Rule: " + vRule.getDescription() + " => FAILED");
                status.setPassed(false);
            }
        }
        status.setMessage("OK");
        status.setPassed(true);
        return status;
    }
    
    public final Product preprocessDeclaration(DeclarationRequest request) {
        Product currentProduct = adapter.getProductDAO().findByProductId(request.getProductId());
        if(currentProduct != null) {
            for(DeclarationPreProcessor processor : adapter.getDeclarationPreProcessorList()){
                debugProduct(currentProduct);
                currentProduct = processor.run(currentProduct, request);
            }
        }
        debugProduct(currentProduct);
        return currentProduct;
    }
    
    private void debugProduct(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.debug("Product: " + mapper.writeValueAsString(product));
        } catch (JsonProcessingException ex) {
            log.error("Error processing JSON. " , ex);
        }
    }

    Product findProductByProductId(String productId) {
        return adapter.getProductDAO().findByProductId(productId);
    }

    PackagingUnit findPackagingUnitByPuId(String puId) {
        return adapter.getPackagingUnitDAO().findByPuId(puId);
    }

    Product findProductById(Long id) {
        return adapter.getProductDAO().findById(id);
    }

    PackagingUnit findPackagingUnitById(Long id) {
        return adapter.getPackagingUnitDAO().findById(id);
    }

    
    
}
