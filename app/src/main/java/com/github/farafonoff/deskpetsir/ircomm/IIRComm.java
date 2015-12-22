package com.github.farafonoff.deskpetsir.ircomm;

public interface IIRComm {

	public void sendIRCode(int frequency, int[] command);
}
