 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 
<section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">인기차트</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                   
                    <c:forEach items="${mvList }" var="movie">
                    <div class="col mb-5">
                        <div class="card h-200">
                                 	
                            <div class="card-body p-1">
                                <div class="text-center">
                                <img class="card-img-top" src="${movie.mvpos}" alt="..." />
                                           <table>
                                           
                                           
                                           <p>
                                           영화제목: ${movie.mvtitle }
                                           </p>
                                           <p>
                                           영화감독: ${movie.mvdir }
                                           </p>
                                           <p>
                                           영화배우: ${movie.mvact }
                                           </p>
                                           <p>
                                           장르: ${movie.mvgenre }
                                           </p>
                                           <p>
                                           개봉일자:  ${movie.mvdate }
                                           </p>
                                           </table>
                                </div>
                            </div>
					            	
                            
                            
                            
                            
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">상세정보</a></div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </section>