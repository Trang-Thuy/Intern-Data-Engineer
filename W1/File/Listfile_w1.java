import java.io.File;

public class Listfile_w1 {
    public void printFileNames(File[] a, int i) {
        if (i == a.length)
            return;

        if (a[i].isFile())
            System.out.println(a[i].getName());

        printFileNames(a, i + 1);
    }

    public static void main(String[] argvs) {
        File fObj = new File("D:\\Downloads\\Session_3_Assignment");
        Listfile_w1 obj = new Listfile_w1();
        if (fObj.exists() && fObj.isDirectory()) {
            File a[] = fObj.listFiles();
            obj.printFileNames(a, 0);
        }
    }
}