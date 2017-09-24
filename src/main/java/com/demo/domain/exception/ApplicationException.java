package com.demo.domain.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String responseCode = null;
    private String responseDetail = null;
    private String responseMsg = null;

    public ApplicationException(String responseCode, String responseDetail, String responseMsg){
        this.setResponseCode(responseCode);
        this.setResponseDetail(responseDetail);
        this.setResponseMsg(responseMsg);
    }

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDetail() {
		return responseDetail;
	}

	public void setResponseDetail(String responseDetail) {
		this.responseDetail = responseDetail;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
}
