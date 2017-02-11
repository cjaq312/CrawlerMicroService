package com.jagan.SearchApiService.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jagan.SearchApiService.exceptions.DefaultException;
import com.jagan.SearchApiService.models.ErrorMessage;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<DefaultException> {

	@Override
	public Response toResponse(DefaultException error) {
		ErrorMessage message = new ErrorMessage("500", "Internal Server Error",
				"There seems to be some issue witht the server at the moment");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}

}
