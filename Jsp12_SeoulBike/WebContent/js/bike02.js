
$(function(){
	parseBike();
});

function parseBike(){
	var obj = {
			id: "myid",
			name: "myname"
		}

		for(var i in obj) {
			console.log(obj[i]);
		}
	$.getJSON("json/bike.json", function(data){
		// $.ajax 랑 같음
		// $.ajax({ url : "bike.json", success : function(data)})와 동일
		// 결국 비동기 통신임
		
		$.ajax({
			url : "bike.do", // 여기에 ?붙여서 command=second_db로 써줘도되는데 post형식이니깐 아래 data에 써주는게 낫다 get방식이면 붙여도됨
			method : "POST",
			data : {"obj":JSON.stringify(data),"command" : "second_db"},
			// 제이슨 객체를 제이슨 문자열로 바꿔서 서버로 보내줌!
			success : function(msg) {
				if(msg > 0) {
					alert(msg);
					$.each(data, function(key, val){
						// 사진 each 참고
						// 콜백 함수
						// $.each은 처음 들어오는 data의 타입이
						// array거나 object가 들어오는 것이다.
						// data의 첫번째 덩어리가 function(key,val)에 적용되고
						// 두번째도 또 적용 
						// 하나하나에 function이 다 적용됨.
						// 배열이거나 배열같이 생긴애들을 받을 수 있음
						// 반복해서 그 값을 가지고올수있는 객체이다.
						// 결국은 반복할 수 있는 객체를 주면 가능하다.
						// 그러면 반복이 가능하니 하나하나 꺼내서 그 하나하나에 함수를 적용시켜준다.
						// $("#list").each랑은 다른 형태임 (하지만 이것도 콜백 형태임)
						if (key == "DESCRIPTION") {
							// DESCRIPTION의 벨류는 제이슨임
							// 키는 문자열 벨류(val)는 제이슨 오브젝트
							$("table").attr("border",1);
							var $tr = $("<tr>");
							// createElement랑 같음
							for(var i in val) {
								// { a : 1 , b : 2 }
								// 이 형태를 for in 문으로 돌리면
								// i는 key를 의미하고 val[i]는 value를 의미함
								$tr.append($("<th>").html(val[i]));
							}
							$("thead").append($tr);
						} else {
							// DATA는 제이슨 배열임
							// 키는 문자열 벨류(val)는 제이슨 배열
							for (var i = 0; i < val.length; i++) {
								var $tr = $("<tr>");
								for(var j in val[i]) {
									$tr.append($("<td>").html(val[i][j]));
									// 여기서 이중배열로 가져온 이유는
									// key가 DATA의 val은 제이슨 배열이라서
									// i의 j를 가져온거임!! val[i]는 제이슨 오브젝트임
									// 거기의 j이면 그 오브젝트의 키벨류에서 벨류를 가져온거임
								}
								$("tbody").append($tr);
							}
							
						}
					});
				} else {
					alert("db 저장 실패...");
				}
			},
			error : function(){
				alert("통신 실패");
			}
		});
	});
}