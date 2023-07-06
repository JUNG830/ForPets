<%@ page pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/view/include/taglibs.jspf" %>
<aof:session key="memberName"          var="ssMemberName"          scope="request"/>
<aof:session key="memberSurname"          var="ssMemberSurname"          scope="request"/>
<aof:session key="memberFullName"          var="ssMemberFullName"          scope="request"/>
<aof:session key="nickname"      	   var="ssMemberNickname"       scope="request"/>
<aof:session key="memberSeq"           var="ssMemberSeq"           scope="request"/>
<aof:session key="memberId"            var="ssMemberId"            scope="request"/>
<aof:session key="memoCount"           var="ssMemoCount"		   scope="request"/>
<aof:session key="currentRolegroupSeq" var="ssCurrentRolegroupSeq" scope="request"/>
<aof:session key="currentRoleCfString" var="ssCurrentRoleCfString" scope="request"/>
<aof:session key="roleGroups"          var="ssRoleGroups"          scope="request"/>
<aof:session key="memberEmsTypeCd"     var="ssMemberEmsTypeCd"     scope="request"/>
<aof:session key="photo"               var="memberPhoto" 		   scope="request"/>
<aof:session key="birthday"               var="ssMemberBirth" 		   scope="request"/>

<aof:session key="photo"               var="ssPhoto" 		       scope="request"/>
<aof:session key="phoneMobileDes"		   var="ssPhoneMobile"     	   scope="request"/>
<aof:session key="emailDes"		   	   var="ssEmail"     	  	   scope="request"/>
<aof:session key="snsLoginYn"		   var="ssSnsLoginYn"     	   scope="request"/>
<aof:session key="taskUserYn"		   var="ssTaskUserYn"     	   scope="request"/>
<aof:session key="taskProfYn"		   var="ssTaskProfYn"     	   scope="request"/>
<aof:session key="taskAdminYn"		   var="ssTaskAdminYn"     	   scope="request"/>
<aof:session key="taskContentsYn"	   		var="ssTaskContentsYn"     scope="request"/>
<aof:session key="taskOerYn"	   		var="ssTaskOerYn"     scope="request"/>
<aof:session key="profTypeCd"		   var="ssProfTypeCd"     	   scope="request"/>
<aof:session key="migMemberId"		   var="ssMigMemberId"     	   scope="request"/>
<aof:session key="graduateYn"		   var="ssGraduateYn"     	   scope="request"/>
<aof:session key="officeTel"		   var="ssOfficeTel"     	   scope="request"/>
<aof:session key="sexCd"		       var="ssSexCd"     	   	   scope="request"/>
<aof:session key="photoFile"		   var="ssPhotoFile"     	   scope="request"/>
<aof:session key="taskManagerYn"	   		var="ssTaskManagerYn"     scope="request"/>
<aof:session key="managerCompanySeq"	   		var="ssManagerCompanySeq"     scope="request"/>
<aof:session key="managerCompanyName"	   		var="ssManagerCompanyName"     scope="request"/>
<aof:session key="host"	   		var="ssHost"     scope="request"/>
<aof:session key="topClass"	   		var="ssTopClass"     scope="request"/>
<aof:session key="memberToken"		    var="ssMemberToken"     	   scope="request"/>
<aof:session key="organizationString"	var="ssOrganizationString"     	   scope="request"/>
<aof:session key="studentYear"		    var="ssStudentYear"     	   scope="request"/>
<aof:session key="sessionId"		    var="sssessionId"     	   scope="request"/>
<aof:session key="loginKind"		    var="ssLoginKind"     	   scope="request"/>

<aof:session key="companySeq"           var="ssCompanySeq"         scope="request"/>
<aof:session key="companyName"          var="ssCompanyName"        scope="request"/>
<aof:session key="companySiteYn"          var="ssCompanySiteYn"        scope="request"/>
<aof:session key="companyNick"          var="ssCompanyNick"        scope="request"/>

<aof:session key="companyTemplateCss"          var="ssCompanyTemplateCss"        scope="request"/>
<aof:session key="regDtime"             var="ssRegDtime"           scope="request"/>
<aof:session key="authCd"               var="ssAuthCd"             		  scope="request"/>
<aof:session key="observerModeYn"               var="ssObserverModeYn"    scope="request"/>
<aof:session key="observerProfYn"               var="ssObserverProfYn"    scope="request"/>
<aof:session key="courseActiveSeqBySso"               var="ssCourseActiveSeqBySso"    scope="request"/>
<aof:session key="courseActiveScoresSum"               var="ssCourseActiveScoresSum"    scope="request"/>
<aof:session key="agencyCode"               var="ssAgencyCode"    scope="request"/>
<aof:session key="regionCode"	   		var="ssRegionCode"     scope="request"/>
<aof:session key="ssPopupCloseYn"		   var="ssPopupCloseYn"     	   scope="request"/>
<aof:session key="ssNowStudyYn"		   var="ssNowStudyYn"     	   scope="request"/>
<aof:session key="mcpYn"		   var="ssMcpYn"     	   scope="request"/>
<aof:session key="entrypage"		   var="ssEntrypage"     	   scope="request"/>

<c:set var="ssMemberPhoto" scope="request"><aof:img type="print" src="common/blank.gif"/></c:set>
<c:if test="${!empty memberPhoto}">
    <c:set var="ssMemberPhoto" value ="${aoffn:config('upload.context.image')}${memberPhoto}.thumb.jpg" scope="request"/>
</c:if>
<c:set var="sessionValidYn" value="N" scope="request"/>
<c:if test="${not empty ssMemberName}">
	<c:set var="sessionValidYn" value="Y" scope="request"/>
</c:if>

<c:if test="${not empty ssMemberName}">
	<c:set var="ssLoginType" value="${ssLoginType}" scope="request"	/>
</c:if>

<c:if test="${ssCurrentRolegroupSeq eq 12}">
	<c:set var="regionYn" value="Y" scope="request"/>
</c:if>

<c:if test="${empty ssCompanyTemplateCss }">
	<c:set var="ssCompanyTemplateCss" value="/new/css/style.css" scope="request"/>
</c:if>