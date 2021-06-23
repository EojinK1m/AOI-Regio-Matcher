package com.eojin.aoi_region_matcher.exception;

import com.eojin.aoi_region_matcher.exception.handler.BusinessException;
import com.eojin.aoi_region_matcher.exception.handler.ErrorCode;


public class BadAreaException extends BusinessException {
    public BadAreaException() {
        super(ErrorCode.BAD_REQUEST);
    }
}
