/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package pers.hao.scts.common.exception;

import lombok.Data;
import pers.hao.scts.common.util.ResultStatus;

/**
 * 自定义异常
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class ResultException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ResultStatus resultStatus;

	public ResultException() {
		this(ResultStatus.INTERNAL_SERVER_ERROR);
	}
    
    public ResultException(ResultStatus resultStatus) {
    	super(resultStatus.getMessage());
		this.resultStatus = resultStatus;
	}

}
