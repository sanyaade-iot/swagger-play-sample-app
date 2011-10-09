/**
 *  Copyright 2011 Wordnik, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.wordnik.swagger.sample.resource;

import com.wordnik.swagger.sample.exception.*;
import com.wordnik.swagger.sample.model.ApiResponse;

import javax.ws.rs.ext.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Provider
public class SampleExceptionMapper implements ExceptionMapper<ApiException> {
	public Response toResponse(ApiException exception) {
		if (exception instanceof NotFoundException) {
			return Response.status(Status.NOT_FOUND)
					.entity(new ApiResponse(ApiResponse.ERROR, exception
							.getMessage())).build();
		} else if (exception instanceof BadRequestException) {
			return Response.status(Status.BAD_REQUEST)
					.entity(new ApiResponse(ApiResponse.ERROR, exception
							.getMessage())).build();
		} else if (exception instanceof ApiException) {
			return Response.status(Status.BAD_REQUEST)
					.entity(new ApiResponse(ApiResponse.ERROR, exception
							.getMessage())).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ApiResponse(ApiResponse.ERROR,
							"a system error occured")).build();
		}
	}
}