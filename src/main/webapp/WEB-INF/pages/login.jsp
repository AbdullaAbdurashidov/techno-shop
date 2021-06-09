<%@include file="templates/header.jsp"%>

<div class="layout-content">
    <div class="row mt-5 mb-5 login" style="margin: 0;padding: 0;">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <br>
            <br>
            <br>
            <br>
            <form action="">
                <div class="form-group">
                    <label>Email</label>
                    <input class="form-control email" type="text" placeholder="email">
                    <div class="invalid-feedback">
                    </div>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input class="form-control password" type="password" placeholder="password">
                    <div class="invalid-feedback">
                    </div>
                </div>
            </form>
            <button class="btn btn-success float-right" onclick="login()" type="submit">Login</button>
    <%--        <form action="/api/product/add" method="post"  enctype="multipart/form-data">--%>
    <%--            <div class="form-group">--%>
    <%--                <label>Name</label>--%>
    <%--                <input class="form-control" name="name" type="text">--%>
    <%--            </div>--%>
    <%--            <div class="form-group">--%>
    <%--                <label>About</label>--%>
    <%--                <input class="form-control" name="about" type="text">--%>
    <%--            </div>--%>
    <%--            <div class="form-group">--%>
    <%--                <label>Quantity</label>--%>
    <%--                <input class="form-control" name="quantity" type="number">--%>
    <%--            </div>--%>
    <%--            <div class="form-group">--%>
    <%--                <label>Price</label>--%>
    <%--                <input class="form-control" name="price" type="number">--%>
    <%--            </div>--%>
    <%--            <div class="form-group">--%>
    <%--                <label>Photo</label>--%>
    <%--                <input class="form-control" name="path" type="file">--%>
    <%--            </div>--%>
    <%--            <button class="btn btn-warning" type="submit">Add</button>--%>
    <%--        </form>--%>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>

<script type="application/javascript">
    function login(){
        let email = $('input.email').val();
        let password = $('input.password').val();
        $.ajax({
            url: "/api/user/login",
            method: "POST",
            dataType: 'json',
            contentType: 'application/json',
            data: '{ "username":"'+email+'", "password":"'+password+'" }',
        }).done(function (data){
            window.location.href= "/home";
        }).fail(function (){
            alert("error");
        });
    }
</script>

<%@include file="templates/footer.jsp"%>