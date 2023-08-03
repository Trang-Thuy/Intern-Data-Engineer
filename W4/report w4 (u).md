
# Báo cáo tuần 4 
## Yêu cầu
### 1. Tự cài đặt một cơ sở dữ liệu trên máy tính (cụ thể là mysql). trình bày chi tiết về các thành phần liên quan 
### 2. Tự lấy ví dụ về 5 câu query không tốt và cách tối ưu nó. 
### 3. Tìm hiểu về các loại db và trình bày lại (ít nhất 3 db thuộc loại sql, 3 db thuộc loại no sql). 
> Tham khảo: [SQL vs. NoSQL Database: When to Use, How to Choose – Machine Learning for Developers (ml4devs.com)](https://www.ml4devs.com/articles/datastore-choices-sql-vs-nosql-database/)
## Nội dung
### 1. Tự cài đặt một cơ sở dữ liệu trên máy tính (cụ thể là mysql). trình bày chi tiết về các thành phần liên quan 
#### 1.1. Cài đặt cơ sở dữ liệu mySQL
MySQL hỗ trợ nhiều hệ điều hành như Windows, Linux, MacOS...
#### 1.2. Các thành phần liên quan 
a. Kiến trúc MySQL: mô tả cách các thành phần trong MySQL tương tác với nhau. Đây là một hệ thống _client-server system_. Client có thể truy cập tài nguyên dữ liệu qua các ứng dụng dịch vụ mạng. 

![Hình ảnh](https://media.geeksforgeeks.org/wp-content/uploads/20210211183907/MySQLArchi.png)
Ta có thể chia kiến trúc của MySQL thành 3 layer chính: 
- Client layer
- Server layer
- Storage layer

Trong đó:
- **Layer 1 - Client Layer**: là nơi client tương tác với MySQL RDBMS. Các dịch vụ chính được cung cấp là _connection handling_, _authentication_, _security_. Layer này có ba thành phần chính là Administrators, Clients, Query Users.
	- **Connection handling**: khi client kết nối với server thì client đó sẽ nhận được thread riêng cho kết nối của nó. Tất cả các yêu cầu của client đều được thực thi trong thread chỉ định kia. Thread được lưu trong bộ nhớ cache của máy chủ nên ta không cần phải tạo/hủy thread mỗi khi có kết nối mới
	- **Authentication**: là sự xác thực được thực hiện ở phía Server khi Client kết nối với MySQL. Việc xác thực được thực thi bởi tên người dùng (username) và mật khẩu (password) và host của client
	- **Security**: Sau khi kết nối tới MySQL Server thành công, phía Server cần kiểm tra client có các quyền đưa ra các truy vấn nhất định với MySQL. 

- **Layer 2 - Server Layer**: Đây là layer chịu trách nhiệm cho các chức năng logic của RDMS MySQL. Layer này còn được gọi là bộ não của kiến trúc MySQL. Các thành phần con của Server là: _Thread Handling_, _Parser_, _Optimizer_, _Query Cache_, _Buffer and Cache_, _Table Metadata Cache_, _Key Cache_.
	- **Thread Handling**:  Khi Client kết nối với Server, Client sẽ nhận được một luồng (thread) riêng cho kết nối của nó. Luồng được cung cấp bằng *thread handling* của Server. Ngoài ra, các truy vấn của client được thực thi bởi luồng cũng được xử lý bởi module *Thread Handling*.
	- **Parser**: là một thành phần phần mềm xây dựng cấu trúc dữ liệu từ dữ liệu đầu vào. Đầu tiên, quá trình phân tích từ vựng được thực thi, dữ liệu đầu vào được chia thành các token...  
	- **Optimizer**: Sau bước phân tích cú pháp (parse), tại khối Optimizer, các kỹ thuật tối ưu hóa như viết lại câu truy vấn, sắp xếp lại thứ tự quyets bảng, chọn đúng chỉ mục được sử dụng... được áp dụng
	- **Query Cache**: là nơi lưu trữ tập hợp kết quả của câu truy vấn. Server sẽ kiểm tra, tham khảo Query Cache trước khi thực thi phân tích cú pháp (parse) câu truy vấn. Nếu client gửi đến câu truy vấn giống với câu truy vấn trong _Query Cache_, Server sẽ bỏ qua thành phần _Parser_ và _Optimizer_ (có thể là cả _Execution_),  để hiển thị kết quả đầu ra từ _Query Cache_.
	- **Buffer và Cache**:  Đây là thành phần lưu trữ truy vấn và các vấn đề người dùng đã yêu cầu. Khi Client gửi đến một truy vấn, Server sẽ chuyển câu truy vấn đến _Query Cache_, sau đó _Query Cache_ sẽ kiểm tra xem câu truy vấn này đã có trong Cache chưa. Nếu tồn tại câu truy vấn trong Cache giống với truy vấn từ Client, Server sẽ hiển thị kết quả mà không gọi đến *Parser* và *Optimizer*.
	- **Table Metadata Cache**: là vùng nhớ được sử dụng để theo dõi thông tin trên cơ sở dữ liệu, chỉ mục hoặc đối tượng. Số lượng cơ sở dữ liệu, chỉ mục, đối tượng càng nhiều thì kích thước _Metadata Cache_ càng lớn. 
	- **Key Cache**: là chỉ mục đầu vào, xác định một đối tượng duy nhất trong bộ đệm. Máy chủ biên lưu trữ nội dung dựa trên toàn bộ đường dẫn tài nguyên và chuỗi truy vấn.
- **Layer 3 - Storage Layer**: Tùy thuộc vào yêu cầu và tình huống, các công cụ lưu trữ dữ liệu được sử dụng là InnoDB, MyISAM, NDB, Memory...

### 2. Tự lấy ví dụ về 5 câu query không tốt và cách tối ưu nó. 
#### 2.1. Thêm chỉ mục cho cột
Việc thêm chỉ mục sẽ tối ưu quá trình tìm kiếm và sắp xếp cột, nhờ đó việc truy vấn (tìm kiếm hoặc sắp xếp dữ liệu) trên cột sẽ nhanh hơn. Tuy nhiên việc thêm chỉ mục có thể ảnh hưởng đến hiệu suất các thao tác thêm, sửa, xóa dữ liệu trong bảng. Vì vậy, ta chỉ nên thêm chỉ mục cho các cột thường xuyên thực hiện truy vấn
#### 2.2. Sử dụng các kí tự đại diện (Wildcard character) hợp lý
Các ký tự đại diện được sử dụng làm tiền tố, hậu tố. Ký tự `%` được sử dụng để tìm kiếm tất cả bản ghi khớp với trường đã chọn.

#### 2.3. Sử dụng SELECT < columns> thay cho SELECT*
Khi sử dụng truy vấn `SELECT*`, các cột thông tin không cần thiết được tải lên database, điều này sẽ làm chậm SQL và toàn bộ hệ thống.

#### 2.4. Sử dụng EXISTS() thay cho COUNT()
Thông thường, để kiểm tra đối sánh một bản ghi, ta thường sử dụng `EXISTS()` hoặc `COUNT()`. Tuy nhiên, cơ chế của `EXISTS()` là khi tìm thấy bản ghi phù hợp, nó sẽ thoát ngay. Còn cơ chế của `COUNT()` sẽ quét toàn bộ database dù bản ghi đã được tìm thấy. Vì vậy, việc sử dụng `EXISTS()` thay vì truy vấn `COUNT()` sẽ tối ưu hơn.

#### 2.5. Sử dụng GROUP_BY thay DISTINCT
Vì truy vấn `DISTINCT` có cơ chế loại bỏ các bản ghi trùng lặp trong tập kết quả. Đối với các database có số lượng bản ghi lớn, việc sử dụng truy vấn này sẽ gây ra việc tốn nhiều tài nguyên và làm chậm quá trình truy vấn. 
Truy vấn `GROUP_BY` sẽ tạo các nhóm (group) dựa trên giá trị của cột, và chỉ trả về một bản ghi đại diện cho mỗi giá trị. Vì vậy, việc sử dụng `GROUP_BY` sẽ tối ưu hóa hiệu suất câu truy vấn, đặc biệt trường hợp có số lượng bản ghi lớn

### 3. Tìm hiểu về các loại db và trình bày lại (ít nhất 3 db thuộc loại sql, 3 db thuộc loại no sql).
![Hình ảnh](https://cdn.hashnode.com/res/hashnode/image/upload/v1611320777073/Dfl-ideXq.png?auto=compress,format&format=webp)

**SQL (Structured Query Language)** là ngôn ngữ truy vấn có cấu trúc (SQL/dạng bảng). Các datatabase thuộc kiểu SQL là các hệ thống quản lý cơ sở dữ liệu sử dụng SQL để lưu trữ, truy vấn, quản lý dữ liệu, tương tác với CSDL. Các database SQL phù hợp để lưu trữ dữ liệu có cấu trúc (dạng bảng). Mỗi bản ghi (hàng/row) có cùng số thuộc tính (columns) cùng loại.
Có hai ứng dụng chính:
- `Xử lý giao dịch trực tuyến (OLTP)`: thu thập, lưu trữ và xử lý dữ liệu của các giao dịch trong điều kiện thời gian thực. Ứng dụng này cần kho dữ liệu hỗ trợ việc đọc ghi các bản ghi có độ trễ thấp
- `Xử lý phân tích trực tuyến (OLAP)`: phân tích dữ liệu quá khứ tổng hợp từ ứng dụng OLTP. Ứng dụng này cần kho dữ liệu hỗ trợ việc đọc dữ liệu thông lượng cao (high throughput) của một số lượng lớn bản ghi. 

![Hình ảnh 2](https://www.ml4devs.com/images/illustrations/rdbms-vs-columnar-or-oltp-vs-olap-databases.webp)
 _Hình ảnh minh họa hai ứng dụng chính: OLTP và OLAP trong SQL_ 

**NoSQL(Not Relational Structured Query Language)** sử dụng các phương thức lưu trữ và truy xuất dữ liệu không cấu trúc hoặc có cấu trúc linh hoạt hơn, phù hợp với các ứng dụng và trường hợp sử dụng đặc biệt. NoSQL phục vụ cho các loại dữ liệu bán cấu trúc như: key-value, wide column, document (cây) và đồ thị (graph).
NoSQL được sử dụng rộng rãi và phổ biến nhờ cung cấp khả năng mở rộng quy mô theo chiều ngang

`CAP (Consistency - Availability - Partition Tolerance)`: là ba yêu cầu về thuộc tính trong một hệ thống phân tán với dữ liệu được sao chép. 
- Tính nhất quán (Consistency): cần đảm bảo rằng mọi nút trong cụm dữ liệu phân tán đều trả về cùng một lần ghi gần nhất. Các nút trong cụm có cùng một bản sao của dữ liệu đã được sao chép, hiển thị cho các giao dịch khác nhau
- Tính khả dụng (Availability): Các yêu cầu đọc/ghi với các mục dữ liệu sẽ được xử lý thành công hoặc sẽ nhận được thông báo thao tác không thể hoàn tất nếu bị lỗi. Mọi nút phải có khả năng phản hồi trong một khoảng thời gian hợp lý.
- Dung sai phân vùng (Partition Tolerance): Ngay cả khi mạng kết nối các nút bị lỗi, dẫn đến hai hoặc nhiều phân vùng khác, hệ thống vẫn có thể tiếp tục hoạt động, duy trì đảm bảo tính nhất quán dù tồn tại các phân vùng mạng.

Định lý CAP: Cơ sở dữ liệu phân tán có thể có tối đa hai trong ba thuộc tính trên. Do đó, các hệ thống CSDL chỉ ưu tiên hai thuộc tính trong một thời điểm. Các CSDL NoSQL được phân loại dựa trên các thuộc tính CAP mà nó hỗ trợ:
- CA: Hệ thống ưu tiên tính khả dụng hơn tính tính nhất quán và có thể phản hồi dữ liệu cũ
- AP: Hệ thống ưu tiên tính khả dụng hơn tính nhất quán và có thể phản hồi với dữ liệu có thể cũ. Hệ thống có thể phân phối trên nhiều nút và được thiết kế tin cậy cho cả trường hợp phân vùng mạng xảy ra.
- CP: Hệ thống ưu tiên tính nhất quán hơn tính khả dụng và phản hồi dữ liệu được cập nhật mới nhất. Hệ thống có thể phân phối trên nhiều nút và được thiết kế tin cậy cho cả trường hợp phân vùng mạng xảy ra.
#### 3.1. So sánh SQL và NoSQL
![Hình a](https://www.ml4devs.com/images/illustrations/sql-vs-nosql-comparision.webp)
 _So sánh SQL và NoSQL_ 

|So sánh      | SQL | NoSQL  |
| -------------- | ---- | -------------- |
| Mô hình dữ liệu      | Dữ liệu có cấu trúc (dạng bảng). Dữ liệu phải tuân thủ các ràng buộc và quan hệ chặt chẽ. | Dữ liệu bán cấu trúc, không quan hệ. Dữ liệu được tổ chức linh hoạt|
| Giao dịch    |Hỗ trợ giao dịch ACID(Atomicity, Consistency, Isolation, Durability) | Sử dụng mô hình BASE (Basically, Available, Soft-state, Eventually consistent) cho các giao dịch, giúp tăng hiệu suất và khả năng phân tán   |
| Độ nhất quán     | Cao | Có thể tồn tại không nhất quán tạm thời   |
|Hiệu suất| Thích hợp cho các giao dịch real-time| Xử lý hiệu quả lượng dữ liệu lớn
|Quy mô| Phạm vi khó mở rộng, cần nâng cấp phần cứng để xử lý tải lớn hơn| Phạm vi mở rộng ngang, có thể mở rộng bằng cách thêm nhiều máy chủ|


#### 3.12 Các database thuộc kiểu SQL
#### 3.2.1.  **Relation Database**
**RDBMS (Relation Database Management Systems)** là một trong những kho dữ liệu xuất hiện đầu tiên, nó được tối ưu hóa cho yêu cầu của ứng dụng OLTP. Vì RDBMS là một cơ sở dữ liệu hướng theo hàng (row-oriented) nên yêu cầu đọc nhanh và việc cập nhật một số lượng nhiều hàng trong dữ liệu được xử lý một cách tối ưu.  Các tính chất của RDBMS:
- Dữ liệu được tổ chức dạng bảng. Các bảng được chuẩn hóa để giảm dư thừa dữ liệu và tính toàn vẹn dữ liệu được đảm bảo tốt hơn.
- Các bảng có thể chứa khóa chính và khóa ngoại.
- Các câu truy vấn và giao dịch (trong OLTP) được mã hóa bằng SQL
- Thỏa mãn yêu cầu trong ứng dụng OLTP: 
	- Cần cập nhật thường xuyên nhiều bản ghi trong nhiều bảng với độ trễ thấp. 
	- Các giao dịch là các họat động nguyên tử (atomic operation). Nếu giao dịch thành công, tất các các thông tin được cập nhật. Ngược lại, cơ sở dữ liệu không thay đổi, không thực hiện bất cứ hành vi cập nhật thông tin nào.
	- Đảm bảo duy trì tính hợp lệ của mọi giao dịch, duy trì tất cả các biến và ràng buộc về cơ sở dữ liệu
	- Cần có tính bền vững: Ngay cả khi hệ thống gặp sự cố, các giao dịch đã được xử lý luôn tồn tại vĩnh viễn.

Các database thuộc kiểu **Relation Database**:
-   Cloud Agnostic: Oracle, Microsoft SQL Server, IBM DB2,  [PostgreSQL](https://www.postgresql.org/),  [MySQL](https://www.mysql.com/)
-   AWS: Hosted PostgreSQL and MySQL in  [Relational Database Service (RDS)](https://aws.amazon.com/rds/)
-   Microsoft Azure: Hosted SQL Server as  [Azure SQL Database](https://azure.microsoft.com/en-in/products/azure-sql/database/)
-   Google Cloud: Hosted PostgreSQL and MySQL in  [Cloud SQL](https://cloud.google.com/sql/), [Cloud Spanner](https://cloud.google.com/spanner)

**a. PostgreSQL**: hệ thống quản lý CSDL mã nguồn mở và mạnh mẽ, hỗ trợ nhiều tính năng phong phú, có tính mở rộng cao hơn MySQL
- PostgreSQL là một hệ thống quản lý cơ sở dữ liệu mã nguồn mở, mạnh mẽ và đa chức năng.
-   Nó hỗ trợ các tính năng phong phú như kiểu dữ liệu tùy chỉnh, chức năng, giao dịch, và truy vấn phức tạp.
-   PostgreSQL được ưa chuộng trong các ứng dụng doanh nghiệp và dự án yêu cầu tính bảo mật cao và hiệu suất tốt.

**b. Oracle Database**: Oracle là một hệ thống quản lý cơ sở dữ liệu do Oracle Corporation phát triển, hỗ trợ cho các hệ thống doanh nghiệp phức tạp.
Tương tự với các RDBMS phổ biến, Oracle Database được xây dựng dựa trên tiêu chuẩn chuẩn hóa của ngôn ngữ lập trình SQL cho phép quản lý và truy vấn dữ liệu trên server một cách hiệu quả. 
Các mô hình kiến trúc của Oracle bao gồm một số các ràng buộc ACID đảm bảo tính chính xác cao nhất và độ xử lý tin cậy cho dữ liệu. 

Kiến trúc của Oracle Database bao gồm:
![hình ảnh](https://www.oracletutorial.com/wp-content/uploads/2019/07/Oracle-Database-Architecture.png)
-   Cấu trúc lưu trữ vật lý của cơ sở dữ liệu là các tệp chứa dữ liệu, siêu dữ liệu và các tệp quản lý ghi lại thay đổi của dữ liệu. Cơ sở dữ liệu và các phiên bản của nó thực hiện lưu trữ và quản lý các tệp.
-   Cấu trúc lưu trữ logic của Oracle Database bao gồm khối dữ liệu là các phạm vi và nhóm các khối dữ liệu liền kề nhau. Phân đoạn là tập hợp các phạm vi mở rộng. Không gian bảng là các vùng chứa cho phân đoạn.

Mỗi một phiên bản Oracle database được tạo nên dựa trên tập hợp các [bộ nhớ đệm](https://bkhost.vn/blog/cache-la-gi/) chứa các nhóm bộ nhớ chia sẻ được gọi là SGA – hệ thống toàn cầu. Các phiên bản Oracle đều có một tiến trình chạy ngầm quản lý các chức năng I/O, giám sát cơ sở dữ liệu giúp hỗ trợ tối ưu hoá hiệu suất và độ tin cậy cao.

Quy trình tương tác Oracle sẽ là các process máy khách được kết nối với một phiên bản dữ liệu phù hợp để chạy code ứng dụng bất kỳ. Process máy chủ được kết nối với khu vực chương trình chung (khác SGA) và chịu trách nhiệm quản lý tương tác của process máy khách và database.
-   Oracle Database hỗ trợ các tính năng cao cấp như quản lý tương thích, bảo mật, và khả năng mở rộng lớn.
#### 3.1.2. Comlumnar Database
Với ứng dụng OLAP, các phân tích dữ liệu được phân tích với thao tác đọc cột. Vì vậy 
Trong BigData, việc lưu trữ sử dụng Data Lake rất phổ biến. Các kho dữ liệu được xây dựng dựa trên Columnar database. 
Các database thuộc kiểu **Column Database**:
-   AWS:  [RedShift](https://aws.amazon.com/redshift/)
-   Azure:  [Synapse](https://azure.microsoft.com/en-in/services/synapse-analytics/)
-   Google Cloud:  [BigQuery](https://cloud.google.com/bigquery)
-   Apache:  [Druid](https://druid.apache.org/),  [Kudu](https://kudu.apache.org/),  [Pinot](https://pinot.apache.org/)
-   Others:  [ClickHouse](https://clickhouse.tech/),  [Snowflake](https://www.snowflake.com/)
#### 3.2. Các database thuộc kiểu NoSQL
#### 3.2.1. Key - Value Database
Lưu trữ dữ liệu kiểu key-value là cơ sở dữ liệu dạng từ điển (dictionary) hoặc bảng băm (hash table). Cơ sở dữ liệu này được thiết kế cho các hoạt động CRUD (Create - Read - Update - Delete) với một khóa (key) duy nhất cho mỗi bản ghi dữ liệu. Giá trị (value) không có lược đồ cố định, có thể là giá trị nguyên thủy hoặc cấu trúc compound.  Lưu trữ kiểu key-value có khả năng phân vùng cao, mở rộng theo chiều ngang.
Các datatabase thuộc kiểu Key-Value Database: Redis

**a. Redis (Key-Value Store):**
- Redis là một hệ thống quản lý cơ sở dữ liệu key-value, trong đó mỗi giá trị được lưu trữ dưới dạng một cặp khóa-giá trị.
- Nó hỗ trợ lưu trữ dữ liệu trong bộ nhớ và cung cấp khả năng truy xuất dữ liệu nhanh chóng.
- Redis thường được sử dụng cho việc lưu trữ cache, quản lý phiên, và các tác vụ đòi hỏi hiệu suất cao
#### 3.2.2. Wide - column Database
Kiểu lưu trữ wide - column có các bảng, cột và hàng. Trong một bảng, tên của các cột và loại của chúng có thể khác nhau trong các hàng. CSDL này là một ma trận thưa phiên bản ánh xạ đa chiều, giống như một kho lưu trữ key-value hai chiều, với mỗi ô, value được phiên bản bằng time stamp.
Lưu trữ dữ liệu kiểu wide-column có khả năng phân vùng cao. 

Các database thuộc kiểu Wide - column: Apache HBase, Cassandra

**a. Cassandra (Wide-Column Store):**
- Cassandra là một hệ thống quản lý cơ sở dữ liệu dạng wide-column store, thích hợp cho các ứng dụng có khối lượng dữ liệu lớn và đòi hỏi tính sẵn sàng cao.
- Nó lưu trữ dữ liệu theo mô hình cột rộng (wide-column model), cho phép lưu trữ và truy xuất các cột của dữ liệu một cách hiệu quả.
- Cassandra thường được sử dụng trong các hệ thống phân tán, máy chủ lưu trữ dữ liệu lớn và ứng dụng có tính sẵn sàng cao.
#### 3.2.3. Document Database
Đây là kho lưu trữ dữ liệu dùng để lưu trữ và truy xuất tài liệu gồm các đối tượng lồng nhau (nested objects)
Trong kho lưu trữ key-value, value không được xác định rõ ràng. 

Các database thuộc kiểu Document Database: MongoDB

**a. MongoDB**: 
- MongoDB là một hệ thống quản lý cơ sở dữ liệu dạng tài liệu (document-oriented).Nó lưu trữ dữ liệu dưới dạng các tài liệu JSON hoặc BSON (Binary JSON), cho phép lưu trữ dữ liệu có cấu trúc linh hoạt và thay đổi theo thời gian.
- MongoDB thường được sử dụng trong các ứng dụng web và dự án yêu cầu lưu trữ dữ liệu phi cấu trúc hoặc có cấu trúc thay đổi nhanh.
#### 3.2.3. Graph Database
Graph Database được thiết kế dạng đồ thị, phù hợp để lưu trữ và truy vấn các kết nối mạng xã hội

Các database thuộc kiểu Graph Database: Neo4j

 **a. Neo4j (Graph Database):**
 - Neo4j là một hệ thống quản lý cơ sở dữ liệu đồ thị (graph database).
 - Nó được thiết kế để lưu trữ và truy vấn các đối tượng và mối quan hệ giữa chúng dưới dạng đồ thị.
 - Neo4j thường được sử dụng trong các ứng dụng liên quan đến phân tích mạng xã hội, kiến trúc hướng đối tượng, và hệ thống tìm kiếm.
 ### 4. Tài liệu tham khảo 
 [1]  [Architecture of MySQL - GeeksforGeeks](https://www.geeksforgeeks.org/architecture-of-mysql/)
 
 [2]  [MySQL :: MySQL 8.0 Reference Manual :: 8.3 Optimization and Indexes](https://dev.mysql.com/doc/refman/8.0/en/optimization-indexes.html)
 
 [3]  [SQL vs. NoSQL Database: When to Use, How to Choose – Machine Learning for Developers (ml4devs.com)](https://www.ml4devs.com/articles/datastore-choices-sql-vs-nosql-database/)
 
 [4]  [O'Reilly High Performance MySQL 3rd Edition Mar 2012.pdf at master · lackrp/lackrp-public (github.com)](https://github.com/lackrp/lackrp-public/blob/master/eBooks/O'Reilly.High.Performance.MySQL.3rd.Edition.Mar.2012.pdf)
[5] [PostgreSQL System Architecture - GeeksforGeeks]( https://www.geeksforgeeks.org/postgresql-system-architecture/)
[6][Oracle Database Architecture](https://www.oracletutorial.com/oracle-administration/oracle-database-architecture/)
