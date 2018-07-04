import java.util.AbstractMap;
import java.util.List;

public class EnginePart extends  MetalPart{

    protected String engineInfo;

    public String getEngineInfo() {
        return engineInfo;
    }

    public void setEngineInfo(String engineInfo) {
        this.engineInfo = engineInfo;
    }

    private static long totalCount;

    public static long getEnginePartTotalCount() {
        return totalCount;
    }
    @Override
    public long getTotalCount() {
        return totalCount;
    }

    @Override
    protected void ChangeCounters(Boolean increase)
    {
        super.ChangeCounters(increase);
        totalCount += increase ? 1 : -1;
    }


    public EnginePart()
    {
        super();
        PrintCreateInfo("EnginePart");
    }

    public EnginePart(EnginePart enginePart)
    {
        super(enginePart);
        PrintCreateInfo("EnginePart");
        this.engineInfo = enginePart.engineInfo;

    }

    @Override
    protected List<AbstractMap.SimpleEntry<String,String>> GetPrintablParts()
    {
        List<AbstractMap.SimpleEntry<String,String>> parts = super.GetPrintablParts();
        parts.add(new AbstractMap.SimpleEntry<String,String>("engineInfo",engineInfo));
        return parts;
    }

    @Override
    public  Product Copy()
    {
        return new EnginePart(this);
    }
    @Override
    protected  void Parse(List<AbstractMap.SimpleEntry<String,String>> lines)
    {
        super.Parse(lines);

        AbstractMap.SimpleEntry<String,String> engineInfoLine = lines.stream()
                .filter(x -> x.getKey() == "engineInfo").findFirst().get();
        engineInfo = engineInfoLine.getValue();
    }
}
