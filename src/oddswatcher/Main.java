package oddswatcher;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException
    {
        System.setProperty("user.timezone", "UTC");
        try
        {
            BetfairMarketReader reader = new BetfairMarketReader();
            BetfairMarket market = reader.readMarketFile(args[0]);

            Chart chart = new Chart(market);
            chart.pack();
            chart.setVisible(true);

            market.exportCsv();

        } catch (IOException error) {
            System.err.println("Could not read file " + args[0]);
        }
    }
}
