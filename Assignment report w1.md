# Báo cáo tuần 1 
### **YÊU CẦU**
### 1. Mô tả: xây dựng chương trình java bất kì có xử dụng đầy đủ 4 tính chất của OOP
Tham khảo: [Java Object Oriented Programming concepts - w3resource](https://www.w3resource.com/java-tutorial/java-object-oriented-programming.php) 
Điều kiên hoàn thành: Từ chương trình đã xây dựng trình bày về đã áp dụng oop như nào, (2) hiểu các khái niệm interface,static,.... 
### 2. Đọc ghi file viết chương trình java đọc ghi file theo 2 dạng binary và text viết chương trình java thao tác với file và thư mục: list các file, đọc nội dung file
### **NỘI DUNG**
### 1. Xây dựng chương trình java có đầy đủ 4 tính chất của Lập trình hướng đối tượng (OOP)
#### 1.1. Cơ sở lý thuyết
#### 1.1.1. Bốn tính chất của lập trình hướng đối tượng
Lập trình hướng đối tượng là một kỹ thuật lập trình cho phép lập trình viên tạo ra các đối tượng để trừu tượng hóa thực thể trong cuộc sống. Bốn tính chất quan trọng của lập trình hướng đối tượng là: Tính đóng gói, Tình trừu tượng, Tính đa hình và Tính kế thừa.

![Hình ảnh 1](https://images.viblo.asia/e8e24a71-54dd-4b1d-94c0-3862a46683b9.png)

a.  _Tính đóng gói (Encapsulation)_ _:_ Đây là tính chất được sử dụng để che đi những phương thức, hành vi xử lý bên trong của đối tượng.
 
b. _Tính kế thừa (Inheritance)_ _:_ Tính kế thừa là kỹ thuật cho phép chúng ta kế thừa lại các phương thức mà một đối tượng khác đã có. Tính chất này giúp chúng ta tránh việc lặp lại các đoạn code một cách dư thừa. Thay vì vậy, để xử lý các công việc tương tự thì lập trình viên có thể kế thừa lại đoạn code có sẵn.

Có các cấp độ kế thừa sau:
- Kế thừa một cấp (Single level Inheritance): với một object/class cha và một object/class con
- Kế thừa nhiều cấp (Multiple level Inheritance): Kế thừa nhiều object/class để sử dụng các phương thức của các object/class cha.

c. _Tính đa hình (Polymorphism)_ _:_ 
Khi sử dụng tính đa hình, một đối tượng (object) thuộc các lớp khác nhau có thể hiểu cùng một thông điệp theo các cách khác nhau.
Có hai cách để thể hiện tính đa hình:
- Method (Overloading): nạp chồng các phương thức có cùng tên phương thức, nhưng có số lượng tham số khác nhau
- Method Overridding: ghi dè lại các phương thức ảo (method được khai báo với từ khóa virtual) của một class cha. Phương pháp này sử dụng hai từ khóa là *virtual* và *override*. Trong đó, *virtual* là từ khóa sử dụng để khai báo một phương thức ảo. Còn từ khóa *override* được sử dụng dể đánh dấu phương thức ghi đè lên phương thức cha.

d. _Tính trừu tượng (Abstraction)_ _:_ Tính trừu tượng định nghĩa những hành vi (behavior) và tính chất của một loại đối tượng. 

#### 1.1.2. Khái niệm interface
Dữ liệu trừu tượng là quy trình che giấu những chi tiết nhất định và chỉ trình bày những thông tin cần thiết với người dùng. Trừu tượng hóa có thể được sử dụng thông qua lớp trừu tượng (_abstract classes_) và giao diện (_interfaces_).
Interface là một lớp trừu tượng được sử dụng để nhóm các phương thức liên quan nhau với phần nội dung (body method) là rỗng.
Tương tự như lớp trừu tượng, ta không thể tạo các object từ interface. Để truy cập phương thức giao diện, giao hiện cần phải kế thừa (_implemented_) từ các lớp khác với từ khóa _implemented_. Phần nội dung của phương thức sẽ được cung cấp bởi các lớp _implement_. Khi kế thừa các phương thức của giao diện, người dùng cần phải ghi đè (_override_) tất cả các phương thức.

Các lưu ý khi sử dụng interface:
- Các phương thức của giao diện được đặt mặc định là *abstract* hoặc *public*
- Các thuộc tính giao diện được đặt mặc định là *public*, *static* và *final*.
- Một giao diện không thể có một phương thức *constructor* (Contrustor là phương thức để tạo đối tượng).
- Người dùng có thể sử dụng giao diện để nâng cao tính bảo mật bằng việc che giấu những chi tiết nhất định, và chỉ trình bày những chi tiết quan trọng của object.
#### 1.1.3. Khái niệm static/public
Trong lập trình hướng đối tượng, các chương trình thường sử dụng thuộc tính hay phương thức loại *static* hoặc *public*.
a. _Các thuộc tính, phương thức kiểu static_: 
Với các thuộc tính và phương thức kiểu static, người dùng có thể truy cập để sử dụng thuộc tính, phương thức mà không cần tạo ra một object của lớp
b. _Các thuộc tính, phương thức kiểu public_:
Ngược lại với kiểu *static* trên, người dùng chỉ có thể truy cập, gọi đến các thuộc tính, phương thức kiểu này khi và chỉ khi truy cập qua object của lớp.
#### 1.2. Cài đặt chương trình java có đầy đủ 4 tính chất
*File chương trình: Oop_w1.java*
4 Tính chất của Lập trình hướng đối tượng trong chương trình:
-  **Tính trừu tượng**: class Object là một lớp trừu tượng. Lớp này có một phương thức abstract là phương thức `PersonIntro()`
```java
abstract  class  Object{
	Integer  age; 
	public  Integer  getYob() {
		Integer  Yob  =  2023  -  age;
		return  Yob;
	}
	abstract  void  PersonIntro();
}
```
Từ abstract class Object này, lớp *Person* sẽ implement (kế thừa) từ lớp *Object* để định nghĩa lại phương thức `PersonIntro()`.
```java
class  Person  extends  Object
	String  name;
	public  void  PersonIntro() {
		System.out.println(name  +  " was born in "  +  getYob());
	}
}
```
- **Tính đóng gói**: Khi người dùng gọi phương thức `EditInfor`, kết quả trả về là thông báo đã thay đổi thông tin (bao gồm tên và tuổi) các đối tượng. Người dùng không biết bên trong phương thức `EditInfor` gồm những chi tiết nào.
```java
class  Person  extends  Object{
	String  name;
	public  void  EditInfor(String  newName, Integer  newAge) {
		name  =  newName;
		age  =  newAge;
		System.out.println("--Information changed!--");
}
```
Phương thức `EditInfor` thuộc class Person
``` java
public  class  classroom {
	public  static  void  main(String[] args) {
		Student  stu  =  new  Student("John", 12, "Computer Science");
		stu.PersonIntro();
		stu.EditInfor("Alice", 11);
	}
}
``` 
Phương thức `EditInfor` được gọi để thay đổi thông tin người dùng 
- **Tính đa hình**: Lớp Person có một phương thức là `PersonIntro()` in ra các thông tin của đối tượng. Các lớp con kế thừa lớp *Person* là lớp Student và lớp Teacher cũng có phương thức `PersonIntro()` của riêng các lớp đó.
```java
class  Person  extends  Object{
	public  void  PersonIntro() {
		System.out.println(name  +  " was born in "  +  getYob());
}
```
Phương thức `PersonIntro()` trong class Person
```java
class  Teacher extends  Person { 
	public  void  PersonIntro() {
		System.out.println(name  +  " was born in "  +  getYob() +  ". He/She is a "  +  qualifi);
	}
}
```
Phương thức `PersonIntro()` trong class Teacher
```java
class  Student  extends  Person { 
	public  void  PersonIntro() {
		System.out.println(name  +  " was born in "  +  getYob() +  ". He/She is a student");
	}
}
```
Phương thức `Person Intro()` trong class Student
- **Tính kế thừa**: class Student, Teacher kế thừa các thuộc tính, phương thức của class Person. 
Ví dụ: class Student và Teacher kế thừa phương thức `sayHi` của class Person
```java
class  Person extends Object {
	public  void  sayHi() {
		System.out.println("Hello");
	}
}
```
Phương thức sayHi trong lớp Person
```java
public  class  classroom {
	public  static  void  main(String[] args) {
		Teacher  tch  =  new  Teacher("Alice", 50, "Marketing", "Professional");
		tch.sayHi();
	}
}
```
Class Teacher kế thừa phương thức này từ class Person.
### 2. Xây dựng chương trình java đọc ghi file
Lớp File trong package ```java.io``` cho phép người dùng có thể làm việc với tệp tin. Để sử dụng lớp File, cần tạo một object thuộc class và một tên file hay đường dẫn cụ thể. Lớp File có nhiều phương thức cung cấp cho người dùng có thể tạo, lấy nhiều thông tin từ tệp tin như: *canRead()*, *canWrite()*, *getName()*, *length()*...

#### 2.1. Chương trình java đọc file 
Người dùng sử dụng lớp *Scanner* để đọc nội dung trong file. Ngoài ra, cần sử dụng thêm package ```java.io.FileNotFoundException``` để xử lý các lỗi có thể xảy ra trong quá trình thao tác với file. Vậy đầu tiên, để cài đặt một chương trình java đọc file, người dùng cần cài đặt thêm các package sau:
```java
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
```
#### 2.1.1. Cài đặt chương trình java đọc file theo dạng binary
Có hai cách để đọc và ghi file dạng binary: sử dụng API kế thừa File I/O và API new File I/O (NIO)
API kế thừa là một lớp trong package `java.io` , là một lớp thể theo tác các hoạt động I/O bậc thấp (low - level) kiểu nhị phân như đọc và ghi mỗi lần một byte.
API new File (NIO) nhanh hơn API I/O cũ.
a. _Sử dụng API File I/O cũ_
Lớp trừu tượng (abstract class) *Inputstream* cung cấp  hai phương thức cơ bản để đọc các byte từ dữ liệu đầu vào:
- `read`: đọc một byte dữ liệu, trả về byte là giá trị nguyên. Khi đến cuối file dữ liệu, trả về -1.
- `read(byte[])`: đọc một chuỗi các byte từ một mảng byte cụ thể. Tương tự, phương thức này sẽ trả về giá trị -1 khi không còn dữ liệu hoặc khi đã đọc đến cuối file.
Lớp trừu tượng *OutputStream* định nghĩa hai phương thức cơ phản để ghi các byte vào file dữ liệu đầu ra:
- `write(int)`: viết byte cụ thể lên dữ liệu đầu ra
-  `write(byte[])`: viết các byte của một mảng cụ thể lên dữ liệu đầu ra.
b. _Sử dụng NIO_
Lớp File được cung cấp hai phương thức để đọc ghi file dạng binary:
- `readAllBytes(path)`: đọc toàn bộ byte từ một file dữ liệu và trả về một mảng các byte. Phương thức này dành cho các tệp có kích thước nhỏ.
- `write(path, byte[] bytes, Open Option...)`: ghi một mảng byte vào một file với các tham số tùy chọn. Các tham số chính trong phương thức: CREATE, TRUNCATE_EXISTING, WRITE, APPEND...
Cả hai phương thức này đều đóng tệp sau khi thực hiện xong và ném ra ngoại lệ IOEException nếu có lỗi.
*File chương trình: Readbinary_w1.java*
```java
try {
	byte[] allBytes  = Files.readAllBytes(Paths.get(inputFile));
	Files.write(Paths.get(outputFile), allBytes);
	} 
catch (IOException  ex) {
	ex.printStackTrace();
}
```
Hai phương thức `ReadAllBytes` và `write` của lớp File được sửu dụng để đọc toàn bộ dữ liệu từ file và ghi dữ liệu.

#### 2.2. Cài đặt chương trình java đọc file theo dạng text
*File chương trình: Readtext_w1.java*
```java
File  myObj  =  new  File("data.txt");
Scanner  myReader  =  new  Scanner(myObj);
while (myReader.hasNextLine()) {
String  data  =  myReader.nextLine();
```
Hai phương thức `hasNextLine()` và `nextLine()` của class Scanner được sử dụng để đọc nội dung file _data.txt_ dạng text.
#### 2.3. Cài đặt chương trình Java thao tác với file và thư mục: list các file, đọc nội dung file
*File chương trình: Listfile_w1.java*
Đầu tiên, chương trình cần kiểm tra điều kiện thư mục có phải là một đường dẫn hay có tồn tại bằng hai phương thức `exists()` và `isDirectory()`của class *File*. Sau đó, lấy danh sách các file trong thư mục bằng cách sử dụng hàm `listFiles()`.
```java
public  static  void  main(String[] argvs) {
	File  fObj  =  new File("D:\\Downloads\\Session_3_Assignment");
	w1  obj  =  new  w1();
	if(fObj.exists() &&  fObj.isDirectory()){
		File  a[] =  fObj.listFiles();
		obj.printFileNames(a, 0);
	}
}
```
Sử dụng phương thức `printFileNames` để in trên của các file trong thư mục với hai tham số đầu vào: a là mảng danh sách file trong thư mục và i là số thứ tự (index) trong mảng. Khởi tạo i bằng 0, i tăng dần. Điều kiện dừng: chỉ số i bằng độ dài mảng a.
```java
public  void  printFileNames(File[] a, int  i) {
	if(i  ==  a.length)
		return;
	if(a[i].isFile())
		System.out.println(a[i].getName());
		printFileNames(a, i  +  1);
}
```

### 3. Tài liệu tham khảo
##### 1. [Java Object Oriented Programming concepts](https://www.w3resource.com/java-tutorial/java-object-oriented-programming.php)
##### 2. [W3 School: Java Read Files](https://www.w3schools.com/java/java_files_read.asp)
##### 3.  [W3 School: Java Interface](https://www.w3schools.com/java/java_interface.asp)
##### 4. [How to Read and Write Binary Files in Java (codejava.net)](https://www.codejava.net/java-se/file-io/how-to-read-and-write-binary-files-in-java)
