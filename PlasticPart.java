import java.util.AbstractMap;
import java.util.List;

public class PlasticPart extends  Part {
    protected Boolean isToxic;

    public Boolean getToxic() {
        return isToxic;
    }

    public void setToxic(Boolean toxic) {
        isToxic = toxic;
    }

    public PlasticPart()
    {
        super();
        PrintCreateInfo("PlasticPart");
    }

    public PlasticPart(PlasticPart plasticPart)
    {
        super(plasticPart);
        PrintCreateInfo("PlasticPart");
        isToxic = plasticPart.isToxic;
    }

    private static long totalCount;

    public static long getPlasticPartTotalCount() {
        return totalCount;
    }
    @Override
    public long getTotalCount() {
        return totalCount;
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
        parts.add(new AbstractMap.SimpleEntry<String,String>("isToxic",Boolean.toString(isToxic)));
        return parts;
    }

    @Override
    public  Product Copy()
    {
        return new PlasticPart(this);
    }

    @Override
    protected  void Parse(List<AbstractMap.SimpleEntry<String,String>> lines)
    {
        super.Parse(lines);
        AbstractMap.SimpleEntry<String,String> isToxicLine = lines.stream()
                .filter(x -> x.getKey() == "isToxic").findFirst().get();
        isToxic = Boolean.parseBoolean(isToxicLine.getValue());
    }
}
