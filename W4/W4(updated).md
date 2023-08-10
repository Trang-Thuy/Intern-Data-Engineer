
# Báo cáo tuần 4 
## Yêu cầu
### 1. Tự cài đặt một cơ sở dữ liệu trên máy tính (cụ thể là mysql). trình bày chi tiết về các thành phần liên quan 
### 2. Tự lấy ví dụ về 5 câu query không tốt và cách tối ưu nó. 
### 3. Tìm hiểu về các loại db và trình bày lại (ít nhất 3 db thuộc loại sql, 3 db thuộc loại no sql). 
> Tham khảo: [SQL vs. NoSQL Database: When to Use, How to Choose – Machine Learning for Developers (ml4devs.com)](https://www.ml4devs.com/articles/datastore-choices-sql-vs-nosql-database/)
#### 4. (BỔ SUNG) Tìm hiểu chi tiết Storage Layer trong MySQL
#### 5. (BỔ SUNG) Tìm hiểu về chỉ mục (index) trong MySQL
#### 6. (BỔ SUNG) Tìm hiểu về một số loại database triển khai theo các tiêu chi trong định lý CAP
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
 ### 4. (BỔ SUNG) Tìm hiểu các Storage Engine trong MySQL
 **Layer 3 - Storage Layer**: Tùy thuộc vào yêu cầu và tình huống, các công cụ lưu trữ dữ liệu được sử dụng là InnoDB, MyISAM, NDB, Memory... **Storage Engine** là module phần mềm được hệ thống quản lý cơ sở dữ liệu sử dụng để thực hiện các thao tác CRUD từ cơ sở dữ liệu. Từ phiên bản MySQL 5.5 trở lên, module Storage Engine mặc định là **InnoDB**, còn các phiên bản trước phiên bản 5.5 thì Storage Engine mặc định là **MyISAM**. 
 Có hai loại Engine chính trong MySQL: transactional và non-transactional
 #### 4.1. Storage Engine kiểu transactional
 **a. InnoDB**: Đây là Storage Engine mới, có nhiều tính năng và ưu điểm vượt trội hơn MyISAM, được thiết kế để đạt hiệu suất tối đa khi xử lý lượng dữ liêu lớn. 
 - InnoDB hỗ trợ giao dịch ACID. Nó hỗ trợ _Transaction_, xử lý nhiều giao dịch ngắn hạn (short-lived), đảm bảo an toàn khi thực hiện một khối lệnh SQL, nhờ vậy nguyên tắc nhất quán dữ liệu được đảm bảo. 
 - Từ phiên bản version 5.5, Engine này có hỗ trợ _Full Text Search_. 
- InnoDB đảm bảo tính toàn vẹn và ràng buộc dữ liệu cao, tình trạng lỗi chỉ mục và bảng crash rất hiếm xảy ra (khó xảy ra). Với cơ chế hoạt động _Row Level Locking (khóa cấp hàng)_, khi thực hiện các thao tác thêm/sửa/xóa trên một bản ghi, các thao tác trên các bản ghi khác của bảng vẫn có thể diễn ra bình thường, giúp hệ thống có thể kiểm soát đồng thời nhiều phiên bản. 
- Tuy nhiên, các thao tác của InnoDB cần nhiều RAM nên với các trường hợp cần thực hiện nhiều thao tác CRUD thì cơ chế _Table Level Locking_ sẽ gây ra hàng đợi lớn, quá trình xử lý lâu. 
- InnoDB phù hợp cho các hệ thống Ngân hàng, trang web có tần suất cập nhật dữ liệu cao như Mạng xã hội, diễn đàn...

**b. CSV**: Đây là Storage Engine lưu trữ dữ liệu dạng file csv. Engine này có tính linh hoạt cao vì dữ liệu định dạng csv dễ dàng tích hợp vào các ứng dụng khác. 
 #### 4.2. Storage Engine kiểu non - transactional
**a. MyISAM**: là Storage Engine đầu tiên. 
- Đây là engine duy nhất tìm kiếm _Full Text_  lập chỉ mục ngược tương tự thuật toán tìm kiếm của Google, được thiết kế cho nhu cầu tốc độ tìm kiếm. Nhờ có kiến trúc đơn giản nên nó có tốc độ truy xuất (đọc, tìm kiếm) nhanh nhất trong các loại Storage Engine. 
- MyISAM không hỗ trợ giao dịch (transaction)
- Tuy nhiên MyISAM có cơ chế hoạt động _Table Level Locking (khóa cấp bảng)_ nên khi có thao tác thêm/sửa/xóa một bản ghi trong bảng thì bảng đó sẽ bị khóa lại. Khi nào thao tác thực hiện xong thì các thao tác khác mới tiếp tục được thực hiện. Ngoài ra, vì kiến trúc của Engine đơn giản nên MyISAM rất dễ lỗi, crash, gặp lỗi chỉ mục đối với các bảng có số lượng bản ghi lớn.
- Storage Engine này được sử dụng chủ yếu trong các trang Web và kho dữ liệu (Data warehouse)

**b. MEMORY**: là loại Storage Engine lưu trữ dữ liệu trực tiếp lên RAM nên tốc độ truy xuất và cập nhật dữ liệu rất nhanh. 
- Đây là engine có tốc độ nhanh nhất nhờ cơ chế tạo các bảng trong bộ nhớ. MEMORY có cơ chế hoạt động cung cấp _Table Level Locking (khóa cấp bảng)_ 
- Không hỗ trợ giao dịch (non-transaction)
- Engine này thường được sử dụng làm các table chứa dữ liệu tạm, chứa các phiên làm việc của user. Khi khởi động lại MySQL, dữ liệu trên bảng MEMORY sẽ bị mất. Dung lượng của bảng Storage Engine dạng MEMORY phụ thuộc vào cấu hình thông số _max_heap_table_size_, giá trị dung lượng mặc định là 16MB.
- Storage Engine này thường được sử dụng để  tạo các bảng tạm thời hoặc tra cứu nhanh (quick lookups). 

**c. Archive**: Đây là công cụ được tối ưu thao tác chèn (insert) tốc độ cao. Các dữ liệu liệu trước khi chèn sẽ được nén.
- Archive không hỗ trợ giao dịch (non-transaction)
- Storage Engine này phù hợp để lưu trữ các dữ liệu lịch sử, dữ liệu được lưu trữ hiếm khi được tham chiếu

**d. Blackhole**: Các thao tác truy xuất trên Engine này luôn trả về một tập rỗng. Chức năng này được sử dụng trong thiết kế cơ sở dữ liệu phân tán, các dữ liệu được sao lưu tự động nhưng không lưu trữ cục bộ. Blackhole có thể được sử dụng để thực hiện các bài kiểm tra hiểu suất và các bài thử nghiệm.
**e. Federated**: Đây là Engine cung cấp khả năng tách các máy chủ MySQl để tạo một CSDL logic từ nhiều máy chủ vật lý. 
- Phù hợp với môi trường phân tán

### 5. (BỔ SUNG) Tìm hiểu về chỉ mục (index) trong MySQL
Chỉ mục (hay key) là cấu trúc dữ liệu được sử dụng để tìm thông tin trên hàng nhanh chóng hơn. Các CSDL nhỏ, số lượng tải nhẹ có thể thực hiện các thao tác truy vấn nhanh chóng mà không cần lập chỉ mục. Tuy nhiên, khi tập dữ liệu tăng lên, việc sử dụng chỉ mục có thể cải thiện hiệu suất hoạt động rất nhiều. 
Trong MySQL, chỉ mục được sử dụng để tìm một giá trị dữ liệu.
Ví dụ:
```sql
SELECT first_name FROM sakila.actor WHERE actor_id = 5;
```
Có chỉ mục trên cột _actor_id_, MySQL sẽ sử dụng chỉ mục này để tìm các hàng các actor_id = 5. MySQL thực hiện tra cứu các giá trị trong chỉ mục và trả về các hàng chứa giá trị đã chỉ định.

Một chỉ mục chứa các giá trị của một hoặc nhiều cột trong bảng. Khi lập chỉ mục cho nhiều cột, ta cần lưu ý thứ tự các cột vì MySQL chỉ tìm kiếm hiệu quả trên tiền tố ngoài cùng bên trái (leftmost prefix) của chỉ mục. Ngoài ra, việc tạo chỉ mục trên hai cột khác với việc tạo hai chỉ mục trên một cột riêng biệt
#### 5.1. Các loại chỉ mục
Có nhiều loại chỉ mục được thiết kế cho các mục đích khác nhau. Các chỉ mục được triển khai ở lớp Storage Engine, không phải ở lớp Server. Vì vậy, việc lập chỉ mục ở các Engine khác nhau sẽ khác nhau về cách thức hoạt động...
**a. B-Tree index**
Chỉ mục B-Tree sử dụng cấu trúc dữ liệu B-Tree để lưu trữ dữ liệu dưới dạng các nút được sắp xếp theo thứ tự nhất định. Dữ liệu index được tổ chức và lưu trữ theo dạng cây, gồm có root, branch và leaf. Nhờ vậy, Storage Engine không cần duyệt toàn bộ bảng để tìm kiếm dữ liệu mà nó sẽ duyệt bắt đầu từ nút root. Đây là một quá trình đệ quy bắt đầu từ nút root, tìm kiếm tới branch và leaf, có điều kiện dừng là khi điều kiện truy vấn được thỏa mãn. Với chỉ mục kiểu B-Tree, dữ liệu được sắp xếp theo thứ tự, nhờ vậy các câu truy vấn như _ORDER BY_ hay _GROUP BY_ được tối ưu hơn
Tuy nhiên khi thực hiện các thao tác insert hay update thì thao tác sẽ bị chậm vì cần sắp xếp lại thứ tự chỉ mục.
**b. Hash index**
Bằng cách sử dụng kỹ thuật hashing, các chỉ mục được xây dựng trên một bảng băm để lưu trữ và tìm kiếm dữ liệu. Hash table là một cấu trúc dữ liệu mà có thể cho phép thực hiện việc map các cặp keys, values. Hash Table sử dụng Hash function để tính toán một index vào một mảng các buckets, từ đó có thể tìm thấy các giá trị mong muốn.  Vì vậy, hash index có tốc độ nhanh hơn kiểu B-Tree index. Tuy nhiên, cũng vì sử dụng kĩ thuật hashing nên dữ liệu trong bảng không được sắp xếp theo thứ tự, hash index không thể hỗ trợ tìm kiếm trong phạm vi mà chỉ phù hợp với tìm kiếm trả về kết quả chính xác.

Kĩ thuật hashing được chia thành 2 loại chính là: Static Hashing và Dynamic Hashing (em sẽ tìm hiểu cụ thể hơn sau)

#### 5.2. Các ưu điểm chính khi sử dụng chỉ mục 
- Cho phép máy chủ nhanh chóng điều hướng đến vị trí dữ liệu cần thiết trong bảng
- 
- Chỉ mục làm giảm lượng dữ liệu mà máy chủ phải quét/kiểm tra
- Chỉ mục giúp máy chủ tránh sắp xếp và bảng tạm thời
- 
### 6. (BỔ SUNG) Tìm hiểu về truy vấn JOIN trong MySQL
### 7. (BỔ SUNG) Tìm hiểu về một số loại database triển khai theo các tiêu chí trong định lý CAP
Định lý CAP: Khi phân vùng mạng xảy ra, một hệ thống phân tán phải lựa chọn giữa tính nhất quán và tính khả dụng. Việc tồn tại cả tính nhất quán và tính khả dụng là không thể.
- C (Consistency): tính nhất quán của cơ sở dữ liệu. Xét một khung thời gian, mọi người dùng truy cập CSDL sẽ cùng đọc một dữ liệu. Để đảm bảo tính nhất quán này, khi một thông tin được cập nhật/một giao dịch thành công, các bản cập nhật sẽ được chuyển tiếp đến tất cả các nút ngay lập tức. Ngược lại, nếu giao dịch không thành công và không được lưu, các dữ liệu cập nhật về giao dịch sẽ không được lưu và được khôi phục. Dữ liệu được duy trì nhất quán trên tất cả các nút
- A (Availability): tính khả dụng của cơ sở dữ liệu. Mọi yêu cầu được truy vấn đối với hệ thống phân tán đều nhận được phản hồi, bất kể có lỗi hoặc gián đoạn hệ thống. Hệ thống luôn hoạt động, không gián đoạn ngay cả khi có một số nút bị lỗi. Khi một số nút ngừng hoạt động, các nút còn lại phải đáp ứng các yêu cầu trong một khoảng thời gian phù hợp.
- P (Partition Tolerance): khả năng chịu lỗi của cơ sở dữ liệu. Khi có lỗi mạng và có phân vùng, tồn tại một số nút không thể truy cập, toàn bộ hệ thống vẫn hoạt động bình thường và thực hiện các kiểm tra cần thiết để khôi phục trạng thái nhanh nhất có thể.
#### 7.1. MongoDB (CP)
MongoDB ưu tiên tính khả dụng và khả năng chịu lỗi hơn tính nhất quán mạnh mẽ. 
MongoDB lưu trữ dữ liệu dưới dạng BSON (JSON nhị phân). CSDL này thường được sử dụng cho dữ liệu lớn và các ứng dụng thời gian thực (real-time) đặt nhiều vị trí khác nhau. 
Cơ sở dữ liệu này đạt được tính khả dụng cao bằng cách sử dụng kiến trúc phân tán cótập bản sao dữ liệu. Tập bản sao dữ liệu gồm nhiều phiên bản dữ liệu, trong có có đúng một phiên bản đóng vai trò là nút chính (primary node). Nút chính có vai trò xử lý các thao tác ghi, còn các phiên bản khác đóng vai trò là nút phụ (secondary node) có nhiệm vụ sao chép dữ liệu từ nút chính. 
Theo cài đặt mặc định, máy khách đọc dữ liệu từ node chính, nhưng cũng có thể tùy chọn đọc dữ liệu từ nút phụ.
![Hình ảnh](https://rukshanjayasekara.files.wordpress.com/2021/03/0_ifcdubxfcevqxnv3.jpg)
- Dữ liệu trên MongoDB có thể có nhiều bản sao nhưng chỉ có một nút chính. Các bản sao là nhóm các bản dữ liệu duy trì cùng một bộ dữ liệu. Nếu nút chính bị hỏng (bị ngắt kết nối với máy khách), một nút mới sẽ được chọn làm nút chính.  Tất cả các nút phụ khác sẽ cập nhật dữ liệu để bắt kịp với nút chính mới. Cụm dữ liệu khả dụng trở lại. Trong khoảng thời này, các thao tác ghi không thể thực hiện nên dữ liệu vẫn _nhất quán_ trên toàn bộ mạng. Nhờ cơ chế thay nút chính trên, khi phân vùng mạng xảy ra, hệ thống vẫn hoạt động bình thường, _khả năng chịu lỗi_ được đảm bảo. 
- Cơ chế sao chép dữ liệu cho phép tính nhất quán trễ trên tập bản sao dữ liệu. Các cập nhật dữ liệu trên nút chính được sao chép không đồng bộ sang các nút phụ, và có độ trễ nhất định trước khi tất cả các nút có dữ liệu nhất quán với nhau. 
- Khi nút chính bị ngắt kết nối khỏi cụm hoặc máy khách bị ngắt kết nối khỏi nút chính, do trình điều khiển của MongoDB chỉ gửi các yêu cầu (đọc/ghi) đến nút chính nên tính khả dụng không được đảm bảo. 
- Tuy nhiên, MongoDB cung cấp các mức độ nhất quán có thể tùy chọn theo cấu hình. Nó cung cấp các tùy chọn để thực thi đảm bảo tính nhất quán mạnh mẽ hơn dựa trên yêu cầu của ứng dụng. Ví dụ như có một thao tác tùy chọn là "read-preference" (đọc không ghi) cho phép máy khách đọc dữ liệu trực tiếp từ nút phụ. Nhưng các nút phụ có thể không có dữ liệu mới nhất được cập nhật nên tính nhất quán sẽ không cao. 
#### 7.2. Cassandra (AP)
Cassandra là cơ sở dữ liệu thuộc loại AP trong CAP, tập trung ưu tiên tính khả dụng và khả năng chịu lỗi của hệ thống.
- Đây là một cơ sở dữ liệu dạng wide-column cho phép lưu trữ dữ liệu trên mạng phân tán. Kiến trúc của Cassandra là kiến trúc masterless, tất cả các nút trong hệ thống đều như nhau. Khác với MongoDB, nếu như nút chính bị lỗi, cần thực hiện thao tác tạo ra nút chính mới thì Cassandra không có nút chính. Nhờ vậy, cơ sở dữ liệu này có tính khả dụng cao
- Một cơ sở dữ liệu logic được chia sẻ giữa một tập các nút, dữ liệu được phân vùng trên các nút dựa trên hàm băm của khóa phân vùng. Cassandra lưu trữ dữ liệu bằng cách chia đều dữ liệu trên toàn bộ nút, đảm bảo tính khả năng chịu lỗi trong CAP. Các thao tác đọc/ghi có thể thực hiện trên bất kỳ nút nào trong cụm
- Cassandra cung cấp tính nhất quán trễ (eventually consistent). Khi một thao tác ghi hoàn tất, dữ liệu mới nhất sẽ khả dụng với điều kiện không còn thay đổi nào được thực hiện sau đó.
- Nhờ kiến trúc trên, Cassandra có thể cung cấp hiệu suất tốt khi số lượng nút tăng
Trong trường hợp mạng bị phân vùng, dữ liệu trở nên không nhất quán, cơ sở dữ liệu này cung cấp chức năng giúp các nút bắt kịp với các nút khác.
![Hình ảnh](https://sp-ao.shortpixel.ai/client/to_auto,q_lossy,ret_img,w_600/https://www.instaclustr.com/wp-content/uploads/2021/10/The-CAP-Theorem-With-Apache-Cassandra-and-MongoDB4-2-1024x1024.png)
 ### 8. Tài liệu tham khảo 
 [1]  [Architecture of MySQL - GeeksforGeeks](https://www.geeksforgeeks.org/architecture-of-mysql/)
 
 [2]  [MySQL :: MySQL 8.0 Reference Manual :: 8.3 Optimization and Indexes](https://dev.mysql.com/doc/refman/8.0/en/optimization-indexes.html)
 
 [3]  [SQL vs. NoSQL Database: When to Use, How to Choose – Machine Learning for Developers (ml4devs.com)](https://www.ml4devs.com/articles/datastore-choices-sql-vs-nosql-database/)
 
 [4]  [O'Reilly High Performance MySQL 3rd Edition Mar 2012.pdf at master · lackrp/lackrp-public (github.com)](https://github.com/lackrp/lackrp-public/blob/master/eBooks/O'Reilly.High.Performance.MySQL.3rd.Edition.Mar.2012.pdf)
	[5] [PostgreSQL System Architecture - GeeksforGeeks]( https://www.geeksforgeeks.org/postgresql-system-architecture/)
	[6][Oracle Database Architecture](https://www.oracletutorial.com/oracle-administration/oracle-database-architecture/)
	[7] [MySQL :: MySQL 8.0 Reference Manual :: 15 The InnoDB Storage Engine](https://dev.mysql.com/doc/refman/8.0/en/innodb-storage-engine.html)
	[8] [MySQL storage engines - Zetcode](https://zetcode.com/mysql/storageengines/)
	[9] [Role of Apache Cassandra in the CAP theorem](https://www.msystechnologies.com/blog/role-of-apache-cassandra-in-the-cap-theorem/)
	[10] [The CAP Theorem With Apache Cassandra and MongoDB - instaclustr](https://www.instaclustr.com/blog/cassandra-vs-mongodb/)
	
