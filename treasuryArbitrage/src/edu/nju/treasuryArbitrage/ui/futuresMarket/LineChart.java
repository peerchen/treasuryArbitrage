package edu.nju.treasuryArbitrage.ui.futuresMarket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.HorizontalAlignment;

public class LineChart extends JPanel {
	private static final long serialVersionUID = 1323688315244501166L;

	@SuppressWarnings("deprecation")
	private TimeSeries timeseries = new TimeSeries("�۸�", Second.class);
	private JFreeChart jfreechart;
	
	public LineChart() {
		this.setLayout(new BorderLayout());
		
		ChartPanel chartPanel = init();
		this.add(chartPanel);
	}
	
	/**
	 * if there is only one line
	 */
	public LineChart(Color lineColor) {
		this();
		XYPlot plot = jfreechart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, lineColor);
		plot.setBackgroundPaint(Color.BLACK);
		plot.setDomainGridlinePaint(Color.RED);
		plot.setRangeGridlinePaint(Color.RED);
	}

	private XYDataset createDataset() { 
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}
	
	public void addNewPrice(double newPrice) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 8);
//		timeseries.addOrUpdate(new Second(calendar.getTime()), newPrice);
		timeseries.addOrUpdate(new Second(new Date()), newPrice);
	}
	
	private ChartPanel init() {
		XYDataset xydataset = createDataset();
		jfreechart = ChartFactory.createTimeSeriesChart(
				"��ʱ����", "", "", xydataset, true, true,true);
		ChartPanel chartPanel = new ChartPanel(jfreechart, true);
		
		jfreechart.setBackgroundPaint(Color.BLACK);
		
		// ���ñ�������
		TextTitle title = jfreechart.getTitle();
		title.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		title.setHorizontalAlignment(HorizontalAlignment.LEFT);
		title.setPaint(Color.WHITE);
		
		XYPlot xyplot = jfreechart.getXYPlot();
		xyplot.setBackgroundPaint(Color.BLACK);
		xyplot.setDomainGridlinePaint(Color.RED);
		xyplot.setRangeGridlinePaint(Color.RED);
		
		DateAxis xAxis = (DateAxis) xyplot.getDomainAxis();
		//TODO change with date
		Calendar minCalendar = Calendar.getInstance();
		minCalendar.set(Calendar.HOUR_OF_DAY, 9);
		minCalendar.set(Calendar.MINUTE, 15);
		Calendar maxCalendar = Calendar.getInstance();
		maxCalendar.set(Calendar.HOUR_OF_DAY, 15);
		maxCalendar.set(Calendar.MINUTE, 15);
		
		xAxis.setRange(minCalendar.getTime(), maxCalendar.getTime());
		xAxis.setTickUnit(new DateTickUnit(DateTickUnitType.MINUTE, 15));
		xAxis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
		xAxis.setAxisLinePaint(Color.RED);
		xAxis.setTickLabelPaint(new Color(255,43,28));
		
		NumberAxis yAxis = (NumberAxis) xyplot.getRangeAxis();
		yAxis.setAxisLinePaint(Color.RED);
		yAxis.setTickLabelPaint(new Color(255,43,28));
		//TODO
		yAxis.setRange(90.0, 100.0);
		
		//ͼ��
		jfreechart.getLegend().setVisible(false);
		return chartPanel;
	}
}