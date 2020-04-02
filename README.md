# demo
毕业设计
基于Spring Boot的视频网站
# @auther:yunfei_fan


# fastdfs文件服务器搭建
ubuntu安装docker：
更新apt包索引：
sudo apt update  

通过HTTPS使用仓库(repository)安装：
sudo apt install apt-transport-https ca-certificates curl software-properties-common

添加Docker官方的GPG密钥：
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

添加Docker源
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"

更新一下源
sudo apt update

查看可以安装的docker版本
apt-cache policy docker-ce

安装
sudo apt install docker-ce

启动
sudo systemctl start docker

查看docker服务是否启动
systemctl status docker

拉取镜像：
docker pull qbanxiaoli/fastdfs

启动fastdfs（IP=后为你虚拟机的IP）：
docker run -d --restart=always --privileged=true --net=host --name=fastdfs -e IP=192.168.142.129 -e WEB_PORT=80 -v ${HOME}/fastdfs:/var/local/fdfs qbanxiaoli/fastdfs

测试fastdfs是否搭建成功
docker exec -it fastdfs /bin/bash
echo "Hello FastDFS!">index.html
fdfs_test /etc/fdfs/client.conf upload index.html

成功后会出现一个example file url
通过浏览器去访问这个链接，能够成功显示html页面，则表示搭建成功。
