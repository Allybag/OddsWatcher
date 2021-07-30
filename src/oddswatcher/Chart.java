package oddswatcher;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.text.ParseException;
import java.util.ArrayList;

public class Chart extends ApplicationFrame
{
    public Chart(BetfairMarket market) throws ParseException
    {
        super(market.mDefinition.eventName);

        XYSeriesCollection chartData = new XYSeriesCollection();
        for (long runnerId : market.mPriceUpdates.keySet())
        {
            String runnerName = String.valueOf(runnerId); // Fallback
            for (BetfairMessage.Runner runner : market.mDefinition.runners)
            {
                if (runner.id == runnerId)
                    runnerName = runner.name;
            }

            XYSeries series = new XYSeries(runnerName);
            for (BetfairMarket.PricePoint pricePoint : market.generatePriceLine(runnerId))
            {
                series.add(pricePoint.mTime, pricePoint.mPrice);
            }

            chartData.addSeries(series);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                market.mDefinition.name, "Seconds since start", "% Chance of Winning", chartData, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
}
