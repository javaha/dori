<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<h1>New Purchase</h1>
<hr />
<table class="table table-striped table-hover table-bordered" >
	<thead>
		<tr>
			<th class="tcenter" width="160px">구매자</th>
			<th class="tcenter" width="160px">보내는사람</th>
			<th class="tcenter" width="80px">받는사람</th>
			<th>주소</th>
			<th class="tcenter" width="80px">도시</th>
			<th class="tcenter" width="200px">구매일</th>
			<th class="tcenter" width="180px">상태</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${pageVO.list}" varStatus="loop">
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
				<select id="purchaseStatus${loop}" name="purchaseStatus">
					<option vlaue="C">요청</option>
					<option vlaue="S">발송</option>
					<option vlaue="R">환불요청</option>
					<option vlaue="D">삭제</option>
				</select>
				<script>
					$("#purchaseStatus${loop}").val("${data.purchaseStatus}");
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

<c:import url="/WEB-INF/jsp/template/footer.jsp" />