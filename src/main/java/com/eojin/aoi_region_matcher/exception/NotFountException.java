package com.eojin.aoi_region_matcher.exception;

import com.eojin.aoi_region_matcher.exception.handler.BusinessException;
import com.eojin.aoi_region_matcher.exception.handler.ErrorCode;

public class NotFountException extends BusinessException{
    public NotFountException() {
        super(ErrorCode.NOT_FOUND);
    }
}
