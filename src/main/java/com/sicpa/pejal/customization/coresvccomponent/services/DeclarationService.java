/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicpa.pejal.customization.coresvccomponent.rules.PluginEntryPoint;
import com.sicpa.pejal.customization.coresvccomponent.data.Product;
import com.sicpa.pejal.customization.coresvccomponent.data.ProductDAO;
import com.sicpa.pejal.customization.coresvccomponent.rules.PluginAdapter;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author azainal
 */
@Slf4j
@Path("/declaration")
public class DeclarationService {
    private final PluginEntryPoint entryPoint;
    private final PluginAdapter adapter;

    public DeclarationService(PluginEntryPoint entryPoint, PluginAdapter adapter) {
        this.entryPoint = entryPoint;
        this.adapter = adapter;
    }
    
    @POST
    @Path("/declare")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public DeclarationResponse createDeclaration(DeclarationRequest request){
        
        ValidationService service = new ValidationService(entryPoint);
        
        ValidationRequest vRequest = mapDeclarationToValidationRequest(request);
        
        service.processValidation(vRequest);
        
        processDeclaration(request);
        
        DeclarationResponse response = 
                DeclarationResponse.builder()
                .status("OK")
                .message("Declaration Service processing")
                .build();
        
        return response;
    }

    private ValidationRequest mapDeclarationToValidationRequest(DeclarationRequest request) {
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.debug("Declaration request: " + mapper.writeValueAsString(request));
        } catch (JsonProcessingException ex) {
            log.error("Error processing JSON. " , ex);
        }
        
        return ValidationRequest.builder()
                .puId(request.getPuid())
                .productId(request.getProductId())
                .hrcStart(request.getStartHrc())
                .hrcEnd(request.getEndHrc())
                .build();
    }
       
    boolean processDeclaration(DeclarationRequest request) {

        Product processedProduct = entryPoint.preprocessDeclaration(request);
        adapter.saveProduct(processedProduct);
        // Change puStatus to DECLARED
        return true;
    }
}
