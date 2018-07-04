import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Product
{
    private UUID id;

    protected double price;

    protected String productName;

    protected String description;

    private static long totalCount;

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static long getProductTotalCount() {
        return totalCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public Product()
    {
        id = UUID.randomUUID();
        ChangeCounters(true);
        PrintCreateInfo("Product");
    }

    public Product(Product product)
    {
        
        ChangeCounters(true);
        PrintCreateInfo("Product");
        price = product.price;
        productName = product.productName;
        description = product.description;
    }
    public abstract Product Copy();

    public void Sell(){
        ChangeCounters(false);
    }

    protected  void ChangeCounters(Boolean increase)
    {
        totalCount += increase ? 1 : -1;
    }


    protected void PrintCreateInfo(String name)
    {
        InfoPrinter.Print("Constructor of class "+ name + "executed.");
    }

    @Override
    public String toString() {

        String fullState = getClass().getName()+"{" + GetPrintablParts().stream().map(x->x.getKey()+":"+ EmptyIfNull(x.getValue())).collect(Collectors.joining("}{")) + "}";

        return fullState;
    }

    public  void RestoreFromFile(String path)
{
    try
    {
        BufferedReader reader  = new BufferedReader(new FileReader(path));
        List<String> lines= reader.lines().collect(Collectors.toList());
        reader.close();
        Parse(SplitLins(lines));

    }
    catch (Exception ex)
    {
        InfoPrinter.Print(ex.getMessage());
    }
}

    protected  void Parse(List<AbstractMap.SimpleEntry<String,String>> lines)
{
    AbstractMap.SimpleEntry<String,String> classTypeLine = lines.get(0);
    if (getClass().getName() != classTypeLine.getKey())
    {
        
    }
    else
    {
        lines.remove(classTypeLine);
    }
    AbstractMap.SimpleEntry<String,String> idLine = lines.stream()
            .filter(x -> x.getKey() == "id").findFirst().get();
    UUID id = UUID.fromString(idLine.getValue());
    if (this.id != id)
    {
        
    }

    
    AbstractMap.SimpleEntry<String,String>  priceLine = lines.stream()
            .filter(x -> x.getKey() == "price").findFirst().get();
    price = Double.parseDouble(priceLine.getValue());

    AbstractMap.SimpleEntry<String,String> nameLine = lines.stream()
            .filter(x -> x.getKey() == "productName").findFirst().get();
    productName = nameLine.getValue();

    AbstractMap.SimpleEntry<String,String> descriptionLine = lines.stream()
            .filter(x -> x.getKey() == "description").findFirst().get();
    description = descriptionLine.getValue();
}


    protected  List<AbstractMap.SimpleEntry<String,String>> SplitLins(List<String> lines)
{
    List<AbstractMap.SimpleEntry<String,String>> splitedLines = new ArrayList<AbstractMap.SimpleEntry<String,String>>();

    for (Iterator<String> i = lines.iterator(); i.hasNext();) {
        String line = i.next();
        
        String[] parts = line.split(":");
        String key = Arrays.stream(parts).findFirst().get();
        String value = null;

        switch (parts.length)
        {
            case 0:
            case 1:
                break;
            case 2:
                value = parts[1];
                break;
            default:
                value = Arrays.stream(parts).skip(1).collect(Collectors.joining(":"));
                break;
        }
        splitedLines.add(new AbstractMap.SimpleEntry<String,String>(key, value));
    }

    return splitedLines;
}

    public void SaveToFile(String path)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            writer.write(getClass().getName());
            writer.newLine();

            for (Iterator<AbstractMap.SimpleEntry<String,String>> i = GetPrintablParts().iterator(); i.hasNext();) {
                AbstractMap.SimpleEntry<String,String> line = i.next();
                writer.write(line.getKey()+":"+ EmptyIfNull(line.getValue()));
                writer.newLine();
            }
            writer.close();
        }
        catch (Exception ex)
        {
            
        }
    }
    protected String EmptyIfNull(String value){
        if(value== null){
            return "";
        }
        return value;
    }

    protected  List<AbstractMap.SimpleEntry<String,String>> GetPrintablParts()
    {
        List<AbstractMap.SimpleEntry<String,String>> parts = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
        parts.add(new AbstractMap.SimpleEntry<String,String>("id",id.toString()));
        parts.add(new AbstractMap.SimpleEntry<String,String>("productName",productName));
        parts.add(new AbstractMap.SimpleEntry<String,String>("description",description));
        parts.add(new AbstractMap.SimpleEntry<String,String>("price",Double.toString(price)));
        return parts;
    }
}