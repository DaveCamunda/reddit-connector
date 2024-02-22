package com.camunda.consulting.connector.dto;

import io.camunda.connector.http.base.model.HttpCommonRequest;
import io.camunda.connector.http.base.model.HttpMethod;

public class RedditConnectorRequest extends HttpCommonRequest {
	
	public RedditConnectorRequest() {

		this.setUrl("https://oauth.reddit.com/api/v1/me");
		this.setMethod(HttpMethod.GET);
	}
}
