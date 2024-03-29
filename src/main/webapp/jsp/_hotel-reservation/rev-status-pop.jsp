<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
<%
    RequestUtils requestUtils = RequestUtils.of(request);
    request.setAttribute("id", requestUtils.getString("id"));
%>
<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>
<ax:set key="axbody_class" value="baseStyle"/>

<ax:layout name="modal">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" var="LANG" />
        <script>
            var modalParams = {id: "${id}"};
            
        </script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/_hotel-reservation/rev-status-pop.js' />"></script> 
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
    </jsp:attribute>
    <jsp:body>
        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel height="*">
                <form name="formView01" class="reservationForm">
                <div role="page-header">
                    
                </div>
                    <div data-ax-tbl class="ax-form-tbl">
                        <div data-ax-tr class="left">
                        <div style="font-weight: bold; display: inline-block; margin: 0px 5px">예약번호 : <input style="border: 0; background: none; width: 100px;" data-ax-path="rsvNum" name="rsvNum" class="js-rsvNum" readonly="readonly"></div>
                        <div style="width: 200px; display: inline-block; margin: 5px;">
                            <c:if test="${modalTyp == 'All'}">
                            <ax:common-code
                                groupCd="CHK_STATUS"
                                dataPath="sttusCd"
                                clazz="js-sttusCd form-control W100"
                            />
                            </c:if>
                            <c:if test="${modalTyp == 'checkIn'}">
                                <div>
                                    <input type="text" data-ax-path="roomNum" name="roomNum"  readonly="readonly"  class="js-roomNum form-control" style="width: 50px; display: inline; text-align: center;">
                                    <button type="button" class="btn btn-default" data-form-view-01-btn="searchRoom"><i class="cqc-magnifier"></i>객실배정</button>
                                </div>
                            </c:if>
                            <c:if test="${modalTyp == 'inHouse' || modalTyp == 'checkOut'}">
                                <div>
                                    <input type="text" data-ax-path="roomNum" name="roomNum"  readonly="readonly" class="js-roomNum form-control" style="width: 50px; display: inline; text-align: center;">
                                </div>
                            </c:if>
                        </div>
                        <div style="float: right; margin-top: 10px; padding-right: 10px;">
                            <span style="color: red;">* </span>표시는 필수 항목 체크 부분
                        </div>
                    </div>
                        <div data-ax-tr>
                            <div data-ax-td>
                                <div data-ax-td-label><span style="color: red;">* </span>도착일</div>
                                <div data-ax-td-wrap>
                                <input type="date" data-ax-path="arrDt" name="arrDt" class="js-arrDt form-control" data-ax-validate="required">               
                                </div>
                            </div>  
                            <div data-ax-td>
                                <div data-ax-td-label><span style="color: red;">* </span>숙박수</div>
                                <div data-ax-td-wrap>
                                <input type="text" data-ax-path="nightCnt" name="nightCnt" class="js-nightCnt form-control" data-ax-validate="required" style="width: 50px;">        
                                </div>
                            </div>  
                            <div data-ax-td>
                                <div data-ax-td-label><span style="color: red;">* </span>출발일</div>
                                <div data-ax-td-wrap>
                                <input type="date" data-ax-path="depDt" name="depDt" class="js-depDt form-control" data-ax-validate="required">               
                                </div>
                            </div>  
                        </div>
                        <div data-ax-tr>
                            <div data-ax-td>
                                <div data-ax-td-label><span style="color: red;">* </span>객실타입</div>
                                <div data-ax-td-wrap>
                                    <ax:common-code groupCd="ROOM_TYPE" name="roomTypCd" dataPath="roomTypCd" clazz="js-roomTypCd form-control W100" />
                                </div>
                            </div>  
                            <div data-ax-td>
                                <div data-ax-td-label><span style="color: red;">* </span>성인수</div>
                                <div data-ax-td-wrap>
                                <input type="text" data-ax-path="adultCnt" name="adultCnt" class="form-control" data-ax-validate="required" style="width: 50px;" >               
                                </div>
                            </div>  
                            <div data-ax-td>
                                <div data-ax-td-label><span style="color: red;">* </span>아동수</div>
                                <div data-ax-td-wrap>
                                <input type="text" data-ax-path="childCnt" name="childCnt" class="form-control" data-ax-validate="required" style="width: 50px;" >               
                                </div>
                            </div>  
                        </div>
                        <div data-ax-tr>
                            <div data-ax-td style="width: 100%;">
                                <div data-ax-td-label>투숙객
                                    <p style="padding-top: 10px;"><button type="button" class="btn btn-default" data-form-view-01-btn="searchGuest">
                                        <i class="cqc-magnifier"></i>검색</button>
                                    </p>
                                </div>
                                    <div data-ax-tr>
                                        <div data-ax-td style="width:50%">
                                            <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이름</div>
                                            <div data-ax-td-wrap style="border-right: 1px solid #ccc;">
                                                <input type="hidden" data-ax-path="guestId" name="guestId" />
                                                <input type="text" data-ax-path="guestNm" name="guestNm" class="js-guestNm form-control" title="투숙객 이름" data-ax-validate="required" style="width: 150px;" />
                                            </div>
                                        </div> 
                                        <div data-ax-td style="width:50%">
                                            <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">영문</div>
                                            <div data-ax-td-wrap>
                                                <input type="text" data-ax-path="guestNmEng" name="guestNmEng" class="form-control" style="width: 150px;" />
                                            </div>
                                        </div>                                                                      
                                    </div>
                                    <div data-ax-tr>
                                        <div data-ax-td style="width:50%">
                                            <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">연락처</div>
                                            <div data-ax-td-wrap style="border-right: 1px solid #ccc;">
                                                <input type="text" data-ax-path="guestTel" name="guestTel" class="js-guestTel form-control" title="투숙객 연락처" data-ax-validate="required"  style="width: 150px;" />
                                            </div>
                                        </div> 
                                        <div data-ax-td style="width:50%">
                                            <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이메일</div>
                                            <div data-ax-td-wrap>
                                                <input type="text" data-ax-path="email" name="email" class="js-email form-control" style="width: 150px;" />
                                            </div>
                                        </div>                                                                      
                                    </div>
                                    <div data-ax-tr>
                                        <div data-ax-td style="width:50%">
                                            <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">언어</div>
                                            <div data-ax-td-wrap style="border-right: 1px solid #ccc;">
                                                <ax:common-code groupCd="PMS_LANG" name="langCd" dataPath="langCd" clazz="form-control W100" />
                                            </div>
                                        </div> 
                                        <div data-ax-td style="width:50%">
                                            <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">생년월일</div>
                                            <div data-ax-td-wrap >
                                                <input type="date" data-ax-path="birth" name="birth" class="form-control" style="width: 150px;"  />
                                                
                                            </div>
                                            <span>
                                                <input type="radio" value="F" name="gender" data-ax-path="gender" > 여
                                                <input type="radio" value="M" name="gender" data-ax-path="gender" > 남
                                            </span>
                                        </div>                                
                                </div>
                            </div>
                            </div>
                        <div data-ax-tr>
                            <div data-ax-td style="width: 100%;">
                                <div data-ax-td-label>판매/결제</div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">판매유형</div>
                                                <div data-ax-td-wrap style="border-right: 1px solid #ccc;">
                                                    <ax:common-code groupCd="SALE_TYPE" name="saleTypCd" dataPath="saleTypCd" clazz="form-control W100" />
                                                </div>
                                            </div> 
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">예약경로</div>
                                                <div data-ax-td-wrap>
                                                    <ax:common-code groupCd="RSV_ROUTE" name="srcCd" dataPath="srcCd" clazz="form-control W100" />
                                                </div>
                                            </div>                                                                      
                                        </div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제방법</div>
                                                <div data-ax-td-wrap style="border-right: 1px solid #ccc;">
                                                    <ax:common-code groupCd="PAY_METHOD" name="payCd" dataPath="payCd" clazz="form-control W100" />
                                                </div>
                                            </div> 
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">선수금여부</div>
                                                <div data-ax-td-wrap>
                                                    <input type="checkbox" data-ax-path="advnYn" name="advnYn" class="js-advnYn" value="Y" />
                                                </div>
                                            </div>                                                                      
                                        </div>
                                        <div data-ax-tr>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제금액</div>
                                                <div data-ax-td-wrap style="border-right: 1px solid #ccc;">
                                                    <input type="text" data-ax-path="salePrc" name="salePrc" class="form-control" data-ax5formatter="money" style="width: 150px;" />
                                                </div>
                                            </div> 
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">서비스금액</div>
                                                <div data-ax-td-wrap>
                                                    <input type="text" data-ax-path="svcPrc" name="svcPrc" class="form-control" data-ax5formatter="money" style="width: 150px;" />
                                        </div>                         
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div data-ax-tr height="*">
                            <div data-ax-td style="width: 100%;">
                                <div data-ax-td-label>투숙메모</div>
                                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                    <div class="left">
                                        <h2>투숙메모 </h2>
                                    </div>
                                    <div class="right">
                                        <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i> 추가</button>
                                        <button type="button" class="btn btn-default" data-grid-view-01-btn="delete"><i class="cqc-circle-with-minus"></i> 삭제</button>
                                    </div>
                                </div>
                                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 100px; max-height: 150px;"></div>
                            </div>
                        </div>
                    </div>                
                    <div style="text-align: center; margin-top: 10px;">
                        <c:if test="${modalTyp == 'All'}">
                            <button type="button" class="btn btn-info" data-page-btn="save"> 저장 </button>
                        </c:if>
                        <c:if test="${modalTyp == 'checkIn'}">
                            <button type="button" class="btn btn-info" data-page-btn="checkIn" data-value="CHK_01"> 체크인 </button>
                        </c:if>
                        <c:if test="${modalTyp == 'inHouse'}">
                            <button type="button" class="btn btn-info" data-page-btn="checkOut" data-value="CHK_02"> 체크아웃 </button>
                            <button type="button" class="btn btn-info" data-page-btn="cancelCheckIn" data-value="CHK_03"> 체크인 취소 </button>
                        </c:if>
                        <button type="button" class="btn btn-default" data-page-btn="close"> 닫기 </button>
                    </div>
                </form>
            </ax:split-panel>
        </ax:split-layout>
    </jsp:body>
</ax:layout>
