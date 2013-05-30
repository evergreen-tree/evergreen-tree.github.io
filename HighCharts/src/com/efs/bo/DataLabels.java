package com.efs.bo;

public class DataLabels {
	private boolean enabled;
	private String color = "#000000";
	private String connectorColor = "#000000";
	private String formatter;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getConnectorColor() {
		return connectorColor;
	}

	public void setConnectorColor(String connectorColor) {
		this.connectorColor = connectorColor;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
}
