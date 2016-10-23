package org.beansugar.oauth.state;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Author archmagece
 * @CreatedAt 2016-10-23 19
 */
@Slf4j
public class DefaultStateLoader implements StateLoader{
	@Override
	public String loadState() {
		return "secret" + new Random().nextInt(999_999);
	}
}
