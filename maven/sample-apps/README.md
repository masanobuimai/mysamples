# Maven Java EEプロジェクト

IntelliJでMavenプロジェクトを作成
* archetypeは指定しない
* プロジェクトを作ってから，pom.xmlを直接編集
* pom.xml上でGenerate | Dependencyを実行してもjavax:javaee-apiは参照できなかった
* そのため，pom.xmlを直接編集してjavaee-api-7.0.jarを参照に加えた
* pom.xmlを読み取って，勝手にArtifactsを登録する
* src/main/webappディレクトリは自分で作る
