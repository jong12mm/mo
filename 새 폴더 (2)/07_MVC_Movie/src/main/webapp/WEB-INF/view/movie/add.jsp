<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				    <li class="breadcrumb-item active" aria-current="page">Movie_ADD</li>
				  </ol>
				</nav>
            </section>
            <section>
      	
            	<form action="${pageContext.request.contextPath}/movie/add" method="post" class="w-25 m-3">
            		<div>
            			<input type="text" name="movieId" placeholder="영화_ID"  class="form-control m-3" />
            		</div>  		
            		<div>
            			<input type="text" name="movieTitle" placeholder="영화제목" class="form-control m-3" />
            		</div>
            		<div>
            			<input type="text" name="moviegenre" placeholder="영화장르" class="form-control m-3" />
            		</div>
             		<div>
            			<input type="text" name="reserv" placeholder="영화예매여부" class="form-control m-3" />
            		</div>
            		<div>
            			<input type="text" name="cgv" placeholder="상영장소" class="form-control m-3" />
            		</div>
            		<div>
            			<input type="text" name="time" placeholder="상영시간" class="form-control m-3" />
            		</div>           		
             		<div>
            			<button class="btn btn-secondary m-3">ADD</button>
            		</div>            				
            	</form>
           
            </section>
            <section>
            
            </section>
        
        <footer>
            <section></section>          
        </main>
        	<!-- footer  -->
			<%@ include file="/resources/static/jsp/footer.jsp" %>
       </footer>
        	
	</div>
		
</body>	
</html>