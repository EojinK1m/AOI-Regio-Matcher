package com.eojin.aoi_region_matcher.exception;

import com.eojin.aoi_region_matcher.exception.handler.BusinessException;
import com.eojin.aoi_region_matcher.exception.handler.ErrorCode;


public class BadRequestException extends BusinessException {
    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }
}
