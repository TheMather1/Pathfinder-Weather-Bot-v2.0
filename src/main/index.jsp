<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"
      xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Pathfinder WeatherBot</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/portal.css"/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
    <div class="index">
        <h1>Pathfinder Weather Bot</h1>

        <a href="/portal" class="portalLink">Web Interface</a>

        <div class="indexText">
            A Discord bot for procedurally generating weather according to the <a
                href="https://aonprd.com/Rules.aspx?Name=Weather%20in%20the%20Wilderness&Category=Mastering%20the%20Wild">Weather Rules</a> of the first
            edition of Pathfinder RPG, modified for hourly reports.
            <br>
            This is primarily intended for use in living worlds, and will provide weather updates in real-time.
        </div>
        <div class="indexButtons">
            <a href="https://github.com/TheMather1/Pathfinder-Weather-Bot-v2.0/">
                <svg viewBox="0 0 16 16" height="50px" width="50px">
                    <path fill-rule="evenodd"
                          d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0016 8c0-4.42-3.58-8-8-8z"></path>
                </svg>
            </a>
            <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=VAUMSQBBYEUBY">
                <img src="https://www.paypalobjects.com/en_US/NO/i/btn/btn_donateCC_LG.gif" title="Donate" alt="Paypal donate button">
            </a>
        </div>
    </div>
</body>
</html>
