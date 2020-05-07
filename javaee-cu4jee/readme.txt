# ManagedExecutorService(MES)のテスト

## TomEEで動かす場合

BooServlet（デフォルトのMESをlookupする）を動かすには，事前にTomEEに仕込みが必要。

TomEEの conf/server.properties に以下の設定を追加する。
   openejb.environment.default=true

TomEEのデフォルトは"comp/DefaultManagedExecutorService"が無効になってるそうな。
https://tomee.apache.org/latest/docs/admin/configuration/server.html

TomEEのログが文字化けする場合，IntelliJでは idea.vmoptions に以下の設定を追加する。
   -Dfile.encoding=UTF-8

その代わりJVMのエラーメッセージが文字化けする。
