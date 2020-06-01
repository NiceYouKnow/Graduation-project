
var app=new Vue({
    el:"#app",
     data:{
        birthday:'',//出生年月日
        agg:'',//年龄
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
     //判断第一次输入的密码是否少于6位数或者大于20位数
     
    //判断第二次输入密码是否少于6位数或者小于20位数，并且判断其是否和第一次输入的相同

)