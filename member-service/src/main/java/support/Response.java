package support;

import org.springframework.lang.Nullable;

public class Response<T> {

	private int code;

	private String message;

	private T result;

	public Response(ResponseCode code, String message) {
		this.code = code.value();
		this.message = message;
	}

	public Response(ResponseCode code, String message, @Nullable T result) {
		this.code = code.value();
		this.message = message;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code.value();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(@Nullable T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Response{"
			+ "code=" + code
			+ ", message='" + message + '\''
			+ ", result=" + result
			+ '}';
	}

}
