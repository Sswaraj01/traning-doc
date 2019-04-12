package com.activitispring.demo.controller;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import com.activitispring.demo.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitiSpringController {
	
	private Logger logger = LoggerFactory.getLogger(ActivitiSpringController.class);

    @Autowired
    private ProcessRuntime processRuntime;
    
    @Autowired
    private SecurityUtil securityUtil;
    
    @RequestMapping(value = "/getProcessDefination", method = RequestMethod.GET)
    public ResponseEntity<Page<ProcessDefinition>> getDefination(){
    	securityUtil.logInAs("system");
    	logger.info("===================inside getDefination=================");
    	Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
    	 logger.info("> Available Process definitions: " + processDefinitionPage.getTotalItems());
         for (ProcessDefinition pd : processDefinitionPage.getContent()) {
             logger.info("\t > Process definition: " + pd);
         }
    	return new ResponseEntity<Page<ProcessDefinition>>(processDefinitionPage,HttpStatus.OK);
    }
  
}
