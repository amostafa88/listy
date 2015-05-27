package com.ecom.rest.api.exceptions;

import com.ecom.rest.api.RestApiException;
import com.ecom.rest.api.RestApiHttpStatus;

public class ResourceNotFoundRestApiException extends RestApiException
{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundRestApiException()
	{
		super(RestApiHttpStatus.NOT_FOUND);
	}
}
