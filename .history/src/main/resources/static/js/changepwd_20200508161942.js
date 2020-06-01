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
  var str = "hello world";
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
      //如果文字的Y轴坐标大于画布的高度，1/100*colunms概率将该文字的Y轴坐标重置为0
      if (y >= can.height && Math.random() > 0.99) {
        arr[i] = 0;
      }
//文字Y轴坐标+1
      arr[i]++;
    }
  }
  draw();
  setInterval(draw, 30);

  var flag = false;   // flag 如果为true（即用户名合法）就允许表单提交， 如果为false（即用户名不合法）阻止提交
  // 当鼠标聚焦于用户名
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
 