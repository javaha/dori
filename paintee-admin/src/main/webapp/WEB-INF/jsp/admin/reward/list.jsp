<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<h1>New Reward</h1>
<hr />
<table class="table table-striped table-hover table-bordered" >
	<thead>
		<tr>
			<th class="tcenter" width="130px">요청자</th>
			<th class="tcenter" width="130px">은행</th>
			<th class="tcenter">계좌번호</th>
			<th class="tcenter" width="130px">계좌주명</th>
			<th class="tcenter" width="130px">요청수수료</th>
			<th class="tcenter" width="130px">요청금액</th>
			<th class="tcenter" width="130px">요청일시</th>
			<th class="tcenter" width="130px">상태</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${pageVO.list}" varStatus="loop">
		<tr>
			<td class="tcenter">${data.userName}</td>
 			<td class="tcenter">${data.bank}</td> 
			<td class="tcenter">${data.accountNo}</td>
			<td class="tcenter">${data.accountName}</td>
			<td class="tcenter">$${data.earmRequestedCommission}</td>
			<td class="tcenter">$${data.earmRequestedMoney}</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="tcenter">
				<select id="rewardStatus${loop}" name="rewardStatus">
					<option vlaue="R">요청</option>
					<option vlaue="A">비정상</option>
					<option vlaue="C">완료</option>
				</select>
				<script>
					$("#rewardStatus${loop}").val("${data.rewardStatus}");
				</script>
			</td>
		</tr>
	</c:forEach>
	<%--  만약, 게시글이 하나도 등록되어 있지 않다면 --%>
	<c:if test="${empty pageVO.list}">
		<tr>
			<td colspan='8'>No Content</td>
		</tr>
	</c:if>
	</tbody>
</table>

<%-- 페이징 처리 --%>
<navi:page />

<c:import url="/WEB-INF/jsp/template/footer.jsp" />