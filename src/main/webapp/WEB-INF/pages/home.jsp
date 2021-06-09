<%@include file="templates/header.jsp" %>

<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request"/>

<style>
    .sort-group button {
        margin: 5px;
    }

    .card:hover{
        border: 1px solid lightgrey;
        border-radius: 5px;
    }
    .card{
        border: 1px solid white;
        padding: 20px;
    }
    .image{
        width: 100%;
        height: 200px;
        text-align: center;
    }
    .image img{
        max-width: 100%;
        max-height: 100%;
        margin: auto;
    }
    .card-body{
        height: 100px;
        overflow: hidden;
    }
    .card-body .card-title{
        font-size: 20px;
    }
    .card-footer{
        height: 20px;
    }

</style>

<div class="layout-content">
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-4">Shopify</h1>
            <p class="lead">
                Online platform to purchase products directly from shops without mediator.
            </p>
            <hr class="my-4">
            <p>Constructed on the basis of Spring Boot</p>
            <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
        </div>
    </div>
    <div class="row" style="margin: 0">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="container-fluid">
                <div class="row">
                   <%@include file="components/productCard.jsp"%>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>

<!--Modal-->
<div class="modal  fade modal-scroll" tabindex="-1" role="dialog" id="add-to-cart" aria-labelledby="add-to-cart-title" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add to cart</h5>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="form-wrap">
                    <label>Quantity</label>
                    <input type="number" id="quantity">
                    <button class="btn btn-success" onclick="toCart()">Add to cart</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    let product;
    let quantity;
    $('.cart-footer button').on('click', function (){
        product = $(this).val();
    });

    $('#quantity').on('change',function (){
       quantity = $(this).val();
    });

    function toCart(){
        alert("Product:  "+product+"  "+quantity);
        $.ajax({
            url: "/api/cart/add/"+product+"/"+quantity,
            method: "GET"
        }).done(function (data){
        }).fail(function (){
        });
        $('#add-to-cart').modal('hide');
    }

    let showAddToCardModal = function(){
        $('#add-to-cart').modal('show');
    }

</script>

<%@include file="templates/footer.jsp"%>