public class FactoryProducer {
    public static AbstractFactory getMate(boolean wood){   
       if(wood){
          return new woodFac();         
       }else{
          return new plasticFac();
       }
    }
 }