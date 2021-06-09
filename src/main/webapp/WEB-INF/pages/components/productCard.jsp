<c:forEach items="${products}" var="product">
    <div class="col-md-3 card" style="">
        <div class="image">
            <img class="card-img" src="${cp}/resource/files${product.fileDto.path}" alt="">
        </div>
        <div class="card-body">
            <span class="card-title">${product.productDto.name}</span><br />
            <span class="card-subtitle text-muted">T-shirt</span>
            <p class="card-text">${product.productDto.about}
            </p>
        </div>
        <div class="cart-footer">
            <br/>
            <span>Quantity: </span>
            <span>${product.productDto.quantity}</span><br/><br/>
            <span class="text-success">$${product.productDto.price}</span>
            <button class="btn btn-danger float-right" value="${product.productDto.hashID}" type="button" onclick="showAddToCardModal()" name="toCart">To Cart</button>
        </div>
    </div>
</c:forEach>