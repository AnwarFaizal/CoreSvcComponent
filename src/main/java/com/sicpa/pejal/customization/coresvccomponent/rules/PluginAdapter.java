/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.rules;

import com.sicpa.pejal.customization.coresvccomponent.CoreSvcConfiguration;
import com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnit;
import com.sicpa.pejal.customization.coresvccomponent.data.PackagingUnitDAO;
import com.sicpa.pejal.customization.coresvccomponent.data.Product;
import com.sicpa.pejal.customization.coresvccomponent.data.ProductDAO;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Jdbi;

/**
 *
 * @author azainal
 */
public final class PluginAdapter {
    
    private Jdbi jdbi;

    private List<ValidationRule> validationRuleList = new ArrayList<>();
    private List<DeclarationPreProcessor> declarationPreProcessorList = new ArrayList<>();
    private ProductDAO productDao;
    private PackagingUnitDAO packagingUnitDao;
    
    private final HibernateBundle<CoreSvcConfiguration> hibernate = 
            new HibernateBundle<CoreSvcConfiguration>(Product.class, PackagingUnit.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(CoreSvcConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }

    };
    
    List<ValidationRule> getValidationRuleList(){
        return this.validationRuleList;
    }
    
    List<DeclarationPreProcessor> getDeclarationPreProcessorList() {
        return this.declarationPreProcessorList;
    }
    

    void intialize(Environment env, CoreSvcConfiguration config) {
        final JdbiFactory factory = new JdbiFactory();
        jdbi = factory.build(env, config.getDataSourceFactory(), "postgresql");
        productDao = new ProductDAO(hibernate.getSessionFactory());
        packagingUnitDao = new PackagingUnitDAO(hibernate.getSessionFactory());
    }

    <T extends CoreSvcConfiguration> ConfiguredBundle<? super T> getHibernate() {
        return this.hibernate;
    }
    
    ProductDAO getProductDAO(){
        return this.productDao;
    }
    
    PackagingUnitDAO getPackagingUnitDAO() {
        return this.packagingUnitDao;
    }

    public final void saveProduct(Product processedProduct) {
        this.productDao.save(processedProduct);
    }
    
}
