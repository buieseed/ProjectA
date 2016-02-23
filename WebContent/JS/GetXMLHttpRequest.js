/**
 * 定義XMLHttpRequest物件 參數：無 傳回值：XMLHttpRequest物件
 */

function createXMLHttpRequestOpject() {
	var XMLHttpFactories = [ // 為相容不同瀏覧器和版本建立函數陣列
	function() {
		return new XMLHttpRequest()
	}, function() {
		return new ActiveXObject("Msmxl2.XMLHTTP")
	}, function() {
		return new ActiveXObject("Microsoft.XMLHTTP")
	} ];

	var xmlhttp = false;
	for (var i = 0; i < XMLHttpFactories.length; i++) {
		try {
			xmlhttp = XMLHttpFactories[i]();
		} catch (e) {
			continue;
		}
		break;
	}
	return xmlhttp;
}

function $(id) {
	return document.getElementById(id);
}