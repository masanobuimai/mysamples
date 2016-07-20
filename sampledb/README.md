Apache DerbyのデモDB（ToursDb）をパクってきた。

**使い方**

 * Derbyをネットワークモードで立ち上げる。
 * jdbc:derby://localhost:1527/sampledb;create=true で接続
 * ToursDB_schema.sql を実行
 * insertMaps を実行
 * loadTables.sql の順番に従って，残りのSQLファイルを実行

sampledbは，mysamples/sampledb/db に作成するように実行構成（Apache-Derby）を組んでる。
それがイヤだったら，他のディレクトリに変更してよい。