public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
    
       AbstractFactory shapeFactory = FactoryProducer.getMate(false);
            Object shape1 = shapeFactory.getShape("Table");
            shape1.getInfor();
            Object shape2 = shapeFactory.getShape("Chair");
            shape2.getInfor();
            AbstractFactory shapeFactory1 = FactoryProducer.getMate(true);
            Object shape3 = shapeFactory1.getShape("Table");
            shape3.getInfor();
            Object shape4 = shapeFactory1.getShape("Chair");
            shape4.getInfor();
       
    }
}