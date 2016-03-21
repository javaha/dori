<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<h1>New Upload</h1>
<hr />
<table class="table table-striped table-hover table-bordered" >
	<thead>
		<tr>
			<th class="tcenter" width="160px">Artist Name</th>
			<th>Sentence</th>
			<th class="tcenter" width="80px">Posted</th>
			<th class="tcenter" width="80px">Share</th>
			<th class="tcenter" width="130px">Posted People</th>
			<th class="tcenter" width="190px">Created Date</th>
			<th class="tcenter" width="180px">status</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${pageVO.list}" varStatus="loop">
		<tr>
			<td class="tcenter">${data.artistName}</td>
 			<td>${data.sentence}</td> 
			<td class="tcenter">${data.postedNum}</td>
			<td class="tcenter">${data.shareCnt}</td>
			<td class="tcenter">${data.postedPeopleCnt}</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="tcenter">
				<select id="paintingStatus${data.seq}" name="paintingStatus">
					<option value="N">정상</option>
					<option value="B">블라인드</option>
					<option value="D">삭제</option>
				</select>
				<script>
					$("#paintingStatus${data.seq}").val("${data.paintingStatus}");
				</script>
			</td>
		</tr>
	</c:forEach>
	<%--  만약, 게시글이 하나도 등록되어 있지 않다면 --%>
	<c:if test="${empty pageVO.list}">
		<tr>
			<td colspan='7'>No Content</td>
		</tr>
	</c:if>
	</tbody>
</table>

<%-- 페이징 처리 --%>
<navi:page />

<script>
	$("[name=paintingStatus]").change(function (event) {
		var seq = this.id.replace("paintingStatus", "");
		var paintingStatus = this.value;
		
		var data = {
			"seq": seq,
			"paintingStatus": paintingStatus
		};
		$.ajax({
				url: "/admin/painting/mod",
				type: "GET",
				async: true,
				cache: false,
				data: data
		})
		.done(function (result) {
			if (result) {
				alert(result.msg);			
			}
		})
		.fail(function () {
			alert("상태 변경중 오류가 발생했습니다.");
		});
	});	
</script>

<c:import url="/WEB-INF/jsp/template/footer.jsp" />