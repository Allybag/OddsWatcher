package oddswatcher;

import java.util.ArrayList;
import java.util.HashMap;

public class BetfairMessage
{
    public class Runner
    {
        // {"status":"ACTIVE","sortPriority":1,"id":1,"name":"0 - 0"}
        String status; // TODO: Enum
        int sortPriority;
        int id;
        String name;

        @Override
        public String toString()
        {
            return "Runner{" +
                    "status='" + status + '\'' +
                    ", sortPriority=" + sortPriority +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public class MarketDefinition
    {
        // {"bspMarket":false,"turnInPlayEnabled":true,"persistenceEnabled":true,"marketBaseRate":5.0,
        // "eventId":"30395655","eventTypeId":"1","numberOfWinners":1,"bettingType":"ODDS","marketType":"CORRECT_SCORE"
        // ,"marketTime":"2021-04-01T20:00:00.000Z","suspendTime":"2021-04-01T20:00:00.000Z","bspReconciled":false,
        // "complete":true,"inPlay":false,"crossMatching":false,"runnersVoidable":false,"numberOfActiveRunners":19,
        // "betDelay":0,"status":"OPEN","runners":[{"status":"ACTIVE","sortPriority":1,"id":1,"name":"0 - 0"},{"status":"ACTIVE","sortPriority":2,"id":4,"name":"0 - 1"},{"status":"ACTIVE","sortPriority":3,"id":9,"name":"0 - 2"},{"status":"ACTIVE","sortPriority":4,"id":16,"name":"0 - 3"},{"status":"ACTIVE","sortPriority":5,"id":2,"name":"1 - 0"},{"status":"ACTIVE","sortPriority":6,"id":3,"name":"1 - 1"},{"status":"ACTIVE","sortPriority":7,"id":8,"name":"1 - 2"},{"status":"ACTIVE","sortPriority":8,"id":15,"name":"1 - 3"},{"status":"ACTIVE","sortPriority":9,"id":5,"name":"2 - 0"},{"status":"ACTIVE","sortPriority":10,"id":6,"name":"2 - 1"},{"status":"ACTIVE","sortPriority":11,"id":7,"name":"2 - 2"},{"status":"ACTIVE","sortPriority":12,"id":14,"name":"2 - 3"},{"status":"ACTIVE","sortPriority":13,"id":10,"name":"3 - 0"},{"status":"ACTIVE","sortPriority":14,"id":11,"name":"3 - 1"},{"status":"ACTIVE","sortPriority":15,"id":12,"name":"3 - 2"},{"status":"ACTIVE","sortPriority":16,"id":13,"name":"3 - 3"},{"status":"ACTIVE","sortPriority":17,"id":9063254,"name":"Any Other Home Win"},{"status":"ACTIVE","sortPriority":18,"id":9063255,"name":"Any Other Away Win"},{"status":"ACTIVE","sortPriority":19,"id":9063256,"name":"Any Other Draw"}],
        // "regulators":["MR_INT"],"countryCode":"BR","discountAllowed":false,"timezone":"GMT",
        // "openDate":"2021-04-01T20:00:00.000Z","version":3717255003,"name":"Correct Score",
        // "eventName":"Luverdense v Bragantino SP"}
        boolean bspMarket;
        boolean turnInPlayEnabled;
        boolean persistenceEnabled;
        double marketBaseRate;
        String eventId; // TODO: Enum
        String eventTypeId; // TODO: Enum
        int numberOfWinners;
        String bettingType; // TODO: Enum
        String marketType; // TODO: Enum
        String marketTime; // TODO: Date
        String suspendTime; // TODO: Date
        boolean bspReconciled;
        boolean complete;
        boolean inPlay;
        boolean crossMatching;
        boolean runnersVoidable;
        int numberOfActiveRunners;
        double betDelay;
        String status; // TODO: Enum
        ArrayList<Runner> runners;
        ArrayList<String> regulators;
        String countryCode; // TODO: Enum
        boolean discountAllowed;
        String timezone; // TODO: Timezone
        String openDate; // TODO: Date
        long version;
        String name; //TODO: Maybe Enum?
        String eventName;

        @Override
        public String toString()
        {
            return "MarketDefinition{" +
                    "bspMarket=" + bspMarket +
                    ", turnInPlayEnabled=" + turnInPlayEnabled +
                    ", persistenceEnabled=" + persistenceEnabled +
                    ", marketBaseRate=" + marketBaseRate +
                    ", eventId='" + eventId + '\'' +
                    ", eventTypeId='" + eventTypeId + '\'' +
                    ", numberOfWinners=" + numberOfWinners +
                    ", bettingType='" + bettingType + '\'' +
                    ", marketType='" + marketType + '\'' +
                    ", marketTime='" + marketTime + '\'' +
                    ", suspendTime='" + suspendTime + '\'' +
                    ", bspReconciled=" + bspReconciled +
                    ", complete=" + complete +
                    ", inPlay=" + inPlay +
                    ", crossMatching=" + crossMatching +
                    ", runnersVoidable=" + runnersVoidable +
                    ", numberOfActiveRunners=" + numberOfActiveRunners +
                    ", betDelay=" + betDelay +
                    ", status='" + status + '\'' +
                    ", runners=" + runners +
                    ", regulators=" + regulators +
                    ", countryCode='" + countryCode + '\'' +
                    ", discountAllowed=" + discountAllowed +
                    ", timezone='" + timezone + '\'' +
                    ", openDate='" + openDate + '\'' +
                    ", version=" + version +
                    ", name='" + name + '\'' +
                    ", eventName='" + eventName + '\'' +
                    '}';
        }
    }

    public class RunnerChange
    {
        // [{"atb":[[1.23,28.95]],"id":1},{"atb":[[4.1,26.39]],"id":16},{"atb":[[8.8,25.54]],"id":15},{"atb":[[16.5,34.06]],"id":14},{"atb":[[18.5,33.21]],"id":13},{"atb":[[8.8,27.25]],"id":12},{"atb":[[17,5.95]],"id":9063256},{"atb":[[5.1,31.5]],"id":11},{"atb":[[2.88,33.21]],"id":10},{"atb":[[2.34,28.95]],"id":9},{"atb":[[6,34.06]],"id":8},{"atb":[[6.2,30.65]],"id":7},{"atb":[[4.2,28.1]],"id":6},{"atb":[[3.6,30.65]],"id":5},{"atb":[[2.74,29.8]],"id":4},{"atb":[[2.82,32.35]],"id":3},{"atb":[[2.64,28.95]],"id":2}]
        long id;
        ArrayList<ArrayList<Double>> atb;
        ArrayList<ArrayList<Double>> atl;

        @Override
        public String toString()
        {
            return "RunnerChange{" +
                    "id=" + id +
                    ", atb=" + atb +
                    ", atl=" + atl +
                    '}';
        }
    }

    public class MarketChange
    {
        //" mc":[{"id":"1.181368193","marketDefinition":{"bspMarket":false,"turnInPlayEnabled":true,"persistenceEnabled":true,"marketBaseRate":5.0,"eventId":"30395655","eventTypeId":"1","numberOfWinners":1,"bettingType":"ODDS","marketType":"CORRECT_SCORE","marketTime":"2021-04-01T20:00:00.000Z","suspendTime":"2021-04-01T20:00:00.000Z","bspReconciled":false,"complete":true,"inPlay":false,"crossMatching":false,"runnersVoidable":false,"numberOfActiveRunners":19,"betDelay":0,"status":"OPEN","runners":[{"status":"ACTIVE","sortPriority":1,"id":1,"name":"0 - 0"},{"status":"ACTIVE","sortPriority":2,"id":4,"name":"0 - 1"},{"status":"ACTIVE","sortPriority":3,"id":9,"name":"0 - 2"},{"status":"ACTIVE","sortPriority":4,"id":16,"name":"0 - 3"},{"status":"ACTIVE","sortPriority":5,"id":2,"name":"1 - 0"},{"status":"ACTIVE","sortPriority":6,"id":3,"name":"1 - 1"},{"status":"ACTIVE","sortPriority":7,"id":8,"name":"1 - 2"},{"status":"ACTIVE","sortPriority":8,"id":15,"name":"1 - 3"},{"status":"ACTIVE","sortPriority":9,"id":5,"name":"2 - 0"},{"status":"ACTIVE","sortPriority":10,"id":6,"name":"2 - 1"},{"status":"ACTIVE","sortPriority":11,"id":7,"name":"2 - 2"},{"status":"ACTIVE","sortPriority":12,"id":14,"name":"2 - 3"},{"status":"ACTIVE","sortPriority":13,"id":10,"name":"3 - 0"},{"status":"ACTIVE","sortPriority":14,"id":11,"name":"3 - 1"},{"status":"ACTIVE","sortPriority":15,"id":12,"name":"3 - 2"},{"status":"ACTIVE","sortPriority":16,"id":13,"name":"3 - 3"},{"status":"ACTIVE","sortPriority":17,"id":9063254,"name":"Any Other Home Win"},{"status":"ACTIVE","sortPriority":18,"id":9063255,"name":"Any Other Away Win"},{"status":"ACTIVE","sortPriority":19,"id":9063256,"name":"Any Other Draw"}],"regulators":["MR_INT"],"countryCode":"BR","discountAllowed":false,"timezone":"GMT","openDate":"2021-04-01T20:00:00.000Z","version":3717255003,"name":"Correct Score","eventName":"Luverdense v Bragantino SP"},"rc":[],"con":true,"img":false}]}
        String id;
        MarketDefinition marketDefinition;
        ArrayList<RunnerChange> rc;

        @Override
        public String toString()
        {
            return "MarketChange{" +
                    "id='" + id + '\'' +
                    ", marketDefinition=" + marketDefinition +
                    ", rc=" + rc +
                    '}';
        }
    }

    String op;
    String clk;
    long pt;
    ArrayList<MarketChange> mc;

    @Override
    public String toString()
    {
        return "BetfairMessage{" +
                "op='" + op + '\'' +
                ", clk='" + clk + '\'' +
                ", pt=" + pt +
                ", mc=" + mc +
                '}';
    }
}
