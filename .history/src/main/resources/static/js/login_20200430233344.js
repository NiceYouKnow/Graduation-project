var $ = document.getElementById('pd');
        var _val = '';
    //转义  元素的innerHTML内容即为转义后的字符
	pd.onchange=function zhuanyi(){
			_val = htmlEncode ($.value);
			$.value = _val;
		}
		function htmlEncode (str) {
		  var ele = document.createElement('span');
		  ele.appendChild( document.createTextNode( str ) );
		  return ele.innerHTML;
        }
        //邮箱保护
    email.onchange = function(){
            var email = this.value;
            var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            if(reg.test(email)){
                console.log("邮箱格式正确")
            }else{
                alert("邮箱格式不正确");
            }
        }
    
    //验证码
      //一、定义一个获取DOM元素的方法
  var $ = function(selector){
    return document.querySelector(selector);
   },
   box = $(".drag"),//容器
   bg = $(".bg"),//背景
   text = $(".text"),//文字
   btn = $(".btn"),//滑块
   success = false,//是否通过验证的标志
   distance = box.offsetWidth - btn.offsetWidth;//滑动成功的宽度（距离）
  //二、给滑块注册鼠标按下事件
  btn.onmousedown = function(e){
   //1.鼠标按下之前必须清除掉后面设置的过渡属性
   btn.style.transition = "";
   bg.style.transition ="";
   //说明：clientX 事件属性会返回当事件被触发时，鼠标指针向对于浏览器页面(或客户区)的水平坐标。
   //2.当滑块位于初始位置时，得到鼠标按下时的水平位置
   var e = e || window.event;
   var downX = e.clientX;
   //三、给文档注册鼠标移动事件
   document.onmousemove = function(e){
    var e = e || window.event;
    //1.获取鼠标移动后的水平位置
    var moveX = e.clientX;
    //2.得到鼠标水平位置的偏移量（鼠标移动时的位置 - 鼠标按下时的位置）
    var offsetX = moveX - downX;
    //3.在这里判断一下：鼠标水平移动的距离 与 滑动成功的距离 之间的关系
    if( offsetX > distance){
     offsetX = distance;//如果滑过了终点，就将它停留在终点位置
    }else if( offsetX < 0){
     offsetX = 0;//如果滑到了起点的左侧，就将它重置为起点位置
    }
    //4.根据鼠标移动的距离来动态设置滑块的偏移量和背景颜色的宽度
    btn.style.left = offsetX + "px";
    bg.style.width = offsetX + "px";
    //如果鼠标的水平移动距离 = 滑动成功的宽度
    if( offsetX == distance){
     //1.设置滑动成功后的样式
     text.innerHTML = "验证通过";
     text.style.color = "#fff";
     btn.innerHTML = "√";
     btn.style.color = "green";
     bg.style.backgroundColor = "lightgreen";
     //2.设置滑动成功后的状态
     success = true;
     //成功后，清除掉鼠标按下事件和移动事件（因为移动时并不会涉及到鼠标松开事件）
     btn.onmousedown = null;
     document.onmousemove = null;
     //3.成功解锁后的回调函数
     setTimeout(function(){
      alert('解锁成功！');
     },100);
    }
   }
   //四、给文档注册鼠标松开事件
   document.onmouseup = function(e){
    //如果鼠标松开时，滑到了终点，则验证通过
    if(success){
     return;
    }else{
     //反之，则将滑块复位（设置了1s的属性过渡效果）
     btn.style.left = 0;
     bg.style.width = 0;
     btn.style.transition = "left 1s ease";
     bg.style.transition = "width 1s ease";
    }
    //只要鼠标松开了，说明此时不需要拖动滑块了，那么就清除鼠标移动和松开事件。
    document.onmousemove = null;
    document.onmouseup = null;
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
