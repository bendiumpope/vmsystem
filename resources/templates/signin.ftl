<#import "common/bootstrap.ftl" as b>


<@b.page>
<div class="container">
  <form id="contact" action="/signin" method="post">
    <input type="hidden" name="action" value="add">
    <#if error??>
      ${error}<br><br>
    </#if>
    <h3>SIGNIN</h3>
    <h4>Contact us for custom quote</h4>
    <fieldset>
      <input name="userName" type="text" class="form-control" id="userName" placeholder="username" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input type="password" name="password" class="form-control" id="password" placeholder="password" tabindex="2" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Signin</button>
    </fieldset>
    <p class="copyright">Don't have an account? <a href="/signup" target="_blank" title="Colorlib">SignUp</a></p>
  </form>
</div>
</@b.page>