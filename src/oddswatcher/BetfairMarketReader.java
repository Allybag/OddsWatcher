package oddswatcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BetfairMarketReader
{
    BetfairMarket readMarketFile(String path) throws IOException
    {
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        Gson gson = new Gson();

        BetfairMessage.MarketDefinition definition = null;
        HashMap<Long, ArrayList<PriceUpdate>> priceUpdates = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null)
        {
            BetfairMessage message = gson.fromJson(line, BetfairMessage.class);

            assert message.mc.size() == 1;
            BetfairMessage.MarketChange mc = message.mc.get(0);
            if (mc.marketDefinition != null)
                definition = mc.marketDefinition;

            if (mc.rc != null)
            {
                long updateTime = message.pt;
                for (BetfairMessage.RunnerChange runnerChange : mc.rc)
                {
                    if (runnerChange.atb != null)
                    {
                        for (ArrayList<Double> priceSize : runnerChange.atb)
                        {
                            assert priceSize.size() == 2;

                            PriceUpdate.UpdateType type = PriceUpdate.UpdateType.Ask;
                            double price = priceSize.get(0);
                            double size = priceSize.get(0);

                            ArrayList<PriceUpdate> runnerUpdates;
                            if (priceUpdates.containsKey(runnerChange.id))
                                runnerUpdates = priceUpdates.get(runnerChange.id);
                            else
                            {
                                runnerUpdates = new ArrayList<>();
                                priceUpdates.put(runnerChange.id, runnerUpdates);
                            }

                            runnerUpdates.add(new PriceUpdate(type, updateTime, price, size));

                        }
                    }
                }
            }
        }

        assert definition != null;
        return new BetfairMarket(definition, priceUpdates);
    }
}
