
var app=new Vue({
    el:"#app",
     data:{
        birthday:'',//出生年月日
        agg:'',//年龄
        passwordModel:"",
        passwordcheckModel:"",
        passwordFlag:false
   },
   methods:{
//      判断用户的年龄
     getAge(){
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
    computed:{
         //判断第一次输入的密码是否少于6位数或者大于20位数
        passwordValidate:function() {
            var errorText;
            if(!/^[0-9A-Za-z]{6,20}$/.test(this.passwordModel)) {
                errorText = '密码少于6位'
            } else {
                errorText = ''
            }
            return {
                errorText
            }
        }, 
         //判断第二次输入密码是否少于6位数或者小于20位数，并且判断其是否和第一次输入的相同
            passwordCheck:function() {
            var errorText;
            if(!/^[0-9A-Za-z]{6,20}$/.test(this.passwordcheckModel)) {
                errorText = '密码少于6位'
            }else if(this.passwordcheckModel !==this.passwordModel ){
                errorText = '两次密码不匹配'
            }
            else {
                errorText = ''
            }
            return{
                errorText
            }

        }
    }
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

