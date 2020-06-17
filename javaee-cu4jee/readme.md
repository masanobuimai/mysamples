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

### WildFlyで動かす場合

特になんも設定しなくていい


## TomEEでデータソースを設定する

* tomee-homeのlib/にJDBCドライバを格納する
* tomee-home/conf/tomee.xml か WEB-INF/resources.xmlにデータソースを定義する
  * tomee.xmlだとwarファイルは汚さない(その代わりtomeeに設定が必要)
  * resources.xmlだとwarファイルを汚すが，tomeeは設定不要
* web.xmlにresource-refを定義する(これを怠ると java:comp/env でルックアップできない)


# JavaEE7対応
  * TomEE plus 7.1.3
  * WildFly 9
