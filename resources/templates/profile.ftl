<#import "common/bootstrap.ftl" as b>
<@b.page>
<form id="contact" method="post"  action="/profile" enctype="multipart/form-data">
      <input type="hidden" name="action" value="upload">
      <fieldset>
        <#if profileUrls?? && (profileUrls !="") >
           <span width="5" />
           <img src="./static/${profileUrls}" class="img-thumbnail" width="250" height="200" alt="profile image"/>
        <#else>
           <img src="/static/profile.png" class="img-thumbnail" width="60" height="60" alt="profile image"/>
        </#if><br><br>
      </fieldset>
      <fieldset>
        <input type="file" name="uploadedFile" class="form-control exp" size="25" id="uploadedFile" />
      </fieldset>
      <fieldset>
         <input name="submit" type="submit" class="btn btn-success exp" />
      </fieldset>
      <#if user??>
        <fieldset>
            <h5>USERNAME:${user.userId}</h5><br>
        </fieldset>
        <fieldset>
            <h5>SURNAME:${user.surName}</h5><br>
        </fieldset>
        <fieldset>
            <h5>OTHER NAME:${user.otherName}</h5><br>
        </fieldset>
        <fieldset>
            <h5>EMAIL:${user.emailAddress}</h5><br>
        </fieldset>
        <fieldset>
            <h5>PHONE No:${user.phoneNumber}</h5><br>
        </fieldset>
        <fieldset>
            <h5>ADDRESS:${user.address}</h5><br>
        </fieldset>
        <fieldset>
            <h5>ROLE:${user.role}</h5><br>
        <fieldset>
  </#if>
</div>
</@b.page>