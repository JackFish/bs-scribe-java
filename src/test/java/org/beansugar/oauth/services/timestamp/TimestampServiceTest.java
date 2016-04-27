package org.beansugar.oauth.services.timestamp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimestampServiceTest {

	private TimestampServiceImpl service;
	private TimestampServiceImpl.Timer timerStub;

	@Before
	public void setup() {
		service = new TimestampServiceImpl();
		TimestampServiceImpl.Timer timerStub = new TimerStub();
		service.setTimer(timerStub);
	}

	@Test
	public void shouldReturnTimestampInSeconds() {
		String expected = "1000";
		assertEquals(expected, service.getTimestampInSeconds());
	}

	@Test
	public void shouldReturnNonce() {
		String expected = "1042";
		assertEquals(expected, service.getNonce());
	}

	private static class TimerStub extends TimestampServiceImpl.Timer {

		@Override
		public Long getMilis() {
			return 1000000L;
		}

		@Override
		public Integer getRandomInteger() {
			return 42;
		}
	}
}
