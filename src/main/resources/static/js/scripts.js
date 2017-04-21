// submit 전에 시점을 잡아서 이벤트 처리
$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	console.log("click!! ");

	var queryString = $(".answer-write").serialize();
	console.log("query " + queryString);

	//  서버에 데이터 전송
	var url = $(".answer-write").attr("action");
	console.log("url " + url);

	$.ajax({
		type : 'post',
		url : url,
		data : queryString, // client 가 입력한 데이터
		dataType : 'json', // json 또는 xml 타입 
		error : onError, // 해당하는 function 수행
		success : onSuccess
	});
}
function onError() {

}
function onSuccess(data, status) {
  console.log("data : " + data);
}