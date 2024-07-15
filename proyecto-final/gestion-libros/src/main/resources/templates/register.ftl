<#include "layout.ftl">
<body style="height: 100vh">
<div class="container d-flex align-items-center justify-content-center h-100">
    <form action="/register" method="post" class="border rounded p-3 col-8">
        <div class="d-flex justify-content-between">
            <h2>Register</h2>
            <a href="/login">Login</a>
        </div>

        <div class="mb-3">
            <label for="nameInput" class="form-label">Name</label>
            <input type="text" class="form-control" id="nameInput" name="name">
        </div>
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password" autocomplete="new-password">
        </div>

        <button type="submit" class="btn btn-primary">Register</button>
    </form>
</div>
</body>