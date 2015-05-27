package com.ecom.rest.api.exceptions;

import com.ecom.rest.api.RestApiException;
import com.ecom.rest.api.RestApiHttpStatus;

public class UnauthorizedRestApiException extends RestApiException
{
	private static final long serialVersionUID = 1L;

	public UnauthorizedRestApiException()
	{
		super(RestApiHttpStatus.UNAUTHORIZED);
	}
}
