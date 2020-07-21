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

