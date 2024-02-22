package com.camunda.consulting.connector;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.api.json.ConnectorsObjectMapperSupplier;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import io.camunda.connector.http.base.components.HttpTransportComponentSupplier;
import io.camunda.connector.http.base.model.HttpCommonResult;
import io.camunda.connector.http.base.services.HttpService;

import com.camunda.consulting.connector.dto.RedditConnectorRequest;
import com.camunda.consulting.connector.dto.RedditConnectorResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(name = "RedditConnector",
    			   inputVariables = {"authentication"},
                   type = "io.camunda:reddit:1") 

@ElementTemplate(id = "bd85f682-1468-4f89-a1e4-b699c6455b7b",
	    	     name = "Reddit Connector",
	             version = 1,
	             description = "Describe this connector",
	             icon = "icon.svg",
	             documentationRef = "",
			     propertyGroups = {
			    	@ElementTemplate.PropertyGroup(id = "authentication", label = "Authentication")
			     },
	             inputDataClass = RedditConnectorRequest.class)

public class RedditConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedditConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) throws Exception {
	  
	  final var connectorRequest = context.bindVariables(RedditConnectorRequest.class);
	  
	  return executeConnector(connectorRequest);
  }

  private RedditConnectorResult executeConnector(final RedditConnectorRequest connectorRequest) throws Exception {

	  LOGGER.info("Executing Reddit connector with request {}", connectorRequest);
	  
	  HttpService httpService = new HttpService(ConnectorsObjectMapperSupplier.getCopy(), HttpTransportComponentSupplier.httpRequestFactoryInstance());
	  
	  HttpCommonResult result = httpService.executeConnectorRequest(connectorRequest);
	  
	  return new RedditConnectorResult(result.getBody());
  }
  
}
