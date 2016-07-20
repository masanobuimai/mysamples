# FaceletsをJSPの代わりに使う

* faceletsは必ずしもJSF管理Beanから呼び出す必要はない。
* 条件さえ揃っていれば，JSPのようにServletから呼び出すこともできる。
* 条件というのは，HttpServletRequest#attribute にfaceletsに展開するオブジェクトが詰まっていること。
* 見かけ上，JSF管理Beanを使っていないだけで，FacesServletは必要（→ web.xml参照）

### メモ

* この使い方でfacletsタグがどこまで使えるのか興味ある。
