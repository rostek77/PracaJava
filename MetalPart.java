import java.util.AbstractMap;
import java.util.List;

public class MetalPart extends  Part  {
    protected String metalType;

    public String getMetalType() {
        return metalType;
    }

    public void setMetalType(String metalType) {
        this.metalType = metalType;
    }

    private static long totalCount;
    public static long getMetalPartTotalCount() {
        return totalCount;
    }
    @Override
    public long getTotalCount() {
        return totalCount;
    }

    public MetalPart()
    {
        super();
        PrintCreateInfo("MetalPart");
    }

    public MetalPart(MetalPart metalPart)
    {
        super(metalPart);
        PrintCreateInfo("MetalPart");
        metalType = metalPart.metalType;
    }
    @Override
    protected  void ChangeCounters(Boolean increase)
    {
        super.ChangeCounters(increase);
        totalCount += increase ? 1 : -1;
    }

    @Override
    protected List<AbstractMap.SimpleEntry<String,String>> GetPrintablParts()
    {
        List<AbstractMap.SimpleEntry<String,String>> parts = super.GetPrintablParts();
        parts.add(new AbstractMap.SimpleEntry<String,String>("metalType",metalType));
        return parts;
    }

    @Override
    public  Product Copy()
    {
        return new MetalPart(this);
    }

    @Override
    protected void Parse(List<AbstractMap.SimpleEntry<String,String>> lines)
    {
        super.Parse(lines);

        AbstractMap.SimpleEntry<String,String> metalTypeLine = lines.stream()
                .filter(x -> x.getKey() == "metalType").findFirst().get();
        metalType = metalTypeLine.getValue();
    }
}
