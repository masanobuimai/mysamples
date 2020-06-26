# メモ
## ManagedExecutorService(MES)のテスト

### TomEEで動かす場合

BooServlet（デフォルトのMESをlookupする）を動かすには，事前にTomEEに仕込みが必要。

TomEEの conf/server.properties に以下の設定を追加する。
```
   openejb.environment.default=true
```

TomEEのデフォルトは"comp/DefaultManagedExecutorService"が無効になってるそうな。
* https://tomee.apache.org/latest/docs/admin/configuration/server.html

TomEEのログが文字化けする場合，IntelliJでは idea.vmoptions に以下の設定を追加する。
```
   -Dfile.encoding=UTF-8
```

その代わりJVMのエラーメッセージが文字化けする。

### WildFlyやGlassfishで動かす場合

特になんも設定しなくていい


## TomEEでデータソースを設定する

* tomee-homeのlib/にJDBCドライバを格納する
* tomee-home/conf/tomee.xml か WEB-INF/resources.xmlにデータソースを定義する
  * tomee.xmlだとwarファイルは汚さない(その代わりtomeeに設定が必要)
  * resources.xmlだとwarファイルを汚すが，tomeeは設定不要
* web.xmlにresource-refを定義する(これを怠ると java:comp/env でルックアップできない)

## WildFlyでデータソースを設定する

* WildFlyを立ち上げて管理コンソールにログインする(事前にユーザ登録する必要あり)
```
  http://localhost:9990/
```

* **Deployments → Upload Deployment** でJDBCドライバを登録する
* **Configuration → Subsystems → Datasources & Drivers → Add Datasource** でデータソースを設定する
  * このとき，JNDI Nameを「```java:/comp/env/～```」で登録する

web.xmlのresource-refは無くても問題ない
  
## Glassfishでデータソースを設定する

* ```$GF_HOME/glassfish/lib``` にJDBCドライバを配置する
* Glassfishを立ち上げて管理コンソールにログインする
```
  http://localhost:4848/
```

* **Resources → JDBC → JDBC Connection Pools** でコネクションプールを作る
  * **Resource Type** は「```javax.sql.DataSource```」
* **Resources → JDBC → JDBC Resources** でデータソースを作る
  * **JNDI Name** は「```jdbc/～```」

web.xmlのresource-refは無くても問題ない  
JNDI lookupするときの名前は ```"jdbc/～``` (```java:comp/env```は付かない)


## データソース設定方法

|                                     |TomEE|Glassfish|Wildfly|
|-------------------------------------|-----|---------|-------|
|JDBCドライバの登録                      |○   |○       |△ *0   |
|``web.xml``に``<data-source>``で設定する|○   |○       |× *1   |
|APサーバ固有の設定ファイルで設定する         |○   |△ *2   |△ *3   |


*0 管理コンソール経由で登録が必要（TomEE，Glassfishはlibディレクトリに配置するだけ）
*1 JDBCドライバが見つけられずデプロイに失敗する（未解決）
*2 JNDIルックアップするときに jndi-name の先頭に「``java:app/``」を付ける
*2 JNDI名に制約あり（「``java:jboss/～``」じゃないとダメ？未確認）


# JavaEE7対応
  * TomEE plus 7.1.3
  * WildFly 9
