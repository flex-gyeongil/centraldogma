/*
 * Copyright 2017 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.centraldogma.server.internal.api;

import static com.linecorp.centraldogma.server.internal.api.HttpApiV1Util.newResponseWithErrorMessage;

import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.RequestContext;
import com.linecorp.armeria.server.annotation.ExceptionHandlerFunction;

/**
 * An {@link IllegalArgumentException} handler for HTTP API version 1.
 */
final class BadRequestHandler implements ExceptionHandlerFunction {

    @Override
    public HttpResponse handleException(RequestContext ctx, HttpRequest req, Throwable cause) {
        if (cause instanceof IllegalArgumentException) {
            if (cause.getMessage() != null) {
                return newResponseWithErrorMessage(HttpStatus.BAD_REQUEST, cause.getMessage());
            }

            return HttpResponse.of(HttpStatus.BAD_REQUEST);
        }

        return ExceptionHandlerFunction.fallthrough();
    }
}