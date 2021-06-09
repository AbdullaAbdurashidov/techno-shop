<%@include file="templates/header.jsp"%>

<div class="layout-content">

    <h1 class="h1" style="text-align: center">Admin</h1>

    <div class="container-fluid row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <button class="btn btn-success add-user" onclick="addUser()">Add User</button>
                </div>
                <div class="panel-body">
                    <table class="table table-striped" id="users" style="width: 100%">
                        <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Image</th>
                            <th>Id</th>
                            <th>Hash id</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Email</th>
                            <th>Card number</th>
                            <th>Role</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tfoot class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Image</th>
                            <th>Id</th>
                            <th>Hash id</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Email</th>
                            <th>Card number</th>
                            <th>Role</th>
                            <th></th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <button class="btn btn-success add-user">Add User</button>
                </div>
                <div class="panel-body">
                    <table class="table table-striped" id="products" style="width: 100%">
                        <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Image</th>
                            <th>Id</th>
                            <th>Hash id</th>
                            <th>Name</th>
                            <th>About</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tfoot class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Image</th>
                            <th>Id</th>
                            <th>Hash id</th>
                            <th>Name</th>
                            <th>About</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="modal  fade modal-scroll" tabindex="-1" role="dialog" id="addUser" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add user</h5>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <div class="form-wrap">
                    <form>
                        <div class="form-group">
                            <label>First name</label>
                            <input class="form-control" name="firstName" type="text">
                        </div>
                        <div class="form-group">
                            <label>Last name</label>
                            <input class="form-control" name="lastName" type="text">
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input class="form-control" name="email" type="text">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input class="form-control" name="password" type="text">
                        </div>
                        <div class="form-group">
                            <label>Card number</label>
                            <input class="form-control" name="cardNumber" type="text">
                        </div>
                        <div class="form-group">
                            <label>Role</label>
                            <select name="role">
                                <option>USER</option>
                                <option>ADMIN</option>
                            </select>
                        </div>
                    </form>
                    <button class="btn btn-success float-right">Add</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">

    let userTable = $('#users');
    let productTable = $('#products');
    let counter = 1;

    $(document).ready(function (){

        userTable.DataTable({
            processing: true,
            serverSide: true,
            scrollX: true,
            oLanguage: {
            },
            order: [[0, "desc"]],
            ajax: {
                url: "/api/admin/users",
                type: 'GET',
            },
            columns:
                [
                    {mData: 'userDto.id',
                        render: function (){
                            return counter++;
                        }},
                    {mData: 'fileDto'},
                    {mData: 'userDto.id'},
                    {mData: 'userDto.hashID'},
                    {mData: 'userDto.firstName'},
                    {mData: 'userDto.lastName'},
                    {mData: 'userDto.email'},
                    {mData: 'userDto.cardNumber'},
                    {mData: 'userDto.role'},
                    {
                        "mRender": function (data, type, row) {
                            return '<a href="#" onclick=")"><i class="bi bi-trash delete-item"></i></a>';
                        }
                    }
                ]
        });
        counter=1;
        productTable.DataTable({
            processing: true,
            serverSide: true,
            scrollX: true,
            oLanguage: {
            },
            order: [[0, "desc"]],
            ajax: {
                url: "/api/admin/products",
                type: 'GET',
            },
            columns:
                [
                    {mData: 'productDto.id',
                        render: function (){
                            return counter++;
                        }},
                    {data: 'fileDto.path',
                        render: function (data){
                        return '<img href="" style="width: 50px" />'
                        }
                    },
                    {mData: 'productDto.id'},
                    {mData: 'productDto.hashID'},
                    {mData: 'productDto.name'},
                    {mData: 'productDto.about'},
                    {mData: 'productDto.quantity'},
                    {mData: 'productDto.price'},
                    {
                        "mRender": function (data, type, row) {
                            return '<a href="javascript:void(0)" onclick="showModal(\'' + row.tin + '\')"><i class="bi bi-trash delete-item"></i></a>';
                        }
                    }
                ]
        });

    });

    function addUser(){
        $('#addUser').modal('show');
    }
</script>


<%@include file="templates/footer.jsp"%>