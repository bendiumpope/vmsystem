<#import "common/bootstrap.ftl" as b>

<@b.page>
<div class="container">
  <form id="contact" method="post" action="/signup">
      <input type="hidden" name="action" value="add">
      <#if error??>
        ${error}<br><br>
      </#if>
    <h3>SIGNUP</h3>
    <fieldset>
      <input name="userName" class="form-control" placeholder="username" type="text" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input name="surName" class="form-control" placeholder="surname"  type="text" tabindex="2" required>
    </fieldset>
    <fieldset>
      <input name="firstName" class="form-control" placeholder="first name" type="text" tabindex="3" required>
    </fieldset>
    <fieldset>
      <input name="otherName" class="form-control" placeholder="other name" type="text" tabindex="4" required>
    </fieldset>
    <fieldset>
      <input name="emailAddress" class="form-control" placeholder="email address" type="email" tabindex="5" required>
    </fieldset>
    <fieldset>
      <input name="phoneNumber" class="form-control" placeholder="Enter your phoneNumber" type="text" tabindex="6" required>
    </fieldset>
    <fieldset>
      <input name="address" class="form-control" id="address" placeholder="Enter your address" type="text" tabindex="7" required>
    </fieldset>
    <fieldset>
      <input name="role" class="form-control" id="role" placeholder="E.g (Admin, VC, HOD, Secretary)" type="text" tabindex="8" required>
    </fieldset>
    <fieldset>
      <input name="password" class="form-control" id="password" placeholder="password" type="password" tabindex="9" required>
    </fieldset>
    <fieldset>
      <input name="confirmPassword" class="form-control" id="confirmPassword" placeholder="confirm password" type="password" tabindex="10" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">SignUp</button>
    </fieldset>
    <p class="copyright">Already have an account? <a href="/signin">SignIn</a></p>
  </form>
</div>
</@b.page>