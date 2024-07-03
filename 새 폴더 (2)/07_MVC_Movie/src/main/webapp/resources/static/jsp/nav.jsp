<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
			<nav class=layout-150>
				<!-- <div class="left"></div> -->
				<div class="right">
					<ul class=menu>
						<li>
							<a href="${pageContext.request.contextPath}/">홈</a>

						</li>
						
						<li>
							<a href="">상영영화소개</a>
							<ul>
								<li><a href="">연혁</a></li>
								<li><a href="">오시는길</a></li>
								<li><a href="">기타등등</a></li>
							</ul>
						</li>
						
						<li>
							<a href="">공지사항</a>
							<!-- 
							<ul>
								<li><a href="">공지사항게시판</a></li>
								<li><a href="">SUBMENU2</a></li>
								<li><a href="">SUBMENU3</a></li>
							</ul>
							 -->
						</li>
						<li>
							<a href="">영화서비스</a>
							<ul>
								
								<li><a href="${pageContext.request.contextPath}/movie/list">영화조회[ALL]</a></li>
								<li><a href="javascript:void(0)">영화예매신청[회원]</a></li>
								<li><a href="">예매신청확인[사서]</a></li>
								<li><a href="">예매신청확인[사서]</a></li>
								<li><a href="${pageContext.request.contextPath}/movie/add">영화등록[사서]</a></li>
								<li><a href="">영화수정[사서]</a></li>
								<li><a href="${pageContext.request.contextPath}/movie/delete">영화삭제[사서]</a></li>
 								
							</ul>
						</li>
						<li>
							<a href="">자유게시판</a>
							<!-- 
							<ul>
								<li><a href="">SUBMENU1</a></li>
								<li><a href="">SUBMENU2</a></li>
								<li><a href="">SUBMENU3</a></li>
							</ul>
							 -->
						</li>
						<li>
							<a href="">행사일정</a>
							<ul>
								<li><a href="">연간행사</a></li>
								<li><a href="">이번달행사</a></li>
								<li><a href="">...</a></li>
							</ul>
						</li>
						<li>
							<a href="">자주하는질문</a>
							<ul>
								<li><a href="">QnA</a></li>
								<li><a href="">FAQ</a></li>
							</ul>
						</li>
																														
					</ul>
				</div>
			</nav>	