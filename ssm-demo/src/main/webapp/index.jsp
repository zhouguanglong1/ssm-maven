<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/29
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ssm-maven</title>
</head>
<%--<script src="js/sockjs.min.js"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<%--<script src="js/jquery.min.js"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<body>
<div>
    <p>
        聊天室
    </p>

    <form id="wiselyForm">
        <textarea rows="4" cols="60" name="text"></textarea>
        <input type="submit"/>
    </form>
</div>
<script type="text/javascript">

    $('#wiselyForm').submit(function(e){
        e.preventDefault();
        var text = $('#wiselyForm').find('textarea[name="text"]').val();
        sendSpittle(text);
    });
    //链接endpoint名称为 "/endpointChat" 的endpoint。
    var sock = new SockJS("/endpointSang");
    var stomp = Stomp.over(sock);
    stomp.connect('guest', 'guest', function(frame) {

        /**  订阅了/user/queue/notifications 发送的消息,这里雨在控制器的 convertAndSendToUser 定义的地址保持一致, 
         *  这里多用了一个/user,并且这个user 是必须的,使用user 才会发送消息到指定的用户。 
         *  */
        stomp.subscribe("/topic/getResponse", handleNotification);
    });



    function handleNotification(message) {
        $('#output').append("<b>Received: " + message.body + "</b><br/>")
    }

    function sendSpittle(text) {
        stomp.send("/welcome", {}, JSON.stringify({ 'name': text }));//3
    }
    $('#stop').click(function() {sock.close()});
</script>
<div id="output"></div>
</body>
</html>
