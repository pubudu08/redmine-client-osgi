package org.wso2.carbon.utility.redmine.exception;

/**
 * Created by Pubudu Dissanayake on 9/12/14.
 * pubudud@wso2.com
 */
public class GenericArtifactException extends Exception {

	private String errorCode = "Unknown_Exception";


	/**
	 * Constructor of GenericArtifactException
	 *
	 * @param message
	 * @param errorCode
	 */
	public GenericArtifactException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param message
	 * @param cause
	 * @param errorCode
	 */
	public GenericArtifactException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	/**
	 * To retrieve Error code of the exception.
	 *
	 * @return
	 */
	public String getErrorCode() {
		return this.errorCode;
	}


}
