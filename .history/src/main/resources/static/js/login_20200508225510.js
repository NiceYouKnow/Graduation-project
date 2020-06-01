var flag = false;   // flag 如果为true就允许表单提交， 如果为false阻止提交
var $1 = document.getElementById('password');
        var _val = '';
    //转义  元素的innerHTML内容即为转义后的字符
	password.onchange=function zhuanyi(){
			_val = htmlEncode ($1.value);
			$1.value = _val;
		}
		function htmlEncode (str) {
		  var ele = document.createElement('span');
		  ele.appendChild( document.createTextNode( str ) );
		  return ele.innerHTML;
        }
        //邮箱保护
        username.onchange = function(){
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
     //验证码部分
var box = $('.box'),
imgBox = $('.imgBox'),
handle = $('.handle'),
swiper = $('.swiper'),
text = $('.text'),
verify = $('.verify'),
verified = $('.verified'),
refresh = $('.refresh')
// 图片集合
var imgs = []
for (let i = 1; i < 5; i++) {
imgs.push('' + i +i)
}
$(function() {
// 随机添加背景图
refreshImg()
refresh.click(function() {
  e = event || window.event
  e.stopPropagation()

  refreshImg()
  start()
})

refresh.mousedown(function() {
  $(this).addClass('click')
})
refresh.mouseup(function() {
  $(this).removeClass('click')
})

window.onload = start()
})

function start() {
var verImg = document.getElementsByClassName('verImg')[0]

if (verImg) {
  verImg.onload = function() {
    // 获取图片高度
    var imgH = this.clientHeight
    // 随机生成坐标（图片框固定宽度为300px，高度不定）
    var verX = 150 * (1 + Math.random()) - 38,
      verY = imgH / 4 + Math.random() * imgH / 2

    // 用户移动滑块函数
    fnDown(verX, verY)
  }
}
}
function fnDown(verX, verY) {
swiper.mousedown(function() {
  e = event || window.event
  e.stopPropagation()
  // 30为模块宽度
  verify.css({
    display: 'block',
    top: `${verY}px`,
    'background-position': `-${verX}px -${verY}px`
  })
  verified.css({ display: 'block', left: `${verX}px`, top: `${verY}px` })
  // 获取鼠标到按钮的距离
  var disX = e.clientX - $(this).offset().left,
    disY = e.clientY - $(this).offset().top
  text.css('opacity', '0')

  // 防止重复绑定触发多次
  box.unbind('mousemove')
  box.unbind('mouseup')

  // 移动
  box.bind('mousemove', function() {
    e = event || window.event
    fnMove(e, disX, disY)
  })

  // 释放
  box.bind('mouseup', function() {
    var stopL = verify.offset().left - 28
    // 误差在xxxpx以内则算做成功
    if (Math.abs(stopL - verX) > 7000) {
      alert('验证失败');
      flag=false;
    } else {
      alert('验证成功');
      flag=true;
    }
    // 解除绑定，并将滑动模块归位
    box.unbind('mousemove')
    swiper.css('left', '0px')
    verify.css('left', '10px')
    text.css('opacity', '1')
    box.unbind('mouseup')
  })
})
}

function fnMove(e, posX, posY) {
// 这里的e是以鼠标为参考
var l = e.clientX - posX - $(handle).offset().left,
  winW = $(handle).width() + 29
// 限制拖动范围只能在handle中
if (l < 0) {
  l = 0
} else if (l > winW) {
  l = winW
}

swiper.css('left', `${l}px`)
verify.css('left', `${l + 10}px`)
}

function refreshImg() {
// 随机生成下标
var index = Math.round(Math.random() * 3)
var imgH = 0
verify.hide()
verified.hide()
//验证图片
var verImg = $('.verImg')
if (verImg.length) {
  verImg.attr('src', `../static/imgs/${imgs[index]}.jpg`)
} else {
  imgBox.prepend(`<img class='verImg' src="../static/imgs/${imgs[index]}.jpg" />`)
}
verify.css('background-image', `url('../static/imgs/${imgs[index]}.jpg')`)
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
      ctx.fillStyle = 'rgba(204, 204, 204, 0.5)';
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
          ctx.strokeStyle = 'rgba(204, 204, 204, 0.5)';
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
//设置cookie
　　　　function setCookie ( name, value, expdays )
        {
            var expdate = new Date();
            //设置Cookie过期日期
            expdate.setDate(expdate.getDate() + expdays) ;
            //添加Cookie并转码
            document.cookie = encodeURI(name) + "=" + escape(value) + ";expires=" + expdate.toUTCString();
            
        }
        
        //得到cookie
        function getCookie ( name )
        {            
            //获取name在Cookie中起止位置
            var start = document.cookie.indexOf(name+"=") ;
            if ( start != -1 )
            {
                start = start + name.length + 1 ;
                //获取value的终止位置
                var end = document.cookie.indexOf(";", start) ;
                if ( end == -1 )
                    end = document.cookie.length ;
                //截获cookie的value值,并返回
                return unescape(document.cookie.substring(start,end)) ;
            }
            return "" ;
        }
        
        //删除cookie
        function delCookie ( name )
        {
            setCookie ( name, "", -1 ) ;
        }
        //提交表单时触发
　　　　　　　function trans() {
            
  //base64加密
  loginForm.password.value = base64encode(loginForm.password.value);
  
  //获取表单输入:用户名,密码,是否保存密码
  var username = document.getElementById("username").value.trim() ;
  var password = document.getElementById("password").value.trim() ;
  var isRmbPwd = document.getElementById("isRmbPwd").checked ;
  
  //判断用户名,密码是否为空(全空格也算空)
   if ( username.length != 0 && password.length != 0 )
  {
      //若复选框勾选,则添加Cookie,记录密码
      if ( isRmbPwd == true )
      {   
          var name = getCookie("username") ;
          if(name != username) {
              delCookie ("username") ;
              delCookie (name) ;
          }
          setCookie ( "username", username, 30 ) ;
          setCookie ( username, password, 30 ) ;
      }
      //否则清除Cookie
      else
      {
          delCookie ( "username" ) ;
          delCookie ( username ) ;
      }
      return true ;
  }
  
}
//从Cookie获取到用户名
var username1 = getCookie("username") ;
//如果用户名为空,则给表单元素赋空值
if ( username1 == "" )
{
    document.getElementById("username").value="" ;
    document.getElementById("password").value="" ;
    document.getElementById("isRmbPwd").checked=false ;
}
//获取对应的密码,并把用户名,密码赋值给表单
else
{
    var pwd= getCookie(encodeURI(username)) ;
    //base64解密
    password = base64decode(pwd);
                    
    document.getElementById("username").value = username ;
    document.getElementById("password").value = password ;
    document.getElementById("isRmbPwd").checked = true ;
}
