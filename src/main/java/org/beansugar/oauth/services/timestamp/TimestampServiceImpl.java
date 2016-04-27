package org.beansugar.oauth.services.timestamp;

import lombok.Setter;

import java.util.Random;

/**
 * Implementation of {@link TimestampService} using plain java classes.
 *
 * @author Pablo Fernandez
 */
public class TimestampServiceImpl implements TimestampService {

	@Setter
	private Timer timer;

	/**
	 * Default constructor.
	 */
	public TimestampServiceImpl() {
		timer = new Timer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNonce() {
		Long ts = getTs();
		return String.valueOf(ts + timer.getRandomInteger());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTimestampInSeconds() {
		return String.valueOf(getTs());
	}

	private Long getTs() {
		return timer.getMilis() / 1000;
	}

	/**
	 * Inner class that uses {@link System} for generating the timestamps.
	 *
	 * @author Pablo Fernandez
	 */
	static class Timer {
		private final Random rand = new Random();

		Long getMilis() {
			return System.currentTimeMillis();
		}

		Integer getRandomInteger() {
			return rand.nextInt();
		}
	}

}
