package com.ml.gitmanager.exceptions;


/**
 * Extends Customized Exception class.
 */

public class UserNotFoundException extends GitManagerException {

	private static final long serialVersionUID = 7114786189459219245L;

	public UserNotFoundException(String message) {
		super(message);
	}

}