
$(function(){
	getBike();
});

function getBike(){
	$.getJSON("json/bike.json", function(data){
		$.each(data, function(key, val){
			if (key == "DESCRIPTION") {
				$("table").attr("border", 1);
				$("thead").append(
					"<tr>"+
						"<th>"+val.ADDR_GU+"</th>"+
						"<th>"+val.CONTENT_ID+"</th>"+
						"<th>"+val.CONTENT_NM+"</th>"+
						"<th>"+val.NEW_ADDR+"</th>"+
						"<th>"+val.CRADLE_COUNT+"</th>"+
						"<th>"+val.LONGITUDE+"</th>"+
						"<th>"+val.LATITUDE+"</th>"+
					"</tr>"
				);
			} else {
				var list = val;
				// 제이슨 key DATA의 벨류인 제이슨 벨류를 list에 담아준것
				for (var i = 0; i < list.length; i++) {
					var str = list[i];
					// str에는 제이슨 벨류의 0번지부터 마지막 번지까지 담는데
					// str에는 제이슨 오브젝트가 담기는거임
					// 왜냐? 배열안에 0번지~~막번지애들은 제이슨 오브젝트니깐
					// 여기 잘 모르겠으면 bike02.js가서 보자
					
					$("tbody").append(
						"<tr>"+
							"<td>"+str.addr_gu+"</td>"+
							"<td>"+str.content_id+"</td>"+
							"<td>"+str.content_nm+"</td>"+
							"<td>"+str.new_addr+"</td>"+
							"<td>"+str.cradle_count+"</td>"+
							"<td>"+str.longitude+"</td>"+
							"<td>"+str.latitude+"</td>"+
								"<input type='hidden' name='bike' value='" +
								str.addr_gu+"/"+
								str.content_id+"/"+
								str.content_nm+"/"+
								str.new_addr+"/"+
								str.cradle_count+"/"+
								str.longitude+"/"+
								str.latitude+"'/>"+
								// bike라는 이름으로 submit으로 날라갈거임
						"</tr>"
					);
				}
			}
		});
	});
}