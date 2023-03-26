package com.thebestgroup.teamquest.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(Integer code,
                                String status,
                                String message) {}