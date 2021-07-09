package com.hcl.day10;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkInfo implements Serializable {

	private static final long serialVersionUID = 617243358688680447L;

	private String ip;
	private long networkId;
	private int port;

	@Override
	public String toString() {
		return "NetworkInfo [ip=" + ip + ", networkId=" + networkId + ", port=" + port + "]";
	}

}
