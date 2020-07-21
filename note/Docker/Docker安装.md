[TOC]

### 1. Centos7安装Docker

- 升级yum
```powershell
yum update
```
- 安装必要工具
```powershell
yum install -y yum-utils device-mapper-persistent-data lvm2
```
- 设置yum源
```powershell
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
- 安装Docker
```powershell
yum install docker-ce
```
- 启动Docker、设置开机自启
```powershell
systemctl start docker
systemctl enable docker
```
- 验证安装
```powershell
docker version 
```
- 设置阿里镜像加速

在/etc/docker目录新建文件daemon.json，并将以下内容复制进去。
```json
{
  "registry-mirrors": ["https://c6m17hct.mirror.aliyuncs.com"]
}
```
执行以下命令
```powershell
sudo systemctl daemon-reload
sudo systemctl restart docker
```