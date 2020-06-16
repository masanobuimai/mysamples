Apache DerbyのデモDB（ToursDb）をパクってきた。

### DBの初期化

* volumeの作成
  ```docker volume create sample_data```
* docker-comple.ymlを実行する
  * docker-entrypoint-initdb.d/ の中身が実行される
  * src/toursdb/insertMaps はまだ直してないので，動かない

volume含めて削除する場合は ```docker-compose down -v```を実行する

### DBの使い方

* 接続文字列
  jdbc:postgresql://localhost:5432/sample_local
* ID/パスワード
  postgres / password

* pgadmin経由の場合
  * URL http://localhost/
  * ID/パスワード
    admin / password
  * サーバを追加する場合
    * host:db (docker-composeのサービス名)
    * database:sample_local
    * username/password:postgres/password
    
