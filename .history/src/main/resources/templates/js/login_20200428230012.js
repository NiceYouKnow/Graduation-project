var $ = document.getElementById('tt');
		var _val = '';
		function getVal(){
			_val = htmlEncode ($.value);
			$.value = _val;
		}
		function setVal(){
			$.value = htmlDecode (_val);
		}
		//转义  元素的innerHTML内容即为转义后的字符
		function htmlEncode (str) {
		  var ele = document.createElement('span');
		  ele.appendChild( document.createTextNode( str ) );
		  return ele.innerHTML;
        }
    email.onchange = function(){
            var email = this.value;
            var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            if(reg.test(email)){
                console.log("邮箱格式正确")
            }else{
                alert("邮箱格式不正确");
            }
        }