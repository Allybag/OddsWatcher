package oddswatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BetfairMarket
{
    static class NumericCsvComparator implements Comparator<String>
    {
        public int compare(String firstRow, String secondRow)
        {
            String firstField = firstRow.substring(0, firstRow.indexOf(','));
            String secondField = secondRow.substring(0, secondRow.indexOf(','));

            double firstNum = Double.parseDouble(firstField);
            double secondNum = Double.parseDouble(secondField);
            return Double.compare(firstNum, secondNum);
        }
    }

    public BetfairMarket(BetfairMessage.MarketDefinition definition, HashMap<Long, ArrayList<PriceUpdate>> priceUpdates)
    {
        mDefinition = definition;
        mPriceUpdates = priceUpdates;
    }

    public class PricePoint
    {
        public PricePoint(long time, double price)
        {
            mTime = time;
            mPrice = price;
        }

        @Override
        public String toString()
        {
            Date calendarTime = new Date(mTime);
            return "PricePoint{" +
                    "mTime=" + mTime +
                    ", calendarTime=" + calendarTime +
                    ", mPrice=" + mPrice +
                    '}';
        }

        long mTime;
        double mPrice;
    }

    ArrayList<PricePoint> generatePriceLine(long runnerId) throws ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date marketStartTime = dateFormat.parse(mDefinition.marketTime);
        ArrayList<PricePoint> priceLine = new ArrayList<>();

        Set<Double> activePrices = new HashSet<>();
        for (PriceUpdate update : mPriceUpdates.get(runnerId))
        {
            long timeSinceStart = update.mTime - marketStartTime.getTime();
            if (timeSinceStart < 0)
                continue;

            double percentage = (1.0 / update.mPrice) * 100.0;
            if (update.mSize == 0)
                activePrices.remove(percentage);
            else
                activePrices.add(percentage);

            if (activePrices.isEmpty())
                continue;

            double bestPrice = Collections.min(activePrices);

            // Don't add duplicate points
            if (!priceLine.isEmpty() && Math.abs(bestPrice - priceLine.get(priceLine.size() - 1).mPrice) < 0.01)
                continue;

            priceLine.add(new PricePoint(timeSinceStart / 1000, bestPrice));
        }

        return priceLine;
    }

    BetfairMessage.MarketDefinition mDefinition;
    HashMap<Long, ArrayList<PriceUpdate>> mPriceUpdates;


    public void exportCsv() throws IOException, ParseException
    {
        // Csv Format should be like this:
        // Seconds,FirstHorse,SecondHorse,ThirdHorse,LastHorse
        // 0,50,,,
        // 1,,30,,
        // 1,,,20,
        // 2,,,,0

        String fileName = mDefinition.eventName + "." + mDefinition.name + ".csv";

        ArrayList<String> data = new ArrayList<>();
        StringBuilder legend = new StringBuilder("Seconds");
        int runnerCount = mDefinition.runners.size();
        for (int runnerIndex = 0; runnerIndex < runnerCount; runnerIndex++)
        {
            BetfairMessage.Runner runner = mDefinition.runners.get(runnerIndex);
            legend.append(",").append(runner.name);
            ArrayList<PricePoint> priceLine = generatePriceLine(runner.id);
            for (BetfairMarket.PricePoint pricePoint : priceLine)
            {
                String row = String.valueOf(pricePoint.mTime);
                row = row + String.join("", Collections.nCopies(runnerIndex + 1, ","));
                row = row + pricePoint.mPrice;
                row = row + String.join("", Collections.nCopies((runnerCount - (runnerIndex + 1)), ","));
                data.add(row);
            }
        }

        data.sort(new NumericCsvComparator());

        PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8);
        writer.println(legend);
        for (String row : data)
            writer.println(row);
        writer.close();
    }

}
