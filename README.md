# StudyProject

记录学习和测试的Project

***

#### Git常用命令

`git clone xxxxxx.git`  拉取远程git仓库

`git add .`  添加文件到本地仓库

`git commit -m "xxxxxx"` 提交到本地仓库，注意提交信息备注使用""，而不是''

`git push`  提交到远程仓库

`git pull`  拉取远程仓库文件

#### 分支的管理

`git branch`  查看本地仓库的分支

`git branch -a`  查看仓库中的所有分支（本地和远程）

`git branch branchName`  切换到指定分支

`git branch -d branchName`  删除本地分支

`git push origin --delete branchName`  删除远程分支

#### 文件忽略

.ignore忽略文件只能影响没有add过的文件，所以如果把不应该提交的文件push到远程仓库的分支上以后，只是通过配置.ignore文件是不生效的。如果想要删除远程仓库分支中提交错误的文件，使用如下命令：

`git rm --cache fileName` 删除本地文件的缓存，即移除该文件的版本控制 fileName表示文件名称

`git rm -r --cache directoryName`  删除本地目录的缓存，directoryName表示文件名

#### 关于GitHub提交记录显示不准确的问题

GitHub的记录提交，需要用户名和用户email才会正确统计提交记录

`git config --gloabl user.name "xxxx"` 是配置全局的用户名
`git config --gloabl user.email "xxxx"` 是配置全局的邮箱

`git config user.email "xxxx"` 是配置当前仓库的用户名
`git config user.email "xxxx"` 是配置当前仓库的用户名

***

### 关于项目结构的管理

目前使用 BuildSrc 统一管理项目模块

***

### 关于Gradle的管理

统一配置`application module`的`build.gradle`
统一配置`library module`的`build.gradle`

[Android中的Gradle基操](https://juejin.cn/post/7053985196906905636)

***

### 关于分包的管理

PBL（按层分包 Package By Layer） 不推荐

PBF（按功能分包 Package By Feature） 推荐

[Android 开发规范（完结版）](https://blankj.com/2017/03/08/android-standard-dev-final/#2-as-%E8%A7%84%E8%8C%83)

***

### 关于启动图标的制作

8.0版本以后，都推荐使用Android Studio的Image Asset功能

只需要一张512*512的logo图片(官方推荐的是前景图和背景图分开，而不是只切一张)

*使用Image Asset生成之前，最好先把所有的mipmap文件删除，避免生成出来的尺寸和分辨率不对或者不全*

***

### 组件化设计

###### BaseComponent 基础组件

说明：提供基本能力的组件，如：基础组件(BaseActivity等等)... 该类组件一般不会依赖除了Google Android标准库的第三方依赖

包名：com.june.base.组件名

实例：com.june.base.component

###### FunctionComponent 功能组件

说明：包含某种或某方面功能、能力的组件，如：网络组件、图片加载组件... 该类组件一般是提供某种功能和能力，不会与业务相关

包名：com.june.fun.组件名

实例：com.june.fun.network

###### BusinessComponent 业务组件

说明：包含完整业务逻辑的组件，如：登录组件... 该类一组件一般会与业务相关，但是又会在多个地方使用

包名：com.june.biz.组件名

实例：com.june.biz.login