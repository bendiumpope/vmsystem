<#macro page>
    <!doctype html>
    <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <link rel="stylesheet" href="css/header.css">
            <link href="https://fonts.googleapis.com/css?family=Lato&display=swap" rel="stylesheet">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
            <link href="/static/css/home.css" rel="stylesheet" />
            <link href="/static/css/navbar.css" rel="stylesheet" />
            <link href="/static/css/form.css" rel="stylesheet" />
            <script src="https://kit.fontawesome.com/a076d05399.js"></script>
            <title>VisitorsManagement</title>
        </head>

        <body>
            <#include "navbar.ftl">

            <div class="container-fluid homeScrn">
                <#nested>
            </div>

            <!-- jquery & Bootstrap JS -->
            <script>
                function togglesidebar(){
                    document.getElementById("sidebar").classList.toggle('active');
                }
            </script>
            <script src="js/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
            <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        </body>
    </html>
</#macro>