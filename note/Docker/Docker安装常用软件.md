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
mkdir -p /root/docker/mysql/master/data \
    && mkdir -p /root/docker/mysql/master/conf \
    && mkdir -p /root/docker/mysql/master/log

docker run \
-p 3306:3306 --name mysql-master \
--restart=always \
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

# 各库ID不同
server_id=1
log-bin=mysql-bin
# 从库设置为1
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

- 查看同步状态

```powershell
# 主库
show master status
# 从库
show slave status\G
```

- 主库添加同步用户并授予权限

```powershell
CREATE USER 'backup'@'%' IDENTIFIED WITH mysql_native_password BY 'back1996';

GRANT REPLICATION SLAVE ON *.* to 'backup'@'%';

FLUSH PRIVILEGES;
```

- 从库同步配置

```powershell
STOP SLAVE;
RESET SLAVE;
CHANGE MASTER TO master_host='192.168.44.131',master_user='backup',master_password='123456',master_log_file='mysql-bin.000006',master_log_pos=0,master_port=3306;
START SLAVE;
SHOW SLAVE STATUS;

set GLOBAL SQL_SLAVE_SKIP_COUNTER=1;
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
docker run -p 6379:6379 \
--name redis --restart=always \
-d redis redis-server \
--appendonly yes \
--requirepass "xxx"
```

### 3. Docker安装MongoDB

- 拉取镜像

```powershell
docker pull mongo:3.6.11
```

- 运行容器

```powershell
docker run -p 27017:27017 \
--name mongo --restart=always \
-d mongo:3.6.11
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
mkdir -p /root/docker/nginx/html \
    && mkdir -p /root/docker/nginx/conf \
    && touch /root/docker/nginx/conf/nginx.conf \
    && mkdir -p /root/docker/nginx/logs \
    && mkdir -p /root/docker/nginx/static

docker run -d -p 80:80 -p 8088:8088 \
--name nginx --restart=always \
-v /root/docker/nginx/html:/usr/share/nginx/html \
-v /root/docker/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-v /root/docker/nginx/logs:/var/log/nginx \
-v /root/docker/nginx/static:/root/nginx/static \
nginx
```

### 5. Docker安装RabbitMQ

- 拉取镜像

```powershell
docker pull rabbitmq:3.7.14
```

- 运行容器

```powershell
docker run -d \
--name rabbitmq --restart=always \
-p 5672:5672 -p 15672:15672 \
--hostname rabbit \
-e RABBITMQ_DEFAULT_VHOST=/ \
-e RABBITMQ_DEFAULT_USER=xxx \
-e RABBITMQ_DEFAULT_PASS=xxx \
rabbitmq:3.7.14
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
docker run --name elasticsearch \
-p 9200:9200 -p 9300:9300 -p5601:5601 \
-e "discovery.type=single-node" \
-e "ES_JAVA_OPTS=-Xms256m -Xmx512m" \
--restart=always \
-d elasticsearch:6.8.6
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
docker run -d \
--name kibana \
--network=container:elasticsearch \
-e "ELASTICSEARCH_URL=http://127.0.0.1:9200" \
--restart=always kibana:6.8.6
```

### 8. Docker安装Zookeeper

- 拉取镜像

```powershell
docker pull zookeeper
```

- 启动容器

```powershell
docker run -d \
--privileged=true \
--name zookeeper \
-v /etc/localtime:/etc/localtime:ro \
-p 2181:2181 \
--restart=always \
zookeeper:latest
```

### 9. Docker安装Nacos

- 拉取镜像

```powershell
docker pull nacos
```

- 启动容器

```powershell
mkdir -p /root/docker/nacos/logs

docker run -d \
-e MODE=standalone \
-e JVM_XMS=256m \
-e JVM_XMX=512m \
-p 8848:8848 \
--restart=always \
-v /root/docker/nacos/logs:/home/nacos/logs \
--name nacos \
nacos/nacos-server
```

### 10. Docker安装Dubbo-Admin

- 拉取镜像

```powershell
docker pull chenchuxin/dubbo-admin
```

- 启动容器

```powershell
docker run -d --name dubbo-admin \
-p 9001:8080 \
-e dubbo.registry.address=zookeeper://192.168.44.131:2181 \
-e dubbo.admin.root.password=root \
-e dubbo.admin.guest.password=root \
--restart=always \
chenchuxin/dubbo-admin 
```

### 11. Docker安装portainer

- 拉取镜像

```powershell
docker pull portainer/portainer
```

- 启动容器

```powershell
docker run -d -p 9000:9000 \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /etc/localtime:/etc/localtime:ro \
--name portainer --restart=always \
portainer/portainer
```

### 12. Docker安装Tale博客

- 拉取镜像

```powershell
docker search tale:1.0
```

- 启动容器

```powershell
mkdir -p /root/docker/tale

docker run -d \
--privileged --hostname tale \
--name tale \
-v /etc/localtime:/etc/localtime:ro \
-v /root/docker/tale:/var/tale_home \
-p 127.0.0.1:9000:9000 \
-m 1024m --memory-swap -1 \
tale:1.0
```