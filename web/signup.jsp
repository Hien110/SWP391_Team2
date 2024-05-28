<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <title>Sign Up</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signupCSS.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="" crossorigin="anonymous" />
    </head>
    <body>

        <div class="main">
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Sign up</h2>
                            <form method="post" action="./signup">
                                <div class="form-group" style="display: flex">
                                    <i class="fas fa-user"></i>
                                    <input type="text" name="username" placeholder="User Name" value="${param.username != null ? param.username : sessionScope.username}" required/>
                                </div>
                                <div class="form-group">
                                    <i class="fas fa-envelope"></i>
                                    <input type="email" name="email"  placeholder="Your Email" value="${param.email}" required/>
                                </div>
                                <div class="form-group">
                                    <i class="fas fa-lock"></i>
                                    <input type="password" name="password" placeholder="Password" required/>
                                </div>
                                <div class="form-group">
                                    <i class="fas fa-lock"></i>
                                    <input type="password" name="re_password" placeholder="Repeat your password" required/>
                                </div>
                                <h4 style="color: red; padding-top: 10px; font-weight: 400">${requestScope.error}</h4>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" class="form-submit" value="Register"/>
                                </div>
                            </form>
                        </div>
                        <div class="signup-image" >
                            <figure><img src="${pageContext.request.contextPath}/images/logo.png" alt="sing up image"></figure>
                            <div style="display: flex; justify-content: center; font-size: 16px">
                                <span>You have account ?</span>&nbsp;
                                <a href="./login" style="display: contents; color: #1863ff;">Login</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


        </div>
    </body>
</html>