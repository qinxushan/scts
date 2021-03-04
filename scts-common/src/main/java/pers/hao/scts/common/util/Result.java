package pers.hao.scts.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "统一响应消息报文")
public class Result<T> {

	@ApiModelProperty(value = "状态码", required = true)
	private Integer code;

	@ApiModelProperty(value = "消息内容", required = true)
	private String message;

	@ApiModelProperty(value = "业务数据")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	public Result() {}

	private Result(ResultStatus resultStatus, T data) {
		this.code = resultStatus.getCode();
		this.message = resultStatus.getMessage();
		this.data = data;
	}

	/** 业务成功返回业务代码和描述信息 */
	public static Result<Void> success() {
		return new Result<>(ResultStatus.SUCCESS, null);
	}

	/** 业务成功返回业务代码,描述和返回的参数 */
	public static <T> Result<T> success(T data) {
		return new Result<>(ResultStatus.SUCCESS, data);
	}

	/** 业务成功返回业务代码,描述和返回的参数 */
	public static <T> Result<T> success(ResultStatus resultStatus, T data) {
		if (resultStatus == null) {
			return success(data);
		}
		return new Result<>(resultStatus, data);
	}

	/** 业务异常返回业务代码和描述信息 */
	public static <T> Result<T> failure() {
		return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
	}

	/** 业务异常返回业务代码,描述和返回的参数 */
	public static <T> Result<T> failure(ResultStatus resultStatus) {
		return failure(resultStatus, null);
	}

	/** 业务异常返回业务代码,描述和返回的参数 */
	public static <T> Result<T> failure(ResultStatus resultStatus, T data) {
		if (resultStatus == null) {
			return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR, null);
		}
		return new Result<>(resultStatus, data);
	}

}
