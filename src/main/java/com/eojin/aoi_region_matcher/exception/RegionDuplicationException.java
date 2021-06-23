package com.eojin.aoi_region_matcher.exception;

import com.eojin.aoi_region_matcher.exception.handler.BusinessException;
import com.eojin.aoi_region_matcher.exception.handler.ErrorCode;

public class RegionDuplicationException extends BusinessException {
    public RegionDuplicationException() {
        super(ErrorCode.REGION_ALREADY_EXISTS);
    }
}