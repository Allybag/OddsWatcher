package oddswatcher;

public class PriceUpdate
{
    public PriceUpdate(UpdateType type, long time, double price, double size)
    {
        mType = type;
        mTime = time;
        mPrice = price;
        mSize = size;
    }

    public enum UpdateType
    {
        Bid,
        Ask,
        Trade
    }

    UpdateType mType;
    long mTime;
    double mPrice;
    double mSize;
}
