package com.ecom.rest.api;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ecom.util.JacksonUtils;


/**
 * 
 * This class will be the generic json error message that will be provided to the client for any none 200 cases.
 * Main intention is to built a standard way to handle errors so that. 
 * - JavaScript client knows exactly what to expect from the server when there is an error
 * - Server side developers know exactly how to generate an error that the client side code and developers can consume
 * 
 * Example:

	{  
	   "httpStatus":400,
	   "apiCode":"062952b8-257f-4fb5-9baf-2d2932375f9d",
	   "userMessage":"An Error has occured and the request could not be completed",
	   "developerMessage":"Invalid request body see validation errors",
	   "validationErrors":[  
	      {  
	         "fieldName":"name",
	         "message":"name must be 10 characters long"
	      }
	   ]
	}
 * 
  * See http://blog.apigee.com/detail/restful_api_design_what_about_errors/ for the rationale behind this class.
 * 
 * @author Adib Saikali
 * 
 */
public class RestApiError
{
	private final int httpStatus;
	private UUID apiCode;
	private String userMessage;	
	private String developerMessage;
	private List<RestApiValidationError> validationErrors;

	@JsonCreator
	public RestApiError(@JsonProperty("httpStatus") RestApiHttpStatus httpStatus)
	{
		this.httpStatus = httpStatus.getStatusCode();
	}

	public static RestApiError fromHttpStatusCodeException(HttpStatusCodeException e)
	{
		String responseBody = e.getResponseBodyAsString();
		return JacksonUtils.fromJSON(responseBody, RestApiError.class);
	}

	@Override
	public String toString()
	{
		return JacksonUtils.toJSON(this);
	}

	public int getHttpStatus()
	{
		return httpStatus;
	}

	public UUID getApiCode()
	{
		return apiCode;
	}

	public RestApiError setApiCode(UUID apiCode)
	{
		this.apiCode = apiCode;
		return this;
	}

	public String getUserMessage()
	{
		return userMessage;
	}

	public RestApiError setUserMessage(String userMessage)
	{
		this.userMessage = userMessage;
		return this;
	}

	public String getDeveloperMessage()
	{
		return developerMessage;
	}

	public RestApiError setDeveloperMessage(String developerMessage)
	{
		this.developerMessage = developerMessage;
		return this;
	}

	public List<RestApiValidationError> getValidationErrors()
	{
		return validationErrors;
	}

	public RestApiError setValidationErros(List<RestApiValidationError> restApiFieldErrors)
	{
		this.validationErrors = restApiFieldErrors;
		return this;
	}
}
