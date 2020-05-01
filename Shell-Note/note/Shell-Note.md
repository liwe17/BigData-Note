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
### 特殊变量
<table>
    <tr>
      <th>变量名</th>
      <th>作用</th>
    </tr>
    <tr>
      <td>$?</td>
      <td>最后一次执行的命令返回的状态,0代表成功,非0(具体哪个数由程序决定)代表失败</td>
    </tr>    
    <tr>
      <td>$$</td>
      <td>当前进程的进程号(PID)</td>
    </tr>    
    <tr>
      <td>$!</td>
      <td>后台运行的最后一个进程的进程号</td>
    </tr>    
    <tr>
      <td>$n</td>
      <td>n为数字,$0代表命令本身,$1-9代表第一到第九个参数,十以上的参数需要用大括号包含,如9代表第一到第九个参数,十以上的参数需要用大括号包含,如{10}</td>
    </tr>
    <tr>
      <td>$*</td>
      <td>这个变量代表命令行中所有参数,$*把所有的参数看成一个整体</td>
    </tr>
    <tr>
      <td>$@</td>
      <td>这个变量也代表命令行中的所有的参数,不过把每个参数区分对待</td>
    </tr>
    <tr>
      <td>$#</td>
      <td>这个变量代表命令行中所有参数的个数</td>
    </tr>             
</table>

1. 位置变量
```shell script
[root@li datas]# vi param.sh 
#!/bin/bash
echo "脚本名称为: $0  第一个参数为: $1  第二个参数为: $2"
echo "一共有 $# 个参数"

echo '遍历 $*'
for i in "$*"
do
 echo "The param is: $i"
done

echo '遍历 $@'
for j in "$@"
do
 echo "the param is: $j"
done
~
"param.sh" 15L, 248C written
[root@li datas]# sh param.sh a b
脚本名称为: param.sh  第一个参数为: a  第二个参数为: b
一共有 2 个参数
遍历 $*
The param is: a b
遍历 $@
the param is: a
the param is: b
[root@li datas]# echo $?
0
[root@li datas]# cat 1.txt
cat: 1.txt: 没有那个文件或目录
[root@li datas]# echo $?
1
[root@li datas]# 

```
> 注意事项:
> - 当$*与$@被双引号""包含时,"$*"会将所有的参数作为一个整体,以"$1 $2 ... $n"的形式输出所有参数
> - "$@"会将各个参数分开,以"$1" "$2"... $n"的形式输出所有参数

2. 预定义变量
```shell script
[root@li datas]# vi pre.sh
#!/bin/bash
echo "the current process is $$"

find /root -name pre.sh &
echo "the last one daemon process is $!"
~
"pre.sh" [New] 5L, 113C written
[root@li datas]# sh pre.sh 
the current process is 1087
the last one daemon process is 1088
[root@li datas]# 
```
## Shell中通配符与其他特殊符号
<table>
    <tr>
      <th>通配符</th>
      <th>作用</th>
    </tr>
    <tr>
      <td>?</td>
      <td>匹配一个任意字符</td>
    </tr>
    <tr>
      <td>*</td>
      <td>匹配0个或任意多个任意字符,也就是可以匹配任何内容</td>
    </tr> 
    <tr>
      <td>[]</td>
      <td>匹配中括号中任意一个字符,例如:[abc]代表一定匹配一个字符,或者是a,或者b,或者c</td>
    </tr> 
    <tr>
      <td>[-]</td>
      <td>匹配中括号中任意一个字符,-代表一个范围,例如:[a-z],代表匹配一个小写字母</td>
    </tr> 
    <tr>
      <td>[^]</td>
      <td>逻辑非,表示匹配不是括号中内的一个字符,例如: [^0-9] 代表匹配一个不是数字的字符</td>
    </tr>     
</table>

1. 通配符实例
```shell script
[root@li datas]# touch abc
[root@li datas]# touch abcd
[root@li datas]# touch 012
[root@li datas]# touch 0abc
[root@li datas]# ls ?abc
0abc
[root@li datas]# ls [0-9]*
012  0abc
[root@li datas]# ls [^0-9]*
abc  abcd  batch.sh  helloword.sh  param.sh  pre.sh
[root@li datas]# 
```

## Shell中输入输出重定向及管道符
### 标准输入输出
<table>
    <tr>
      <th>设备</th>
      <th>设备文件名</th>
      <th>文件描述</th>
      <th>类型</th>
    </tr>
    <tr>
      <td>键盘</td>
      <td>/dev/stdin</td>
      <td>0</td>
      <td>标准输入</td>
    </tr>
    <tr>
      <td>显示器</td>
      <td>/dev/stdout</td>
      <td>1</td>
      <td>标准输出</td>
    </tr>
    <tr>
      <td>显示器</td>
      <td>/dev/stdout</td>
      <td>2</td>
      <td>标准错误输出</td>
    </tr>
</table>

### 输出重定向
<table>
    <tr>
        <th>类型</th>
        <th>符号</th>
        <th>作用</th>
    </tr>
    <tr>
        <th>标准输出重定向</th>
        <th>命令>文件</th>
        <th>以覆盖方式,把命令正确输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th>标准输出重定向</th>
        <th>命令>>文件</th>
        <th>以追加方式,把命令正确输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th>标准错误输出重定向</th>
        <th>命令2>文件</th>
        <th>以覆盖方式,把命令的错误输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th>标准错误输出重定向</th>
        <th>命令2>>文件</th>
        <th>以追加方式,把命令的错误输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th></th>
        <th>命令>文件 2>&1</th>
        <th>以覆盖方式,把命令的正确和错误输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th></th>
        <th>命令>>文件 2>&1</th>
        <th>以追加方式,把命令的正确和错误输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th>正确和错误输出同时保存</th>
        <th>命令&>文件</th>
        <th>以覆盖方式,把命令的正确和错误输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th></th>
        <th>命令&>>文件</th>
        <th>以追加方式,把命令的正确和错误输出,输出到指定的文件或设备当中</th>
    </tr>
    <tr>
        <th></th>
        <th>命令>>文件1 命令2>>文件2</th>
        <th>把正确输出追加到文件1,错误输出追加到文件2</th>
    </tr>
</table>

1. 举例说明
```shell script
[root@li datas]# ls &>/dev/null
[root@li datas]# ls 2>1.txt
012  0abc  1.txt  abc  abcd  batch.sh  helloword.sh  param.sh  pre.sh
[root@li datas]# cat 1.txt 
[root@li datas]# 
[root@li datas]# ls >1.txt
[root@li datas]# cat 1.txt 
012
0abc
1.txt
abc
abcd
batch.sh
helloword.sh
param.sh
pre.sh
[root@li datas]# lss &>2.txt
[root@li datas]# cat 2.txt 
-bash: lss: 未找到命令
[root@li datas]# ls &>>2.txt
[root@li datas]# cat 2.txt 
-bash: lss: 未找到命令
012
0abc
1.txt
2.txt
abc
abcd
batch.sh
helloword.sh
param.sh
pre.sh
[root@li datas]# ls >3.txt 2>&1
[root@li datas]# cat 3.txt 
012
0abc
1.txt
2.txt
3.txt
abc
abcd
batch.sh
helloword.sh
param.sh
pre.sh
[root@li datas]# lss >>3.txt 2>&1
[root@li datas]# cat 3.txt 
012
0abc
1.txt
2.txt
3.txt
abc
abcd
batch.sh
helloword.sh
param.sh
pre.sh
-bash: lss: 未找到命令
[root@li datas]# ls >>11.txt 2>>22.txt
[root@li datas]# cat 11.txt 
012
0abc
11.txt
1.txt
22.txt
2.txt
3.txt
abc
abcd
batch.sh
helloword.sh
param.sh
pre.sh
[root@li datas]# cat 22.txt  
```
2. 判断一条命令是否正确
```shell script
[root@li datas]# ls &>/dev/null && echo yes || echo no
yes
[root@li datas]# lss &>/dev/null && echo yes || echo no
no 
```
> grep [选项] “搜索内容” 文件名
  <br>
  选项
  <br>
  -i: 忽略大小写
  <br>
  -n: 输出行号
  <br>
  -v: 反向查找
  <br>
  -r: 指定目录下查找,并高亮关键字

1. grep小例子
```shell script
[root@li datas]# cat batch.sh 
#!/bin/bash
cd /home
touch test.txt
echo "today is 2020-04-28" >> test.txt
[root@li datas]# grep -v 'home' batch.sh 
#!/bin/bash
touch test.txt
echo "today is 2020-04-28" >> test.txt
[root@li datas]# grep -vn 'home' batch.sh 
1:#!/bin/bash
3:touch test.txt
4:echo "today is 2020-04-28" >> test.txt
[root@li datas]# grep -r 'home' ./
./batch.sh:cd /home
[root@li datas]# grep "home" --color=auto -n batch.sh 
2:cd /home
[root@li datas]# 
```
## Shell中的运算符
> 基本语法
1. $((运算式)) 或 $[运算式]
2. expr +,-,\*,/,%  加,减,乘,除,取余. 注意:运算符之间要有空格
```shell script
[root@li datas]# expr 1 \* 2
2
[root@li datas]# expr 1 - 2 
-1
[root@li datas]# expr `expr 2 + 3` \* 4
20
[root@li datas]# s=$[(2+3)*4]
[root@li datas]# echo $s
20
```

## shell中的条件判断
> 基本语法
1. [ condition ] 注意:condition前后要有空格,条件非空即为true,[ wei ]返回true,[] 返回false.

> 常用判断条件
1. 两个整数之间的比较
```shell script
= 字符串比较
-lt 小于(less than)        -le 小于等于(less equal)
-eq 等于(equal)            -gt 大于(greater than)
-ge 大于等于(greater equal) -ne 不等于(not equal)
```
2. 按照文件权限判断
```shell script
-r 有读权限(read)   -w 有写权限(write) -x 由执行权限(execute)
```
3. 按照文件类型判断
```shell script
-f 文件存在并且是一个常规文件(file)
-e 文件存在(existence)
-d 文件存在并且是一个目录(directory)
```
```shell script
[root@li datas]# [ 22 -eq 23 ]
[root@li datas]# echo $?
1
[root@li datas]# [ 22-eq23 ]
[root@li datas]# echo $?
0
[root@li datas]# [ -w batch.sh ]
[root@li datas]# echo $?
0
[root@li datas]# [ -e batch1.sh ]
[root@li datas]# echo $?
1
[root@li datas]# 
```
## Shell中的流程控制
### if判断
1. 基本语法
```shell script
if [ 条件判断式 ];then
 程序
fi

或者
if [ 条件判断式 ]
 then
  程序
fi
```
> 注意事项:
> - [ 条件判断式 ],中括号和条件判断式之间必须有空格
> - if后要有空格

2. 小例子:输入一个数字,如果是1,则输出1,如果是2,则输出2,如果是其它,输出3
```shell script
[root@li datas]# vi iftest.sh 
#!/bin/bash

if [ $1 -eq 1 ];then
echo 1
elif [ $1 -eq 2 ]
 then
  echo 2
else
  echo 3
fi
~
"iftest.sh" 10L, 91C written
[root@li datas]# sh iftest.sh 1
1
[root@li datas]# sh iftest.sh 2
2
[root@li datas]# sh iftest.sh 3
3
[root@li datas]# sh iftest.sh 4
3
[root@li datas]# 
```
### case语句
1. 基本语法
```shell script
case $变量名 in
"值 1")
    如果符合,执行程序1
  ;;
"值 2")
    如果符合,执行程序2
  ;;
...省略其他分支...
*)
    如果符合,执行程序1
  ;;
esac
```
> 注意事项:
> - case行尾必须为单词"in",每一个模式匹配必须以右括号")"结束
> - 双分号";;"表示命令序列结束,相当于java中的break
> - 最后的"*）"表示默认模式,相当于java中的default

2. 小例子:输入一个数字,如果是1,则输出1,如果是2,则输出2,如果是其它,输出3
```shell script
[root@li datas]# vi casetest.sh
#!/bin/bash
case $1 in
"1")
 echo 1
 ;;
"2")
 echo 2
 ;;
*)
 echo 3
 ;;
esac
~
"casetest.sh" [New] 12L, 77C written
[root@li datas]# sh casetest.sh 1
1
[root@li datas]# sh casetest.sh 2
2
[root@li datas]# sh casetest.sh 3
3
[root@li datas]# sh casetest.sh 4
3
```
### for循环
1. 基本语法1
```shell script
for(("初始值";"循环控制条件";"变量变化"))
 do
  程序
 done 
```
2. 小案例,1累加到100
```shell script
[root@li datas]# vi fortest1.sh 
#!/bin/bash
s=0
for((i=1;i<=100;i++))
do
 s=$(expr $s + $i)
done

echo $s
~
"fortest1.sh" 8L, 74C written
[root@li datas]# sh fortest1.sh 
5050
```
3. 基本语法2
```shell script
for "变量" in 值1 值2 ...
do
 程序
done
```

```shell script
[root@li datas]# vi fortest2.sh 

#!/bin/bash
for i in $@
do
 echo $i
done

for i in $*
do
 echo $i
done

for i in "$*"
do
 echo $i
done
~
"fortest2.sh" 15L, 103C written
[root@li datas]# sh fortest2.sh 1 2 3
1
2
3
1
2
3
1 2 3
[root@li datas]#
```
### while循环
1. 基本语法
```shell script
while [ condition ]
do
 程序
done
```
2. 小例子:从1累加到100
```shell script
[root@li datas]# vi whiletest.sh 
#!/bin/bash
s=0
i=0
while [ $i -le $1 ]
do
 s=$[$s+$i]
 i=$[$i+1]
done

echo $s
~
"whiletest.sh" 10L, 80C written
[root@li datas]# sh whiletest.sh 100
5050
[root@li datas]# 
```
## Shell中读取控制台输入
> 接受键盘输入:read [选项] [变量名]
 <br>
 -p “提示信息”:在等待read输入时,输出提示信息
 <br>
 -t 秒数 : read 命令会一直等待用户输入,使用此选项可以指定等待时间
 <br>
 -s: 隐藏输入的数据
 <br>
 -n 字符数:read命令只接受指定的字符数,就会执行
```shell script
[root@li datas]# vi read.sh
#!/bin/bash
read -t 7 -p "enter your name in 7 seconds " -s -n 2 NAME
echo $NAME
~
"read.sh" 3L, 81C written
[root@li datas]# sh read.sh 
enter your name in 7 seconds 22
[root@li datas]# 
```
## Shell中的函数
### 系统函数
1. basename基本语法
> basename [string / pathname] [suffix]
<br>  	
> 功能描述:basename命令会删掉所有的前缀包括最后一个（'/'）字符,然后将字符串显示出来
<br>
> suffix为后缀,如果suffix被指定了,basename会将pathname或string中的suffix去掉
```shell script
[root@li datas]# basename /datas/11.txt
11.txt
[root@li datas]# basename /datas/11.txt .txt
11
[root@li datas]#
```
2. dirname基本语法
> dirname文件绝对路径
<br>
> 功能描述:从给定的包含绝对路径的文件名中去除文件名(非目录的部分),然后返回剩下的路径(目录的部分)
```shell script
[root@li datas]# dirname /datas/11.txt 
/datas
[root@li datas]# 
```

### 自定义函数
1. 基本语法
```shell script
[ function ] funname[()]
{
	action;
	[return int;]
}
```
> 注意事项:
> - 必须在调用函数地方之前,先声明函数,shell脚本是逐行运行.不会像其它语言一样先编译
> - 函数返回值,只能通过$?系统变量获得,可以显示加:return返回,如果不加,将以最后一条命令运行结果,作为返回值.
> - return后跟数值n(0-255)

```shell script
[root@li datas]# vim fun.sh
#!/bin/bash
function sum()
{
 s=0
 s=$[$1+$2]
 echo $s
}

read -p "Please input the number1: " n1;
read -p "Please input the number2: " n2;
sum $n1 $n2;
~
"fun.sh" 12L, 154C 已写入
[root@li datas]# sh fun.sh
Please input the number1: 2
Please input the number2: 3
5
[root@li datas]# 
```
## Shell工具
### cut
> cut的工作就是"剪",具体的说就是在文件中负责剪切数据用的.
<br>
> cut作用就是从文件的每一行剪切字节,字符和字段并将这些字节,字符和字段输出

1. 基本用法
<br>
cut [选项参数] filename
<br>
说明:默认分隔符是制表符
2. 选项参数说明
<table>
    <tr>
        <th>选项参数</th>
        <th>功能</th>
    </tr>
    <tr>
        <th>-f</th>
        <th>列号,提取第几列</th>
    </tr>
    <tr>
        <th>-d</th>
        <th>分隔符,按照指定分隔符分割列</th>
    </tr>
</table>

3. cut小例子
```shell script
[root@li datas]# vi cut.txt 
dong shen
guan zhen
wo wo
lai lai
le le
~
"cut.txt" 5L, 40C written
[root@li datas]# cut -f 1,2 cut.txt
dong shen
guan zhen
wo wo
lai lai
le le
[root@li datas]# 
[root@li datas]# cut -f 1 cut.txt
dong shen
guan zhen
wo wo
lai lai
le le
[root@li datas]# cut -d " " -f 1 cut.txt
dong
guan
wo
lai
le
[root@li datas]# echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/usr/java/jdk1.8.0_221/bin:/usr/java/jdk1.8.0_221/jre/bin:/root/bin
[root@li datas]# echo $PATH | cut -d ":" -f 2- 
/usr/local/bin:/usr/sbin:/usr/bin:/usr/java/jdk1.8.0_221/bin:/usr/java/jdk1.8.0_221/jre/bin:/root/bin
```
### sed
> sed是一种流编辑器,它一次处理一行内容.
> - 处理时,把当前处理的行存储在临时缓冲区中,称为"模式空间",接着用sed命令处理缓冲区中的内容
> - 处理后,把缓冲区的内容送往屏幕.接着处理下一行,这样不断重复,直到文件末尾.文件内容并没有改变,除非你使用重定向存储输出.

1. 基本用法
```shell script
sed [选项参数] 'command' filename
```
2. 选项参数
<table>
    <tr>
        <th>选项参数</th>
        <th>功能</th>
    </tr>
    <tr>
        <th>-e</th>
        <th>直接在指令列模式上进行sed的动作编辑</th>
    </tr>
</table>

3. 命令功能描述
<table>
    <tr>
        <th>命令</th>
        <th>功能描述</th>
    </tr>
    <tr>
        <th>a</th>
        <th>新增,a的后面可以接字串,在下一行出现</th>
    </tr>
    <tr>
        <th>d</th>
        <th>删除</th>
    </tr>
    <tr>
        <th>s</th>
        <th>查找并替换</th>
    </tr>
</table>

4. sed小例子
```shell script
[root@li datas]# vi sed.txt 
dong shen
guan zhen
wo  wo
lai  lai

le  le
~
"sed.txt" 6L, 44C written
[root@li datas]# sed '2a mei nv' sed.txt 
dong shen
guan zhen
mei nv
wo  wo
lai  lai

le  le
[root@li datas]# cat sed.txt 
dong shen
guan zhen
wo  wo
lai  lai

le  le
[root@li datas]# sed '/wo/d' sed.txt 
dong shen
guan zhen
lai  lai

le  le
[root@li datas]# sed 's/wo/ni/g' sed.txt 
dong shen
guan zhen
ni  ni
lai  lai

le  le
[root@li datas]# sed -e '2d' -e 's/wo/ni/g' sed.txt 
dong shen
ni  ni
lai  lai

le  le
[root@li datas]# cat sed.txt 
dong shen
guan zhen
wo  wo
lai  lai

le  le
[root@li datas]#  
```
### awk
> 一个强大的文本分析工具,把文件逐行的读入,以空格为默认分隔符将每行切片,切开的部分再进行分析处理

1. 基本用法
```shell script
awk [选项参数] 'pattern1{action1}  pattern2{action2}...' filename
pattern:表示AWK在数据中查找的内容,就是匹配模式
action:在找到匹配内容时所执行的一系列命令
```
2. 选项参数说明
<table>
    <tr>
        <th>选项参数</th>
        <th>功能</th>
    </tr>
    <tr>
        <th>-F</th>
        <th>指定输入文件折分隔符</th>
    </tr>
    <tr>
        <th>-v</th>
        <th>赋值一个用户定义变量</th>
    </tr>
</table>

3. awk小例子
```shell script
[root@li datas]# 筛选指定列
[root@li datas]# cp /etc/passwd ./
[root@li datas]# awk -F: '/^root/{print $7}' passwd
/bin/bash
[root@li datas]# awk -F: '/^root/{print $1","$7}' passwd
root,/bin/bash
[root@li datas]# awk -F: 'BEGIN{print "user,shell"} {print $1","$7} END {print "li,/bin/bash"}' passwd 
user,shell
root,/bin/bash
...省略
hadoop,/bin/bash
li,/bin/bash
[root@li datas]# 指定列+1
[root@li datas]# awk -v i=1 -F: '{print $3+1}' passwd 
1
2
...省略
1001
```

4. awk的内置变量
<table>
    <tr>
        <th>变量</th>
        <th>说明</th>
    </tr>
    <tr>
        <th>FILENAME</th>
        <th>文件名</th>
    </tr>
    <tr>
        <th>NR</th>
        <th>已读记录数</th>
    </tr>
    <tr>
        <th>NF</th>
        <th>浏览记录的域的个数(切割后,列的个数)</th>
    </tr>
</table>

5. awk小例子2,统计每行的行号,每行的列数
```shell script
[root@li datas]# awk -F: '{print "filename:"FILENAME",linenumber:"NR",columns:"NF}' passwd 
filename:passwd,linenumber:1,columns:7
filename:passwd,linenumber:2,columns:7
...省略
filename:passwd,linenumber:21,columns:7
filename:passwd,linenumber:22,columns:7
[root@li datas]# 
```
6. awk小例子3,查询空行的行号
```shell script
[root@li datas]# cat sed.txt 
dong shen
guan zhen
wo  wo
lai  lai

le  le
[root@li datas]# awk '/^$/{print NR}' sed.txt 
5
[root@li datas]# 
```
### sort
> sort命令是在Linux里非常有用,它将文件进行排序,并将排序结果标准输出.
1. 基本语法
```shell script
sort(选项)(参数)
```
<table>
    <tr>
        <th>选项</th>
        <th>说明</th>
    </tr>
    <tr>
        <th>-n</th>
        <th>按照数值大小排序</th>
    </tr>
    <tr>
        <th>-r</th>
        <th>相反的顺序排序</th>
    </tr>
    <tr>
        <th>-t</th>
        <th>设置排序时,使用的分隔符</th>
    </tr>
    <tr>
        <th>-k</th>
        <th>指定需要排序的列</th>
    </tr>
</table>
2. soft小例子,按照":"分割后的第三列倒序排序

```shell script
[root@li datas]# touch sort.sh
[root@li datas]# vi sort.sh 
bb:40:5.4
bd:20:4.2
xz:50:2.3
cls:10:3.5
ss:30:1.6
~
"sort.sh" 5L, 51C written
[root@li datas]# sort -t : -nrk 3  sort.sh
bb:40:5.4
bd:20:4.2
cls:10:3.5
xz:50:2.3
ss:30:1.6
[root@li datas]# 
```
### 应用小例子
1. 使用Linux命令查询file1中空行所在的行号

```shell script
[root@li datas]# awk '/^$/{print NR}' file1
```

2. 有文件file2.txt内容如下:<br>
张三 40
<br>
李四 50
<br>
王五 60
<br>
使用Linux命令计算第二列的和并输出

```shell script
[root@li datas]# cat file2.txt | awk -F " " '{sum+=$2} END{print sum}'
150
[root@li datas]# awk '{sum+=$2} END{print sum}' file2.txt 
150
```
3. 请用shell脚本写出查找当前文件夹,所有的文本文件内容中包含有字符”shen”的文件名称

```shell script
[root@li datas]# grep -r 'shen' ./
./sed.txt:dong shen
./cut.txt:dong shen
[root@li datas]# grep -r 'shen' ./ |cut -d: -f 1
./sed.txt
./cut.txt
[root@li datas]# 
```
