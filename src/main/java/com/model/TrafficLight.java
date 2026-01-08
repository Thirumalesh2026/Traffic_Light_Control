package com.model;

public class TrafficLight {

	private LightColor color = LightColor.RED;
	public LightColor getColor() {
		return color;
	}
	public void changeTo(LightColor newColor) {
		this.color=newColor;
	}
	
}
