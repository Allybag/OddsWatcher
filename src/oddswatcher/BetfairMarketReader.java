package oddswatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BetfairMarketReader
{
    BetfairMarket readMarketFile(String path) throws IOException
    {
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        String line;
        // TODO: Temporary
        int linesToRead = 10;
        while ((line = reader.readLine()) != null)
        {
            processLine(line);
            if (--linesToRead == 0)
                break;
        }

        return new BetfairMarket();
    }

    void processLine(String line)
    {
        System.out.println(line);
    }
}
