import java.util.AbstractMap;
import java.util.List;

public class Part extends  Product
{
    protected Size size;

    public Size getSize() {
        return size;
    }

    protected void setSize(Size size) {
        this.size = size;
    }

    public Part()
    {
        super();
        setSize(new Size());
        PrintCreateInfo("Part");
    }
    public Part(Part part)
    {
        super(part);
        setSize(new Size(part.getSize()));
        PrintCreateInfo("Part");
    }

    private static long totalCount;

    public static long getPartTotalCount() {
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
    public  Product Copy()
    {
        return new Part(this);
    }

    @Override
    protected List<AbstractMap.SimpleEntry<String,String>> GetPrintablParts()
    {
        List<AbstractMap.SimpleEntry<String,String>> parts = super.GetPrintablParts();

        //  za pobieranie tych wartości mogła by odpoiwadać klasa Size, umiesczono tu dla uproszczenia,
        //dodatkowo jeśli przeniósł bym tą funkcjonalnośc do klasy Size to podobnie  trzeba byłoby zrobć z zapisem stanu do pliku
        // a w taki przypadku sam zapis powinien być zaimplementowany w osobnej klasie np FilesHandler/Serializer lub jak sobie kto nazwe wybierze
        //dodatkowo wystąpi problem jak któraś z klas dziedziczacych będzie miała propertę np Width

        parts.add(new AbstractMap.SimpleEntry<String,String>("width",Double.toString(size.getWidth())));
        parts.add(new AbstractMap.SimpleEntry<String,String>("height",Double.toString(size.getHeight())));
        parts.add(new AbstractMap.SimpleEntry<String,String>("depth",Double.toString(size.getDepth())));

        return parts;
    }

    @Override
    protected  void Parse(List<AbstractMap.SimpleEntry<String,String>> lines)
    {
        super.Parse(lines);

        AbstractMap.SimpleEntry<String,String> widthLine = lines.stream()
                .filter(x -> x.getKey() == "width").findFirst().get();
        size.setWidth(Double.parseDouble(widthLine.getValue()));

        AbstractMap.SimpleEntry<String,String> heightLine = lines.stream()
                .filter(x -> x.getKey() == "height").findFirst().get();
        size.setHeight(Double.parseDouble(heightLine.getValue()));

        AbstractMap.SimpleEntry<String,String> depthLine = lines.stream()
                .filter(x -> x.getKey() == "depth").findFirst().get();
        size.setDepth(Double.parseDouble(depthLine.getValue()));

    }

}
