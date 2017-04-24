// submit 전에 시점을 잡아서 이벤트 처리
$(".answer-write input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	console.log("click!! ");

	var queryString = $(".answer-write").serialize();
	console.log("query " + queryString);

	//  서버에 데이터 전송
	var url = $(".answer-write").attr("action");
	console.log("url : " + url);

	$.ajax({
		type : 'post',
		url : url,
		data : queryString,		// client 가 입력한 데이터
		dataType : 'json', 		// json 또는 xml 타입 
		error : onError, 			// 해당하는 function 수행
		success : onSuccess
	});
}
function onSuccess(data, status) { // data : AnswerDTO
	console.log(data);
	// 1. 동적 HTML 생성
	// 2. JSON 인자값 전달
	// 3. 기존 HTML에 추가
	// 4. 댓글 입력 폼값 초기화
	
	var answerTemplate = $("#answerTemplate").html(); 	// 클래스 (.) 아이디 (#)
	var template = answerTemplate.format(data.writer.userId, data.formattedCreateDate, data.contents, data.id, data.id); 				// 하위에 정의해둔 포멧 함수가 동작된다. 
	$(".qna-comment-slipp-articles").prepend(template);
	$("textarea[name=contents]").val("");
	
	
}
function onError(e) {
	console.log("errorMessage : " + e + " 가 없습니다.");
}

// 동적 HTML 템플릿을 사용하기위한 코드
String.prototype.format = function() {
	  var args = arguments;
	  return this.replace(/{(\d+)}/g, function(match, number) {
	    return typeof args[number] != 'undefined'
	        ? args[number]
	        : match
	        ;
	  });
	};