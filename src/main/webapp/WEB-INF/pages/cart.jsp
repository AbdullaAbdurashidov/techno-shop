<%@include file="templates/header.jsp"%>

<div class="layout-content">
    <h1 class="h1" style="text-align: center">Cart</h1>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">About</th>
                <th scope="col">Price(x1)</th>
                <th scope="col">Quantity</th>
                <th scope="col">Total</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${products.size()>0}">
                <% int count=1;%>
                <c:set var="total" value="${0}"/>
                <c:forEach items="${products}" var="product">
                    <tr>
                        <td scope="row"><%=count++%></td>
                        <td scope="row"><img class="card-img" style="width: 50px" src="${cp}/resource/files${product.productTable.fileDto.path}" alt=""></td>
                        <td scope="row">${product.productTable.productDto.name}</td>
                        <td scope="row">${product.productTable.productDto.about}</td>
                        <td scope="row">$${product.productTable.productDto.price}</td>
                        <td scope="row">${product.quantity}</td>
                        <td scope="row">$<c:out value="${product.productTable.productDto.price*product.quantity}"/></td>
                        <td scope="row"><button class="btn btn-danger float-right delete" value="${product.productTable.productDto.hashID}" type="button" name="toCart">Delete</button></td>
                    </tr>
                    <c:set var="total" value="${total+(product.productTable.productDto.price*product.quantity)}"/>

                </c:forEach>
                <tr>
                    <td scope="row"></td>
                    <td scope="row"></td>
                    <td scope="row"></td>
                    <td scope="row"></td>
                    <td scope="row"></td>
                    <td scope="row"></td>
                    <td scope="row"></td>
                    <td scope="row">
                        $<c:out value="${total}"/><br><br>
                        <button class="btn btn-success buy">Buy</button>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<script>

    $('button.delete').on('click', function (){
        let product = $(this).val();
        $.ajax({
            url: "/api/cart/delete/"+product,
            method: "GET"
        }).done(function (data){
            location.reload();
        }).fail(function (){

        });
    });

    $('button.buy').on('click', function (){
        $.ajax({
            url: "/api/order/add",
            method: "GET"
        }).done(function (data) {
            if (data == null)
                alert("Error");
            else
                alert("Success");
        }).fail(function () {
            alert("Error in request sending");
        });
        location.reload();
    });



</script>

<%@include file="templates/footer.jsp"%>