package jdu.sample.line;

import java.util.ArrayList;
import java.util.List;

import jdu.sample.option.Chart;
import jdu.sample.option.Legend;
import jdu.sample.option.Options;
import jdu.sample.option.PlotLines;
import jdu.sample.option.Serie;
import jdu.sample.option.Subtitle;
import jdu.sample.option.Title;
import jdu.sample.option.Tooltip;
import jdu.sample.option.XAxis;
import jdu.sample.option.YAxis;
import jdu.sample.option.YAxisTitle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/line_tempe")
public class LineTemperatureController {

	@RequestMapping(value = "")
	public String home() {
		System.out.println("LineTemperatureController: Passing through...");
		return "line_tempe";
	}

	@RequestMapping(value = "/json")
	@ResponseBody
	public Options jsonData() {
		
		float[] Tokyo = { 7.0f, 6.9f, 9.5f, 14.5f, 18.2f, 21.5f, 25.2f, 26.5f,
				23.3f, 18.3f, 13.9f, 9.6f };
		float[] NY = { -0.2f, 0.8f, 5.7f, 11.3f, 17.0f, 22.0f, 24.8f, 24.1f,
				20.1f, 14.1f, 8.6f, 2.5f };
		float[] Berlin = { -0.9f, 0.6f, 3.5f, 8.4f, 13.5f, 17.0f, 18.6f, 17.9f,
				14.3f, 9.0f, 3.9f, 1.0f };
		float[] London = { 3.9f, 4.2f, 5.7f, 8.5f, 11.9f, 15.2f, 17.0f, 16.6f,
				14.2f, 10.3f, 6.6f, 4.8f };
		List<Serie> series = new ArrayList<Serie>();
		series.add(new Serie("Tokyo",Tokyo));
		series.add(new Serie("New York",NY));
		series.add(new Serie("Berlin",Berlin));
		series.add(new Serie("London",London));
		
		List<PlotLines> plotLines = new ArrayList<PlotLines>();
		plotLines.add(new PlotLines(0, 1, "#808080"));

		Options options = new Options();
		options.setChart(new Chart("container", "line", 130, 25));
		options.setLegend(new Legend("vertical", "right", "top", -10, 100, 0));
		options.setTitle(new Title("Monthly Average Temperature", -20));
		String[] categories = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		options.setxAxis(new XAxis(categories));
		options.setyAxis(new YAxis(new YAxisTitle("Temperature (°C)"),plotLines));
		options.setSeries(series);
		options.setSubtitle(new Subtitle("Source: WorldClimate.com",-20));
		options.setTooltip(new Tooltip());
		return options;
	}

}