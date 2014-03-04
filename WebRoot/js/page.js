$(function(){
	$("#button").click(function(){
		var pagenum = $("input[id='pagenum']").val();
		var totalpage = parseInt($("#totalpage").val());
		var servletName = $("#servletName").val();
		if(pagenum==null || pagenum==""){
			alert("Please input page number");
			$("input[id='pagenum']").val(null);
			return;
		}
		
		if(!pagenum.match("\\d+")){
			alert("Please input number");
			$("input[id='pagenum']").val(null);
			return;
		}
		
		if((pagenum < 1) || (pagenum > totalpage)){
			alert("Valid Page Number: 1~"+ totalpage);
			$("input[id='pagenum']").val(null);
			return;	
		}
		
		window.location.href=servletName+"?pagenum="+pagenum;		
	});
});