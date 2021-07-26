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

public class BetfairMarketReader
{
    BetfairMarket readMarketFile(String path) throws IOException
    {
        BufferedReader reader = Files.newBufferedReader(Paths.get(path));
        String line;
        while ((line = reader.readLine()) != null)
        {
            processLine(line);
        }

        return new BetfairMarket();
    }

    void processLine(String line)
    {
        Gson gson = new Gson();
        System.out.println(gson.fromJson(line, BetfairMessage.class));
    }
}
