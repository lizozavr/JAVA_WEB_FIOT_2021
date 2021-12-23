<%--
  Created by IntelliJ IDEA.
  User: elizavetabojko
  Date: 22.12.2021
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Profile</title>
</head>
<body>
<h2>Admin Profile</h2>
<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.role}</td>
        </tr>
        <tbody>
    </table>
</div>

<div class="form-group">
    <div class="col-sm-9" align="center">
        <a href="/admin/activity" class="btn">All activities</a>
        <a href="/admin/activity/byName" class="btn">All activities sorted by name</a>
        <a href="/admin/activity/byCategory" class="btn">All activities sorted by category</a>
        <a href="/admin/activity/byUsersAmount" class="btn">All activities sorted by User's amount</a>
        <a href="/admin/setActivity" class="btn">Assign activity to user</a>
    </div>
</div>
<div class="form-group">
    <div class="col-sm-9" align="center">
        <a href="/setActivityTime" class="btn">Set activity time</a>
        <a href="/activity/save" class="btn">Create activity</a>
        <a href="/activity/delete" class="btn">Delete activity</a>
    </div>
</div>
<div class="form-group">
    <div class="col-sm-9" align="right">
        <a href="/login" class="logout_btn">Logout</a>
    </div>
</div>
</body>
</html>

<style>
    .form-group{
        margin: 10px 0 12px 0;
    }

    .logout_btn {
        display: inline-block;
        background: #a9444d;
        color: #fff;
        padding: 1rem 1.5rem;
        text-decoration: none;
        border-radius: 3px;
        font-size: 12px;
    }
    .btn {
        display: inline-block;
        background: #324960;
        color: #fff;
        padding: 1rem 1.5rem;
        text-decoration: none;
        border-radius: 3px;
        font-size: 12px;
    }
    *{
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
    }
    body{
        font-family: Helvetica;
        -webkit-font-smoothing: antialiased;
        background: rgba( 71, 147, 227, 1);
    }
    h2{
        text-align: center;
        font-size: 18px;
        text-transform: uppercase;
        letter-spacing: 1px;
        color: white;
        padding: 30px 0;
    }

    /* Table Styles */

    .table-wrapper{
        margin: 10px 70px 70px;
        box-shadow: 0px 35px 50px rgba( 0, 0, 0, 0.2 );
    }

    .fl-table {
        border-radius: 5px;
        font-size: 12px;
        font-weight: normal;
        border: none;
        border-collapse: collapse;
        width: 100%;
        max-width: 100%;
        white-space: nowrap;
        background-color: white;
    }

    .fl-table td, .fl-table th {
        text-align: center;
        padding: 8px;
    }

    .fl-table td {
        border-right: 1px solid #f8f8f8;
        font-size: 12px;
    }

    .fl-table thead th {
        color: #ffffff;
        background: #4FC3A1;
    }


    .fl-table thead th:nth-child(odd) {
        color: #ffffff;
        background: #324960;
    }

    .fl-table tr:nth-child(even) {
        background: #F8F8F8;
    }

    /* Responsive */

    @media (max-width: 767px) {
        .fl-table {
            display: block;
            width: 100%;
        }
        .table-wrapper:before{
            content: "Scroll horizontally >";
            display: block;
            text-align: right;
            font-size: 11px;
            color: white;
            padding: 0 0 10px;
        }
        .fl-table thead, .fl-table tbody, .fl-table thead th {
            display: block;
        }
        .fl-table thead th:last-child{
            border-bottom: none;
        }
        .fl-table thead {
            float: left;
        }
        .fl-table tbody {
            width: auto;
            position: relative;
            overflow-x: auto;
        }
        .fl-table td, .fl-table th {
            padding: 20px .625em .625em .625em;
            height: 60px;
            vertical-align: middle;
            box-sizing: border-box;
            overflow-x: hidden;
            overflow-y: auto;
            width: 120px;
            font-size: 13px;
            text-overflow: ellipsis;
        }
        .fl-table thead th {
            text-align: left;
            border-bottom: 1px solid #f7f7f9;
        }
        .fl-table tbody tr {
            display: table-cell;
        }
        .fl-table tbody tr:nth-child(odd) {
            background: none;
        }
        .fl-table tr:nth-child(even) {
            background: transparent;
        }
        .fl-table tr td:nth-child(odd) {
            background: #F8F8F8;
            border-right: 1px solid #E6E4E4;
        }
        .fl-table tr td:nth-child(even) {
            border-right: 1px solid #E6E4E4;
        }
        .fl-table tbody td {
            display: block;
            text-align: center;
        }
    }
</style>
