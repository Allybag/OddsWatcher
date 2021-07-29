package oddswatcher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BetfairMarket
{
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
        System.out.println(marketStartTime);
        ArrayList<PricePoint> priceLine = new ArrayList<>();

        Set<Double> activePrices = new HashSet<>();
        boolean firstPrice = false;
        boolean goalScored = false;
        for (PriceUpdate update : mPriceUpdates.get(runnerId))
        {
            Date updateTime = new Date(update.mTime);
            long timeSinceStart = updateTime.getTime() - marketStartTime.getTime();
            assert updateTime.getTime() == update.mTime;
            if (timeSinceStart < 0)
                continue;

            if (!firstPrice)
            {
                System.out.println("Received first in play price at " + updateTime);
                firstPrice = true;
            }

            double percentage = (1.0 / update.mPrice) * 100.0;
            if (update.mSize == 0)
                activePrices.remove(percentage);
            else
                activePrices.add(percentage);

            if (activePrices.isEmpty())
                continue;

            double bestPrice = Collections.min(activePrices);
            priceLine.add(new PricePoint(timeSinceStart / 1000, bestPrice));

            if (!goalScored && bestPrice < 0.2)
            {
                System.out.println("Goal! Scored at " + updateTime);
                goalScored = true;
            }

            System.out.println("BestPrice: " + bestPrice + " at " + updateTime);
        }

        return priceLine;
    }

    BetfairMessage.MarketDefinition mDefinition;
    HashMap<Long, ArrayList<PriceUpdate>> mPriceUpdates;


}
