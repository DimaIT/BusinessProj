<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'business.label', default: 'Business')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
            var files;

            // Grab the files and set them to our variable
            function prepareUpload(event) {
                files = event.target.files;
            }
            function uploadFiles(event) {
                event.preventDefault(); // Totally stop stuff happening

                // START A LOADING SPINNER HERE

                // Create a formdata object and add the files
                var data = new FormData();
                $.each(files, function (key, value) {
                    data.append(key, value);
                });

                $.ajax({
                    url: '/business/loadFromCSV',
                    type: 'POST',
                    data: data,
                    cache: false,
                    dataType: 'json',
                    processData: false, // Don't process the files
                    contentType: false, // Set content type to false as jQuery will tell the server its a query string request
                    success: function (data, textStatus, jqXHR) {
                        if (typeof data.error === 'undefined') {
                            // Success so call function to process the form
                            submitForm(event, data);
                        }
                        else {
                            // Handle errors here
                            console.log('ERRORS: ' + data.error);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        // Handle errors here
                        console.log('ERRORS: ' + textStatus);
                        // STOP LOADING SPINNER
                    }
                });
            }
        </script>
    </head>
    <body>
        <a href="#list-business" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav-def" role="navigation">
            <form id="upload-form" method="POST">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                      args="[entityName]"/></g:link></li>
                <li>
                    <input class="form-control" type="file" onchange="prepareUpload(event)" name="file">
                </li>
                <li>
                    <input class="btn-block" type="submit" onclick="uploadFiles(event)" value="Load From CSV"/>
                </li>
            </ul>
        </form>
        </div>
        <div id="list-business" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${businessList}" />

            <div class="pagination">
                <g:paginate total="${businessCount ?: 0}" />
            </div>
        </div>
    </body>
</html>