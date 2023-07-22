
# Báo cáo Tuần 3 
## Yêu cầu
### 3.1. Exception
Nắm được cách xử lí exception, hệ thống các exception trong java, lấy ví dụ về ít nhất 2 loại exception(Checked Exception và Unchecked Exception) và cách fix nó. 
Tham khảo: [Exception Handling in Java | DigitalOcean](https://www.digitalocean.com/community/tutorials/exception-handling-in-java)
### 3.2 Concurrency (optional)
Yêu cầu: 
- (1) viết 1 luồng chạy ngầm kế thừa Runnable sử dụng java
- (2) viết chương trình sử dụng threadpool bằng ngôn ngữ java 
- tìm hiểu thêm khái niệm: safe thread, unsafe thread, daenmon thread

Tham khảo: [Java 8 Concurrency Tutorial: Threads and Executors - winterbe](https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/) (cái này nó viết bằng syntax của java 8).
Tìm hiểu lock, atomic integer và concurrent hashmap Seminar: thực tập sinh trong phần này nếu có nhu cầu có thể làm silde thuyết trình về những tìm hiểu của mình với team platform. 

### 3.3. json 
Yêu cầu: Dựa trên Serialize ở java tại mục 2.2, viết code có sử dụng json (yêu cầu có sử dụng maven) 
Tham khảo: 
- (1) [JSON - Overview | Tutorialspoint](https://www.tutorialspoint.com/json/json_overview.htm) 
- (2) [JSON with Java | Tutorialspoint](https://www.tutorialspoint.com/json/json_java_example.htm)

Nắm được json là gì, sử dụng java parse json, lấy giá trị, chuyển jsonobject thành string sử dụng thư viện gson để parse trực tiếp 1 string sang 1 object tương ứng
## Nội dung
### 1. Exception
Exception là một sự kiện lỗi xảy ra trong quá trình thực thi chương trình và gián đoạn luồng chạy của chương trình.
Các exception trong Java có thể phát sinh từ các tình huống khác nhau, ví dụ như: người dùng nhập sai dữ liệu, lỗi phần cứng, lỗi kết nối mạng/CSDL.
#### 1.1. Nắm được cách xử lí exception
**a. Ném và bắt các exception**

Khi một sự kiện xảy ra lỗi, Java tạo ra một _exception object_, chứa các thông tin liên quan đến lỗi xảy ra như vị trí xảy ra exception, loại exception, phân cấp phương thức...
Khi một ngoại lệ xảy ra trong phương thức, quá trình ném ngoại lệ xảy ra, luồng chạy thực thi chương trình tạm dừng và JRE (**Java Runtime Environment**) cố gắng tìm trình xử lý ngoại lệ (**Exception Handler**).

Logic tìm trình xử lý ngoại lệ: bắt đầu bằng việc tìm kiếm phương thức chứa exception object. Nếu không tìm thấy trình xử lý ngoại lệ thích hợp thì nó sẽ chuyển sang phương thức chứa phương thức kia.
Nếu tìm thấy Exception Handler thích hợp, chương trình sẽ kết thúc và in ra thông tin về exception. 

Exception Handler framework của Java chỉ được sử dụng để xử lý các lỗi runtime. Các lỗi compile-time cần được xử lý bởi các lập trình viên.

**b. Các từ khóa xử lý Exception**
Các từ khóa được java cung cấp để xử lý ngoại lệ (exception):
- `throw`: từ khóa được sử dụng để ném các ngoại lệ vào bộ thực thi (runtime) để xử lý
- `throws`: từ khóa được sử dụng để ném các ngoại lệ và không xử lý nó
- `try - catch`: xử lý ngoại lệ trong chương trình. Với, `try` là điểm bắt đầu chương trình và `catch` là điểm cuối khối `try` để xử lý các ngoại lệ. Các tham số đầu vào của `catch` phải là một tham số thuộc kiểu `Exception`. Có thể sử dụng một `try` - nhiều `catch`, hoặc lồng các `try - catch` với nhau 
	- Tại một thời điểm, chỉ có một exception xảy ra và chỉ có một khối lệnh`catch` được thực thi
	- Các khối `catch` cần được sắp xếp theo thứ tự từ cụ thể nhất đến chung nhất
- `finally`: đây là một từ khóa không bắt buộc, được sử dụng trong `try-catch`. Dù ngoại lệ (exception) có xảy ra hay không thì phần chương trình trong khối `finally` luôn được thực thi
#### 1.2. Hệ thống các exception trong java
a. Giới thiệu các loại ngoại lệ trong Java

Các ngoại lệ trong Java có tính đa dạng, phân cấp và kế thừa.
`Throwable` là lớp cha (class parent) của Java Exception Hierachy. Nó có hai đối tượng con (object child) là `Error` và `Exception`. 

![Exceptions1](https://www.tutorialspoint.com/java/images/exceptions1.jpg)
- `Error`: là các tình huống đặc biệt, nằm ngoài phạm vi ứng dụng, không thể dự đoán/khắc phục. Ví dụ như lỗi phần cứng, sự cố máy ảo Java (JVM), lỗi hết bộ nhớ. Một số lỗi phổ biến là `OutOfMemoryError` và `StackOverflowError`
- `Exception`: Các ngoại lệ được chia thành `Checked Exception` và `Runtime Exception`.
	- `Checked Exception`: Ngoại lệ này là tình huống đặc biệt có thể dự đoán và cố gắng khắc phục được. Chúng ta nên bắt (catch) các ngoại lệ này và cung cấp một thông báo về ngoại lệ cho người dùng kèm các thông tin nhằm mục đích gỡ lỗi.  Nếu `Checked Exception` được ném ra, ta cần bắt (catch) ngoại lệ đó theo cùng phương thức, hoặc truyền nó cho phương thức gọi nó bằng từ khóa `throws`. Ví dụ như `FileNotFoundException`
	- `Runtime Exception`: Ngoại lệ này xảy ra do quá trình lập trình gây ra một số lỗi. Vì vậy, các `Runtime Exception` có thể tránh được bằng cách lập trình tốt hơn. Ví dụ như `ArrayIndexOutOfBoundException` là ngoại lệ xảy ra khi chương trình truy xuất một phần tử mảng có chỉ số lớn hơn độ dài của mảng.
Nếu ta ném (throw) một `Runtime Exception` trong một phương thức, Không cần thiết phải không cần . 


 
b. Lấy ví dụ về ít nhất 2 loại exception (Checked Exception và Unchecked Exception) và cách fix nó. 

**`Unchecked Exception (Runtime Exception)`** là ngoại lệ xảy ra tại thời điểm chạy (execute). Ngoại lệ kiểu này bao gồm các lỗi lập trình như lỗi logic, sử dụng API sai cách. Các ngoại lệ này được bỏ qua trong tại thời điểm biên dịch (compile). Một số ngoại lệ thường gặp thuộc loại `Unchecked RuntimeException`:
-  `ArithmeticException`: Lỗi số học, ví dụ như chia cho 0
- `ArrayIndexOutOfBoundsException`: truy xuất phần tử có chỉ số lớn hơn chiều dài của mảng
- `ArrayStoreException`: gán cho một phần tử mảng kiểu không tương thích
- `ClassCastException`: cast không hợp lệ
- `NullPointerException`: sử dụng tham chiếu nul không hợp lệ
- `IndexOutBoundsException`: chỉ mục nằm ngoài giới hạn
- `NegativeArraySizeException`: Mảng được tạo với kích thước âm
- `NumberFormatException`: Chuyển đổi từ chuỗi sang dạng số không hợp lệ
- `StringIndexOutOfBounds`: chỉ mục nằm ngoài giới hạn của chuỗi

**`Checked Exception (Compile Time Exception)`**: là ngoại lệ được trình biên dịch kiểm tra và thông báo tại thời điểm biên dịch. Các ngoại lệ kiểu này nên được xử lý, không nên bỏ qua. Một số ngoại lệ thường gặp thuộc loại `Checked Exception` :
- `ClassNotFoundException`: Lớp được truy xuất không tìm thấy/tồn tại
- `InterruptedException`: Một luồng bị ngắt bởi một luồng khác
- `NoSuchMethodException`: Phương thức được yêu cầu không tồn tại
- `NoSuchFieldException`: Trường được yêu cầu không tồn tại
- `IllegalAccessException`: Từ chối truy xuất một class
- `InstantiationException`: Xảy ra khi tạo một object của một lớp trừu tượng (abstract) hoặc interface

Chương trình cài đặt: **_ExceptionExample.java_**
### 2. Concurrency (optional)
framework `Concurrency` thuộc package `java.util.concurrent` được thiết kế để giải quyết các vấn đề xảy ra khi chạy multi-threading như deadlock, threa stavation..., ở mức high-level giúp lập trình viên dễ dàng quản lý các resource, thread, đồng bộ các tiến trình.
#### 2.1. Tìm hiểu khái niệm: safe thread, unsafe thread, daenmon thread
Safe Thread và Unsafe Thread là hai khái niệm liên quan đến việc đồng bộ hóa (synchronization) và quản lý các luồng trong ứng dụng đa luồng (multithreading)

_a. Unsafe Thread_

Đây là tình trạng các luồng cạnh tranh và truy cập vào tài nguyên chung (shared resources) mà không có quá trình đồng bộ hóa. Khi không sử dụng cơ chế đồng bộ hóa, các luồng có thể ghi đè, đọc dữ liệu trong lúc các tác vụ khác đang thực thi. Hậu quả là tồn tại một số vấn đề như đọc/ghi không nhất quán, race conditions, lỗi dữ liệu.
- Race Condition: từ 2 luồng (thread/process) trở lên cùng truy cập vào nguồn tài nguyên chung và ít nhất 1 luồng làm thay đổi giá trị vùng nhớ chung đó. Vì vậy, các giá trị trong nguồn tài nguyên ghi đè lẫn nhau và giá trị đọc sai.
- Đọc/ghi không nhất quán: khi một luồng đọc dữ liệu trong khi một luồng khác đang ghi dữ liệu vào cùng một tài nguyên, việc đọc và ghi có thể gây ra kết quả không nhất quán, thông tin sai lệch.
- Deadlock: các luồng bị khóa và ở trạng thái chờ đợi giải phóng khóa.
- Starvation: một luồng không thể thực hiện tác vụ của mình vì độ ưu tiên của nó thấp hơn các luồng khác, hoặc bị ngắt liên tục bởi các luồng khác, dẫn đến hiệu suất hệ thống kém.

_b. Safe Thread_

Đây là tình trạng khi việc truy cập và ghi (thay đổi) tài nguyên chung (shared data) giữa các luồng được quản lý và đồng bộ hóa một cách an toàn, ngăn chặn các vấn đề không nhất quán dữ liệu, race conditions.
Một số cơ chế đồng bộ hóa trong Java:
- Synchronized Blocks/Methods: Sử dụng từ khóa `synchronized` để quản lý việc truy cập dữ liệu trong một đoạn chương trình hoặc phương thức
- Locks: sử dụng các gói trong `java.util.concurrent.locks` như `ReentrantLock`, `ReadWriteLock`, `StampedLock` để tạo các khóa chi tiết, linh hoạt (quá trình đặt và giải phóng khóa theo yêu cầu) giúp quản lý luồng truy cập
- Atomic Classes: thực hiện các hoạt động nguyên tử trên dữ liệu

_c. Daenmon Thread_: Đây là một luồng có độ ưu tiên thấp, chạy trong nền chương trình để thực hiện các tác vụ nền như: thu gom file rác, cung cấp các dịch vụ cho luồng người dùng. Khi các luồng người dùng thực thi xong các tác vụ, JVM sẽ tự động đóng Daenmon Thread.
Ngoài ra, việc tìm hiểu, thông thạo Daemon Thread giúp các lập trình viên có thể quản lý hiệu quả hành vi của các luồng và tối ưu hóa hiệu suất.
Một số tính chất của Daenmon Thread:
- Không thể ngăn JVM kết thúc: JVM sẽ tự động kết thúc khi tất cả các luồng user đã hoàn thành các nhiệm vụ, bất kể luồng daemon đang chạy hoặc không
- Độ ưu tiên thấp: Daemon Thread có độ ưu tiên thấp nhất trong số tất cả các luồng trong Java

Các phương thức của Daemon Thread gồm:
- `void set Daemon (boolean status)`: đánh dấu luồng hiện tại là daemon thread hay user thread.
- `boolean isDaemon()`: kiểm tra xem luồng hiện tại có phải `thread daemon` không. Nếu luồng đó là Daemon, trả về `true`. Ngược lại, trả về `false`.

Chương trình cài đặt: _**DaemonThread.java**_
#### 2.4. Tìm hiểu lock, atomic integer và concurrent hashmap Seminar
_a. Lock_

Thay vì sử dụng khóa ngầm bằng cách sử dụng từ khóa `synchronized`, ta có thể sử dụng khóa tường minh bằng interface `Lock`. Khóa này hỗ trợ nhiều phương thức khóa:
- `ReentrantLock`: có khả năng thực hiện khóa không chờ (non-blocking lock) và thực hiện giải phóng khóa tùy ý. Cần quản lý việc giải phóng khóa một cách cẩn thận để tránh deadlock.
- `ReadWriteLock`: cung cấp cơ chế khóa hỗ trợ nhiều luồng đọc đồng thời nhưng chỉ cho phép một luồng ghi tại một thời điểm, giúp cải thiện hiệu suất trong cách tình huống mà dữ liệu thường xuyên được truy vấn.
- `StampedLock`: cung cấp cơ chế khóa tương tự `ReadWriteLock` và cung cấp thêm tính năng tối ưu hóa cho trường hợp có nhiều thao tác & ghi đồng thời.

Chương trình cài đặt: _**LockExample.java**_

_b. Atomic Integer_

Các thao tác nguyên tử (atomic operation) được thực hiện trong một đơn vị tác vụ (task) mà không có sự can thiệp liên quan từ các hoạt động khác. Việc sử dụng thao tác nguyên tử  rất quan trọng trong môi trường đa luồng, để tránh sự không nhất quán dữ liệu.

Ưu điểm khi sử dụng Atomic Integer: vấn đề về đồng bộ hóa được giải quyết, khả năng đọc chương trình và khả năng giảm lỗi xảy ra được cải thiện

Chương trình cài đặt: _**JavaAtomic.java**_

_c. Concurrent Hashmap Seminar_
Class `ConcurrentHashMap` thuộc package `java.util.concurrent`, thực thi `ConcurrentMap` và interface `Seriablizable`. `Concurrent HashMap` là bản cải tiến của `HashMap`, là một triển khai luồng an toàn của interface `Map` trong Java. Nó cho phép nhiều luồng có thể truy cập đồng thời mà không gặp sự cố đồng bộ nào. 

Tính năng chính của `Concurrent HashMap`:
- Cung cấp khóa chi tiết: thay vì khóa toàn bộ cấu trúc dữ liệu, `ConcurrentHashMap` sử dụng khóa chi tiết (fine-grained locking) để khóa phần Map đang được sửa đổi. Nhờ vậy, chương trình có khả năng mở rộng cao và hiệu quả cho các hoạt động đồng thời. Tuy nhiên, cơ chế khóa chi tiết yêu cầu dung lượng bộ nhớ bổ sung nhiều hơn cơ chế đồng bộ khác.
- Cung cấp các phương thức cho các hoạt động nguyên tử (atomic operation) như `putIfAbsent()`, `replace()`, `remove()` rất hữu ích để triển khai các thuật toán đồng thời phức tạp

Chương trình cài đặt: _**ConcurrentHM.java**_

#### 2.3. Viết 1 luồng chạy ngầm kế thừa Runnable sử dụng java
`java.lang.Runnable` là một interface được triển khai bởi một lớp có các thể hiện (instance) được dự định sẽ thực thi bởi một luồng. 
Có hai cách để bắt đầu một luồng (Thread) mới: sử dụng `Subclass Thread` (mở rộng class Thread) và triển khai interface `Runnable`.
- Trong cách tiếp cận sử dụng `Subclass Thread`: lớp `Thread` luôn được mở rộng, nhưng không sử dụng được ưu điểm của tính chất kế thừa. Các lớp con (mở rộng từ class Thread) cũng không được mở rộng
- Còn với cách tiếp cận bằng `Runnable` , ta có thể mở rộng bất kỳ lớp nào và tận dụng tính chất kế thừa. Từ đó, có thể chia sẻ các thể hiện, phương thức... của một đối tượng cho nhiều luồng.

Vì vậy, cách tiếp cận sử dụng interface `Runnable` được triển khai phổ biến hơn.
Các bước tạo luồng mới bằng `Runnable`:
- Tạo một trình triển khai `Runnable` và triển khai phương thức `run()`
- Khởi tạo class Thread, chuyển trình triển khai vừa tạo vào Thread. Trong Thread có một constructor nhận các thể hiện của Runnable
- Gọi `start` của thể hiện Thread, bắt đầu gọi `run`. Gọi `start` tạo ra một luồng mới thực thi phần chương trình được viết trong hàm run().
- Đối với các Checked Exception, Runnable không thể ném các ngoại lệ kiểu này
- Đối với các Unchecked Exception (Runtime Exception): Runnable có thể ném các ngoại lệ này từ run(). Các ngoại lệ chưa được bắt (catch) sẽ được bởi Exception Handler của luồng.

Chương trình cài đặt: _**TestUsingRunnable**_

Ngoài ra, em có cài đặt thêm một chương trình mô tả cách tạo một luồng mới sử dụng `SubClass Thread`. Chương trình cài đặt: _**TestUsingSubClass**_
#### 2.4. Viết chương trình sử dụng thread pool bằng ngôn ngữ java 
Thread pool đại diện cho một nhóm các luồng thực thi đang đợi các nhiệm vụ (task) và được sử dụng nhiều lần, nhằm giải quyết vấn đề về chi phí hoạt động của chu trình luồng, chi phí tài nguyên. Bởi vì luồng đã tồn tại có sẵn khi yêu cầu đến, nên độ trễ tạo ra khi tạo nên luồng mới sẽ được giải quyết, giúp chương trình trả lời yêu cầu nhanh chóng.

Java cung cấp framework `Executor` trong interface `Executor`, và hai sub interface `ExecutorService` và class `ThreadPoolExecutor`.
Để sử dụng `thread pool`, ta cần tạo một object thuộc `ExecutorService` và chuyển tập các nhiệm vụ (task) cho class này. Class `ThreadPoolExecutor` cho phép thiết lập các chỉ số kích thước nhóm tối đa và kích thước core. Các `runnable` được chạy bởi một luồng cụ thể, được thực thi cụ thể.

Chương trình cài đặt: _**ThreadPoolExample.java**_
### 3. Json
#### 3.1. Json là gì
JSON là viết tắt của JavaScript Object Notation. Đây là một định dạng chuyển đổi nhẹ. Cú pháp JSON bắt nguồn từ javascript object notation, nhưng định dạng json chỉ là văn bản.

Để làm việc với Json trong Java, có các thư viện hỗ trợ phổ biến như 
- `GSON`: hỗ trợ chuyển đổi giữa JSON và Java Object
- `Jackson`: có chức năng chuyển đổi tương tự thư viện GSON
#### 3.2. Sử dụng java parse json
JSON có hàm (chức năng) tích hợp để chuyển các chuỗi JSON String thành các Object và ngược lại.

Chương trình mô tả: _**StringtoObject.java**_

### 4. Tài liệu tham khảo
- [Exception Handling in Java | DigitalOcean](https://www.digitalocean.com/community/tutorials/exception-handling-in-java)
- [Types of Exception in Java - Javatpoint](https://www.javatpoint.com/types-of-exception-in-java#:~:text=In%20Java%2C%20exception%20is%20an,are%20referred%20to%20as%20exceptions.)
- [Java - Exceptions| tutorialspoint](https://www.tutorialspoint.com/java/java_exceptions.htm)
- [Java 8 Concurrency Tutorial: Threads and Executors - winterbe](https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/) 
- [Runnable interface in Java](https://www.geeksforgeeks.org/runnable-interface-in-java/)
- [Tìm hiểu về Thread Pool trong java (viblo.asia)](https://viblo.asia/p/tim-hieu-ve-thread-pool-trong-java-OeVKBDQMlkW)
- [Thread Pools in Java - GeeksforGeeks](https://www.geeksforgeeks.org/thread-pools-java/)
- [AtomicInteger in Java | DigitalOcean](https://www.digitalocean.com/community/tutorials/atomicinteger-java)
