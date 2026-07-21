## 國泰世華 JAVA engineer 線上作業


## 環境

- JDK 8
- Spring Boot 2.7.18
- Maven
- H2 Database

## 執行方式

預設執行於：http://localhost:8080

H2 Console：http://localhost:8080/h2-console

JDBC URL：jdbc:h2:mem:coindesk

## 資料初始化

本專案使用 H2 In-Memory Database。

程式啟動時：

- Hibernate 根據 Entity 自動建立資料表。
- Spring Boot 執行 `data.sql` 初始化幣別資料。

初始資料：

| Code | 中文名稱 |
|------|---------|
| EUR | 歐元 |
| GBP | 英鎊 |
| USD | 美元 |

## 我做了什麼

- 實作 RESTful API
- 完成幣別資料 CRUD 功能
- 串接 CoinDesk API
- 將 CoinDesk API 資料轉換為指定格式
- 使用 MockMvc 撰寫 API 測試
