
# Bài tập tuần 2
## Yêu cầu:
### 1 Collection(s)
- Mô tả: viết chương trình java sử dụng các cấu trúc dữ liệu HashMap, HashSet, ArrayList  
> Tham khảo (Overview)  [Java Collection](http://cs.lmu.edu/~ray/notes/collections/) (compare) 
> [Java Core](http://www.codejava.net/java-core/collections/java-collections-framework)  (performance) 
[Information Tech Gems](http://infotechgems.blogspot.com/2011/11/java-collections-performance-time.html) 
- Điều kiện hoàn thành: Cần nắm được HashMap, HashSet, ArrayList là gì, cách phương thức sử dụng ra sao, so sánh các đặc điểm. Nắm được khái niệm hashcode, equals và lấy ví dụ minh họa sử dụng của hashcode, equal trong Set.

### 2. Design Pattern

- Đọc hiểu các Design Pattern cơ bản, lựa chọn ít nhất 3 Design Pattern để implement bằng java và trình bày lại

### 3. Serialize

- tìm hiểu liên quan serialize trong java. (viết code ví dụ minh họa bằng java và giải thích code) (phần này có chút liên quan tới trên cơ sở kiến thức từ 1.2, thực tập sinh có thể code liên hệ với code cũ)
## Nội dung
### 1. Collection(s)
#### 1.1. Cấu trúc dữ liệu HashMap
*a. Giới thiệu*

**HashMap** là một lớp trong package `java.util.package`.  HashMap là một cấu trúc dữ liệu lưu trữ các object theo cặp "key/value". Một object được sử dụng như một key (index) tới một một object khác (value). key và value có thể có cùng một kiểu dữ liệu hoặc là hai kiểu dữ liệu khác nhau

*b. Cài đặt chương trình mô tả các phương thức sử dụng*

Để sử dụng cấu trúc dữ liệu HashMap, ta cần sử dụng package `java.util`
```java
import  java.util.HashMap;
```
- Tạo một object thuộc class HashMap
```java
HashMap <String, String> capitalCities  =  new  HashMap<String, String>();
```
Object `capitalCities` được đặt giá trị key và value đều là kiểu dữ liệu String.
- Thêm một phần tử: sử dụng hàm `put()`
```java
capitalCities.put("England", "London");
```
- Kiểm tra hashMap có chứa key/value bằng cách sử dụng hàm `containsKey()` hoặc `containsValue()`
- Xóa một phần tử: sử dụng hàm `remove()`
```java
System.out.println("***Remove item***");
capitalCities.remove("USA");
```
Phần tử có key là USA bị xóa khỏi HashMap
- Lấy số lượng phần tử có trong HashMap: sử dụng hàm `size()`
```java
System.out.println("There are "  +  capitalCities.size() +  " items in HashMap");
```
- Xóa toàn bộ phần tử: sử dụng hàm `clear()`
```java 
capitalCities.clear();
```
Tất cả phần tử trong object `capitalCities` đã được xóa.

- Kiểm tra hashMap là rỗng: sử dụng hàm `isEmpty()`.  Kết quả trả về là _true_ nếu hashMap là rỗng, trả về _false_ nếu ngược lại.

Ngoài ra, để sử dụng vòng lặp qua các phần tử trong HashMap, ta có thể dụng các hàm sau:
-  Hàm `keySet()`: chỉ trả về giá trị của các key
- Hàm `values()`: chỉ trả về giá trị value
Một phần tử có key là "England" và value là "London" được thêm vào đầu HashMap
- Lấy danh sách key của HashMap: sử dụng hàm`keySet()`
```java
System.out.println(capitalCities.keySet());
```
- Lấy danh sách value của HashMap: sử dụng hàm `get()`:
```java
for (String  i:  capitalCities.keySet()){
System.out.println(capitalCities.get(i));
}
```
Chương trình in ra danh sách value của HashMap đã tạo

#### 1.2. Cấu trúc dữ liệu HashSet

*a. Giới thiệu*
**HashSet** là một cấu trúc dữ liệu lưu trữ các phần tử có giá trị là duy nhất. HashSet thuộc package `java.until`

*b. Cài đặt chương trình mô tả các phương thức sử dụng*
- Tạo một object HashSet: 
```java
HashSet<String> people  =  new  HashSet<String>();
```
- Thêm một phần tử vào HashSet: sử dụng hàm `add()`
```java
people.add("An");
people.add("Trung");
people.add("Binh");
```
- Kiểm tra phần tử có tồn tại trong HashSet: sử dụng hàm `contains()`
```java
static  void  checkExist(HashSet<String> x, String  y){
	if (x.contains(y)){
		System.out.println("Check item: "  +  y+  " => Exist");
	}else{
		System.out.println("Check item: "  +  y+  " => Not Exist");
	}
}
```
- Xóa một phần tử: sử dụng hàm `remove()`
``` java
static  void  removeItem(HashSet<String> x, String  y){	
	System.out.println("***Remove 1 Item***");
	x.remove(y);
	System.out.println("Number of items: "  +  x.size());
}
```
- Xóa toàn bộ phần tử: sử dụng hàm `
clear()`
```java
static  void  removeAllItem(HashSet<String> x){
	System.out.println("***Remove All Items***");
	x.clear();
	System.out.println("Number of items: "  +  x.size());

}
```
#### 1.3. Cấu trúc dữ liệu Array List
*a. Giới thiệu*
**Array List** là một lớp trong package `java.util.package`. Array List là một cấu trúc dữ liệu kiểu mảng có thể thay đổi kích thước.
So sánh Array List với mảng dựng sẵn:
- Kích thước mảng dựng sẵn không thể thay đổi. Nếu muốn thêm/xóa phần tử trong mảng, cần tạo một mảng mới
-  Array List: kích thước của mảng có thể thay đổi. Người dùng có thể thêm/xóa các phần tử khỏi Array List

*b. Cài đặt chương trình mô tả các phương thức sử dụng*
- *Tạo một object Array List*:
```java
ArrayList<String> cars  =  new  ArrayList<String>();
```
Các phần tử của object `cars` có kiểu dữ liệu String
- *Thêm phần tử vào Array List:* sử dụng hàm `add()`
```java
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");
cars.add("Mazda");
```
Các phần tử có giá trị "Volvo", "BMW", "Ford" và "Mazda" được thêm vào object cars
- *Truy cập một phần tử trong Array List:* sử dụng hàm `get()` và số chỉ mục của phần tử. Ngoài ra,  để *trả về số lượng phần tử trong Array List*, ta sử dụnghàm `size()`
```java
for (Integer  i  =  0; i  <  x.size(); i++) {
System.out.println(x.get(i));
}
```
Chương trình in ra danh sách các phần tử trong ArrayList
- *Sửa giá trị của một phần tử trong Array List:* sử dụng hàm `set()` và số chỉ mục của phần tử. 
```java
static  void  changeCar(ArrayList<String> x, Integer  y, String  z) {
	System.out.println("***Change Item***");
	x.set(y, z);
}
```
- *Xóa một phần tử:* sử dụng hàm `remove()` và số chỉ mục của phần tử
```java
cars.remove(1);
```
Chương trình xóa phần tử có index = 1 trong ArrayList. Lưu ý: index trong ArrayList được khởi tạo giá trị từ 0.
- *Xóa toàn bộ phần tử:* sử dụng hàm `clear()`
```java
static  void  removeAllCar(ArrayList<String> x) {
	System.out.println("**Remove all Items***");
	x.clear();
	for (Integer  i  =  0; i  <  x.size(); i++) {
		System.out.println(x.get(i));
	}
	System.out.println("There are "  +  x.size() +  " items");
}
```

Phương thức removeAllCar sử dụng hàm `clear()` xóa toàn bộ các phần tử trong ArrayList. 
- Kiểm tra Array là rỗng: sử dụng hàm `isEmpty()`.  Kết quả trả về là _true_ nếu ArrayList là rỗng, trả về _false_ nếu ngược lại.

Ngoài ra, class *Collections* trong package `java.util` còn hỗ trợ thêm phương thức sắp xếp các phần tử trong Array List theo thứ tự alphabet hoặc tăng/giảm dần.
```java 
Collections.sort(cars);
for (String  i  :  cars) {
	System.out.println(i);
}
```
Hàm `sort()` trong class Colllections giúp sắp xếp các phần tử trong ArrayList cars theo thứ tự alphabet.

#### 1.4. So sánh 3 kiểu cấu trúc dữ liệu trên
- ArrayList là cấu trúc dữ liệu kiểu mảng chứa các phần tử một cách có thứ tự. Người dùng truy cập các phần tử theo chỉ số. Array List có kích thước có thể thay đổi
- HashMap: Một object ánh xạ key với value. Các key là duy nhất, mỗi key chỉ ánh xạ đến duy nhất một value.
- HashSet là cấu trúc dữ liệu không chứa các phần tử trùng lặp. Cụ thể, HashSet chỉ chứa nhiều nhất một phần tử null, và không chứa hai cặp e1 và e2 nếu `e1.equals(e2)` bằng true. Ngoài ra, nếu hàm `hashCode()` hoạt động tốt thì các hàm `add()`, `remove()` và `contains` có thời gian chạy gần như không đổi.

| Đặc điểm   | HashMap        | HashSet  | ArrayList|
| :-------------: |:-------------:| :-----:|:---:|
|Cấu trúc dữ liệu |Bảng băm | Bảng băm | Mảng|
| Add operator   | O(1)      |   O(1) |O(1)|
| Get/Contains operator| O(1) |    O(1)|    O(n)|
|Next| O(h/n)|O(h/n)| O(1)|
|Thread safe|No|No|No|

#### 1.5.  Phương thức equals() & hashCode()
Hai phương thức hashCode() và equals() của Java là hai phương thức được dùng để triển khai bảng băm, nhằm mục đích lưu trữ và truy xuất dữ liệu

a. Khái niệm equals
**equals()** là một phương thức có các tính chất sau
- Đối với đối tượng x bất kỳ, ta luôn có
`x.equals(x)` trả về giá trị true
- Đối với hai đối tượng x và y bất kỳ, `x.equals(y) ` trả về true, khi và chỉ khi `y.equals(x)`
- Đối với các đối tượng x, y, z: có tính chất bắc cầu. Nếu `x.equals(y)` trả về true, `y.equals(z)` trả về true thì `x.equals(z)` trả về true.
- Khi sử dụng phương thức `equals()` trên đối tượng của lớp, phương thức chỉ trả về true khi cả hai tham chiếu trỏ đến cùng một đối tượng.

b. Khái niệm Hash Code
**HashCode** là một phương thức trả về các kết quả của hàm băm số nguyên của các đối tượng.
Các tính chất chung của `hashCode()` là: 
- Kết quả trả về phương thức `hashCode()` của một đối tượng là không đổi, trừ khi các thuộc tính của đối tượng thay đổi.
- Nếu `x.equals(y)` trả về true thì kết quả hàm băm của chúng giống nhau
- Khi `x.equals(y)` trả về false, kết quả hàm băm của x và y có thể giống nhau hoặc khác nhau.

c. Ghi đè phương thức
Việc ghi đè phương thức `equals()` yêu cầu thêm ghi đè phương thức `hashCode()` để các tính chất của hai phương thức không vi phạm. 
d. Cài đặt chương trình mô tả cách sử dụng `equals` và `hashCode()`
Chương trình: _DataKey.java_ và _HasingEx.java_

### 2. Design Pattern
Một Design Pattern (DP) là một kỹ thuật trong lập trình hướng đối tượng (OOP). DP cung cấp các "mẫu thiết kế", giải pháp cho các vấn đề phần mềm phổ biến. Các ưu điểm khi sử dụng Design Pattern:
- Giải quyết vấn đề một cách tối ưu hơn, code được tối ưu hóa.
- Tái sử dụng mã nguồn, giúp khả năng bảo trì cao hơn, dễ hiểu, dễ gỡ lỗi và dễ mở rộng hơn
- Cung cấp giải pháp ở dạng tổng quát, tăng tốc độ phát triênr phần mềm

Theo mục đích sử dụng, Design Pattern được chia thành 3 nhóm chính: nhóm khởi tạo (creational), nhóm cấu trúc (structural) và nhóm hành vi (behavioral)
-   **Creational Pattern** (nhóm khởi tạo – 5 mẫu) gồm: _Factory Method, Abstract Factory, Builder, Prototype, Singleton_. Nhóm này cung cấp một giải pháp để tạo ra các object và che giấu được logic của việc tạo ra nó (thay vì tạo ra object một cách trực tiếp bằng cách sử dụng method `new`). Điều này giúp cho chương trình trở nên linh hoạt hơn trong việc quyết định object nào cần được tạo ra trong những tình huống được đưa ra.

![Hình ảnh 1](https://gpcoder.com/wp-content/uploads/2018/08/Creational.png)
-   **Structural Pattern** (nhóm cấu trúc – 7 mẫu) gồm: _Adapter, Bridge, Composite, Decorator, Facade, Flyweight và Proxy_. Những DP loại này liên quan tới _class_ và các thành phần của _object_. Nó dùng để thiết lập, định nghĩa quan hệ giữa các đối tượng.

![Hình ảnh 2](https://gpcoder.com/wp-content/uploads/2018/08/Structural.png)
-   **Behavioral Pattern** (nhóm tương tác/ hành vi – 11 mẫu) gồm: _Interpreter, Template Method, Chain of Responsibility, Command, Iterator, Mediator, Memento, Observer, State, Strategy và Visitor_. Nhóm này cung cấp giải pháp để các object tương tác tốt hơn và các khớp nối linh hoạt, dùng trong thực hiện các hành vi của đối tượng, sự giao tiếp giữa các **object** với nhau, nhằm mục đích mở rộng dễ dàng hơn.

![Hình ảnh 3](https://gpcoder.com/wp-content/uploads/2018/08/Behavioral.png) 
#### 2.1. Các Design Pattern cơ bản
#### 2.2. Creational Pattern: Abstract Factory
a. Giới thiệu

Đây là một Design Pattern cung cấp class interface cho việc tạo lập các đối tượng (có liên hệ với nhau) mà không cần quy định class hay xác định các class cụ thể (concrete) khi tạo mỗi đối tượng. Nên sử dụng mẫu này khi cho trường hợp chương trình có một lớp cha với nhiều lớp con và cần trả về một trong các lớp con dựa vào đầu vào. Design Pattern này thích hợp sử dụng khi có sự tham gia của các bước khởi tạo đối tượng phức tạp.
*b. Cài đặt chương trình*

![Hình ảnh minh họa](https://images.viblo.asia/842436c5-bd66-4d15-a18d-f92d99f76739.png)
-   ConcreteProduct: Là các cách triển khai khác nhau của AbstractProduct gồm nhiều các biến thể. Mỗi nhóm AbstractProduct phải được thực hiện cùng các biến thể nhất định. Ví dụ ở trên ta có:
    -   AbstractFactory là Document thì ConcreteFactory sẽ là FancyDocument, MordernDocument
    -   AbstractProduct của AbstractFactory Document là CreateLetter, CreatePage, CreateResume Thì ConcreteProduct sẽ là FacyCreateLetter, FancyCreatePage, FancyCreateResume

- **Abstract Factory**:  Khai báo kiểu `interface` hoặc `abstract` class, chứa phương thức `getShape()` để tạo ra các đối tượng Abstract Product.
```java
public  abstract  class  AbstractFactory {
	abstract  Object  getShape(String  shapeType) ;

}
```
AbstractFactory là `Furniture` 
-  ** ConcreteFactory**: Xây dựng, cài đặt, thực hiện các phương thức được tạo nên từ Abstract Factory. Mỗi ConcreteFactory tương ứng với một biến thể khác của product và chỉ tạo ra những biến thể của sản phẩm đó. 
```java
public  class  plasticObj  extends  AbstractFactory {
	@Override
	public  Object  getShape(String  shapeType){
		if(shapeType.equalsIgnoreCase("Table")){
			return  new plasticTable();
		}else  if(shapeType.equalsIgnoreCase("Chair")){
			return  new  plasticChair();
}
	return  null;
}
}
```
```java
public  class  woodObj  extends  AbstractFactory {
	@Override
	public  Object  getShape(String  shapeType){
		if(shapeType.equalsIgnoreCase("Table")){
			return  new  woodTable();	
}	
		else  if(shapeType.equalsIgnoreCase("Chair")){
			return  new  woodChair();
}
		return  null;
}
}
```
ConcreteFactory là `woodObj` và `plasticObj`
-   AbstractProduct: Khai báo dạng interface hoặc abstract class cho một tập hợp các product riêng biệt nhưng có liên quan đến nhau. Ví dụ: AbstractProduct của AbstractFactory Document là CreateLetter, CreatePage, CreateResume
-   **Client**: là đối tượng sử dụng AbstractFactory, AbstractProduct và client cũng có thể làm việc với bất kỳ biến thể nào của ConcreteFactory
```java
AbstractFactory  shapeFactory  =  FactoryProducer.getMate(false);
```

#### 2.3. Facade
*a. Giới thiệu*
Facade là một Design Pattern thuộc nhóm cấu trúc (structural) vì Facade thêm interface vào hệ thống sẵn có deered che giấu đi những chi tiết phức tạp. Thêm vào đó, DP này cung cấp class interface thuần nhất cho một tập hợp các class interface trong một hệ thống con (subsystem). Nó định nghĩa một interface cao hơn (interface cha) các interface có sẵn để làm cho hệ thống con dễ dàng sử dụng hơn. Ngoài ra, DP này hỗ trợ giúp các ứng dụng client dễ dàng tương tác với hệ thống hơn.

#### 2.4. Interator
*a. Giới thiệu*
Interator truy xuất các phần tử của đối tượng dạng tập hợp tuần tự (list, array...) mà không phụ thuộc vào biểu diễn bên trong của các phần tử.
*b. Cài đặt chương trình mô tả*
`Iterator` là một interface chứa phương thức điều hướng
```java
public  interface  Iterator {
	public  boolean  hasNext();
	public  Object  next();

}
```
`Container` là interface dùng để truy xuất các phần tử của đối tượng
```java
public  interface  Container {
	public  Iterator  getIterator();

}
```
class `NameRepository` kế thừa lớp `Container` và chứa class `NameIterator` kế thừa lớp `Iterator`. Trong lớp này, các phương thức của interface được override để truy xuất, in ra các đối tượng.
### 3. Serialize
#### 3.1. Giới thiệu Serialize
**Serialization (Tuần tự hóa)** là một quy trình chuyển đổi một object thành stream, sau đó có thể chia sẻ qua mạng, lưu trữ dưới dạng tệp hoặc trong CSDL để sử dụng. 
Còn **Deserializtion** là quy trình chuyển đổi một Object stream thành một Object thực để sử dụng trong chương trình.
#### 3.2. Cài đặt chương trình
- Để tuần tự hóa một object/class, ta cần sử dụng triển khai interface `java.io.Serializable`. Đây là một interface không có bất kỳ thuộc tính hay phương thức nào. 
```java
import  java.io.Serializable;
```
- `serialVersionUID` là mã định danh phiên bản chung cho một class Serializable. Phương thức Deserialization sử dụng `serialVersionUID` dể chắc chắn rằng một class được truy xuất chính xác với object được tuần tự hóa tương ứng, đảm bảo rằng trước và sau khi chuyển đổi, class được truy xuất và object là một. Nếu không tìm thấy kết nối phù hợp, ngoại lệ `InvalidClassException` được ném ra. Nếu class không xác định `serialVersionUID`, giá trị này sẽ tự động được tính toán và gán cho class. 
- Nếu muốn một thuộc tính/phương thức không bị chuyển đổi serialized thành stream, ta sử dụng từ khóa `transient`. 
```java
private  String  name;
private  int  age;
transient  private  String  address;
static String major;
```
Thuộc tính `address` được khai báo là `transient` nên khi lưu object vào file, giá trị của nó không được lưu. Vì vậy, giá trị này không được truy xuất trong đối tượng mới. Kết quả hiển thị sẽ là `null`.
Tương tự, các biến/thuộc tính được khai báo là `static` như `major` cũng không được serialized bởi vì các biến này thuộc về class chứa nó, chứ không phải đối tượng.
- `Serialize` được triển khai bởi hai phương thức `ObjectOutputStream`.
```java
public  static  void  serialize(Object  obj, String  fileName) throws  IOException{
	FileOutputStream  fos  =  new  FileOutputStream(fileName);
	ObjectOutputStream  oos  =  new  ObjectOutputStream(fos);
	oos.writeObject(obj); 
	fos.close();
}
```
- `Deserialized` được triển khai bởi phương thức `ObjectInputStream`
```java
public  static  Object  deserialize(String  fileName) throws  IOException, ClassNotFoundException{
	FileInputStream  fis  =  new  FileInputStream(fileName);
	ObjectInputStream  ois  =  new  ObjectInputStream(fis);
	Object  obj  =  ois.readObject();
	ois.close();
	return  obj;
}
```
#### 3.3 Chương trình liên hệ bài 1.2 
Trong chương trình đọc file text/binary, em đã gọi thêm `Seriablized` và `Deserialized` cho phần dữ liệu trong file.

### 4. Tài liệu tham khảo
- [Java equals() and hashCode() | DigitalOcean](https://www.digitalocean.com/community/tutorials/java-equals-hashcode)
- [Giới thiệu Design Patterns| GP Coder](https://gpcoder.com/4164-gioi-thieu-design-patterns/) 
- [Facade Pattern|Tutorial Spoint](https://www.tutorialspoint.com/design_pattern/facade_pattern.htm)
- [Serialization in Java - Java Serialization | DigitalOcean](https://www.digitalocean.com/community/tutorials/serialization-in-java)
- [Java Collections (lmu.edu)](https://cs.lmu.edu/~ray/notes/collections/)
