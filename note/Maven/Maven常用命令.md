[TOC]

### 1. Maven安装本地jar包

```powershell
mvn install:install-file -Dfile=D:\ojdbc7-12.1.0.jar -DgroupId=oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar -DgeneratePom=true 
```