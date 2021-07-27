package oddswatcher;

import java.util.ArrayList;
import java.util.HashMap;

public class BetfairMarket
{
    public BetfairMarket(BetfairMessage.MarketDefinition definition, HashMap<Long, ArrayList<PriceUpdate>> priceUpdates)
    {
        mDefinition = definition;
        mPriceUpdates = priceUpdates;

    }

    BetfairMessage.MarketDefinition mDefinition;
    HashMap<Long, ArrayList<PriceUpdate>> mPriceUpdates;
}
