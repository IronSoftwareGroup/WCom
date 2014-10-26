
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Commander 1.0</title>
    </head>
    <body>
        <h1>Instruction</h1>
        <p>Web Commander is a simple proxy for remote command invokation.</p>
        <p>The main url to execute a command is: http://hostname:port/webc/executor?exe=commnadToExevute&p1=parametro1</p>
        <p>Just issue a get to the url and the command will be executed on the remote host</p>
        <ul>
           hostname: the host where the application is running
        </ul>
        <ul>
           port: the port configured in your web server
        </ul>
        <ul>
           commandToExecute: the main command to be executed
        </ul>
        <ul>
           p1: the first parameter to pass (4 parameters are supported)
        </ul>
          
    </body>
</html>
