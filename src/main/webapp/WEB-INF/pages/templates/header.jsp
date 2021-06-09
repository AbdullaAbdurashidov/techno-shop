<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.5.0/css/flag-icon.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script href="${cp}/WEB-INF/pages/js/cookie.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/json2/20160511/json2.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <link href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" rel="stylesheet">
    <title>Shopify</title>
</head>

<style>
    ::-webkit-scrollbar {
        width: 5px;
    }
    ::-webkit-scrollbar-track {
        background: #f1f1f1;
    }
    ::-webkit-scrollbar-thumb {
        background: #888;
    }
    ::-webkit-scrollbar-thumb:hover {
        background: #555;
    }

    nav a.nav-icon{
        margin-right:15px;
        margin-left:15px;
    }

    .main-layout{
        display: flex;
        flex-direction: column;
        min-height: 100vh;
    }
    .main-layout .layout-content{
        flex: 1;
    }

</style>

<body >
<div class="main-layout">
    <nav  class="navbar navbar-expand-lg navbar-light bg-dark" style="width: 100%; box-sizing: border-box; margin: 0;">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="width: 100%">
                <a class="navbar-brand text-light" href="/home">Shopify</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="mainNavbar">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <div class="dropdown">
                                <a class="nav-link text-light dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="category-dropdown"  href="#">Categories</a>
                                <div class="dropdown-menu category-dropdown" aria-labelledby="category-dropdown">
                                </div>
                            </div>
                        </li>
                        <li class="nav-item">
                            <form class="form-inline my-2 my-lg-0">
                                <input type="text" class="form-control mr-sm-2" placeholder="search..">
                                <button class="btn btn-success my-2 my-sm-0">Search</button>
                            </form>
                        </li>
                    </ul>

                    <a href="#" class="nav-icon"><i class="flag-icon flag-icon-us" style="font-size: 120%; margin-top: 10px"></i></a>
                    <a href="/cart" class="nav-icon" onclick=""><i class="bi bi-cart3" style="color:white; font-size: 120%"></i></a>

                    <c:if test="${empty user}">
                        <div class="dropdown">
                            <button class="btn btn-success my-2 my-sm-0 dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="login-dropdown" type="submit"><i class=""></i>Login</button>
                            <div class="dropdown-menu" aria-labelledby="login-dropdown">
                                <a class="dropdown-item" href="/login"><i class="bi bi-box-arrow-in-right"></i> Login</a>
                                <a class="dropdown-item" href="#"><i class="bi bi-person-plus"></i> Register</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty user}">
                        <div class="dropdown">
                            <button class="btn btn-success my-2 my-sm-0 dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="profile-dropdown" type="submit"><i class="bi bi-person-circle"></i>  My Profile</button>
                            <div class="dropdown-menu" aria-labelledby="login-dropdown">
                                <a class="dropdown-item" href="/info"><i class="bi bi-info-circle"></i>  My Information</a>
                                <a class="dropdown-item" href="/orders"><i class="bi bi-receipt"></i>  Purchases</a>
                                <a class="dropdown-item" href="/cart"><i class="bi bi-cart"></i>  Cart</a>
                                <c:if test="${user.role=='ADMIN'}">
                                    <a class="dropdown-item" href="/admin"><i class="bi bi-shield-shaded"></i>Admin</a>
                                </c:if>
                                <a class="dropdown-item" href="/user/logout"><i class="bi bi"></i>Log out</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </nav>
        </div>
    </nav>
    <script>
        $(document).ready(function (){

            $.ajax({
                url: "/api/product/test",
                method: "GET"
            }).done(function (data){
                let result = "<ul>"
                result = result+ rec(data.children, "");
                result = result + "</ul>"
                $('.category-dropdown').append(result);
            })

        });

        function rec(data, result){
            result = result + "<ul>";
            for(let i=0; i<data.length; i++){
                result = result + "<li><a href='#'>"+data[i].name+"</a></li>";
                result = rec(data[i].children, result);
            }
            result = result + "</ul>";
            return result;
        }

    </script>



