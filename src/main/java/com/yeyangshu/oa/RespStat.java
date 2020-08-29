package com.yeyangshu.oa;

/**
 * 通用 Json 返回报文
 */
public class RespStat {

	/** 前端校验返回码 200为成功，400/500出错 */
	private int code;
	/** 状态信息 */
	private String msg;
	/** 返回数据 */
	private String data;
	
	public RespStat() {
		super();
	}
	
	public RespStat(int code, String msg, String data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public RespStat(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public static RespStat build(int code) {
		return new RespStat(200, "ok", "none");
	}

	public static RespStat build(int code, String message) {
		return new RespStat(code, message);
	}
}
