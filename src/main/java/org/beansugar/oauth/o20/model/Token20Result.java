package org.beansugar.oauth.o20.model;

import lombok.Getter;
import org.beansugar.oauth.o20.type.AccessTokenType;

import java.io.Serializable;

/**
 * @author archmagece
 * @date 2015-11-18
 */
@Getter
public class Token20Result implements Serializable {

	private String accessToken;
	private int expireIn;
	private AccessTokenType type;
	private String idToken;
	private String refreshToken;

	public Token20Result(String accessToken, int expireIn){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.type = AccessTokenType.BEARER;
	}

	public Token20Result(String accessToken, int expireIn, AccessTokenType type){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.type = type;
	}

	public Token20Result(String accessToken, int expireIn, AccessTokenType type, String idToken, String refreshToken){
		this.accessToken = accessToken;
		this.expireIn = expireIn;
		this.type = type;
		this.idToken = idToken;
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Token20Result{");
		sb.append("accessToken='").append(accessToken).append('\'');
		sb.append(", expireIn=").append(expireIn);
		sb.append(", type=").append(type);
		sb.append(", idToken='").append(idToken).append('\'');
		sb.append(", refreshToken='").append(refreshToken).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
