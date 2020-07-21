[TOC]

### 1.Nginx静态资源服务器配置

```nginx
server {
	listen 8088; // 端口
	server_name 47.101.186.23; // 服务器名称

	location / {
		alias /root/nginx/static/; // 静态资源路径
		autoindex on; 
		autoindex_localtime on; // 本地时间
		autoindex_exact_size off;
		charset gbk;
    }
}
```