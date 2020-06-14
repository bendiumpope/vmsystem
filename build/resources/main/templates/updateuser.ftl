<#import "common/bootstrap.ftl" as b>

<@b.page>
<div class="container">
   <form method="post" action="/updateuser" id="contact">
     <input type="hidden" name="date" value="${date?c}" />
     <input type="hidden" name="code" value="${code}" />
     <input type="hidden" name="action" value="update" />
     <input type="hidden" name="id" value="${id}" />
      <#if error??>
        ${error}<br><br>
      </#if>
    <h3>Update User Details</h3>
    <fieldset>
      <input name="userName" class="form-control" placeholder="username" type="text" value="${updateUser.userId}" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input name="surName" class="form-control" placeholder="surname"  value="${updateUser.surName}" type="text" tabindex="2" required>
    </fieldset>
    <fieldset>
      <input name="firstName" class="form-control" placeholder="first name" value="${updateUser.firstName}" type="text" tabindex="3" required>
    </fieldset>
    <fieldset>
      <input name="otherName" class="form-control" placeholder="other name" type="text" value="${updateUser.otherName}" tabindex="4" required>
    </fieldset>
    <fieldset>
      <input name="emailAddress" class="form-control" placeholder="email address" type="email" value="${updateUser.emailAddress}" tabindex="5" required>
    </fieldset>
    <fieldset>
      <input name="phoneNumber" class="form-control" placeholder="Enter your phoneNumber" value="${updateUser.phoneNumber}" type="text" tabindex="6" required>
    </fieldset>
    <fieldset>
      <input name="address" class="form-control" id="address" placeholder="Enter your address" value="${updateUser.address}" type="text" tabindex="7" required>
    </fieldset>
    <fieldset>
      <input name="role" class="form-control" id="role" placeholder="E.g (Admin, VC, HOD, Secretary)" value="${updateUser.role}" type="text" tabindex="8" required>
    </fieldset>
    <fieldset>
      <input type="hidden" name="passwordHash" type="hidden" class="form-control" id="passwordHash" placeholder="passwordHash" value="${updateUser.passwordHash}" tabindex="9" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Update</button>
    </fieldset>
  </form>
</div>
</@b.page>