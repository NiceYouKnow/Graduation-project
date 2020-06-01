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
    