[TOC]

### 1. Docker安装MySQL

- 查找MySQL镜像

```powershell
docker search mysql
```

- 拉取镜像（默认为最新版本）

```powershell
docker pull mysql
```

- 运行容器

```powershell
docker run \
-p 3306:3306 --name mysql-master --restart=always \
-e MYSQL_ROOT_PASSWORD=123456 \
-v /etc/localtime:/etc/localtime:ro \
-v /root/docker/mysql/master/data:/var/lib/mysql-files \
-v /root/docker/mysql/master/conf:/etc/mysql \
-v /root/docker/mysql/master/log:/var/log/mysql \
-d mysql
```

- 修改MySQL8相关连接配置

```powershell
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'caker1996';

ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'caker1996';

FLUSH PRIVILEGES;
```

- my.cnf

```properties
[client]
default-character-set=utf8
 
[mysql]
default-character-set=utf8
 
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve

server_id=1
log-bin=mysql-bin
read-only=0
binlog-do-db=gmall_ums
binlog-do-db=gmall_pms
binlog-do-db=gmall_oms
binlog-do-db=gmall_sms
binlog-do-db=gmall_cms
binlog-do-db=gmall
binlog-do-db=test

replicate-ignore-db=mysql
replicate-ignore-db=sys
replicate-ignore-db=information_schema
replicate-ignore-db=performance_schema
```

- 主从同步用户

```powershell
CREATE USER 'backup'@'%' IDENTIFIED WITH mysql_native_password BY 'back1996';

GRANT REPLICATION SLAVE ON *.* to 'backup'@'%';

FLUSH PRIVILEGES;
```

### 2. Docker安装Redis

- 查找Redis镜像

```powershell
docker search redis
```

- 拉取镜像

```powershell
docker pull redis
```

- 运行容器

```powershell
docker run -p 6379:6379 --name redis --restart=always -d redis redis-server --appendonly yes --requirepass "xxx"
```

### 3. Docker安装MongoDB

- 拉取镜像

```powershell
docker pull mongo:3.6.11
```

- 运行容器

```powershell
docker run -p 27017:27017 --name mongo --restart=always -d mongo:3.6.11
```

- 设置用户密码（依次执行以下命令）

```powershell
docker exec -it mongo /bin/bash
mongo
use admin
db.createUser({user:"xxx",pwd:"xxx",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})
```

### 4. Docker安装Nginx

- 拉取镜像

```powershell
docker pull nginx
```

- 创建相关目录（方便配置）

- 运行容器

```powershell
docker run -d -p 80:80 -p 8088:8088 --name nginx --restart=always -v ~/docker/nginx/html:/usr/share/nginx/html -v ~/docker/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v ~/docker/nginx/logs:/var/log/nginx -v ~/docker/nginx/static:/root/nginx/static nginx
```

### 5. Docker安装RabbitMQ

- 拉取镜像

```powershell
docker pull rabbitmq:3.7.14
```

- 运行容器

```powershell
docker run -d --name rabbitmq --restart=always -p 5672:5672 -p 15672:15672 --hostname rabbit -e RABBITMQ_DEFAULT_VHOST=/ -e RABBITMQ_DEFAULT_USER=xxx -e RABBITMQ_DEFAULT_PASS=xxx rabbitmq:3.7.14
```

- 配置webUI

```powershell
docker exec -it rabbitmq /bin/bash
rabbitmq-plugins list
rabbitmq-plugins enable rabbitmq_management
```

### 6. Docker安装ElasticSearch

- 拉取镜像

```powershell
docker pull elasticsearch:6.8.6
```

- 创建网络 

```powershell
docker network create esnet
```

- 运行容器

```powershell
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 -p5601:5601 -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xms256m -Xmx512m"  --restart=always -d elasticsearch:6.8.6
```

- 查看日志

```powershell
docker logs elasticsearch
```

- 修改配置，解决跨域

```powershell
docker exec -it elasticsearch /bin/bash
cd /usr/share/elasticsearch/config/
apt-get update
apt-get install vim
vi elasticsearch.yml
```

`elasticsearch.yml`添加配置

> http.cors.enabled: true

> http.cors.allow-origin: "*"

- 安装ik分词器

```powershell
docker exec -it elasticsearch /bin/bash
cd /usr/share/elasticsearch/plugins/
elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.8.6/elasticsearch-analysis-ik-6.8.6.zip
exit
```

### 7. Docker安装Kibana

- 拉取镜像

```powershell
docker pull kibana:6.8.6
```

- 启动容器 

```powershell
docker run -d --name kibana --network=container:elasticsearch -e "ELASTICSEARCH_URL=http://127.0.0.1:9200" --restart=always kibana:6.8.6
```