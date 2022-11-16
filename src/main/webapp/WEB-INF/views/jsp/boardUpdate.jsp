<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@include file="../jsp/includes/header.jsp"%>

<script src="/resources/js/reply.js"></script>
<script>
	// reply.js 테스트용
	$(function() {

		var bnoValue = "<c:out value='${boardVO.bno}' />";
		var replyUL = $(".chat");

		//
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
		
		
		//
		
		$
				.getJSON(
						"/board/selectAttachList",
						{
							bno : bnoValue
						},
						function(arr) {
							console.log(arr);
							var str = "";

							$(arr)
									.each(
											function(i, attach) {
												if (attach.fileType) {
													var fileCallPath = encodeURIComponent(attach.uploadPath
															+ "/thumb_"
															+ attach.uuid
															+ "_"
															+ attach.fileName);
													str += "<li data-path='" + attach.uploadPath+"'";
					str += " data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName+"' data-type='"+attach.fileType+"'";
					str += "><div>"
													str += "<img src='/display?fileName="
															+ fileCallPath
															+ "'>";
													str += "<div> "
															+ attach.fileName
															+ " <button type='button' data-file=\'" + fileCallPath+ "\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button></div>";
													str += "</div>";
													str += "</li>";
												}

												else {
													var fileCallPath = encodeURIComponent(attach.uploadPath
															+ "/"
															+ attach.uuid
															+ "_"
															+ attach.fileName);
													var fileLink = fileCallPath
															.replace(
																	new RegExp(
																			/\\/g),
																	"/");
													str += "<li data-path='" + attach.uploadPath+"'";
					str += " data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName+"' data-type='"+attach.fileType+"'";
					str += "><div>";
													str += "<img src='/resources/img/attach.png'>";
													str += "<div> "
															+ attach.fileName
															+ " <button type='button' data-file=\'" + fileCallPath+ "\' data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button></div>";
													str += "</div>";
													str += "</li>";

												}
											});
							$(".uploadResult ul").html(str);
						});
		$(".uploadResult").on("click", "button", function(e) {
			var targetFile = $(this).data("file");
			var type = $(this).data("type");

			var targetLi = $(this).closest("li");

			$.ajax({
				url : '/deleteFile',
				data : {
					fileName : targetFile,
					type : type
				},
				dataType : 'text',
				type : 'POST',
				success : function(result) {
					alert(result);
					targetLi.remove();
				}
			});

		});
		$(".uploadResult")
				.on(
						"click",
						"li",
						function(e) {
							console.log("view image");
							var liObj = $(this);

							var path = encodeURIComponent(liObj.data("path")
									+ "/" + liObj.data("uuid") + "_"
									+ liObj.data("filename"));
							if (liObj.data("type")) {
								showImage(path.replace(new RegExp(/\\/g), "/"));
							} else {
								self.location = "/download?fileName=" + path;
							}
						});

		function showImage(fileCallPath) {
			$(".bigPictureWrapper").css("display", "flex").show();
			$(".bigPicture").html(
					"<img src='/display?fileName=" + fileCallPath + "'>")
					.animate({
						width : '100%',
						height : '100%'
					}, 1000);
		}

		$(".bigPictureWrapper").on("click", function(e) {
			$(".bigPicture").animate({
				width : '0%',
				height : '0%'
			}, 1000);
			setTimeout(function() {
				$('.bigPictureWrapper').hide();
			}, 1000)
		})

		showList(1);

		function showList(page) {
			replyService
					.listReplyVO(
							{
								bno : bnoValue,
								page : page || 1
							},
							function(replyCnt, list) {

								if (page == -1) {
									pageNum = Math.ceil(replyCnt / 10.0);
									showList(pageNum);
									return;
								}

								if (list == null || list.length == 0) {
									replyUL.html("");
									return;
								}

								var str = "";
								var listLength = list.length;

								for (var i = 0, len = listLength; i < len; i++) {
									str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
									str += "  <div><div class='header'><strong class='primary-font'>["
											+ list[i].rno
											+ "] "
											+ list[i].replyer + "</strong>";
									str += "    <small class='pull-right text-muted'>"
											+ replyService
													.displayTime(list[i].replyDate)
											+ "</small></div>";
									str += "    <p>" + list[i].reply
											+ "</p></div></li>";
								}
								replyUL.html(str);

								showReplyPage(replyCnt);
							});
		}

		var modal = $(".modal");
		var modalInputReply = modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");

		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		var modalCloseBtn = $("#modalCloseBtn");

		$("#addReplyBtn").on("click", function(e) {
			modal.find("input").val("");
			modalInputReplyDate.closest("div").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();

			modalRegisterBtn.show();
			$(".modal").modal("show");
		});

		modalRegisterBtn.on("click", function(e) {
			var reply = {
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val(),
				bno : bnoValue
			};

			replyService.insertReplyVO(reply, function(result) {
				alert(result);

				modal.find("input").val("");
				modal.modal("hide");

				showList(-1);
			})
		});

		$(".chat").on(
				"click",
				"li",
				function() {
					var rno = $(this).data("rno");
					replyService.selectReplyVO(rno, function(reply) {
						modalInputReply.val(reply.reply);
						modalInputReplyer.val(reply.replyer);
						modalInputReplyDate.val(
								replyService.displayTime(reply.replyDate))
								.attr("readonly", "readonly");
						modal.data("rno", reply.rno);
						modal.find("button[id !='modalCloseBtn']").hide();
						modalModBtn.show();
						modalRemoveBtn.show();

						$(".modal").modal("show");
					});
				});

		modalModBtn.on("click", function(e) {
			var reply = {
				rno : modal.data("rno"),
				reply : modalInputReply.val(),
				replyer : modalInputReplyer.val()
			};

			replyService.updateReplyVO(reply, function(result) {

				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});

		modalRemoveBtn.on("click", function(e) {
			var rno = modal.data("rno");

			replyService.deleteReplyVO(rno, function(result) {
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			})
		});

		modalCloseBtn.on("click", function(e) {
			$(".modal").modal("hide");
		});

		var pageNum = 1;
		var replyPageFooter = $(".panel-footer");

		function showReplyPage(replyCnt) {
			var endNum = Math.ceil(pageNum / 10.0) * 10;
			var startNum = endNum - 9;

			var prev = startNum != 1;
			var next = false;

			if (endNum * 10 >= replyCnt) {
				endNum = Math.ceil(replyCnt / 10.0);
			}

			if (endNum * 10 < replyCnt) {
				next = true;
			}

			var str = "<ul class='pagination pull-right'>";
			if (prev) {
				str += "<li class='page-item'><a class='page-link' href='"
						+ (startNum - 1) + "'>Previous</a></li>";
			}

			for (var i = startNum; i <= endNum; i++) {
				var active = pageNum == i ? "active" : "";

				str += "<li class='page-item "+active+"'><a class='page-link' href='"+i+"'>"
						+ i + "</a></li>";
			}

			if (next) {
				str += "<li class = 'page-item'><a class = 'page-link' href='"
						+ (endNum + 1) + "'>Next</a></li>";
			}

			str += "</ul></div>";
			console.log(str);
			replyPageFooter.html(str);
		}

		replyPageFooter.on("click", "li a", function(e) {
			e.preventDefault();
			var targetPageNum = $(this).attr("href");

			pageNum = targetPageNum;

			showList(pageNum);

		});

	})
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Update</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">Board Update</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<form id="updateForm" role="form" action="/board/updateProc"
					method="post">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type='hidden' name='pageNum'
						value="${param.pageNum}">
					<%-- 	    <input type='hidden' name='type' value='<c:out value="${param.type }"/>'> --%>
					<%-- 		<input type='hidden' name='keyword' value='<c:out value="${param.keyword }"/>'> --%>


					<div class="form-group">
						<label>Bno</label> <input class="form-control" name='bno'
							value='<c:out value="${boardVO.bno }"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title'
							value='<c:out value="${boardVO.title }"/>'>
					</div>

					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" name='content'><c:out
								value="${boardVO.content}" /></textarea>
					</div>

					<div class="form-group">
						<label>Writer</label> <input class="form-control" name='writer'
							value='<c:out value="${boardVO.writer}"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>RegDate</label> <input class="form-control" name='regDate'
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${boardVO.regdate}" />'
							readonly="readonly">
					</div>

					<div class="form-group">
						<label>Update Date</label> <input class="form-control"
							name='updateDate'
							value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${boardVO.updateDate}" />'
							readonly="readonly">
					</div>

					<sec:authentication property="principal" var="pinfo"/>
					<sec:authorize access="isAuthenticated()">
						<c:if test="${pinfo.username eq boardVO.writer}">
							<button type="submit" id='updateBtn' class="btn btn-default">Update</button>
							<button type="button" id='deleteBtn' class="btn btn-danger"
								bno="${boardVO.bno}" pageNum="${param.pageNum}">Delete</button>
						</c:if>
					</sec:authorize>
					<button type="button" id='listBtn' class="btn btn-info"
						pageNum="${param.pageNum}">List</button>
				</form>
			</div>
		</div>
	</div>
</div>

<div class='row'>

	<div class="col-lg-12">

		<!-- /.panel -->
		<div class="panel panel-default">
			<!--       <div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> Reply
      </div> -->

			<!--       <div class="panel-heading">
        <i class="fa fa-comments fa-fw"></i> Reply
        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
      </div>      -->

			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i> Reply
				<sec:authorize access="isAuthenticated()">
					<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New
						Reply</button>
				</sec:authorize>
			</div>



			<!-- /.panel-heading -->
			<div class="panel-body">

				<ul class="chat">

				</ul>
				<!-- ./ end ul -->
			</div>
			<!-- /.panel .chat-panel -->

			<div class="panel-footer"></div>


		</div>
	</div>
	<!-- ./ end row -->
</div>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label> <input class="form-control" name='reply'
						value='New Reply!!!!'>
				</div>
				<div class="form-group">
					<label>Replyer</label> <input class="form-control" name='replyer'
						value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label> <input class="form-control"
						name='replyDate' value='2018-01-01 13:13'>
				</div>

			</div>
			<div class="modal-footer">

				<c:if test="${principal.username eq boardVO.writer}">
					<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
					<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				</c:if>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="bigPictureWrapper">
	<div class="bigPicture"></div>
</div>

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
	heigth: 100px;
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
		<div class="panel panel-defalut">
			<div class="panel-heading">Files</div>
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name="uploadFile" multiple="multiple">
				</div>
				<div class="uploadResult">
					<ul>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="../jsp/includes/footer.jsp"%>