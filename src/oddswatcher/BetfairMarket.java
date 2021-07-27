package oddswatcher;

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
            return "PricePoint{" +
                    "mTime=" + mTime +
                    ", mPrice=" + mPrice +
                    '}';
        }

        long mTime;
        double mPrice;
    }

    ArrayList<PricePoint> generatePriceLine(long runnerId)
    {
        ArrayList<PricePoint> priceLine = new ArrayList<>();

        Set<Double> activePrices = new HashSet<>();
        for (PriceUpdate update : mPriceUpdates.get(runnerId))
        {
            if (update.mSize == 0)
                activePrices.remove(update.mPrice);
            else
                activePrices.add(update.mPrice);

            if (activePrices.isEmpty())
                continue;

            double bestPrice = Collections.max(activePrices);
            priceLine.add(new PricePoint(update.mTime, bestPrice));
        }

        return priceLine;
    }

    BetfairMessage.MarketDefinition mDefinition;
    HashMap<Long, ArrayList<PriceUpdate>> mPriceUpdates;


}
