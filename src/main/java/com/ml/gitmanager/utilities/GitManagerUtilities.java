package com.ml.gitmanager.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class GitManagerUtilities {

	private static final Logger LOG = getPersonalizedLogger(GitManagerUtilities.class, Level.ERROR);

	private GitManagerUtilities() {
		// private constructor
	}

	public static void detailedStacktrace(Throwable t) {
		StringBuilder message = new StringBuilder(t.getMessage());
		Throwable cause = t.getCause();
		while (cause != null) {
			message.append(cause.getMessage());
			cause = cause.getCause();
		}
		if (LOG.isErrorEnabled()) {
			LOG.error(message.toString());
		}
	}

	public static String hashGenerator(String password) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				/**
				 * bytes[i] & 0xff(=255) is masking off the higher order bits. 0x100(= 256) is
				 * being added to the masked value of each element. .toString(..., 16) is
				 * ensuring that the leading 1, that was added in 2nd step is removed properly
				 * before element's value is appended to the StringBuilder instance.
				 * 
				 * thus, as a whole, this entire line of code is ensuring that the value of each
				 * element is unsigned-integer before it is converted to a string to be appended
				 * to the StringBuilder instance.
				 */
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {

			LOG.info("Exception occurred while generating hash-password");
			GitManagerUtilities.detailedStacktrace(e);

			return null;

		}
	}

	public static Logger getPersonalizedLogger(Class<?> cl, Level level) {

		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(cl);
		logger.setLevel(level);

		return logger;

	}

}
