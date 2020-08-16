[TOC]

## Java IO笔记

### 1. `File` 常用方法

- `getAbsolutePath()` ：获取绝对路径
- `getPath()` ：获取构造器中的路径
- `getName()` ：获取文件名
- `getParent()` ：获取父级路径
- `length()` ：获取文件字节数
- `lastModified()` ：获取最近修改时间（ms）
- `list()` ：获取当前目录下所有文件及文件夹名称
- `listFiles()` ：获取当前目录下所有文件及文件夹
- `renameTo(File dest)` ：重命名，要求 `dest` 不存在
- `isDirectory()` ：判断是否为文件目录
- `isFile()` ：判断是否为文件
- `exists()` ：判断是否存在
- `canRead()` ：判断是否可读
- `canWrite()` ：判断是否可写
- `isHidden()` ：判断是否隐藏
- `createNewFile()` ：创建对应磁盘文件
- `mkdir()` ：创建文件目录，上层目录不存在则失败
- `mkdirs()` ：创建文件目录，上层目录不存在则先创建上层目录
- `delete()` ：删除磁盘文件或目录（目录必须是空的）