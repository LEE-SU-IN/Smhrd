package view;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart {
	
	//createSeries() �ø��� ����
	public XYSeries createSeries(int[] data, String series_name) {
		XYSeries series = new XYSeries(series_name);
		for(int i=0; i<5; i++) {
			series.add(i+1,data[i]);
		}
		return series;
	}
	
	public ChartPanel getChartPanel(XYSeries series1, XYSeries series2) {
		ChartPanel chartPanel = null;
		XYDataset dataset = null;
		
		//chart ����
		JFreeChart chart = ChartFactory.createXYLineChart("��������ǰ/���ã����ǰ", "��",  "��ǰ���� + �����������������(��)", dataset, PlotOrientation.VERTICAL, true, true, false);
		//title, xAxisLabel, yAxisLabel, dataset, orientation, legend, tooltips, urls
		
		//������ �߰�
		TextTitle subTitle = new TextTitle("���ϱ�");
		chart.addSubtitle(subTitle);
		
		//��Ʈ ��Ʈ, ���� ����
		Font f = new Font("Gulim", Font.BOLD, 14);
		chart.getTitle().setFont(f);
		chart.getLegend().setItemFont(f);
		chart.setBackgroundPaint(Color.white);
		
		//dataset ����
		XYSeriesCollection dataset1 = new XYSeriesCollection();
		XYSeriesCollection dataset2 = new XYSeriesCollection();
		dataset1.addSeries(series1);
		dataset1.addSeries(series2);

		//��Ʈ�� plot�ϱ�
		XYPlot plot = chart.getXYPlot();
		plot.setDataset(0,dataset1);
		plot.setDataset(1,dataset2);
		
		//�� ���� ���� ������ ����
		plot.setRenderer(0, new XYSplineRenderer());
		XYSplineRenderer sp = new XYSplineRenderer();
		sp.setSeriesFillPaint(0, Color.RED);
		sp.setSeriesFillPaint(1, Color.BLUE);
		
		//plot ��Ʈ ����
		plot.getDomainAxis().setLabelFont(f);
		plot.getDomainAxis().setTickLabelFont(f);
		plot.getRangeAxis().setLabelFont(f);
		plot.getRangeAxis().setTickLabelFont(f);
		
		chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
}
