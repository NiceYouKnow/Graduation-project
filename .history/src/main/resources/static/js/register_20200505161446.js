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
//密码是否相同的对比函数
function validate() {
    var pwd1 = document.getElementById("pwd").value;
    var pwd2 = document.getElementById("pwd1").value;
 //对比两次输入的密码 
    if(pwd1 == pwd2)
     {
        document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
        document.getElementById("button").disabled = false;
     }
else {
        document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
        document.getElementById("button").disabled = true;
     }
}
//检测密码是否符合8-16位包含数字大小写字母 
function checkPassword(){
    var pwd1 = document.getElementById("pwd").value;
    var nameObj = document.getElementById("pwdtishi");
    var pa=/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;//密码规则
    var pa1=/select|update|delete|exec|count|’|"|=|;|>|<|%/i;//过滤sql语句
    nameObj.innerHTML ="";
    if(pa.test(pwd1))
    {
    nameObj.innerHTML="<font color='green'密码符合规则</font>"
        flag=true;
    }
    else
    {
    nameObj.innerHTML="<font color='red'>密码不符合规则</font>" 
        flag=false;
    }
    if(pa1.test(pwd1))
    {
    nameObj.innerHTML="<font color='green'密码符合规则</font>"
        flag=true;
    }
    else
    {
    nameObj.innerHTML="<font color='red'>密码不符合规则</font>" 
        flag=false;
    }
}

class Circle {
    //创建对象
    //以一个圆为对象
    //设置随机的 x，y坐标，r半径，_mx，_my移动的距离
    //this.r是创建圆的半径，参数越大半径越大
    //this._mx,this._my是移动的距离，参数越大移动
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.r = Math.random() * 10 ;
        this._mx = Math.random() ;
        this._my = Math.random() ;

    }

    //canvas 画圆和画直线
    //画圆就是正常的用canvas画一个圆
    //画直线是两个圆连线，为了避免直线过多，给圆圈距离设置了一个值，距离很远的圆圈，就不做连线处理
    drawCircle(ctx) {
        ctx.beginPath();
        //arc() 方法使用一个中心点和半径，为一个画布的当前子路径添加一条弧。
        ctx.arc(this.x, this.y, this.r, 0, 360)
        ctx.closePath();
        ctx.fillStyle = 'rgba(204, 204, 204, 0.3)';
        ctx.fill();
    }

    drawLine(ctx, _circle) {
        let dx = this.x - _circle.x;
        let dy = this.y - _circle.y;
        let d = Math.sqrt(dx * dx + dy * dy)
        if (d < 150) {
            ctx.beginPath();
            //开始一条路径，移动到位置 this.x,this.y。创建到达位置 _circle.x,_circle.y 的一条线：
            ctx.moveTo(this.x, this.y);   //起始点
            ctx.lineTo(_circle.x, _circle.y);   //终点
            ctx.closePath();
            ctx.strokeStyle = 'rgba(204, 204, 204, 0.3)';
            ctx.stroke();
        }
    }

    // 圆圈移动
    // 圆圈移动的距离必须在屏幕范围内
    move(w, h) {
        this._mx = (this.x < w && this.x > 0) ? this._mx : (-this._mx);
        this._my = (this.y < h && this.y > 0) ? this._my : (-this._my);
        this.x += this._mx / 2;
        this.y += this._my / 2;
    }
}
//鼠标点画圆闪烁变动
class currentCirle extends Circle {
    constructor(x, y) {
        super(x, y)
    }

    drawCircle(ctx) {
        ctx.beginPath();
        //注释内容为鼠标焦点的地方圆圈半径变化
        //this.r = (this.r < 14 && this.r > 1) ? this.r + (Math.random() * 2 - 1) : 2;
        this.r = 8;
        ctx.arc(this.x, this.y, this.r, 0, 360);
        ctx.closePath();
        //ctx.fillStyle = 'rgba(0,0,0,' + (parseInt(Math.random() * 100) / 100) + ')'
        ctx.fillStyle = 'rgba(255, 77, 54, 0.6)'
        ctx.fill();

    }
}
//更新页面用requestAnimationFrame替代setTimeout
window.requestAnimationFrame = window.requestAnimationFrame || window.mozRequestAnimationFrame || window.webkitRequestAnimationFrame || window.msRequestAnimationFrame;

let canvas = document.getElementById('canvas');
let ctx = canvas.getContext('2d');
let w = canvas.width = canvas.offsetWidth; 
let h = canvas.height = canvas.offsetHeight;
let circles = [];
let current_circle = new currentCirle(0, 0)
//画图
let draw = function () {
    ctx.clearRect(0, 0, w, h);
    for (let i = 0; i < circles.length; i++) {
        circles[i].move(w, h);
        circles[i].drawCircle(ctx);
        for (j = i + 1; j < circles.length; j++) {
            circles[i].drawLine(ctx, circles[j])
        }
    }
    if (current_circle.x) {
        current_circle.drawCircle(ctx);
        for (var k = 1; k < circles.length; k++) {
            current_circle.drawLine(ctx, circles[k])
        }
    }
    requestAnimationFrame(draw)
}

let init = function (num) {
    for (var i = 0; i < num; i++) {
        circles.push(new Circle(Math.random() * w, Math.random() * h));
    }
    draw();
}
//引入图像
window.addEventListener('load', init(60));
window.onmousemove = function (e) {
    e = e || window.event;
    current_circle.x = e.clientX;
    current_circle.y = e.clientY;
}
window.onmouseout = function () {
    current_circle.x = null;
    current_circle.y = null;
};
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
	var d = []; 
	e.requestAnimationFrame = function() {
		return e.requestAnimationFrame || e.webkitRequestAnimationFrame || e.mozRequestAnimationFrame || e.oRequestAnimationFrame || e.msRequestAnimationFrame ||
		function(e) {
			setTimeout(e, 1e3 / 60)
		}
	} (),
	n()
} (window, document);