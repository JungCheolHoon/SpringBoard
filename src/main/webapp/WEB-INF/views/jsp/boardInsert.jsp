<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@include file="../jsp/includes/header.jsp"%>

<script>
$(document).ready(function(e){
	var formObj = $("form[role='form']");
		
	$("button[type='submit']").on("click",function(e){
		e.preventDefault();
		
		console.log("submit clicked");
		
		var str = "";
		
		$(".uploadResult ul li").each(function(i, obj){
			var jobj = $(obj);
			console.log(jobj);
			
			str += "<input type='hidden' name='attachList["+i+"].fileName' value='" + jobj.data("filename")+"' />";
			str += "<input type='hidden' name='attachList["+i+"].uuid' value='" + jobj.data("uuid")+"'>";
			str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='" + jobj.data("path")+"' />";
			str += "<input type='hidden' name='attachList["+i+"].fileType' value='" + jobj.data("type")+"' />";
			
			console.log(str);
		});
		formObj.append(str).submit();
		
	});
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;

	function checkExtensions(fileName) {
		if (regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드 하실 수 없습니다!")
			return false;
		}
		return true;
	}
	function checkFileSize(fileSize) {
		if (fileSize >= maxSize) {
			alert("업로드 하실 수 있는 최대 파일사이즈는 최대 5MB 입니다!");
			return false;
		}
		return true;
	}

	$("input[type='file']").change(function(e) {
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		var filesLength = files.length;

		for (var i = 0; i < filesLength; i++) {

			if (!checkExtensions(files[i].name)) {
				return false;
			}
			if (!checkFileSize(files[i].size)) {
				return false;
			}

			formData.append("uploadFile", files[i]);
		}

		$.ajax({
			url : "/uploadAjaxAction",
			processData : false, // 데이터 처리
			contentType : false, // MIME타입 (application/xml ...)
			data : formData, // 요청 시 전송 데이터
			type : "POST", // HTTP Method
			dataType : "json", // 응답 시 전송받는 데이터의 타입
			success : function(result) { // result : 응답데이터
				console.log(result);
				showUploadResult(result);
			}
		});
	});
		
	function showUploadResult(uploadResultArr){
		if(!uploadResultArr || uploadResultArr.length ==0) {return;}
		
		var uploadUL = $(".uploadResult ul");
		
		var str = "";
		
			$(uploadResultArr).each(function(i, obj) {
				
					if(obj.fileType){
						var fileCallPath = encodeURIComponent( obj.uploadPath + "/thumb_" + obj.uuid +"_"+obj.fileName);
						str += "<li data-path='" + obj.uploadPath+"'";
						str += " data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName+"' data-type='"+obj.fileType+"'";
						str += "><div>"
// 						str += "<button type='button' data-file=\'" + fileCallPath+ "\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button>";
						str += "<img src='/display?fileName=" + fileCallPath +"'>";
						str += "<div> " + obj.fileName +" <button type='button' data-file=\'" + fileCallPath+ "\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button></div>";
						str += "</div>";
						str += "</li>";
					}
					
					else{
						var fileCallPath = encodeURIComponent( obj.uploadPath+"/" + obj.uuid+ "_" + obj.fileName);
						var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
						str += "<li data-path='" + obj.uploadPath+"'";
						str += " data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName+"' data-type='"+obj.fileType+"'";
						str += "><div>";
						str += "<img src='/resources/img/attach.png'>";
						str += "<div> " + obj.fileName +" <button type='button' data-file=\'" + fileCallPath+ "\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button></div>";
						str += "</div>";
						str += "</li>";
						
					}
			});
		uploadUL.append(str);
		
	}
	
	$(".uploadResult").on("click","button",function(e){
		var targetFile = $(this).data("file");
		var type=$(this).data("type");
		
		var targetLi = $(this).closest("li");
		
		$.ajax({
			url : '/deleteFile',
			data: {fileName: targetFile, type: type},
			dataType: 'text',
			type: 'POST',
			success: function(result){
				alert(result);
				targetLi.remove();
			}
		});
		
	});
		
});
</script>

<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
	align-content: center;
	text-align: center;
}

.uploadResult ul li img {
	width: 100px;
	heigth : 100px;
}

.uploadResult ul li div {
	color: white;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background: rgba(255, 255, 255, 0.5);
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 600px;
}
</style>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Insert</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">Board Insert</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<form role="form" action="/board/boardInsertProc" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title' />
					</div>
					
					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" name='content'></textarea>
					</div>

					<div class="form-group">
						<label>Writer</label> <input class="form-control" name="writer" value='<sec:authentication property="principal.username" />' readnoly="readonly" />
					</div>
					<button type="submit" class="btn btn-default">Submit
						Button</button>
					<button onclick="reset();" class="btn btn-default">Reset
						Button</button>
				</form>

			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-defalut">
			<div class="panel-heading">File Attach</div>
			<div class="panel-body">
				<input type="file" name="uploadFile" multiple />
			</div>
			<div class="uploadResult">
				<ul>
				</ul>
			</div>
		</div>
	</div>
</div>
<%@include file="../jsp/includes/footer.jsp"%>
