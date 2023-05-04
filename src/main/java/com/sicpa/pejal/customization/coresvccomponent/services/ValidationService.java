/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sicpa.pejal.customization.coresvccomponent.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicpa.pejal.customization.coresvccomponent.CoreSvcConfiguration;
import com.sicpa.pejal.customization.coresvccomponent.rules.PluginEntryPoint;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author azainal
 */
@Slf4j
@Path("/validate")
public class ValidationService {
    
    private PluginEntryPoint entryPoint;
    
    public ValidationService(PluginEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    ValidationService() {
        
    }
    
    
    @GET
    @Path("/{codeSequence}")
    public Response validate(@PathParam("codeSequence") String codeSequence ){
        log.debug("Validating: " + codeSequence);
        ValidationRequest request = ValidationRequest.builder().puId(codeSequence).build();
        processValidation(request);
        return Response.ok().entity("Validated: " + codeSequence).build();
    }
    
    boolean processValidation(ValidationRequest request){
        
        ValidationStatus status = entryPoint.runValidationRules(request);
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.debug("Validation Request: " + mapper.writeValueAsString(request));
        } catch (JsonProcessingException ex) {
            log.error("Error processing JSON. " , ex);
        }
        return status.isPassed();
    }
}
