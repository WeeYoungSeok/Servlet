$(function() {
	$("#weaView").click(
			function() {
				var url = "weatherOpen";
				var code = $("#address option:selected").val();
				$.ajax({
					type : "GET",
					url : url + "?code=" + code,
					dataType : "text",
					// dataType을 JSON으로 바꾸면 굳이 파싱해줄필요없음 여기서 해줘도됨
					// 그러면 data.pubDate로 가져올수있음
					// dataType 생략해주면 기본값 text
					success : function(data) {
						
						var temp = $.trim(data);
						console.log(temp);
						var obj = JSON.parse(data);
						// 제이슨 형태의 문자열을 제이슨 객체로 바꾸어줌
						// JSON.stringify(); : JSON형태를 문자열로 변환
						// console.log(obj);
						
						// weatherInfo.jsp에 대한 설명은 서블릿폴더안에있는
						// weatherInfo 메모장
						
						$("#pubDate").val(obj.pubDate);
						$("#temp").val(obj.temp);
						$("#x").val(obj.x);
						$("#y").val(obj.y);
						$("#reh").val(obj.reh);
						$("#pop").val(obj.pop);
						$("#wfKor").val(obj.wfKor);

						var weather_condition = obj.wfKor;
						if (weather_condition == "맑음"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/sun.png");
						}else if (weather_condition == "비"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/rain.png");
						}else if (weather_condition == "눈"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/snow.png");
						}else if (weather_condition == "흐림"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/cloud.png");
						}else if (weather_condition == "구름 조금"){
							$("#weather_img").attr("src","/Jsp11_Weather/image/cloud_sun.png");
						}else{
							$("#weather_img").attr("src","/Jsp11_Weather/image/etc.png");
						}
					},
					error : function() {
						alert("정보를 불러오는데 실패하였습니다.");
					}
				});
			});
});
