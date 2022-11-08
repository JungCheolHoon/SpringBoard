$(function() {
	
	// form 안의 button은 기본적으로 submit이벤트를 발생시킨다.
	// 기본이벤트를 방지해야 한다.
	$("button").click(function() {
		event.preventDefault();
	});
	
	// 등록버튼 클릭 시 등록화면으로 이동
	$("#regBtn").click(function() {
		location.href = "/board/boardInsert";
	
	});
	
	// 수정버튼 클릭 시 수정처리
	$("#updateBtn").click(function(event) {
		if(confirm("수정하시겠습니까?")){
			$("#updateForm").submit();
		} else {
			return false;
		}
	
	});
	
	// 삭제버튼 클릭 시 삭제처리
	$("#deleteBtn").click(function(event) {
		if(confirm("삭제하시겠습니까?")){
			location.href="/board/delete?bno="+$(this).attr("bno")+"&pageNum="+$(this).attr("pageNum");
		} else {
			return false;
		}
	});
		
	// 목록페이지에서 페이지번호 클릭 시 해당페이지로 이동
	formSetting(".pageNumLink",["list"],"/board/list");
	
	// 목록페이지에서 제목 클릭 시 조회페이지로 이동
	formSetting(".selectLink",["select"],"/board/select");
	
	// 조회페이지에서 목록버튼 클릭 시 목록페이지로 이동	
	formSetting("#listBtn",["list"],"/board/list");
	
	// 목록페이지에서 검색버튼 클릭 시 목록페이지로 이동
	$("#searchBtn").click(function(event) {
		$("#searchForm").submit();
	});
	
});

function formSetting(className, nameValues, actionURI){
		$(className).click(function(event){
			event.preventDefault();
			$("input[name='pageNum']").val($(this).attr("pageNum"))
			$("input[name='action']").val(nameValues[0]);
			$("input[name='bno']").val($(this).attr("bno"))
			$("#actionForm").attr("action",actionURI);
			$("#actionForm").submit(); 
		});
}