# 大数据基础之Shell
## Shell概述
> 大数据程序员学习shell的目的:
1. 需要看懂运维人员写的shell程序
2. 偶尔编写一些简单shell程序来管理集群,提高开发效率

> shell概述
1. shell是一个命令行解析器,它接收用户程序/用户命令,然后调用操作系统内核.
2. shell是一个功能强大的编程语言,易编写,易调试,灵活性强.

## Shell解析器
### Linux常见的shell解析器
```shell script
[root@li ~]# cat /etc/shells 
/bin/sh
/bin/bash
/usr/bin/sh
/usr/bin/bash
[root@li ~]#
```
### bash与sh的关系
```shell script
[root@li bin]# ll |grep bash
-rwxr-xr-x. 1 root root      964608 10月 31 2018 bash
lrwxrwxrwx. 1 root root          10 7月  27 2019 bashbug -> bashbug-64
-rwxr-xr-x. 1 root root        6964 10月 31 2018 bashbug-64
lrwxrwxrwx. 1 root root           4 7月  27 2019 sh -> bash
[root@li bin]# 
```
### Centos默认的shell
```shell script
[root@li bin]# echo $SHELL
/bin/bash
[root@li bin]# 
```

## Shell脚本入门
### Shell脚本格式以及HelloWord
> 脚本以 #!/bin/bash 开头,作用是指定解析器

```shell script
[root@li datas]# touch helloword.sh
[root@li datas]# vi helloword.sh 
#!/bin/bash
echo "hello word"
~
"helloword.sh" 2L, 30C written
[root@li datas]# sh helloword.sh 
hello word
[root@li datas]# 
```
### shell脚本的执行方式
> 采用bash或sh+脚本的相对路径或绝对路径,无需赋予脚本可执行权限(+x)
1. sh+脚本的相对路径
```shell script
[root@li datas]# sh helloword.sh 
hello word
[root@li datas]# 
```
2. sh+脚本的绝对路径
```shell script
[root@li datas]# sh /datas/helloword.sh 
hello word
[root@li datas]# 
```
3. bash+脚本的相对路径
```shell script
[root@li datas]# bash helloword.sh 
hello word
[root@li datas]# 
```
4. bash+脚本的绝对路径
```shell script
[root@li datas]# bash /datas/helloword.sh 
hello word
[root@li datas]# 
```
> 采用输入脚本的绝对路径或相对路径执行脚本,必须具有可执行权限(+x)
1. 脚本不具有可执行权限
```shell script
[root@li datas]# ./helloword.sh
-bash: ./helloword.sh: 权限不够
[root@li datas]# /datas/helloword.sh
-bash: /datas/helloword.sh: 权限不够
[root@li datas]# 
```

2. 赋予脚本可执行权限
```shell script
[root@li datas]# chmod +x helloword.sh 
[root@li datas]# ./helloword.sh 
hello word
[root@li datas]# /datas/helloword.sh
hello word
[root@li datas]# 
```
### 多命令处理
<table>
    <tr>
      <th>多命令执行符</th>
      <th>格式</th>
      <th>作用</th>
    </tr>
    <tr>
      <td>;</td>
      <td>命令1;命令2</td>
      <td>多个命令顺序执行,命令之间没有任何逻辑联系</td>
    </tr>
    <tr>
       <td>&&</td>
       <td>命令1&&命令2</td>
       <td>逻辑与 当命令1正确执行,则命令2才会执行;当命令1执行不正确,命令2不执行</td>
    </tr>
    <tr>
        <td>||</td>
        <td>命令1||命令2</td>
        <td>逻辑或 当命令1执行不正确,命令2才执行;命令1正确执行,命令2不执行</td>
    </tr>
  </table>
  
> 新建一个shell文件,在/tmp 目录下新建一个test.txt,并在文件中新增内容"today is 2020-04-28"
```shell script
[root@li datas]# vi batch.sh 
#!/bin/bash
cd /home
touch test.txt
echo "today is 2020-04-28" >> test.txt
~
"batch.sh" 4L, 63C written
[root@li datas]# 
[root@li datas]# sh batch.sh 
[root@li datas]# cd /home/
[root@li home]# ll |grep test
-rw-r--r--  1 root   root    20 4月  28 23:12 test.txt
[root@li home]# cat test.txt 
today is 2020-04-28
[root@li home]# 
```
## Shell中的变量
### 系统变量
> 常用的系统变量,$HOME、$PWD、$SHELL、$USER等预先定义变量
```shell script
[root@li home]# echo $USER
root
[root@li home]# echo $PWD
/home
[root@li home]# echo $HOME
/root
[root@li home]# 
```
> 显示当前系统中的变量
```shell script
[root@li home]# set
BASH=/bin/bashBASH_VERSINFO=([0]="4" [1]="2" [2]="46" [3]="2" [4]="release" [5]="x86_64-redhat-linux-gnu")
BASH_VERSION='4.2.46(2)-release'
JAVA_HOME=/usr/java/jdk1.8.0_221
JAVA_PATH=/usr/java/jdk1.8.0_221/bin:/usr/java/jdk1.8.0_221/jre/bin
JRE_HOME=/usr/java/jdk1.8.0_221/jre
LANG=zh_CN.UTF-8
[root@li home]# 
```
### 自定义变量
> 基本语法
1. 定义变量: 变量名=值
2. 撤销变量: unset 变量名
3. 声明静态变量: readonly变量,注意:不能unset
> 变量定义规则
1. 变量名称可以由字母,数字和下划线组成,但是不能以数字开头,环境变量名建议大写
2. 等号两侧不能有空格
3. 在bash中,变量默认类型都是字符串类型,无法直接进行数值运算
4. 变量的值如果有空格,需要使用双引号或单引号括起来

> 使用案例
1. 定义变量A
```shell script
[root@li home]# A=5
[root@li home]# echo $A
5
```
2. 修改变量A
```shell script
[root@li home]# A=8
[root@li home]# echo $A
8
```
3. 撤销变量A
```shell script
[root@li home]# unset A
[root@li home]# echo $A

```
4. 声明静态的变量B=2,不能unset
```shell script
[root@li home]# readonly B=2
[root@li home]# echo $B
2
[root@li home]# B=9
-bash: B: 只读变量
[root@li home]# unset B
-bash: unset: B: 无法反设定: 只读 variable
```
5. 在bash中,变量默认类型都是字符串类型,无法直接进行数值运算
```shell script
[root@li home]# C=1+2
[root@li home]# echo $C
1+2
```
6. 变量的值如果有空格,需要使用双引号或单引号括起来
```shell script
[root@li home]# D=I LOVE YOU
-bash: LOVE: 未找到命令
[root@li home]# D="I LOVE YOU"
[root@li home]# echo $D
I LOVE YOU
```
7. 可把变量提升为全局环境变量,可供其他Shell程序使用
```shell script
[root@li datas]# vi helloword.sh 
#!/bin/bash
echo "hello word"
echo $B
~
"helloword.sh" 3L, 38C written
[root@li datas]# ./helloword.sh 
hello word

[root@li datas]# export B
[root@li datas]# ./helloword.sh 
hello word
2
[root@li datas]#  
```

