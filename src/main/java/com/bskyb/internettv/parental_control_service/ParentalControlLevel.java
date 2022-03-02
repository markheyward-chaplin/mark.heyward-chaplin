package com.bskyb.internettv.parental_control_service;

public enum ParentalControlLevel {
	U("U", 1), PG("PG", 2), TWELVE("12", 3), FIFTEEN("15", 4), EIGHTEEN("18", 5);

	private String level;
	private int value;

	private ParentalControlLevel(String level, int value) {
		this.level = level;
		this.value = value;
	}

	public String getLevel() {
		return level;
	}

	public int getValue() {
		return value;
	}
}