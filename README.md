# 寝室快修系统SSM

### 用最完善的系统，打造最实用的学生寝室维修方案 

#### 环境配置
    服务器采用Linux，容器为Tomcat，数据库为MySql
#### 系统框架
    采用SSM框架
#### 配置Tomcat的配置文件server.xml
    # 在此系统的HOST标签中加入以下两句，用于配置系统的客户端和图片的映射地址
    <Context docBase="/usr/drepair/apk" path="/apk" reloadable="true"/>
    <Context docBase="/usr/drepair/imgs" path="/imgs" reloadable="true"/>
    # 配置Tomcat端口为8080
#### 配置Linux必要的文件夹
    在/usr/中添加drepair文件夹，并在此文件夹中继续添加apk，imgs，sqlBackup等文件夹，用于系统存放客户端，图片和数据库备份文件
#### 配置MySql
    新建用户root，密码sa，并创建数据库drepair和初始化系统所需的表
#### 最后
    启动Tomcat即可
    Demo：http://118.89.101.23:8080
    
