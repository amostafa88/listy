package com.ecom.rest.api.exceptions;

import com.ecom.rest.api.RestApiException;
import com.ecom.rest.api.RestApiHttpStatus;

public class ForbiddenRestApiException extends RestApiException
{
	private static final long serialVersionUID = 1L;

	public ForbiddenRestApiException()
	{
		super(RestApiHttpStatus.FORBIDDEN);
	}
}
