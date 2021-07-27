package oddswatcher;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;

public class Chart extends ApplicationFrame
{
    public Chart(ArrayList<BetfairMarket.PricePoint> data)
    {
        super("OddsWatcher");
        XYSeries series = new XYSeries("Odds vs Time");
        for (BetfairMarket.PricePoint pricePoint : data)
        {
            series.add(pricePoint.mTime, pricePoint.mPrice);
        }

        XYSeriesCollection chartData = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "OddsWatcher", "Odds", "Time", chartData, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
}
