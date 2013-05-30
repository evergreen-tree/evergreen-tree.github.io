package jdu.sample.option;

public class Tooltip {

    String formatter = "function() { " +
    		"return '<b>'+ this.series.name +'</b><br/>'+ " +
    		"this.x +': '+ this.y +'°C';}";

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
}
