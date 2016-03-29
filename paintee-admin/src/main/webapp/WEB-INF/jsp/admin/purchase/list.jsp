<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<h1>New Purchase</h1>
<hr />
<table class="table table-striped table-hover table-bordered" >
	<thead>
		<tr>
			<th class="tcenter" width="130px">구매자</th>
			<th class="tcenter" width="130px">보내는사람</th>
			<th class="tcenter" width="130px">받는사람</th>
			<th>주소</th>
			<th class="tcenter" width="80px">도시</th>
			<th class="tcenter" width="190px">구매일</th>
			<th class="tcenter" width="180px">상태</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${pageVO.list}">
		<tr>
			<td class="tcenter">${data.userName}</td>
 			<td class="tcenter">${data.senderName}</td> 
			<td class="tcenter">${data.receiverName}</td>
			<td>(${data.receiverZipcode})${data.receiverBasicAddr} ${data.receiverDetailAddr}</td>
			<td class="tcenter">${data.receiverCity}</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.purchaseDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="tcenter">
				<select id="purchaseStatus${data.seq}" name="purchaseStatus">
					<option value="C">요청</option>
					<option value="S">발송</option>
					<option value="R">환불요청</option>
					<option value="D">삭제</option>
				</select>
				<script>
					$("#purchaseStatus${data.seq}").val("${data.purchaseStatus}");
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
	$("[name=purchaseStatus]").change(function (event) {
		var seq = this.id.replace("purchaseStatus", "");
		var purchaseStatus = this.value;
		
		var data = {
			"seq": seq,
			"purchaseStatus": purchaseStatus
		};
		$.ajax({
				url: "/admin/purchase/mod",
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