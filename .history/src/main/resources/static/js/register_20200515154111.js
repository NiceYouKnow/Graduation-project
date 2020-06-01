var app=new Vue({
    el:"#app",
     data:
     {
        birthday:'',//出生年月日
        agg:'',//年龄
   },
   methods:{
//      判断用户的年龄
     getAge()
     {
       let birthdays = new Date(this.birthday.replace(/-/g, "/"));
       let d = new Date();
       let age =
         d.getFullYear() -
         birthdays.getFullYear() -
         (d.getMonth() < birthdays.getMonth() ||
         (d.getMonth() == birthdays.getMonth() &&
         d.getDate() < birthdays.getDate())
           ? 1
           : 0); //现在时间减去出生日期
       this.agg = age;
     },
    },
   })
/*
    表单验证
*/
var flag = false;   // flag 如果为true（即用户名合法）就允许表单提交， 如果为false（即用户名不合法）阻止提交
// 当鼠标聚焦于用户名
function focus_username()
{
    // 找到后面的div, id = result_name
    var nameObj = document.getElementById("result_name");
    nameObj.innerHTML = "用户名不能包含特殊字符且为5~20位";
    nameObj.style.color="#999";
}
// 当鼠标不聚焦于用户名input
function blur_username()
{
    // 找到id=result_name的div
    var nameObj = document.getElementById("result_name");
    // 判断用户名是否合法
    var str2 = check_user_name(document.form1.username.value);
    nameObj.style.color="red";
    if ("<font color='green'>该用户名合法</font>" ==  str2)
    {
        flag = true;
        nameObj.innerHTML = str2;
    }
    else
    {
        nameObj.innerHTML = str2;
    }
}   
// 检查用户名是否合法        合法就返回"该用户名合法"
function check_user_name(str)
{
    var str2 = "<font color='green'>该用户名合法</font>";
    if ("" == str)
    {
        str2 = "用户名为空";
        return str2;
    }
    else if ((str.length < 5) || (str.length > 20))
    {
        str2 = "用户名必须为5 ~ 20位";
        return str2;
    }
    else if (check_other_char(str))
    {
        str2 = "不能含有特殊字符";
        return str2;
    }
    return str2;
}
// 验证用户名是否含有特殊字符
function check_other_char(str)
{
    var arr = ["&", "\\", "/", "*", ">", "<", "@", "!"];
    for (var i = 0; i < arr.length; i++)
    {
        for (var j = 0; j < str.length; j++)
        {
            if (arr[i] == str.charAt(j))
            {
                return true;
            }
        }
    }   
    return false;
}
//检测email在改变时输出提示
email.onchange = function(){
    var email = this.value;
    var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if(reg.test(email)){
        console.log("邮箱格式正确");
        flag=true;
    }else{
        alert("邮箱格式不正确");
        flag=false;
    }
}
 // 根据验证结果确认是否提交
 function check_submit()
 {
     if (flag == false)
     {
         return false;
     }
     return true;
 }

//检测密码是否符合8-16位包含数字大小写字母 
function checkPassword(){
    var pwd1 = document.getElementById("pwd").value;
    var nameObj = document.getElementById("pwdtishi");
    var pa=/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;//密码规则
    var pa1=/select|update|delete|exec|count|’|"|=|;|>|<|%/i;//过滤sql语句
    nameObj.innerHTML ="";
    if(pa.test(pwd1)&&!pa1.test(pwd1))
    {
    nameObj.innerHTML="<font color='green'>密码符合规则</font>"
        flag=true;
    }
    else
    {
    nameObj.innerHTML="<font color='red'>密码不符合规则</font>" 
        flag=false;
    }
}

  //获取画布对象
  var can = document.querySelector("canvas");
  //获取画布的上下文
  var ctx = can.getContext("2d");
  //设置canvas的宽度和高度
  can.width = window.innerWidth;
  can.height = window.innerHeight;
  //每个文字的字体大小
  var fontSize = 16;
  //计算列
  var colunms = Math.floor(window.innerWidth / fontSize);
  //记录每列文字的y轴坐标
  var arr = [];
  //给每一个文字初始化一个起始点的位置
  for (var i = 0; i < colunms; i++) {
    arr.push(0);
  }
  //运动的文字
  var str = "10101010101";
  //绘画的函数
  function draw() {
    //布满全屏的白色遮罩层
    ctx.fillStyle = "rgba(255,255,255,0.05)";
    ctx.fillRect(0, 0, window.innerWidth, window.innerHeight);
    //给字体设置样式
    ctx.font = "700 " + fontSize + "px  微软雅黑";
    //给字体添加颜色
    ctx.fillStyle = "#00cc33";
    //写入画布中
    for (var i = 0; i < colunms; i++) {
      var index = Math.floor(Math.random() * str.length);
      var x = i * fontSize;
      var y = arr[i] * fontSize;
      ctx.fillText(str[index], x, y);
      //如果文字的Y轴坐标大于画布的高度
      if (y >= can.height && Math.random() > 0.9) {
        arr[i] = 0;
      }
//文字Y轴坐标+1
      arr[i]++;
    }
  }
  setInterval(draw, 30);

//点击爱心方案
!function(e, t, a) {
	function n() {
		c(".heart{width: 10px;height: 10px;position: fixed;background: #f00;transform: rotate(45deg);-webkit-transform: rotate(45deg);-moz-transform: rotate(45deg);}.heart:after,.heart:before{content: '';width: inherit;height: inherit;background: inherit;border-radius: 50%;-webkit-border-radius: 50%;-moz-border-radius: 50%;position: fixed;}.heart:after{top: -5px;}.heart:before{left: -5px;}"),
		o(),
		r()
	}
	function r() {
		for (var e = 0; e < d.length; e++) d[e].alpha <= 0 ? (t.body.removeChild(d[e].el), d.splice(e, 1)) : (d[e].y--, d[e].scale += .004, d[e].alpha -= .013, d[e].el.style.cssText = "left:" + d[e].x + "px;top:" + d[e].y + "px;opacity:" + d[e].alpha + ";transform:scale(" + d[e].scale + "," + d[e].scale + ") rotate(45deg);background:" + d[e].color + ";z-index:99999");
		requestAnimationFrame(r)
	}
	function o() {
		var t = "function" == typeof e.onclick && e.onclick;
		e.onclick = function(e) {
			t && t(),
			i(e)
		}
    }
      // 定义创建爱心函数
	function i(e) {
		var a = t.createElement("div");//创建div对象
		a.className = "heart",//类名
		d.push({
			el: a, //对象
			x: e.clientX - 5,//x轴
			y: e.clientY - 5,//y轴
			scale: 1,//缩放
			alpha: 1,//透明度
			color: s()//颜色
		}),
		t.body.appendChild(a) //添加到body
    }
      // 定义生成样式函数
	function c(e) {
		var a = t.createElement("style");
		a.type = "text/css";
		try {
			a.appendChild(t.createTextNode(e))
		} catch(t) {
			a.styleSheet.cssText = e
		}
		t.getElementsByTagName("head")[0].appendChild(a)
    }
    // 定义生成随机颜色函数
	function s() {
		return "rgb(" + ~~ (255 * Math.random()) + "," + ~~ (255 * Math.random()) + "," + ~~ (255 * Math.random()) + ")"
	}
    var d = []; //全局变量hearts
    //在浏览器种的函数实现该功能，如果没有则使用setTimeout
	e.requestAnimationFrame = function() {
        return e.requestAnimationFrame || 
        e.webkitRequestAnimationFrame || 
        e.mozRequestAnimationFrame|| 
        e.oRequestAnimationFrame ||
         e.msRequestAnimationFrame||
		function(e) {
			setTimeout(e, 1e3 / 60)
		}
	} (),
	n()
} (window, document);