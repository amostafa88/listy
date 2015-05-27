package com.ecom.rest.api;

import java.util.UUID;

public class ApiErrorCodes {
	
	public final static UUID UANBLE_TO_PARSE_REQUEST = UUID.fromString("91449dbc-f4d6-11e4-b9b2-1697f925ec7b");
	public final static UUID BIND_EXCEPTION = UUID.fromString("9144a08c-f4d6-11e4-b9b2-1697f925ec7b");
	public final static UUID TYPE_MISMATCH_EXCEPTION = UUID.fromString("9144a438-f4d6-11e4-b9b2-1697f925ec7b");
	public final static UUID INVALID_REQUEST_BODY = UUID.fromString("9144a58c-f4d6-11e4-b9b2-1697f925ec7b");
	public final static UUID METHOD_NOT_SUPPORTED_EXCEPTION = UUID.fromString("23abad28-54f8-4e66-b98e-9bb5d51c05d2");

	public final static UUID UNHANDLED_SERVER_EXCEPTION = UUID.fromString("9144a726-f4d6-11e4-b9b2-1697f925ec7b");
	public final static UUID BAD_SERVER_URL = UUID.fromString("9144a8fc-f4d6-11e4-b9b2-1697f925ec7b");
	public final static UUID SESSION_EXPIRED = UUID.fromString("9144aa1e-f4d6-11e4-b9b2-1697f925ec7b");
	public final static UUID API_LOGIN_FAILURE = UUID.fromString("9144ab40-f4d6-11e4-b9b2-1697f925ec7b");
	
	public static void main(String[] args){
		System.out.println(UUID.randomUUID().toString());
	}
	

}
