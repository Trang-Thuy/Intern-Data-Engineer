public class plasticFac extends AbstractFactory {
    @Override
    public Object getShape(String shapeType){    
       if(shapeType.equalsIgnoreCase("Table")){
          return new plasticTable();         
       }else if(shapeType.equalsIgnoreCase("Chair")){
          return new plasticChair();
       }	 
       return null;
    }
 }