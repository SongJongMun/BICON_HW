package com.example.bicon_hw;

import com.radiusnetworks.ibeacon.IBeacon;

public class beacon extends IBeacon{
	public int major = 4000;
	public int minor = 56601;
	public String proximityUuid = "00000000-0000-0000-0000-000000000000";
	
	@Override
	public int getMajor(){
		return this.major;
	}
	
	@Override
	public int getMinor(){
		return this.minor;
	}
	
	@Override
	public String getProximityUuid() {
		return proximityUuid;
	}
}
