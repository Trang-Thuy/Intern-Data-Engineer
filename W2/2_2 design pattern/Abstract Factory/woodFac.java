public class woodFac extends AbstractFactory {
    @Override
    public Object getShape(String shapeType){    
       if(shapeType.equalsIgnoreCase("Table")){
          return new woodTable();         
       }else if(shapeType.equalsIgnoreCase("Chair")){
          return new woodChair();
       }	 
       return null;
    }
 }