* spring-boot-starter-actuatorを入れると Run/Debug ConfigはgradleよりSpring Bootのほうが便利
* spring-boot-devtoolsが入っているならば
  * Spring BootのRun/Debug Configの「構成」タブで以下の設定にする
    * '更新'アクション時 → Update classes and resources
    * フレーム切り替え時 → Update classes and resources
  * Ctrl+F10で更新
  * または
    * Settings | ビルド、実行、デプロイ | コンパイラ の「自動的にプロジェクトをビルドする」
    * Find Action → 「レジストリ(registry)」で「compiler.automake.allow.when.app.running」をチェックする
