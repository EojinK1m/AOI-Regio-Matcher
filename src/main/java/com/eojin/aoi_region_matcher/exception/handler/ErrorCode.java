package com.eojin.aoi_region_matcher.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(400, "Bad Request"),
    AOI_ALREADY_EXISTS(409, "AOI already exists"),
    REGION_ALREADY_EXISTS(409, "Region already exists"),
    NOT_FOUND(404, "Not found");

    private final int status;

    private final String message;
}