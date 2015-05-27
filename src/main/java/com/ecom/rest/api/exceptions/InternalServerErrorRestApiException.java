package com.ecom.rest.api.exceptions;

import com.ecom.rest.api.RestApiException;
import com.ecom.rest.api.RestApiHttpStatus;

public class InternalServerErrorRestApiException extends RestApiException
{
	private static final long serialVersionUID = 1L;

	public InternalServerErrorRestApiException()
	{
		super(RestApiHttpStatus.INTERNAL_SERVER_ERROR);
	}
}
