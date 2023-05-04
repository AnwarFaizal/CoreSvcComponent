/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.rules;

import com.sicpa.pejal.customization.coresvccomponent.services.DeclarationRequest;

/**
 *
 * @author azainal
 */
public abstract class PluginRule {
    
    protected PluginEntryPoint entryPoint;
    
    private PluginAdapter adapter;

    public final void setEntryPoint(PluginEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }
    
    final void setPluginAdapter(PluginAdapter adapter){
        this.adapter = adapter;
    }
    
    
    
}
