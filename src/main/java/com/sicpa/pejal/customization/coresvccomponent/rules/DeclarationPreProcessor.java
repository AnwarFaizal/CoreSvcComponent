/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.rules;

import com.sicpa.pejal.customization.coresvccomponent.data.Product;
import com.sicpa.pejal.customization.coresvccomponent.services.DeclarationRequest;

/**
 *
 * @author azainal
 */
public abstract class  DeclarationPreProcessor extends PluginRule {
    
    private Product processedProduct = null;
    private Product previousProduct = null;
    
    public String getDescription() {
        return "General DeclarationPreProcessor";
    }
    
    public abstract Product execute(DeclarationRequest request, Product product);
 
    protected final Product getProduct(String productId) {
        return entryPoint.findProductByProductId(productId);
    }
    
    protected final boolean saveProduct(Product product) {
        this.previousProduct = this.processedProduct;
        this.processedProduct = product;
        return true;
    }
    
    protected final Product getProcessedProduct() {
        return this.processedProduct;
    }
    
    protected final Product getPreviousProduct() {
        return this.previousProduct;
    }

    final Product run(Product product, DeclarationRequest request) {
        return execute(request, product);
    }

}
