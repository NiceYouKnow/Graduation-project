
$(function () {
    $("#upload").click(function () {
        //限制视频格式
        let limitType = ['video/mp4','video/avi','video/mov','video/rmvb','video/rm','video/flv','video/wmv',];
        //限制大小100Mb
        let limitMax = 100000 * 1024;
        var form = new FormData();
        var titlestring = document.getElementById('title').value;
        var title=document.getElementById('title').value;
        let file = document.getElementById("file").files[0];
        let SQLre = /exec|insert|select|drop|grant|alter|delete|update|count|chr|mid|master|truncate|char|declare|or|and/;
        //判断标题是否为空
        if(!title){
            alert("视频标题不能为空！")
            return;
        }
        else if(SQLre.test(title)){
            alert("输入字符不规范！");
            return;
        }
        //去除字符串左右两侧空格
        titlestring = titlestring.replace(/(^\s*)|(\s*$)/g, "");
        //去除字符串中的特殊字符
        titlestring = titlestring.replace(/[&\|\\\*^%$#@\-]/g, "");
        //去除字符串中用户可能会输入的一些具有攻击性的恶意代码
        titlestring = titlestring.replace("<SCRIPT>", "");
        titlestring = titlestring.replace("</SCRIPT>", "");
        titlestring = titlestring.replace("<script>", "");
        titlestring = titlestring.replace("</script>", "");
        titlestring = titlestring.replace("select", "");
        titlestring = titlestring.replace("where", "");
        titlestring = titlestring.replace("drop", "");
        titlestring = titlestring.replace("create", "");
        titlestring = titlestring.replace("rename", "");
        titlestring = titlestring.replace("join", "");
        titlestring = titlestring.replace("union", "");
        titlestring = titlestring.replace("insert", "");
        titlestring = titlestring.replace("delete", "");
        titlestring = titlestring.replace("update", "");
        titlestring = titlestring.replace("rename", "");
        titlestring = titlestring.replace("passwd", "");
        titlestring = titlestring.replace("root", "");
        titlestring = titlestring.replace("char", "");
        titlestring = titlestring.replace("alter", "");
        titlestring = titlestring.replace("or", "");
        //判断文件是否存在
        if(!file){
            return;
        };
        //判断文件格式是否为指定的格式
        if(!limitType.includes(file.type)){
            alert('必须是视频格式！');
            return;
        };
        //判断文件大小
        if(file.size > limitMax){
            alert('大小最多为100mb！');
            return;
        }
        form.append("title",titlestring);
        form.append("file",file);
        //Ajax请求
         $.ajax({
            //后台url
             url: "http://localhost:8080/govideoupload",
             //data数据类型
             data: form,
             //cache属性是flase时：每次读取的是最新的数据。
             cache: false,
             //类型，POST
             type: "POST",     
             //数据返回类型，可以是xml、json等              
            //  dataType: "html",       
            //默认情况下，processData 的值是 true，其代表以对象的形式上传的数据都会被转换为字符串的形式上传。而当上传文件的时候，则不需要把其转换为字符串，因此要改成false
             processData: false,
             //contentType 设置为 false 会改掉之前默认的数据格式，在上传文件时就不会报错。
             contentType: false,
             //方法beforeSend，用于在向服务器发送请求前添加一些处理函数。这是一个ajax事件，在ajax请求开始之前就被触发，通常允许用户修改XMLHttpRequest对象（比如说设置附加的头部信息）
             beforeSend:function(XMLHttpRequest){  
                console.log(this);  
            },
            //Success() 方法规定 AJAX 请求成功完成时运行的函数。
             success:function(data){
                    if(data.status==200){
                       console.log(data);
                       alert("上传成功！");
                       window.location.href = "/homepage" ;
                    }
                    if(data.status==500){
                        console.log(data);
                        alert("服务器出错！");
                    }                    
                },
                //Complete() 方法规定的函数会在请求完成时运行，即使请求并未成功。
            complete:function(XMLHttpRequest,textStatus){
                if(textStatus=='timeout'){
                    var xmlhttp = window.XMLHttpRequest ? new window.XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHttp");
                    xmlhttp.abort(); 
        　　　　}
            },
            //Error() 方法在 AJAX 请求发生错误时执行函数
            error:function(XMLHttpRequest, textStatus){
                //XMLHttpRequest.responseText    XMLHttpRequest.status   XMLHttpRequest.readyState
                console.log(XMLHttpRequest);  
                console.log(textStatus);
                console.log(XMLHttpRequest.status);
                if(XMLHttpRequest.status==200){
                    alert("上传成功！");
                }
                if(XMLHttpRequest.status==500){
                    alert("上传失败！");
                }
             }
         });
    })
})
//搜索框search事件
function search(){
    var $keyword = $("#sousuo").val();
        window.location.href = "http://www.baidu.com/s?wd=" + $keyword;
}
//返回主页的点击函数
function zhuye(){
    window.location.href = "/homepage" ;
}