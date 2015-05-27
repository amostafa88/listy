package com.ecom.rest.api.exceptions;

import com.ecom.rest.api.RestApiException;
import com.ecom.rest.api.RestApiHttpStatus;

public class BadRequestRestApiException extends RestApiException
{
	private static final long serialVersionUID = 1L;

	public BadRequestRestApiException()
	{
		super(RestApiHttpStatus.BAD_REQUEST);
	}
}
