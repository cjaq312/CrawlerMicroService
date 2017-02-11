package com.jagan.SearchApiService.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jagan.SearchApiService.exceptions.DataNotFoundException;
import com.jagan.SearchApiService.models.ErrorMessage;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException error) {
		ErrorMessage message = new ErrorMessage("404", "Data Not Found",
				"Data you are looking doesnt exist in the database");
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}
