public class IteratorPatternDemo {

    public static void main(String [] args){
        NameRepository namesRepo = new NameRepository();
        for (Iterator iter = namesRepo.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name: "+ name);
        }
    }
    
}
