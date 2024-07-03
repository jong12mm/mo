<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- link  -->
	<%@ include file="/resources/static/jsp/link.jsp" %>
	
	<!-- common.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/common.css" />
	
	<!-- common.js -->
	<script src="${pageContext.request.contextPath}/resources/static/js/common.js" defer></script>
	
	<!-- book/list.css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/book/list.css" />
	
	<!-- book/list.js -->
	<script src="${pageContext.request.contextPath}/resources/static/js/book/list.js" defer></script>
	
</head>

<body>
	
	<div class="wrapper">
		<header>
			
			<!-- top-header -->
			<%@ include file="/resources/static/jsp/topHeader.jsp" %>
			<!-- nav -->
			<%@ include file="/resources/static/jsp/nav.jsp" %>

		</header>
       <main class=layout-150>

            <section class=breadcrum-block>
	            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
				  <ol class="breadcrumb">
				    <li class="breadcrumb-item"><a href="#">HOME</a></li>
				    <li class="breadcrumb-item active" aria-current="page">Movie_LIST</li>
				  </ol>
				</nav>
            </section>
            
            <section class="search-block layout-150">
       
            	<form action="${pageContext.request.contextPath}/movie/delete">          		
            		<div class="m-2">
	            		<select name="type" id="" class="form-select">
	            			<option value="영화_ID" selected>영화_ID</option>
	            			<option value="영화제목">영화제목</option>
	            			<option value="영화장르">영화장르</option>
	            			<option value="영화예매여부">영화예매여부</option>
	            			<option value="상영장소">상영장소</option>
	            			<option value="상영시간">상영시간</option>
	            		</select>
            		</div>
            		<div class="m-2">
            			<input name="keyword" placeholder="KEYWORD" class="form-control">
            		</div>	
            		<div class="m-2">
            			<input type="hidden" name="pageNo" value=${pageDto.criteria.pageno} />
            		</div>
            		<div class="m-2">
            			<button class="btn btn-secondary">조회</button>
            		</div>
            	</form>
            </section>

            <section>
            	<%-- ${pageDto} --%>
         		<div>
            		<div>전체게시물 개수 : <span> ${count}</span> </div>	  
            		<div>전체페이지 개수 : <span> ${pageDto.totalpage}</span> </div>
            		<div>전체페이징 블럭 개수 : <span> ${pageDto.totalBlock}</span> </div>
            		<div>현재페이징 블럭 번호 : <span> ${pageDto.nowBlock}</span> </div>
            		<div>현재페이지 번호 : <span> ${pageDto.criteria.pageno}</span> </div>
            	</div> 
            </section>
            
            <section class="show-block">
            	<table class=table>
            		<tr>
            			<td>영화_ID</td>
            			<td>영화제목</td>
            			<td>영화장르</td>
            			<td>영화예매여부</td>
            			<td>상영장소</td>
            			<td>상영시간</td>
            		</tr>
            		
            		<c:forEach  var="movieDto"	items="${list}" varStatus="status">    		
		            	<tr>	
								<td>${movieDto.movieId}</td>
								<td>${movieDto.movieTitle}</td>
								<td>${movieDto.moviegenre}</td>
								<td>${movieDto.reserv}</td>
								<td>${movieDto.cgv}</td>
								<td>${movieDto.time}</td>
								<td>
									<form action="${pageContext.request.contextPath}/movie/update" method="GET">
									    <input type="hidden" name="movieId" value="${movieDto.movieId}">
									    <button type="submit" class="btn btn-secondary">수정</button>
									</form>
								</td>
								<td>
									<form action="${pageContext.request.contextPath}/movie/delete" method="POST">
									    <input type="hidden" name="movieId" value="${movieDto.movieId}">
									    <button type="submit" class="btn btn-secondary" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
									</form>
								</td>
						</tr>          		
            		</c:forEach>

									
            	</table>      
            </section>
            
            <!-- paging -->
            <section>
	            	<nav aria-label="Page navigation example">
					  <ul class="pagination">
					    
					    <!-- prev -->
 						<c:if test="${pageDto.prev}">
	 						<li class="page-item">
								   <a class="page-link" href="${pageContext.request.contextPath}/movie/delete?pageNo=${pageDto.nowBlock*pageDto.pagePerBlock-pageDto.pagePerBlock*2+1}" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								   </a>
							</li>  							
 						</c:if>
      
					    
					    
					    <!-- paging -->
						<c:forEach 	var="pageNo"	begin="${pageDto.startPage}" end="${pageDto.endPage}" 	step="1">
							<li class="page-item">
								<a class="page-link" href="${pageContext.request.contextPath}/movie/delete?pageNo=${pageNo}">${pageNo}</a>
							</li>
						</c:forEach>
						
				
						
					    
					    <!-- next -->
					   	<c:if test="${pageDto.next}">
							<li class="page-item">
								      <a class="page-link" href="${pageContext.request.contextPath}/movie/delete?pageNo=${pageDto.nowBlock*pageDto.pagePerBlock+1}" aria-label="Next">
								        	<span aria-hidden="true">&raquo;</span>
								      </a>
							</li>
						</c:if>
						
					  </ul>
					</nav>
            </section>
            
            <script>
    function submitHandler(event) {
        // 기본 동작 방지
        event.preventDefault();
        
        // 폼 요소 가져오기
        var form = event.target;
        
        // hidden input의 값을 가져와서 콘솔에 출력
        var movieId = form.querySelector('input[name="movieId"]').value;
        console.log("클릭된 버튼의 movieId 값: " + movieId);
        
        // 폼 제출
        form.submit();
    }
</script>

            
        </main>
        
        
        
        <footer>  
        	<!-- footer  -->
			<%@ include file="/resources/static/jsp/footer.jsp" %>
       </footer>
        	
	</div>
		
</body>	
</html>