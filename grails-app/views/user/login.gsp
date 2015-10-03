<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>

<body>
<div class="body">
    <g:form action="authenticate" method="post">
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <fieldset class="col-sm-6 col-sm-offset-3">
        <h1>Login</h1>
                <table>
                    <tbody>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="login">Login:</label>
                        </td>
                        <td valign="top">
                            <input type="text"
                                   id="login" name="login"/>
                        </td>
                    </tr>
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="password">Password:</label>
                        </td>
                        <td valign="top">
                            <input type="password"
                                   id="password" name="password"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <input class="col-sm-6 col-sm-offset-3 btn-lg btn-info" type="submit" value="Login"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>