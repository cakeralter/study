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

### 2. 跨域转发配置

```nginx
upstream vue {
    server     127.0.0.1:8080;
}

server {
    listen  80;
    server_name vue.caker.top;

    location / {
        proxy_pass http://vue;
    }

    location /api/ {
    # 	add_header Access-Control-Allow-Origin *;
    #   add_header Access-Control-Allow-Headers "Origin, X-Requested-With, Content-Type, Accept";
    #   add_header Access-Control-Allow-Methods "GET, POST, OPTIONS";
        #rewrite ^/api/(.*)$ /$1 break;
		# proxy_redirect off;
		# proxy_set_header Host $http_host;
		# proxy_set_header X-Real-IP $remote_addr;
		# proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://localhost:808/;
    }
}
```

