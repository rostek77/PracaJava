import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inventory {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    private void setProducts(List<Product> products) {
        this.products = products;
    }

    public Inventory()
    {
        setProducts(new ArrayList<Product>());
    }

    public void DuplcatePart(Product product)
    {
        DuplcatePart( product, 1);
    }

    public void DuplcatePart(Product product, int numberOfCopies )
    {
        for(int i=0;i<numberOfCopies;i++)
        {
            products.add(product.Copy());
        }
    }

    public void Sell(Product part)
    {
        part.Sell();
        products.remove(part);
    }

    public void Sell(UUID id)
    {
        Sell(GetProduct(id));
    }

    public Product GetProduct(UUID id)
    {
        Product part = products.stream().filter(x -> x.getId() == id).findFirst().get();
        return part;
    }

    public void SaveToFile(Product product, String path)
    {
        product.SaveToFile(path);
    }

    public void RestoreFromFile(Product product, String path)
    {
        product.RestoreFromFile(path);
    }

}
