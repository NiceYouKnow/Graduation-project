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
function checkPassword()
{
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