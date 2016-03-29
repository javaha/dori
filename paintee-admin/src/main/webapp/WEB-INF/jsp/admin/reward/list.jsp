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
			<th class="tcenter" width="110px">요청수수료</th>
			<th class="tcenter" width="110px">요청금액</th>
			<th class="tcenter" width="190px">요청일시</th>
			<th class="tcenter" width="180px">상태</th>
		</tr>
	</thead>
	<tbody>
	<%--  최신 등록된 글부터 출력합니다. --%>
	<c:forEach var="data" items="${pageVO.list}">
		<tr>
			<td class="tcenter">${data.userName}</td>
 			<td class="tcenter">${data.bankName}</td> 
			<td class="tcenter">${data.accountNo}</td>
			<td class="tcenter">${data.accountName}</td>
			<td class="tcenter" id="earmRequestedCommission${data.seq}">$${data.earmRequestedCommission}</td>
			<td class="tcenter" id="earmRequestedMoney${data.seq}">$${data.earmRequestedMoney}</td>
			<td class="tcenter">
				<fmt:formatDate value="${data.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
			<td class="tcenter">
				<select id="rewardStatus${data.seq}" name="rewardStatus">
					<option value="R">요청</option>
					<option value="A">비정상</option>
					<option value="C">완료</option>
				</select>
				<script>
					$("#rewardStatus${data.seq}").val("${data.rewardStatus}");
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

<script>
	$("[name=rewardStatus]").change(function (event) {
		var rewardId = this.id.replace("rewardStatus", "");
		var rewardStatus = this.value;
		console.log($("#earmRequestedCommission" + rewardId));
		console.log($("#earmRequestedCommission" + rewardId).html());
		console.log($("#earmRequestedCommission" + rewardId).html().replace("$", ""));
		var data = {
			"seq": rewardId,
			"rewardStatus": rewardStatus,
			"earmRequestedCommission": $("#earmRequestedCommission" + rewardId).html().replace("$", ""), 
			"earmRequestedMoney": $("#earmRequestedMoney" + rewardId).html().replace("$", "")
		};
		$.ajax({
				url: "/admin/reward/mod",
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