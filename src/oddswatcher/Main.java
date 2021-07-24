package oddswatcher;

import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {
        try
        {
            BetfairMarketReader reader = new BetfairMarketReader();
            reader.readMarketFile(args[0]);
        } catch (IOException error) {
            System.err.println("Could not read file " + args[0]);
        }
    }
}
