package com.ml.gitmanager.utilities;

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

	public static Logger getPersonalizedLogger(Class<?> cl, Level level) {

		ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(cl);
		logger.setLevel(level);

		return logger;

	}

}
