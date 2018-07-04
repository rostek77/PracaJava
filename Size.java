public class Size {

    private double height;

    private double width;

    private double depth;

    public double getDepth() {
        return depth;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Size()
    {
    }

    public Size(Size size)
    {
        height = size.height;
        width = size.width;
        depth = size.depth;
    }
}
