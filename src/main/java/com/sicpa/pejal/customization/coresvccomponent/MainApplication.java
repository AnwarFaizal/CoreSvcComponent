/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent;

import com.sicpa.pejal.customization.coresvccomponent.services.ValidationService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author azainal
 */
@Slf4j
public class MainApplication extends Application<CoreSvcConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }
    
    public MainApplication() {
        log.debug("Init MainApplication::default");
    }
    
    public MainApplication(String msg) {
        log.debug("Init MainApplication with message: " + msg);
    }
    

    @Override
    public void run(CoreSvcConfiguration config, Environment env) throws Exception {
    }
    
    
}
