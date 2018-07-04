import java.util.AbstractMap;
import java.util.List;

public class BodyPart extends  MetalPart{
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private static long totalCount;

    public static long getBodyPartTotalCount() {
        return totalCount;
    }
    @Override
    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public   void ChangeCounters(Boolean increase)
    {
        super.ChangeCounters(increase);
        totalCount += increase ? 1 : -1;
    }

    public BodyPart()
    {
        super();
        PrintCreateInfo("BodyPart");
    }

    public BodyPart(BodyPart enginePart)
    {
        super(enginePart);
        PrintCreateInfo("BodyPart");
        this.color = enginePart.color;

    }

    @Override
    public List<AbstractMap.SimpleEntry<String,String>> GetPrintablParts()
    {
        List<AbstractMap.SimpleEntry<String,String>> parts = super.GetPrintablParts();
        parts.add(new AbstractMap.SimpleEntry<String,String>("color",color));
        return parts;
    }

    @Override
    public  Product Copy()
    {
        return new BodyPart(this);
    }

    @Override
    public   void Parse(List<AbstractMap.SimpleEntry<String,String>> lines)
    {
        super.Parse(lines);

        AbstractMap.SimpleEntry<String,String> colorLine = lines.stream()
                .filter(x -> x.getKey() == "color").findFirst().get();
        color = colorLine.getValue();
    }
}
