package oddswatcher;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        try
        {
            BetfairMarketReader reader = new BetfairMarketReader();
            BetfairMarket market = reader.readMarketFile(args[0]);

            System.out.println(market.mDefinition.runners.get(0));
            for (long runnerId : market.mPriceUpdates.keySet())
            {
                long updateCount = market.mPriceUpdates.get(runnerId).size();
                System.out.println("Runner " + runnerId + " has " + updateCount + " updates");
            }

            long key = 1; // 0-0
            for (BetfairMarket.PricePoint pricePoint : market.generatePriceLine(key))
            {
                System.out.println(pricePoint);
            }

        } catch (IOException error) {
            System.err.println("Could not read file " + args[0]);
        }
    }
}
