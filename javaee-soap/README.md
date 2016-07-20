# SOAPのサンプル

### IntelliJのWebServiceモジュール

* 設定が古いので，自動生成する ``web.xml`` や ``sun-jaxws.xml`` とかは削除する（ ``WEB-INF``は無くて良い）
* New→Web Serviceで作成するSOAPサーバのエンドポイントは，比較的マシなコードを書くが，余計なコードも紛れている（あとイチイチ， ``sun-jaxws.xml`` を生成する）

* New→Web Service Clientで，WSDLを指定してクライアントコードを生成させても，まともなコードは生成されない。
* Tools→WebServices→Generate Java Code From Wsdl...もダメ。
* IntelliJのWebクライアントの生成機能は腐ってる。


