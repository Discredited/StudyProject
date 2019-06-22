
# StudyProject

记录学习和测试的Project

***

### Git常用命令

`git clone xxxxxx.git`  拉取远程git仓库

`git add .`  添加文件到本地仓库

`git commit -m "xxxxxx"` 提交到本地仓库，注意提交信息备注使用""，而不是''

`git push`  提交到远程仓库

`git pull`  拉取远程仓库文件

`git branch`  查看本地仓库的分支

`git branch -a`  查看仓库中的所有分支（本地和远程）

.ignore忽略文件只能影响没有add过的文件，所以如果把不应该提交的文件push到远程仓库的分支上以后，只是通过配置.ignore文件是不生效的。如果想要删除远程仓库分支中提交错误的文件，使用如下命令：

`git rm --cache XXXX.XX` 删除本地文件的缓存，即移除该文件的版本控制  XXXX.XX表示文件名称

`git rm -r --cache XXXX`  删除本地目录的缓存，XXXX表示文件名


