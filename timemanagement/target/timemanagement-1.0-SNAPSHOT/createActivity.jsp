<%--
  Created by IntelliJ IDEA.
  User: elizavetabojko
  Date: 17.12.2021
  Time: 03:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<jsp:include page="navbar.jsp"/>

<div>
    <form autocomplete="off" action="/carAdd" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <div class="col-sm-9">

                <div class="form-group">
                    <div class="col-sm-9">
                        <input type="text" placeholder="Name" name="activityName"
                               class="form-control" minlength="1" maxlength="30"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <input type="text" placeholder="Category" name="activityCategory"
                               class="form-control" minlength="1" maxlength="30"/>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-sm-9">
                        <button type="submit" class="btn btn-primary btn-block">Create Activity</button>
                    </div>
                </div>

                <c:if test="${sessionScope.successMessage != null}">
                    <h2 ><span class="text-success">${sessionScope.successMessage}</span></h2>
                </c:if>
            </div>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
