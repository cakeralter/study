[TOC]

### 1. Docker容器内安装vim

```powershell
docker exec -it nginx /bin/bash
apt-get update
apt-get install vim
```

### 2. Docker容器和宿主机时间相差8小时解决方法

- 重启容器，添加参数 `-v /etc/localtime:/etc/localtime:ro`

- 不重启容器的话，复制文件到容器中:

 ```powershell
 docker cp /etc/localtime [containerId]:/etc/localtime
 ```

### 3. Docker容器和宿主机文件复制

```powershell
# 复制宿主机nacos-server.jar文件到nacos容器/home/nacos/target目录下
docker cp /home/dc2-user/nacos-server.jar nacos:/home/nacos/target

# 复制nacos容器nacos-server.jar文件到宿主机/home/nacos/target目录下
docker cp nacos:/home/nacos/targetnacos-server.jar /home/dc2-user/
```