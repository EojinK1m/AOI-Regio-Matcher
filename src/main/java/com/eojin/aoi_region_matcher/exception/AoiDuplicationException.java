package com.eojin.aoi_region_matcher.exception;


import com.eojin.aoi_region_matcher.exception.handler.BusinessException;
import com.eojin.aoi_region_matcher.exception.handler.ErrorCode;

public class AoiDuplicationException extends BusinessException {
    public AoiDuplicationException() {
        super(ErrorCode.AOI_ALREADY_EXISTS);
    }
}