
var app=new Vue({
    el:"#app",
     data:
     {
        birthday:'',//出生年月日
        agg:'',//年龄
        passwordModel:"",
        passwordcheckModel:"",
        passwordFlag:false
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
    if ("该用户名合法" ==  str2)
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
    var str2 = "该用户名合法";
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
// 根据验证结果确认是否提交
function check_submit()
{
    if (flag == false)
    {
        return false;
    }
    return true;
}
//检测email在改变时输出提示
email.onchange = function(){
    var email = this.value;
    var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if(reg.test(email)){
        console.log("邮箱格式正确")
    }else{
        alert("邮箱格式不正确");
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
