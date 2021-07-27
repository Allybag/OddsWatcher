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

    @Override
    public String toString()
    {
        return "PriceUpdate{" +
                "mType=" + mType +
                ", mTime=" + mTime +
                ", mPrice=" + mPrice +
                ", mSize=" + mSize +
                '}';
    }

    UpdateType mType;
    long mTime;
    double mPrice;
    double mSize;
}
