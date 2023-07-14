public class ShapeMaker {
    private Shape circle;
    private Shape retangle;
    private Shape square;

    public ShapeMaker(){
        circle = new Circle();
        retangle = new Rectangle();
        square = new Square();

    }
    public void drawCircle(){
        circle.draw();
    }

    public void drawSquare(){
        square.draw();
    }

    public void drawRetangle(){
        retangle.draw();
    }
    
}
